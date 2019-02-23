<?php
	include_once 'user.php';
	#include_once 'main.php';
	$username = "";
	$password = "";
	$email = "";
	$student_id = "";

	if (isset($_POST['username']))
		$username = $_POST['username'];

	if (isset($_POST['password']))
		$password = $_POST['password'];

	if (isset($_POST['email']))
		$email = $_POST['email'];

	if (isset($_POST['student_id']))
		$student_id = $_POST['student_id'];

	$object = new User();
	if (!empty($username) && !empty($password) && !empty($email) && !empty($student_id)){
		$hashPassword = md5($password);
		$json_reg = $object ->createNewReg($student_id,$username, $hashPassword, $email);
		echo json_encode($json_reg);
	}
	if (!empty($student_id) && !empty($password) && empty($email) && empty($username)){
		$hashPassword = md5($password);
		$json_array = $object -> loginUser($student_id, $hashPassword);
		echo json_encode($json_array);
	}

?>