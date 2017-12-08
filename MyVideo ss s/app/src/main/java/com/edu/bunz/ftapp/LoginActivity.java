package com.edu.bunz.ftapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    private EditText mUserIdView;
    private EditText mPasswordView;
    private ProgressBar mProgressBar;
    static String muserId;
    static String mpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserIdView = (EditText) findViewById(R.id.user_id);
        mPasswordView = (EditText) findViewById(R.id.password);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        Button mLoginButton = (Button) findViewById(R.id.longin_button);
        mLoginButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.longin_button:
          //      mProgressBar.setVisibility(View.VISIBLE);
                muserId = mUserIdView.getText().toString();
                mpassword = mPasswordView.getText().toString();
                String url = "http://vediotrans.applinzi.com/index.php?account="+muserId+"&password="+mpassword;
                Utils.sendHttpRequest(url, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        parseJSONWithJSONObject(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        //  Toast.makeText(LoginActivity.this,"连接超时，请重新登录",Toast.LENGTH_SHORT);

                    }
                });

                break;
            default:
                break;

        }
    }



    private void parseJSONWithJSONObject(String jsonData){
        try{

            JSONObject jsonObject = new JSONObject(jsonData);
            String account = jsonObject.getString("account");
            Log.d("MainSActivity","account is "+account);
            //判断账户密码是否正确，正确登录进入另一个界面，错误给予提示
            if ("match".equals(account)){
            //    mProgressBar.setVisibility(View.GONE);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this,"你的账户或者密码错误",Toast.LENGTH_SHORT).show();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

