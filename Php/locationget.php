<?php
	include 'config.php';
	include 'db-connect.php';
	#include 'locationG.php';

	$db = new DBConnect();
	$connect = $db->getDB();
	$table_name = "location_table";
	$lat = 0.0;
	$lan = 0.0;
	$bus = 0;
	$bus = 0;
	$json = array();
	if (isset($_POST['bus']))
		$bus = (int)($_POST['bus']);
	if ($bus != 0) {
		$query = "SELECT * FROM ".$table_name." WHERE bus_id = '$bus' ";
		$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME),$query); 
		$json = mysqli_fetch_assoc($result);
		echo (json_encode($json));
	}
	else
		echo (json_encode($json['message'] = "error"));
?>