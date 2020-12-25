package com.infinity.yogacorrectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class YogaSession extends AppCompatActivity {

    TextView mLevel;
    TextView mPosture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager.getInstance(this).registerReceiver(mHandler,new IntentFilter("com.infinity.yogacorrectionapp_FCM-MESSAGE"));
        setContentView(R.layout.activity_yoga_session);

        mLevel=findViewById(R.id.textLevel);
        mPosture=findViewById(R.id.textPosture);

        Intent intent=getIntent();
        String level=intent.getStringExtra(SeverityLevel.EXTRA_SEVERITY);

        mLevel.setText(level);

        if(getIntent().getExtras()!=null){
            for(String key: getIntent().getExtras().keySet()){

                // mPosture.setText(getIntent().getExtras().getString(key));
            }
        }

    }

    private BroadcastReceiver mHandler=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message=intent.getStringExtra("message");
            mPosture.setText(message);
        }
    };

    @Override
    protected void onPause(){
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mHandler);
    }
}
