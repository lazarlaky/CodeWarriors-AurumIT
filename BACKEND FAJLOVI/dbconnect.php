<?php


// database connection variables
$servername = "localhost";
$username = "admin";
$password = "password";
$database = "snapit";


// Connecting to mysqli database
$con = new mysqli($servername, $username, $password, $database);

if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

?>