package com.example.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OTPActivity extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    Button btnResend,btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        et1=findViewById(R.id.et_one);
        et2=findViewById(R.id.et_two);
        et3=findViewById(R.id.et_three);
        et4=findViewById(R.id.et_four);
        btnResend=findViewById(R.id.btn_otp_resend);
        btnSubmit=findViewById(R.id.btn_otp_submit);

        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.getText().clear();
                et2.getText().clear();
                et3.getText().clear();
                et4.getText().clear();
                et1.requestFocus();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp1=et1.getText().toString();
                String otp2=et2.getText().toString();
                String otp3=et3.getText().toString();
                String otp4=et4.getText().toString();
                if(otp1.isEmpty() && otp2.isEmpty() && otp3.isEmpty() && otp4.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter otp", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent submit = new Intent(OTPActivity.this, LogoutActivity.class);
                    startActivity(submit);
                    finish();
                }

            }
        });

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==1){
                    et2.requestFocus();
                }
                else if(editable.length()==0) {
                    et1.clearFocus();
                }
            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==1){
                    et3.requestFocus();
                }
                else if(editable.length()==0) {
                    et2.clearFocus();
                }
            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==1){
                    et4.requestFocus();
                }
                else if(editable.length()==0) {
                    et3.clearFocus();
                }
            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==1){
                    et4.requestFocus();
                }
                else if(editable.length()==0) {
                    et4.clearFocus();
                }
            }
        });

    }
}
