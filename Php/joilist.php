<?php
	include 'config.php';
	include 'db-connect.php';

	$db = new DBConnect();
	$connect = $db->getDB();

	$table_name = "route_table";

	$query = "SELECT r.start_at, r.end_at, r.gender,r.bus_id, r.seat, l.lat, l.lan
				from 'route_table' AS r INNER JOIN 'location_table' AS l ON r.bus_id = l.bus_id";
	$result = mysqli_query($connect,$query);
	$rows = array();
	if (mysqli_num_rows($result) > 0){
		while($r = mysqli_fetch_assoc($result)) {
		    $rows[] = $r;
		}
	}
	$jsonarray['array'] = $rows;
	echo (json_encode($jsonarray));

?>