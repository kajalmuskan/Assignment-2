package com.example.androidui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidui.Util.CommonUtil;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {
    ImageButton backRegistration;
    Button btnCountinue;
    TextView signIn;
    EditText etUsername,etGender,etDOB,etUsertype,etEmail,etPassword,etOccupation;

    public static ArrayList<signUp> data=new ArrayList<signUp>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //create span for text signin with different account
        signIn=findViewById(R.id.tv_span_signin);
        String text=getResources().getString(R.string.tv_signin_span);
        SpannableString ss=new SpannableString(text);
        ClickableSpan cs=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent click=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(click);
                finish();
            }
        };
        //Initialize span for text
        ss.setSpan(cs,0,30,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signIn.setText(ss);
        signIn.setMovementMethod(LinkMovementMethod.getInstance());

        backRegistration=findViewById(R.id.btn_back_reg);
        etUsername=findViewById(R.id.et_username);
        etGender=findViewById(R.id.et_gender);
        etDOB=findViewById(R.id.et_dob);
        etUsertype=findViewById(R.id.et_usertype);
        etEmail=findViewById(R.id.et_email);
        etPassword=findViewById(R.id.et_password);
        etOccupation=findViewById(R.id.et_occupation);
        btnCountinue=findViewById(R.id.btn_countinue);


        //onclick on back icon.
        backRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToLogin=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(backToLogin);
            }
        });



        //Countinue Button Operation
       btnCountinue.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             String name=etUsername.getText().toString().trim();
             String gender=etGender.getText().toString().trim().toLowerCase();
             String dob=etDOB.getText().toString().trim();
             String userType=etUsertype.getText().toString().trim();
             String email=etEmail.getText().toString().trim().toLowerCase();
             String password=etPassword.getText().toString().trim();
             String occupation=etOccupation.getText().toString().trim().toLowerCase();
             //Name vaidation.
               String namePattern =("^[a-zA-Z\\s]*$");

               //email validation
               String emailpattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                       + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

               //password validation
               String passwordpattern ="((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

               if(email.isEmpty()){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Please enter email");
               }
               else if (!email.matches(emailpattern)){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Enter valid email");
               }
               else if(password.isEmpty()){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Please enter password");
               }
               else if(!password.matches(passwordpattern)){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Enter valid password");
               }
               else if(name.isEmpty()){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Please enter name");
               }
               else if(!name.matches(namePattern)){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Invalid name");
               }
               else if(gender.isEmpty()){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Please enter gender");
               }
               else if(!gender.equals("male") && !gender.equals("female")){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Please enter valid gender");
               }
//                else if(!gender.equals("female")){
//                    CommonUtil.showSnackBar(RegisterActivity.this,"Please enter valid gender");
//                }
               else if(dob.isEmpty()){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Please enter date of birth");
               }
               else if(userType.isEmpty()){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Please enter user type");
               }
               else if(occupation.isEmpty()){
                   CommonUtil.showSnackBar(RegistrationActivity.this,"Please enter occupation");
               }
               else{
                   //no error or if validated then sent to next page
                   data.add(new signUp(email,password));
                   Intent signUp=new Intent(RegistrationActivity.this,OTPActivity.class);
                   startActivity(signUp);
                   finish();
               }
           }
       });
    }


}
