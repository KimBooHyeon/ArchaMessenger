package com.archa.archamessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login, btn_signUp, btn_signUp_join;
    private RelativeLayout layout_login;
    private LinearLayout layout_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        init();
    }

    public void init(){
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_signUp = (Button)findViewById(R.id.btn_signUp);
        btn_signUp_join = (Button)findViewById(R.id.btn_signUp_join);
        btn_login.setOnClickListener(this);
        btn_signUp.setOnClickListener(this);
        btn_signUp_join.setOnClickListener(this);

        layout_login = (RelativeLayout)findViewById(R.id.layout_login);
        layout_signUp = (LinearLayout)findViewById(R.id.layout_signUp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_signUp:
                layout_login.setVisibility(View.GONE);
                layout_signUp.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_signUp_join:
                layout_login.setVisibility(View.VISIBLE);
                layout_signUp.setVisibility(View.GONE);
                break;
        }
    }
}
