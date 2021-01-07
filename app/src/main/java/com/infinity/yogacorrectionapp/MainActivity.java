package com.infinity.yogacorrectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.infinity.yogacorrectionapp.liveVideoBroadcaster.LiveVideoBroadcasterActivity;

public class MainActivity extends AppCompatActivity {

    Button mStartBtn;

    public static final String RTMP_BASE_URL = "rtmp://192.168.1.4:1935/live/";
    //public static final String RTMP_BASE_URL = "rtmp://192.168.1.5/LiveApp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartBtn=findViewById(R.id.StartBtn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SeverityLevel.class));
            }
        });

    }

    /*public void openVideoBroadcaster(View view) {
        Intent i = new Intent(this, LiveVideoBroadcasterActivity.class);
        startActivity(i);
    }*/

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),SignIn.class));
        finish();
    }
}



