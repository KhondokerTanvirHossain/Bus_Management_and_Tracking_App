<?php
	include 'config.php';
	include 'db-connect.php';

	$db = new DBConnect();
	$connect = $db->getDB();

	$table_name = "route_table";

	$query = "SELECT * FROM route_table  INNER JOIN location_table ON route_table.bus_id = location_table.bus_id
				WHERE location_table.lat > 20.0 AND location_table.lan > 90.0";
	$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME),$query);
	$rows = array();
	if (mysqli_num_rows($result) > 0){
		while($r = mysqli_fetch_assoc($result)) {
		    $rows[] = $r;
		}
	}
	$jsonarray['array'] = $rows;
	echo (json_encode($jsonarray));

?>