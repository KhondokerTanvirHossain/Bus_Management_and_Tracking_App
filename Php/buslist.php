<?php
	include 'config.php';
	include 'db-connect.php';
	#include 'mainBus.php';

	$db = new DBConnect();
	$connect = $db->getDB();
	$table_name = "bus_table";
	$route = 0;
	$json = array();
	if (isset($_POST['route_id']))
		$route = (int)($_POST['route_id']);
	if ($route != 0) {
		$query = "SELECT * FROM ".$table_name." WHERE route_id = '$route' ";
		$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME),$query); 
		$json = mysqli_fetch_assoc($result);
		echo (json_encode($json));
	}
	else
		$json['message'] = "fail";

?>