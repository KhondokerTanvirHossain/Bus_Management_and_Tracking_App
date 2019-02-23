<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title><Table> Responsive</title>
  
  
  
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>

  <h1><span class="blue"></span>BUS<span class="blue"></span> <span class="yellow">Shedule</pan></h1>
<h2>International Islamic University of Chittagong </h2>

<table class="container">
	<thead>
		<tr>
			<th><h1>Time</h1></th>
			<th><h1>From</h1></th>
			<th><h1>To</h1></th>
			<th><h1>Route</h1></th>
			<th><h1>Gender</h1></th>
			<th><h1><form action="table.php" method="post">
    				<input type="submit" name="Action" value="+" />
				</form></h1></th>
		</tr>
	</thead>
	<tbody>
		


<?php 
	include 'db-connect.php';
	$query = "SELECT * from route_table";
	$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME), $query);
	while ($row=$result->fetch_assoc()) {
  		echo"<tr>
			<td>".$row['time']."</td>
			<td>".$row['start_at']."</td>
			<td>".$row['end_at']."</td>
			<td>".$row['route']."</td>
			<td>".$row['gender']."</td>
			<td><form action='' method='post'>
    				<input type='submit' name='someAction' value='-' />
    				<input type='hidden' name='someID' value='".$row['route_id']."' />
				</form></td>
		</tr> ";
 	}
?>







	</tbody>
</table>
  
  

</body>

</html>
<?php
	if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['someAction']))
    {
    	$id = $_POST['someID'];
    	$conn = mysqli_connect('localhost','root','','bus_management');	
    	if (!$conn) {
 		   	die("Connection failed: " . mysqli_connect_error());
		}
		if ($conn)
 		   	echo "no error";
 		 $nid = (int) $id;
		$delete_query="delete from route_table where route_id='$nid'";
	    $delete_query_result=mysqli_query($conn,$delete_query);       
    	if ($delete_query_result)
			header( 'Location: http://localhost/user1/table.php');
		else
			echo "\nfailed $id";
    }
    if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['Action']))
    {
        funcCreate();
    }
    function funcCreate()
    {	
        header( 'Location: http://localhost/user1/insert.html');
    }
?>

<script>
function openWin() {
    <?php
		
			#header( 'Location: http://www.youtube.com');
			#echo("<button onclick=\"href='http://www.youtube.com\">Back to Home</button>");
		
	?>
}
</script>