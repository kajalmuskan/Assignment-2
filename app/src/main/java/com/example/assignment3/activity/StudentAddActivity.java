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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.assignment3.R;

import static com.example.assignment3.activity.StudentRecordActivity.studentListModel;

public class StudentAddActivity extends AppCompatActivity {
    EditText etStudentName, etStudentClass, etStudentRoll;
    TextView tvLabel;
    ImageButton ibSort, ibGrid, ibList;
    Button btnSubmitAddStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);
        etStudentName = findViewById(R.id.et_student_name);
        etStudentClass = findViewById(R.id.et_student_class);
        etStudentRoll = findViewById(R.id.et_student_roll);
        btnSubmitAddStudent = findViewById(R.id.btn_addstudent_submit);
        tvLabel = findViewById(R.id.tv_student_home);
        ibGrid = findViewById(R.id.imgbtn_grid);
        ibList = findViewById(R.id.imgbtn_list);
        ibSort = findViewById(R.id.imgbtn_studentlist_sort);

        tvLabel.setText("Add Student");
        ibSort.setVisibility(View.INVISIBLE);
        ibGrid.setVisibility(View.INVISIBLE);
        ibList.setVisibility(View.INVISIBLE);


        //sumbit button for add student
        btnSubmitAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    Integer.parseInt(etStudentClass.getText().toString().trim());
                    try {
                        Integer.parseInt(etStudentRoll.getText().toString().trim());
                        submitStudentData();
                    } catch (Exception e) {
                        CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_invalid_roll));
                    }
                } catch (Exception e) {
                    CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_invalid_class));
                }

            }
        });


        int code = getIntent().getIntExtra("Code", 0);
        final StudentListModel studentListModel = getIntent().getParcelableExtra("Object");
        if (code == 2) {
            tvLabel.setText("View Student");


            etStudentName.setText(getResources().getString(R.string.label_name) + studentListModel.getStudentName());
            etStudentRoll.setText(getResources().getString(R.string.label_roll) + String.valueOf(studentListModel.getStudentRoll()));
            etStudentClass.setText(getResources().getString(R.string.label_class) + String.valueOf(studentListModel.getStudentClass()));

            etStudentName.setBackground(getResources().getDrawable(R.drawable.view_data));
            etStudentRoll.setBackground(getResources().getDrawable(R.drawable.view_data));
            etStudentClass.setBackground(getResources().getDrawable(R.drawable.view_data));

            etStudentName.setInputType(InputType.TYPE_NULL);
            etStudentClass.setInputType(InputType.TYPE_NULL);
            etStudentRoll.setInputType(InputType.TYPE_NULL);

            tvLabel.setText(R.string.view_student);
            ibSort.setVisibility(View.INVISIBLE);
            ibGrid.setVisibility(View.INVISIBLE);
            ibList.setVisibility(View.INVISIBLE);
            btnSubmitAddStudent.setText(R.string.back_btn_view);

            btnSubmitAddStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StudentAddActivity.this, StudentRecordActivity.class);
                    startActivity(intent);

                    finish();
                }
            });
        }
        if (code == 3) {
            tvLabel.setText(R.string.btn_dialog_update);
            ibSort.setVisibility(View.INVISIBLE);
            ibGrid.setVisibility(View.INVISIBLE);
            ibList.setVisibility(View.INVISIBLE);
            btnSubmitAddStudent.setText(R.string.btn_dialog_update);

            etStudentRoll.setInputType(InputType.TYPE_NULL);
            etStudentRoll.setBackground(getResources().getDrawable(R.drawable.view_data));


            btnSubmitAddStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String etUpdateName = etStudentName.getText().toString().trim();
                    final String etUpdateClass = etStudentClass.getText().toString().trim();
                    studentListModel.setStudentName(etUpdateName);
                    studentListModel.setStudentClass(Integer.parseInt(etUpdateClass));
                    Intent intent = new Intent(StudentAddActivity.this, StudentRecordActivity.class);
                    intent.putExtra("Object", studentListModel);
                    //setResult(RESULT_OK, intent);
                    startActivity(intent);

                    finish();


                }
            });
        }


    }

    protected void submitStudentData() {
        String etAddName = etStudentName.getText().toString().trim();
        String etAddClass = etStudentClass.getText().toString().trim();
        String etAddRoll = etStudentRoll.getText().toString().trim();

        if (isValidate()) {


            studentListModel.add(new StudentListModel(etAddName, Integer.parseInt(etAddClass), Integer.parseInt(etAddRoll)));
            // StudentListModel studentListModel = new StudentListModel(etAddName, Integer.parseInt(etAddName), Integer.parseInt(etAddClass));

            Intent intent = new Intent(getApplicationContext(), StudentRecordActivity.class);
            intent.putExtra("Object", studentListModel);
            setResult(RESULT_OK, intent);
            startActivity(intent);
            finish();
        }
    }

    private boolean isValidate() {
        String etAddName = etStudentName.getText().toString().trim();
        String etAddClass = etStudentClass.getText().toString().trim();
        String etAddRollNo = etStudentRoll.getText().toString().trim();
        String namePattern = ("^[a-zA-Z\\s]*$");
        if (etAddName.isEmpty()) {
            CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_enter_name));
            return false;
        } else if (etAddName.equals(namePattern)) {
            CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_invalid_name));
            return false;
        } else if (etAddClass.isEmpty()) {
            CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_enter_class));
            return false;
        } else if (etAddRollNo.isEmpty()) {
            CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_enter_roll));
            return false;
        } else if (Integer.parseInt(etAddClass) > 12) {
            CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_invalid_class));
            return false;
        } else if (Integer.parseInt(etAddClass) < 0) {
            CommanUtil.showSnackBar(StudentAddActivity.this, getResources().getString(R.string.err_invalid_class));
            return false;
        }
//        else if(Integer.parseInt(etAddRollNo)<0){
//            CommanUtil.showSnackBar(StudentAddActivity.this,getResources().getString(R.string.err_invalid_roll));
//            return false;
//        }
        else {
            return true;
        }
    }

}

