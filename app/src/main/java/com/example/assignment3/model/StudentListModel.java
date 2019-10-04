package com.example.assignment3.model;

import android.os.Parcel;
import android.os.Parcelable;


public class StudentListModel implements Parcelable {
    private String studentName;
    private int studentClass;
    private int studentRoll;

    public StudentListModel(String studentName, int studentClass, int studentRoll) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentRoll = studentRoll;


    }


    protected StudentListModel(Parcel in) {
        studentName = in.readString();
        studentClass = in.readInt();
        studentRoll = in.readInt();
    }

    public static final Creator<StudentListModel> CREATOR = new Creator<StudentListModel>() {
        @Override
        public StudentListModel createFromParcel(Parcel in) {
            return new StudentListModel(in);
        }

        @Override
        public StudentListModel[] newArray(int size) {
            return new StudentListModel[size];
        }
    };

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(int studentClass) {
        this.studentClass = studentClass;
    }

    public int getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(int studentRoll) {
        this.studentRoll = studentRoll;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(studentName);
        parcel.writeInt(studentClass);
        parcel.writeInt(studentRoll);
    }
}
