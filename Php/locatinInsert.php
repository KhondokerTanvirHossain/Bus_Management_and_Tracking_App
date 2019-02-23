<?php
	include 'config.php';
	include 'db-connect.php';
	#include 'locationU.php';

	$db = new DBConnect();
	$connect = $db->getDB();
	$table_name = "location_table";
	$lat = 0.0;
	$lan = 0.0;
	$bus = "";
	$json['message'] = "fail";
	if (isset($_POST['bus']))
		$bus = ($_POST['bus']);	
	$query = "INSERT INTO location_table(bus_id, lat, lan) values ('$bus','$lat','$lan')";
	$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME),$query); 
	if($result)	
		$json['message'] = "success";
	echo (json_encode($json));
?>