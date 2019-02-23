
<head>
    <title>My Page</title>
    <!-- <link rel="stylesheet" type="text/css" href="main.css"> -->
</head>
 <script src="main.js"></script> 
 <form action="routeEntry.php" method="post" style="border:1px solid #ccc">
  <div class="container">
    <h1>Sign Up</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
    <label for="route"><b>route</b></label>
    <input type="text" placeholder="Enter route" name="route" >

    <label for="route_id"><b>route_id</b></label>
    <input type="text" placeholder="Enter route_id" name="route_id" >

    <label for="start_at"><b>start_at</b></label>
    <input type="text" placeholder="Enter start_at" name="start_at">

    <label for="end_at"><b>end_at</b></label>
    <input type="text" placeholder="Enter end_at" name="end_at">

    <label for="time"><b>time</b></label>
    <input type="time" placeholder="Enter time" name="time" required>

    <label for="gender"><b>gender</b></label>
    <input type="gender" placeholder="Enter gender" name="gender" required>


    <label>
      <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
    </label>

    <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>






    <div class="clearfix">
      <button type="button" class="cancelbtn">Cancel</button>
      <button type="submit" class="signupbtn">Sign Up</button>
    </div>

    <nav class="main-nav">
  <ul>
    <li><a class="signin" href="#0">Sign in</a></li>
    <li><a class="signup" href="#0">Sign up</a></li>
  </ul>
</nav>
