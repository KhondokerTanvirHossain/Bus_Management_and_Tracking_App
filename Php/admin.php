<?php
	include 'db-connect.php';
	/**
	 * MAIN APPLICATION FOR LOGIN 
	 */
	class Admin 
	{
		private $db;
		private $conn;
		private $db_table = "admin_table";
		function __construct()
		{
			$this->db = new DBConnect();
			$this->conn = new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);

		}
			
		public function isLoginExist($admin_id, $password)
		{
			$query = "select * from ".$this->db_table." where admin_id = '$admin_id'";
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
		public function isEmailUsernameExist($admin_id, $email)
		{
			$query = "select * from ".$this->db_table." where admin_id = '$admin_id' AND email = '$email'";
			$result = mysqli_query(new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME), $query);
			#$result = $conn->query($sql);
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
		public function createNewReg($admin_id,$username, $password, $email)
		{
			$json = array();
			if ($this -> isEmailUsernameExist($admin_id, $email)){
				$json['success'] = 0;
				$json['message'] = "ID or Email ALready exist";
			}
			else{
				if ($this->isValidEmai($email)){
					$query = "insert into ".$this->db_table." (admin_id, username, password, email) values ('$admin_id','$username', '$password', '$email')";
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
		public function loginUser($admin_id, $password)
		{
			$json = array();
			if ($this->isLoginExist($admin_id, $password)){

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