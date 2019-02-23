<?php
	include 'config.php';
	include 'db-connect.php';

	$db = new DBConnect();
	$connect = $db->getDB();

	$table_name = "route_table";

	$query = "SELECT * FROM ".$table_name;
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