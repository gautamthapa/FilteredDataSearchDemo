<?php
define('HOST', 'localhost');
define('USER', 'id4516262_student_record');
define('PASS', '123');
define('DB', 'id4516262_student_record');

$con=mysqli_connect(HOST,USER, PASS, DB) or die("connection failed");

mysqli_select_db($con, "id4516262_student_record") or die("database not found");

if ($_GET['roll_number1'])
{
  $ia=$_GET['roll_number1'];

  $r=mysqli_query($con, "select * from student_info where ROLL_NO='$ia'");

  while($row=mysqli_fetch_assoc($r))
  {
    $cls[]=$row;
  }
  echo json_encode($cls);

}

mysqli_close($con);

 ?>
