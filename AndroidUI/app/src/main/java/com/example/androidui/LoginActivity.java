package com.example.androidui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidui.Util.CommonUtil;
import com.example.androidui.signUp;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail,etPassword;
    Button btnLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail=findViewById(R.id.et_login_email);
        etPassword=findViewById(R.id.et_login_password);
        btnLogin=findViewById(R.id.btn_login);
        TextView tv=findViewById(R.id.tv_login_register);

        String text=getResources().getString(R.string.txt_login_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etEmail.getText().toString().trim();
                String password=etPassword.getText().toString().trim();

                if (email.isEmpty()){
                    CommonUtil.showSnackBar(LoginActivity.this,"Enter email");
                }
                else if(password.isEmpty()){
                    CommonUtil.showSnackBar(LoginActivity.this,"Enter password");
                }
                else{
                    for(int i=0;i<signUp.data.size();i++){
                        String getEmail=signUp.data.get(i).getEmail();
                        String getPassword=signUp.data.get(i).getPassword();

                        if(!email.equals(getEmail)){
                            CommonUtil.showSnackBar(LoginActivity.this,"Enter valid email");
                        }
                        else if(!password.equals(getPassword)){
                            CommonUtil.showSnackBar(LoginActivity.this,"Enter valid password");
                        }
                        else {
                            Intent login = new Intent(LoginActivity.this,OTPActivity.class);
                            startActivity(login);
                            finish();
                            CommonUtil.showSnackBar(LoginActivity.this,"Logged In successfully");
                        }

                    }
                }

                
            }

        });

        //Initialize a new Clickable span
        ClickableSpan clickableSpan =new ClickableSpan() {
            @Override
            public void onClick( View view) {
                Intent intentRegister = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intentRegister);

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
       SpannableStringBuilder ss=new SpannableStringBuilder(text);
       // apply the clickable text to the span
        ss.setSpan(
                clickableSpan, // span to add
                text.lastIndexOf("REGISTER"), // start of the span (inclusive)
                text.lastIndexOf("REGISTER") + String.valueOf("REGISTER").length(), // end of the span (exclusive)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // do not extend the span when text add later
        );
        //Initialize color span for Register
        ss.setSpan(
               new ForegroundColorSpan(getResources().getColor(R.color.colorBlue)),
               text.lastIndexOf("REGISTER"),
               text.lastIndexOf("REGISTER")+String.valueOf("REGISTER").length(),
               Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
       );
        tv.setText(ss);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        };



    public boolean cheekUserExist(){
        if( RegistrationActivity.data.size()==0)
        {View view=findViewById(R.id.btn_login);
            Snackbar.make(view,"User Do not Exist",Snackbar.LENGTH_SHORT);
            return false;
        }
        return true;
    }
    }

