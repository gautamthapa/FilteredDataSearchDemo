package com.strontech.imgautam.setdatahttpclientapp1;

/**
 * Created by imgautam on 25/3/18.
 */

public class StudentInfo {

  private String student_name;
  private String student_roll_no;
  private String student_class;
  private String student_father_name;
  private String student_phone;
  private String student_address;

  public StudentInfo() {
  }

  public StudentInfo(String student_name, String student_roll_no, String student_class,
      String student_father_name, String student_phone, String student_address) {
    this.student_name = student_name;
    this.student_roll_no = student_roll_no;
    this.student_class = student_class;
    this.student_father_name = student_father_name;
    this.student_phone = student_phone;
    this.student_address = student_address;
  }

  public String getStudent_name() {
    return student_name;
  }

  public void setStudent_name(String student_name) {
    this.student_name = student_name;
  }

  public String getStudent_roll_no() {
    return student_roll_no;
  }

  public void setStudent_roll_no(String student_roll_no) {
    this.student_roll_no = student_roll_no;
  }

  public String getStudent_class() {
    return student_class;
  }

  public void setStudent_class(String student_class) {
    this.student_class = student_class;
  }

  public String getStudent_father_name() {
    return student_father_name;
  }

  public void setStudent_father_name(String student_father_name) {
    this.student_father_name = student_father_name;
  }

  public String getStudent_phone() {
    return student_phone;
  }

  public void setStudent_phone(String student_phone) {
    this.student_phone = student_phone;
  }

  public String getStudent_address() {
    return student_address;
  }

  public void setStudent_address(String student_address) {
    this.student_address = student_address;
  }
}
