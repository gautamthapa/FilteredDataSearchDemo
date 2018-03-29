<?php
$con=mysqli_connect("localhost", "id4516262_student_record","123","id4516262_student_record")
or die("connection not successful");

mysqli_select_db($con, "id4516262_student_record") or die("database not found");

if(isset($_POST['NAME']) && isset($_POST['ROLL_NO']) && isset($_POST['CLASS'])
  && isset($_POST['FATHER_NAME']) && isset($_POST['PHONE']) && isset($_POST['ADDRESS']))
{
  $student_name=$_POST['NAME'];
  $student_roll_no=$_POST['ROLL_NO'];
  $student_class=$_POST['CLASS'];
  $student_father_name=$_POST['FATHER_NAME'];
  $student_phone=$_POST['PHONE'];
  $student_address=$_POST['ADDRESS'];

  $qry="insert into student_info(NAME, ROLL_NO, CLASS, FATHER_NAME, PHONE, ADDRESS)
   values('$student_name', '$student_roll_no', '$student_class', '$student_father_name',
          '$student_phone', '$student_address')";

  mysqli_query($con, $qry) or die ("Query Problem");
}
else {
echo "waiting for data......";
}
?>
