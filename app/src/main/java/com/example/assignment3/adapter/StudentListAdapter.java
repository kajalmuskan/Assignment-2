package com.example.assignment3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.R;
import com.example.assignment3.activity.StudentRecordActivity;
import com.example.assignment3.model.StudentListModel;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class StudentListAdapter extends Adapter<StudentListAdapter.MyViewHolder> {

  private   ArrayList<StudentListModel> studentListModel;
    private int position;
    public ItemClicked clickActivity;

    public StudentListAdapter( ArrayList<StudentListModel> studentListModel,ItemClicked clickActivity) {
        this.studentListModel = studentListModel;
        this.clickActivity=clickActivity;

    }


    @Override
    public StudentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View studentListModel = layoutInflater.inflate(R.layout.recycler_design, parent, false);
        StudentListAdapter.MyViewHolder viewHolder = new StudentListAdapter.MyViewHolder(studentListModel);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( StudentListAdapter.MyViewHolder holder, int position) {
        holder.itemView.setTag((studentListModel.get(position)));
        holder.tvRoll.setText("Roll No:"+String.valueOf(studentListModel.get(position).getStudentRoll()));
        holder.tvname.setText("Name:"+String.valueOf(studentListModel.get(position).getStudentName()));
        holder.tvclass.setText("Class:"+String.valueOf(studentListModel.get(position).getStudentClass()));
    }




    @Override
    public int getItemCount() {

        return studentListModel.size();
    }


    public class MyViewHolder extends ViewHolder {
        TextView tvname;
        TextView tvclass;
        TextView tvRoll;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tv_rv_name);
            tvclass = itemView.findViewById(R.id.tv_rv_class);
            tvRoll=itemView.findViewById((R.id.tv_rv_roll));

            linearLayout=itemView.findViewById(R.id.linear_layout);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    clickActivity.onItemClicked(studentListModel.indexOf(view.getTag()));

                }


            });

        }

    }
    public interface ItemClicked{
        void onItemClicked(int position);
    }

}
