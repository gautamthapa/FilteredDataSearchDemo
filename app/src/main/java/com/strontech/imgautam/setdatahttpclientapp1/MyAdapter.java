package com.strontech.imgautam.setdatahttpclientapp1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

  private Context context;
  private List<StudentInfo> studentInfos;

  public MyAdapter(Context context, List<StudentInfo> studentInfos)
  {
    this.context=context;
    this.studentInfos=studentInfos;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_info_layout,
        parent,false);

    ViewHolder viewHolder=new ViewHolder(v);
    return viewHolder ;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

    StudentInfo student=studentInfos.get(position);
    holder.textViewName.setText(student.getStudent_name());
    holder.textViewRollNo.setText(student.getStudent_roll_no());
    holder.textViewClass.setText(student.getStudent_class());
    holder.textViewFatherName.setText(student.getStudent_father_name());
    holder.textViewParentNumber.setText(student.getStudent_phone());
    holder.textViewAddress.setText(student.getStudent_address());
  }

  @Override
  public int getItemCount() {
    return studentInfos.size();
  }



  public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView textViewName;
    public TextView textViewRollNo;
    public TextView textViewClass;
    public TextView textViewFatherName;
    public TextView textViewParentNumber;
    public TextView textViewAddress;

    public ViewHolder(View itemView) {
      super(itemView);


      textViewName=itemView.findViewById(R.id.textViewName);
      textViewRollNo=itemView.findViewById(R.id.textViewRollNo);
      textViewClass=itemView.findViewById(R.id.textViewClass);
      textViewFatherName=itemView.findViewById(R.id.textViewFatherName);
      textViewParentNumber=itemView.findViewById(R.id.textViewParentNumber);
      textViewAddress=itemView.findViewById(R.id.textViewAddress);
    }
  }
}
