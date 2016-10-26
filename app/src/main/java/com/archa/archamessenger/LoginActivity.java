package com.archa.archamessenger;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login, btn_signUp, btn_signUp_join, btn_signUp_cancel, btn_confirm;
    private RelativeLayout layout_login;
    private LinearLayout layout_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        if(checkPermission())
        checkPermission();
        init();
        FirebaseMessaging.getInstance().subscribeToTopic("notice");
        Log.d("Login", FirebaseInstanceId.getInstance().getToken());
        Log.d("Login", ""+FirebaseInstanceId.getInstance().getToken().length());
//        else
//            Toast.makeText(LoginActivity.this, "권한 허용하셔야 사용가능합니다.", Toast.LENGTH_SHORT);
    }
    private boolean checkPermission() {
        boolean check ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                    // Explain to the user why we need to write the permission.
                    Toast.makeText(this, "권한설정에 동의하셔야 친구목록을 불러올 수 있습니다.", Toast.LENGTH_SHORT).show();
                }

                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 0);

                // MY_PERMISSION_REQUEST_STORAGE is an
                // app-defined int constant
                check = false;
            } else {
                // 다음 부분은 항상 허용일 경우에 해당이 됩니다.
                check = true;
            }
        }
        else    check = true;

        return check;
    }

    public void init(){
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_signUp = (Button)findViewById(R.id.btn_signUp);
        btn_signUp_join = (Button)findViewById(R.id.btn_signUp_join);
        btn_signUp_cancel = (Button)findViewById(R.id.btn_signUp_cancel);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        btn_login.setOnClickListener(this);
        btn_signUp.setOnClickListener(this);
        btn_signUp_join.setOnClickListener(this);
        btn_signUp_cancel.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);

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
            case R.id.btn_signUp_cancel:
                layout_signUp.setVisibility(View.GONE);
                layout_login.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_confirm:
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                alert.setMessage("1234");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
//                new AlertDialog.Builder(LoginActivity.this)
//                        .setMessage("1234")
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        }).show();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            if(layout_signUp.getVisibility() == View.VISIBLE){
                layout_signUp.setVisibility(View.GONE);
                layout_login.setVisibility(View.VISIBLE);
            } else if(layout_login.getVisibility() == View.VISIBLE){
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
