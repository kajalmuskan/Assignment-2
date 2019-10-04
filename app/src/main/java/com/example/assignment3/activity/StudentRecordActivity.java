package com.example.assignment3.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.adapter.StudentListAdapter;
import com.example.assignment3.model.StudentListModel;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Comparator;

public class StudentRecordActivity extends AppCompatActivity implements StudentListAdapter.ItemClicked {
    Button btnAddStudent;
    ImageButton imgbtnSort, imgbtnList, imgbtnGrid;
    RecyclerView recyclerView;
    TextView tvBlankList;
    RecyclerView.Adapter studentListAdapter;

    public static ArrayList<StudentListModel> studentListModel = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_record);
        btnAddStudent = findViewById(R.id.btn_add_student);
        imgbtnSort = findViewById(R.id.imgbtn_studentlist_sort);
        imgbtnList = findViewById(R.id.imgbtn_list);
        imgbtnGrid = findViewById(R.id.imgbtn_grid);
        recyclerView = findViewById(R.id.recycleView);
        tvBlankList = findViewById(R.id.tv_blank_list);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentRecordActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        studentListAdapter = new StudentListAdapter(studentListModel, this);
        recyclerView.setAdapter(studentListAdapter);

        studentListAdapter.notifyDataSetChanged();
        // imgbtnGrid.setVisibility(View.INVISIBLE);

        imgbtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgbtnList.setVisibility(View.INVISIBLE);
                imgbtnGrid.setVisibility(View.VISIBLE);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(StudentRecordActivity.this, 2);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        });

        imgbtnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgbtnGrid.setVisibility(View.INVISIBLE);
                imgbtnList.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });


        //button for sorting of  data either name or roll.
        imgbtnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(StudentRecordActivity.this, imgbtnSort);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.sort_name:
                                Collections.sort(studentListModel, new Comparator<StudentListModel>() {
                                    @Override
                                    public int compare(StudentListModel n1, StudentListModel n2) {
                                        return n1.getStudentName().compareToIgnoreCase(n2.getStudentName());
                                    }
                                });

                                studentListAdapter.notifyDataSetChanged();
                                return true;
                            case R.id.sort_roll:
                                Collections.sort(studentListModel, new Comparator<StudentListModel>() {
                                    @Override
                                    public int compare(StudentListModel r1, StudentListModel r2) {
                                        String roll1 = String.valueOf(r1.getStudentRoll());
                                        String roll2 = String.valueOf(r2.getStudentRoll());
                                        return roll1.compareToIgnoreCase(roll2);
                                    }
                                });
                                studentListAdapter.notifyDataSetChanged();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }


        });
        //add student button
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addStudentIntent = new Intent(StudentRecordActivity.this, StudentAddActivity.class);

                startActivityForResult(addStudentIntent, 1);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (requestCode == RESULT_OK) {
                StudentListModel student = data.getParcelableExtra("Object");
                studentListModel.add(student);
                if (studentListModel.size() == 0) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    tvBlankList.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    tvBlankList.setVisibility(View.INVISIBLE);
                }

            }
        }
    }

    @Override
    public void onItemClicked(final int position) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        Button btnView = dialog.findViewById(R.id.btn_dialog_view);
        Button btnUpdate = dialog.findViewById(R.id.btn_dialog_update);
        Button btnDelete = dialog.findViewById(R.id.btn_dialog_delete);
        dialog.show();


        //view details of student
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StudentRecordActivity.this, StudentAddActivity.class);
                intent.putExtra("Code", 2);
                intent.putExtra("Object", studentListModel.get(position));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        //update student detail
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentRecordActivity.this, StudentAddActivity.class);
                intent.putExtra("Code", 3);
                intent.putExtra("Object", studentListModel.get(position));
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        //Delete Student details
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentListModel.remove(position);
                studentListAdapter.notifyDataSetChanged();
                dialog.dismiss();

            }

        });


    }
}



