package com.archa.archamessenger.Utils;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Boo on 2016-06-22.
 */
public class FireBaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    private static String token;
    private int UID;
    private String UDID;

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);
        Log.d(TAG, "LENGTH : "+token.length());
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(token);
    }

    public static String getGKey(){
        return token;
    }

   //private void sendRegistrationToServer(String token){}

    private void sendRegistrationToServer(String token) {
        Log.d("GCM KEY ", "TOKEN : "+token);
//        UID = SettingInfo.getInstance(this).getUid(); // 저장된 uid를 반환
//        if(UID > -1){
//            UDID = SettingInfo.getInstance(this).getDUID(); // udid값을 얻어옴
//            if (UDID.equals("null")) {
//                SettingInfo.getInstance(this).setDUID(this);
//                UDID = SettingInfo.getInstance(this).getDUID();
//            }
//            String userID = SettingInfo.getInstance(this).getID();
//            String userPW = SettingInfo.getInstance(this).getPASSWD();
//            ServerConn.getMember(null, UDID, null, userID, userPW, 1, token);
//        }
        // Add custom implementation, as needed.
        /*
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        //request
        Request request = new Request.Builder()
                .url("http://서버주소/fcm/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
