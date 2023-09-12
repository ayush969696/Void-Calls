package com.myandayush.zegocalling;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    EditText userIdEditText, username ;
    Button startBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.username);
        userIdEditText = findViewById(R.id.edittext);
        startBtn = findViewById(R.id.strtBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = userIdEditText.getText().toString().trim();
                String name = username.getText().toString();
                if(name.isEmpty()){
                    username.setError("REQUIRED");
                }
                if (userID.isEmpty()){
                    userIdEditText.setError("REQUIRED");
                }

                //start the service
                startService(userID,name);
                Intent intent = new Intent(MainActivity.this, CallActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("username", name);
                startActivity(intent);

            }
        });

    }

    void startService(String userID, String username){
        Application application = getApplication(); // Android's application context
        long appID = 1859509430;   // yourAppID
        String appSign = "70664bf460c9c43b3644c39b3b710cd1bf2a2f71694c800e9e2504f29489244b";  // yourAppSign

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, username,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}