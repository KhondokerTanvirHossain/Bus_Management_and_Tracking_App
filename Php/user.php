<?php
	include 'db-connect.php';
	/**
	 * MAIN APPLICATION FOR LOGIN 
	 */
	class User 
	{
		private $db;
		private $db_table = "student_table";
		function __construct()
		{
			$this->db = new DBConnect();
		}	
		public function isLoginExist($student_id, $password)
		{
			$query = "select * from ".$this->db_table." where student_id = '$student_id'";
			$result = mysqli_query($this->db->getDB(), $query);
			if (mysqli_num_rows($result) > 0) {
				mysqli_close($this->db->getDB());
				while ($row=$result->fetch_assoc()) {
					if ($row['password'] == $password)
						return true;
				}
				return false;
			}
			mysqli_close($this->db->getDB());
			return false;
		}
		public function isEmailUsernameExist($student_id, $email)
		{
			$query = "select * from ".$this->db_table." where student_id = '$student_id' AND email = '$email'";
			$result = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME), $query);
			if (mysqli_num_rows($result) > 0){
				mysqli_close($this->db -> getDB());
				return true;
			}
			mysqli_close($this->db->getDB());
			return false;
		}
		public function isValidEmai($email)
		{
			return filter_var($email, FILTER_VALIDATE_EMAIL);
		}
		public function createNewReg($student_id,$username, $password, $email)
		{
			$json = array();
			if ($this -> isEmailUsernameExist($student_id, $email)){
				$json['success'] = 0;
				$json['message'] = "ID or Email ALready exist";
			}
			else{
				if ($this->isValidEmai($email)){
					$query = "insert into ".$this->db_table." (student_id, username, password, email) values ('$student_id','$username', '$password', '$email')";
					$inserted = mysqli_query(mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME), $query);
					if ($inserted != null){
						$json['success'] = 1;
						$json['message'] = "Successfully Regisetered";
					}
					else{
						$json['success'] = 0;
						$json['message'] = "Data insertion failed";
					}
				}
			}
			#mysqli_close($this->db->getDB());
			return $json;
		}
		public function loginUser($student_id, $password)
		{
			$json = array();
			if ($this->isLoginExist($student_id, $password)){

				$json['success'] = 1;
				$json['message'] = "Login Successfull";
			}
			else{

				$json['success'] = 0;
				$json['message'] = "INcorrect details";
			}
			return $json;
		}

	}
?>