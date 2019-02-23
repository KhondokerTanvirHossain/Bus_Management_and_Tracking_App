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
	if (isset($_POST['lat']))
		$lat = (float)($_POST['lat']);
	if (isset($_POST['lan']))
		$lan = (float)($_POST['lan']);
	if (isset($_POST['bus']))
		$bus = ($_POST['bus']);	
	if ($lat != 0.0 && $lan != 0.0) {
		$query = "UPDATE ".$table_name." SET lat = '$lat' , lan = '$lan' WHERE bus_id = '$bus'";
		$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME),$query); 
		$json['message'] = "success";
	}
	echo (json_encode($json));
?>