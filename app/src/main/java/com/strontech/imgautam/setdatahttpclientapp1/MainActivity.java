package com.strontech.imgautam.setdatahttpclientapp1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private TextInputEditText textInputEdiTextViewName;
  private TextInputEditText textInputEdiTextViewRollNo;
  private TextInputEditText textInputEdiTextViewClass;
  private TextInputEditText textInputEdiTextViewFatherName;
  private TextInputEditText textInputEdiTextViewPhone;
  private TextInputEditText textInputEdiTextViewAddress;

  private TextInputLayout textInputLayoutName;
  private TextInputLayout textInputLayoutRollNo;
  private TextInputLayout textInputLayoutClass;
  private TextInputLayout textInputLayoutFatherName;
  private TextInputLayout textInputLayoutFatherPhone;
  private TextInputLayout textInputLayoutFatherAddress;


  private Button buttonSubmitInfo;
  private Button buttonSearch;

  private JSONParser jsonParser;
  private StudentInfo studentInfo;

  private String url;
  private ProgressDialog progressDialog;


  String student_name;
  String student_roll_no;
  String student_class;
  String student_father_name;
  String student_phone;
  String student_address;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
    initListeners();
    initObjects();



  }

  /**
   * For Initialize Views
   */
  private void initViews() {
    textInputEdiTextViewName = findViewById(R.id.textInputEdiTextViewName);
    textInputEdiTextViewRollNo = findViewById(R.id.textInputEdiTextViewRollNo);
    textInputEdiTextViewClass = findViewById(R.id.textInputEdiTextViewClass);
    textInputEdiTextViewFatherName = findViewById(R.id.textInputEdiTextViewFatherName);
    textInputEdiTextViewPhone = findViewById(R.id.textInputEdiTextViewPhone);
    textInputEdiTextViewAddress = findViewById(R.id.textInputEdiTextViewAddress);


    textInputLayoutName=findViewById(R.id.textInputLayoutName);
    textInputLayoutRollNo=findViewById(R.id.textInputLayoutRollNo);
    textInputLayoutClass=findViewById(R.id.textInputLayoutClass);
    textInputLayoutFatherName=findViewById(R.id.textInputLayoutFatherName);
    textInputLayoutFatherPhone=findViewById(R.id.textInputLayoutFatherPhone);
    textInputLayoutFatherAddress=findViewById(R.id.textInputLayoutFatherAddress);


    buttonSubmitInfo = findViewById(R.id.buttonSubmitInfo);
    buttonSearch = findViewById(R.id.buttonSearch);

  }


  private void initListeners() {
    buttonSubmitInfo.setOnClickListener(this);
    buttonSearch.setOnClickListener(this);
  }

  /**
   * For Create objects
   */

  private void initObjects() {

    url = "https://imstar1lord.000webhostapp.com/setDataStudentInfo.php";
    jsonParser = new JSONParser();

    //studentInfo=new StudentInfo();

  }

  @Override
  public void onClick(View v) {

    switch (v.getId()) {
      case R.id.buttonSubmitInfo:


        student_name = textInputEdiTextViewName.getText().toString().trim();
        student_roll_no = textInputEdiTextViewRollNo.getText().toString().trim();
        student_class = textInputEdiTextViewClass.getText().toString().trim();
        student_father_name = textInputEdiTextViewFatherName.getText().toString().trim();
        student_phone = textInputEdiTextViewPhone.getText().toString().trim();
        student_address = textInputEdiTextViewAddress.getText().toString().trim();

        if (student_name.equals("")){
          textInputLayoutName.setError("Enter Name");
        }else if (student_roll_no.equals("")){
          textInputLayoutRollNo.setError("Enter Roll No");
        }else if (student_class.equals("")){
          textInputLayoutClass.setError("Enter Class");
        }else if (student_father_name.equals("")){
          textInputLayoutFatherName.setError("Enter Father Name");
        }else if (student_phone.equals("")){
          textInputLayoutFatherPhone.setError("Enter Parent's Contact");
        }else if (student_address.equals("")){
          textInputLayoutFatherAddress.setError("Enter Address");
        }else {
          new AddNewRecord().execute();
        }
        break;
      case R.id.buttonSearch:
        Intent intent = new Intent(MainActivity.this, ShowInfoActivity.class);
        startActivity(intent);
        break;
    }
  }

  public class AddNewRecord extends AsyncTask<String, String, String> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(MainActivity.this);
      progressDialog.setMessage("Saving Info");
      progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

      List<NameValuePair> params = new ArrayList<NameValuePair>();
      params.add(new BasicNameValuePair("NAME", student_name));
      params.add(new BasicNameValuePair("ROLL_NO", student_roll_no));
      params.add(new BasicNameValuePair("CLASS", student_class));
      params.add(new BasicNameValuePair("FATHER_NAME", student_father_name));
      params.add(new BasicNameValuePair("PHONE", student_phone));
      params.add(new BasicNameValuePair("ADDRESS", student_address));
      Log.e("Data", student_name);
      Log.e("Params", String.valueOf(params));
      JSONObject object = jsonParser.makeHttpRequest(url, "POST", params);
      Log.d("CREATE RESPONSE", object.toString());

      try {
        int success = object.getInt("TAG Successfully");
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }

    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);

      progressDialog.dismiss();
      textInputEdiTextViewName.setText(null);
      textInputEdiTextViewRollNo.setText(null);
      textInputEdiTextViewClass.setText(null);
      textInputEdiTextViewFatherName.setText(null);
      textInputEdiTextViewPhone.setText(null);
      textInputEdiTextViewAddress.setText(null);

      Toast.makeText(MainActivity.this, "Record saved successfully", Toast.LENGTH_SHORT).show();
    }
  }
}
