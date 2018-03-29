package com.strontech.imgautam.setdatahttpclientapp1;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowInfoActivity extends AppCompatActivity {

  String myJSON;
  List<StudentInfo> infoList;
  ArrayAdapter<String> adapter;
  JSONArray records = null;

  ArrayList<String> list;
  JSONArray studentInfo = null;

  String student_name;
  String student_roll_no;
  String student_class;
  String student_father_name;
  String student_phone;
  String student_address;

  //these are the same the json tag
  private static final String TAG_RESULTS = "result";

  private static final String TAG_NAME = "NAME";
  private static final String TAG_ROLL_NUMBER = "ROLL_NO";
  private static final String TAG_CLASS = "CLASS";
  private static final String TAG_FATHER_NAME = "FATHER_NAME";
  private static final String TAG_PHONE = "PHONE";
  private static final String TAG_ADDRESS = "ADDRESS";

  private EditText editTextRollNumber;
  private Button buttonSearch;
  TextView textView;


  public TextView textViewName;
  public TextView textViewRollNo;
  public TextView textViewClass;
  public TextView textViewFatherName;
  public TextView textViewParentNumber;
  public TextView textViewAddress;





  private MyAdapter myAdapter;
  private RecyclerView recyclerView;


  String roll_number;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_info);

    editTextRollNumber = findViewById(R.id.editTextRollNumber);
    buttonSearch = findViewById(R.id.buttonSearch);

    textViewName=findViewById(R.id.textViewName);
    textViewRollNo=findViewById(R.id.textViewRollNo);
    textViewClass=findViewById(R.id.textViewClass);
    textViewFatherName=findViewById(R.id.textViewFatherName);
    textViewParentNumber=findViewById(R.id.textViewParentNumber);
    textViewAddress=findViewById(R.id.textViewAddress);

    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    infoList = new ArrayList<StudentInfo>();

    //
    list=new ArrayList<String>();

    myAdapter = new MyAdapter(this, infoList);

    recyclerView.setAdapter(myAdapter);

    buttonSearch.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        roll_number = editTextRollNumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put("1", roll_number);

        getRecord();

      }
    });
  }

  private void getRecord() {

    class GetJSONData extends AsyncTask<String, String, String> {

      ProgressDialog progressDialog;

      @Override
      protected void onPreExecute() {

        progressDialog = new ProgressDialog(ShowInfoActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        super.onPreExecute();
      }

      @Override
      protected String doInBackground(String... strings) {
        DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost httpPost = new HttpPost(
            "https://imstar1lord.000webhostapp.com/getFilteredDataStuInfo.php?roll_number1="
                + roll_number);

        httpPost.setHeader("Content-type", "application/json");

        InputStream inputStream = null;
        String result = null;

        try {
          HttpResponse response = httpClient.execute(httpPost);
          HttpEntity entity = response.getEntity();

          inputStream = entity.getContent();

          BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),
              8);
          StringBuilder sb = new StringBuilder();
          String line = null;
          while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
          }
          result = sb.toString();
        } catch (Exception e) {
          // e.printStackTrace();
        } finally {
          try {
            if (inputStream != null) {
              inputStream.close();
            }
          } catch (Exception e) {
            //e.printStackTrace();
          }
        }
        //doubt
        return result;
      }

      @Override
      protected void onPostExecute(String s) {
        myJSON = s;
        showLists();
//        infoList.clear();
//        infoList.addAll(showList());
        progressDialog.dismiss();
        super.onPostExecute(s);
      }
    }

    GetJSONData g = new GetJSONData();
    g.execute();
  }

//  private List<StudentInfo> showList() {
//
//    List<StudentInfo> studentInfoList = new ArrayList<StudentInfo>();
//
//    try {
////      JSONObject jsonObject=new JSONObject(myJSON);  // This is used when JSON data is in [] array node
////      records=jsonObject.getJSONArray(TAG_RESULTS);
//
//      records = new JSONArray(myJSON);     // Our JSON data is in {} Object node
//
//      for (int i = 0; i < records.length(); i++) {
//        JSONObject c = records.getJSONObject(i);
//
//        StudentInfo student = new StudentInfo();
//        student.setStudent_name(c.getString(TAG_NAME));
//        student.setStudent_roll_no(c.getString(TAG_ROLL_NUMBER));
//        student.setStudent_class(c.getString(TAG_CLASS));
//        student.setStudent_father_name(c.getString(TAG_FATHER_NAME));
//        student.setStudent_phone(c.getString(TAG_PHONE));
//        student.setStudent_address(c.getString(TAG_ADDRESS));
//
//        studentInfoList.add(student);
//
//
////       student_name=c.getString(TAG_NAME);
////        student_roll_no=c.getString(TAG_ROLL_NUMBER);
////        student_class= c.getString(TAG_CLASS);
////        student_father_name= c.getString(TAG_FATHER_NAME);
////        student_phone= c.getString(TAG_PHONE);
////        student_address= c.getString(TAG_ADDRESS);
//
////        infoList.clear();
////        infoList.add(student_name);
////        infoList.add(student_roll_no);
////        infoList.add(student_class);
////        infoList.add(student_father_name);
////        infoList.add(student_phone);
////        infoList.add(student_address);
//
////
//        //infoList.add(student_name, student_roll_no, student_class, student_father_name, student_phone, student_address);
//      //  Log.w("Data Get", infoList.toString());
//        //Toast.makeText(this, "" + infoList.toString(), Toast.LENGTH_SHORT).show();
//         myAdapter.notifyDataSetChanged();
//      }
//    } catch (JSONException e) {
//
//      //show message if Roll number not exist
//      Toast.makeText(this, "Sorry Student not exist!", Toast.LENGTH_SHORT).show();
//      e.printStackTrace();
//    }
//    return studentInfoList;
//  }
//


  private void showLists() {

    List<StudentInfo> studentInfoList = new ArrayList<StudentInfo>();

    try {
//      JSONObject jsonObject=new JSONObject(myJSON);  // This is used when JSON data is in [] array node
//      records=jsonObject.getJSONArray(TAG_RESULTS);

      records = new JSONArray(myJSON);     // Our JSON data is in {} Object node

      for (int i = 0; i < records.length(); i++) {
        JSONObject c = records.getJSONObject(i);

       student_name=c.getString(TAG_NAME);
        student_roll_no=c.getString(TAG_ROLL_NUMBER);
        student_class= c.getString(TAG_CLASS);
        student_father_name= c.getString(TAG_FATHER_NAME);
        student_phone= c.getString(TAG_PHONE);
        student_address= c.getString(TAG_ADDRESS);

        textViewName.setText("Name: "+student_name);
        textViewRollNo.setText("Roll No: "+student_roll_no);
        textViewClass.setText("Class: "+student_class);
        textViewFatherName.setText("Father name: "+student_father_name);
        textViewParentNumber.setText("Parent's Contact: "+student_phone);
        textViewAddress.setText("Address: "+student_address);

      }
    } catch (JSONException e) {

      //show message if Roll number not exist
      Toast.makeText(this, "Sorry Student not exist!", Toast.LENGTH_SHORT).show();
      e.printStackTrace();
    }
  }
}
