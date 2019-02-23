<?php
	include_once 'admin.php';
	#include_once 'main.php';
	$username = "";
	$password = "";
	$email = "";
	$admin_id = "";
	if (isset($_POST['username']))
		$username = $_POST['username'];

	if (isset($_POST['password']))
		$password = $_POST['password'];

	if (isset($_POST['email']))
		$email = $_POST['email'];

	if (isset($_POST['admin_id']))
		$admin_id = $_POST['admin_id'];

	$object = new Admin();
	if (!empty($username) && !empty($password) && !empty($email) && !empty($admin_id)){
		$hashPassword = md5($password);
		$json_reg = $object -> createNewReg($admin_id,$username, $hashPassword, $email);
		if ($json_reg['success'] == 1)
			header('Location: http://localhost/user/table.php');
		else
			echo $json_reg['message'];
	}
	if (!empty($admin_id) && !empty($password) && empty($email) && empty($username)){
		$hashPassword = md5($password);
		$json_array = $object -> loginUser($admin_id, $hashPassword);
		if ($json_array['success'] == 1)
			header('Location: http://localhost/user/table.php');
		else
			echo "login failled";
	}

?>