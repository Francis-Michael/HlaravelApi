package com.gh.mike.hlaravelapi;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Downloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    EditText etFullName,etEmail,etPassword,etConfirmPassword,etPhone;
    Button btRegister;
    public  static String txtemail,txtphone,txtpass,txtfullname;
    public static String token;
    SweetAlertDialog sweetAlertDialogSuccess;
    StringRequest mFstringRequest;
    JSONObject mFjsonObject;
    RequestQueue mFqueue;
    String mFurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etFullName = findViewById(R.id.etFullName);
        btRegister = findViewById(R.id.btRegister);
        etPhone = findViewById(R.id.etPhone);
        signUP();

    }


    public void signUP(){
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String mFname = null;
//                try {
//                    mFname = URLEncoder.encode(etFullName.getText().toString(), "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//
//
//                }
                txtemail = etEmail.getText().toString();
                txtphone = etPhone.getText().toString();
                txtpass = etPassword.getText().toString();
                txtfullname = etFullName.getText().toString();


//                Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_LONG).show();
                Response.Listener<String> mfResponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            mFjsonObject = new JSONObject(response);
                            if (mFjsonObject.getString("statuscode").equals("0001")) {
                                final String token = mFjsonObject.getString("api_token");
                                sweetAlertDialogSuccess.dismiss();

                                sweetAlertDialogSuccess = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                sweetAlertDialogSuccess.setTitleText("Success");
                                sweetAlertDialogSuccess.setContentText(mFjsonObject.getString("Message"));
                                sweetAlertDialogSuccess.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                    }
                                }).show();


                            } else {
                                sweetAlertDialogSuccess.dismiss();
                                sweetAlertDialogSuccess = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                                sweetAlertDialogSuccess.setTitleText("Failed");
                                sweetAlertDialogSuccess.setContentText(mFjsonObject.getString("Message"));
                                sweetAlertDialogSuccess.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialogSuccess.dismissWithAnimation();

                                    }
                                }).show();
                            }

                        } catch (JSONException e) {
                            sweetAlertDialogSuccess.dismiss();

                            sweetAlertDialogSuccess = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                            sweetAlertDialogSuccess.setTitleText("Failed");
                            sweetAlertDialogSuccess.setContentText("Oops, Network Issue, try again later");
                            sweetAlertDialogSuccess.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                }


                            }).show();


                        }


                    }

                };
                token = "F2FPx4rk1bUUGtjvLSnwPVS3CqItkzaGh1f";
                LaraRegisterRequest laraRegisterRequest = new LaraRegisterRequest(mfResponseListener);
                mFqueue = Volley.newRequestQueue(MainActivity.this);
                mFqueue.add(laraRegisterRequest);
            }

        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        mFqueue = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFqueue = null;
    }


}