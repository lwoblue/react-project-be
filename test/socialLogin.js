"use strict";

// import necessary modules
const express = require("express");
const bodyParser = require("body-parser");
const request = require("request-promise");
var cors = require("cors"); // cors

// Firebase setup
// var firebase = require('firebase');
// Initialize Firebasee App with
// var config = require("./firebaseConfig.json");
// firebase.initializeApp(config);

// MySQL setting
var mysql = require("mysql2");

var con = mysql.createConnection({
  host: "192.168.0.18",
  user: "solomon",
  password: "admin",
  database: "react", // database name
});

// Firesvase admin setup
var admin = require("firebase-admin");
// you should manually put your serviceAccountKey.json in the same folder app.js
// is located at.
var serviceAccount = require("./serviceAccountKey.json");

// Initialize FirebaseApp with service-account.json
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://slack-clone-77dc8-default-rtdb.firebaseio.com",
});

/**
 * updateOrCreateUser - Update Firebase user with the give email, create if
 * none exists.
 *
 * @param  {String} userId        user id per app
 * @param  {String} email         user's email address
 * @param  {String} displayName   user
 * @param  {String} photoURL      profile photo url
 * @return {Prommise<UserRecord>} Firebase user record in a promise
 */
function updateOrCreateUser(userId, email, displayName, photoURL) {
  console.log("updating or creating a firebase user");
  const updateParams = {
    provider: "KAKAO",
    displayName: displayName,
  };
  if (displayName) {
    updateParams["displayName"] = displayName;
  } else {
    updateParams["displayName"] = email;
  }
  if (photoURL) {
    updateParams["photoURL"] = photoURL;
  }
  console.log(updateParams);
  // TODO: local DB user profile, userName update
  con.connect(function (err) {
    if (err) throw err;
    console.log("Connected!");
    // get user primarykey id
    var param = email;
    // update data (photoURL userName)
    con.query(`select id from users WHERE email=(?)`,param,function(err, result_idx){
      if (err) throw err;
      console.log("1 record selected");
      // if user DB exist
      if(result_idx.length == 1){
        var sql_update = `UPDATE users SET photoURL= (?), userName=(?) WHERE id=(?)`;
        console.log(result_idx[0].id);
        var params = [updateParams["photoURL"],updateParams["displayName"],result_idx[0].id];
        con.query(sql_update, params, function (err, result) {
          if (err) throw err;
          console.log("1 record updated");
        }); 
      }
    });
  });
  return admin
    .auth()
    .updateUser(userId, updateParams)
    .catch((error) => {
      if (error.code === "auth/user-not-found") {
        updateParams["uid"] = userId;
        if (email) {
          updateParams["email"] = email;
        }
        // put data in mySql DB
        con.connect(function (err) {
          if (err) throw err;
          console.log("Connected!");
          // random string pwd
          var randPwd = Math.random().toString(36).substr(2, 11);
          // userId, email, displayName, photoURL
          var sql = `INSERT INTO users ( email, userName, photoURL, deleteYN ,provider,password) VALUES (?,?,?, 'n',?,?)`;
          var params = [email, displayName, photoURL,updateParams["provider"], randPwd];
          con.query(sql, params, function (err, result) {
            if (err) throw err;
            console.log("1 record inserted");
          });
        });
        return admin.auth().createUser(updateParams);
      }
      // TODO: 이미 동일한 email을 사용하는 계정이 존재합니다 error 처리      
      throw error;
    });
}
function createFirebaseToken(kakaoAccessToken, uid, email, name, photoURL) {
  const userId = uid;
  let userName = name;
  let profileImage = photoURL;
  return updateOrCreateUser(userId, email, userName, profileImage).then(
    (userRecord) => {
      const userId = userRecord.uid;
      console.log(`creating a custom firebase token based on uid ${userId}`);
      return admin.auth().createCustomToken(userId, { provider: "KAKAO" });
    }
  );
}

// create an express app and use json body parser
const app = express();
// 특정 url에대한 cors allow
const corsOptions = {
  origin: "http://localhost:3000",
  credentials: true,
};
// app.use(cors());
app.use(cors(corsOptions));
app.use(bodyParser.json());

// default root url to test if the server is up
app.get("/", (req, res) =>
  res.status(200).send("KakaoLoginServer for Firebase is up and running!")
);

// actual endpoint that creates a firebase token with Kakao access token
app.post("/users/verifyToken", (req, res) => {
  //   const token = req.body.access_token;
  console.log(req.headers.authorization);
  const token = req.headers.authorization;
  const uid = req.body.uid;
  const email = req.body.email;
  const name = req.body.name;
  const photoURL = req.body.photoURL;
  if (!token)
    return res
      .status(400)
      .send({ error: "There is no token." })
      .send({ message: "Access token is a required parameter." });

  console.log("photoURL>>>>>", typeof photoURL);
  console.log(`Verifying Kakao token: ${token}`);
  createFirebaseToken(token, uid, email, name, photoURL).then(
    (firebaseToken) => {
      console.log(`Returning firebase token to user: ${firebaseToken}`);
      res.send({
        firebase_token: firebaseToken,
        name: name,
        photoURL: photoURL,
      });
    }
  );
});

app.post("/users/loginGoogle", (req, res) => {
  console.log("here in!!!!");
  console.log(req.body);
  // put data in mySql DB
  con.connect(function (err) {
    if (err) throw err;
    console.log("Connected! DB for post google user data");
    // check is exist
    var sql_check = `SELECT email,id FROM users where email=(?)`;
    var params = [req.body.email];
    con.query(sql_check, params, function (err, result_check) {
      if (err) throw err;
      console.log("1 record selected:",result_check);
      if(result_check.length == 0){
        console.log("no data");
        // insert data
        // random string pwd
        var randPwd = Math.random().toString(36).substr(2, 11);
        var sql = `INSERT INTO users ( email, userName, photoURL, deleteYN ,provider,password) VALUES (?,?,?, 'n','Google',?)`;
        var params = [req.body.email,req.body.username,req.body.photoURL,randPwd,];
        con.query(sql, params, function (err, result) {
          if (err) throw err;
          console.log("1 record inserted");
        });
      }else{
        console.log("yes data");
        // update data (photoURL userName)
        var sql_update = `UPDATE users SET photoURL= (?), userName=(?) WHERE id=(?)`;
        console.log(result_check[0].id);
        var params = [req.body.photoURL,req.body.username,result_check[0].id];
        con.query(sql_update, params, function (err, result) {
          if (err) throw err;
          console.log("1 record updated");
        });   
      }
    });
  });
});

// Start the server
const server = app.listen(process.env.PORT || "8090", () => {
  console.log(
    "KakaoLoginServer for Firebase listening on port %s",
    server.address().port
  );
});
