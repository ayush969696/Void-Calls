package com.myandayush.zegocalling;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {

    EditText userIDEditText;
    TextView heyUserTextView;
    ZegoSendCallInvitationButton voiceBtn, vidoBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        userIDEditText = findViewById(R.id.edittext);
        heyUserTextView = findViewById(R.id.user_text_view);
        voiceBtn = findViewById(R.id.voicecallBtn);
        vidoBtn = findViewById(R.id.videocallBtn);

        String username = getIntent().getStringExtra("username");
        String userId = getIntent().getStringExtra("userID");
        heyUserTextView.setText(username);

        userIDEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String targetUserID = userIDEditText.getText().toString().trim();
                String targetUserName = username;
                setVideoceCall(targetUserID, targetUserName);
                setVoiceCall(targetUserID, targetUserName);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    void setVoiceCall(String targetUserID, String targetUserName){
        voiceBtn.setIsVideoCall(false);
        voiceBtn.setResourceID("zego_uikit_call");
        voiceBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID,targetUserName)));
    }

    void setVideoceCall(String targetUserID, String targetUserName){
        vidoBtn.setIsVideoCall(true);
        vidoBtn.setResourceID("zego_uikit_call");
        vidoBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID,targetUserName)));
    }

}