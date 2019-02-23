<?php
	include 'db-connect.php';
	$route = "";
	$time = "";
	$gender = "";
	$start_at = "";
	$start_at = "";
	$route_id = "";
	$bus_id = "";
	$seat = "50";
	if (isset($_POST['route']))
		$route = $_POST['route'];

	if (isset($_POST['time']))
		$time = $_POST['time'];
	
	if (isset($_POST['gender']))
		$gender = $_POST['gender'];

	if (isset($_POST['start_at']))
		$start_at = $_POST['start_at'];

	if (isset($_POST['end_at']))
		$end_at = $_POST['end_at'];

	if (isset($_POST['route_id']))
		$route_id = $_POST['route_id'];

	if (isset($_POST['bus_id']))
		$bus_id = $_POST['bus_id'];


	$query = "INSERT INTO route_table(route_id, route, start_at, end_at, gender, time, bus_id, seat) values ('$route_id','$route', '$start_at', '$end_at', '$gender', '$time', '$bus_id', '$seat')";
	$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME), $query);
	if ($result){
		echo "inserted";
		$lat = 0.0;
		$lan = 0.0;
		$query = "INSERT INTO location_table(bus_id, lat, lan) values ('$bus_id','$lat','$lan')";
		$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME),$query); 
		header( 'Location: http://localhost/user1/table.php');
	}
	else
		echo "failed";
?>