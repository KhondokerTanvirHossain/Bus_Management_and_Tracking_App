<?php
	include 'config.php';
	include 'db-connect.php';
	#include 'mainseat.php';

	$db = new DBConnect();
	$connect = $db->getDB();
	$table_name = "route_table";
	$bus = "";
	$seat = "";
	$json['message'] = "fail to update";
	if (isset($_POST['bus_id']))
		$bus = ($_POST['bus_id']);
	if (isset($_POST['seat']))
		$seat = ($_POST['seat']);
	#$seat--;
	$query = "UPDATE ".$table_name." SET seat = '$seat' WHERE bus_id = '$bus'";
	$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME),$query);
	if ($result)
		$json['message'] = "sucess";
	echo (json_encode($json));
?>