package com.example.assignment3.activity;

import com.example.assignment3.model.StudentListModel;

import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment3.util.CommanUtil;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment3.R;

import static com.example.assignment3.activity.StudentRecordActivity.studentListModel;

public class StudentAddActivity extends AppCompatActivity {
    EditText etStudentName, etStudentClass, etStudentRoll;
    Button btnSubmitAddStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);
        etStudentName = findViewById(R.id.et_student_name);
        etStudentClass = findViewById(R.id.et_student_class);
        etStudentRoll = findViewById(R.id.et_student_roll);
        btnSubmitAddStudent = findViewById(R.id.btn_addstudent_submit);
        //sumbit button for add student
        btnSubmitAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitStudent();
            }
        });


        int code=getIntent().getIntExtra("Code",0);
        final StudentListModel studentListModel=getIntent().getParcelableExtra("Object");
        if(code==2){
            etStudentName.setText(R.string.et_student_name);
            etStudentName.setText(studentListModel.getStudentName());
            etStudentClass.setText(String.valueOf(studentListModel.getStudentClass()));
            etStudentRoll.setText(String.valueOf(studentListModel.getStudentRoll()));
            etStudentName.setBackground(getResources().getDrawable(R.drawable.view_data));
            etStudentClass.setBackground(getResources().getDrawable(R.drawable.view_data));
            etStudentRoll.setBackground(getResources().getDrawable(R.drawable.view_data));
            etStudentName.setInputType(InputType.TYPE_NULL);
            etStudentClass.setInputType(InputType.TYPE_NULL);
            etStudentRoll.setInputType(InputType.TYPE_NULL);
            btnSubmitAddStudent.setText(R.string.back_btn_view);
            btnSubmitAddStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


    }

    protected void submitStudent() {
        String addName = etStudentName.getText().toString().trim();
        int addClass = 0;
        int addRoll=0;
        try {
            addClass = Integer.parseInt(etStudentClass.getText().toString().trim());
            addRoll = Integer.parseInt(etStudentRoll.getText().toString().trim());

        }catch (Exception e){
            CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_enter_valid_input));
        }



        studentListModel.add(new StudentListModel(addName,addClass,addRoll));
        Intent intent=new Intent(getApplicationContext(), StudentRecordActivity.class);
        // Intent intent=new Intent(StudentAddActivity.this,StudentRecordActivity.class);
        intent.putExtra("Object",studentListModel);
        setResult(RESULT_OK,intent);
        startActivity(intent);
        finish();
    }

}

