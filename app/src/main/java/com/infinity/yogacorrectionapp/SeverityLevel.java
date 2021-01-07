package com.infinity.yogacorrectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.infinity.yogacorrectionapp.liveVideoBroadcaster.LiveVideoBroadcasterActivity;

public class SeverityLevel extends Activity implements View.OnClickListener{

    Button mBeginnerBtn;
    Button mIntermediateBtn;
    Button mExpertBtn;

    public static final String EXTRA_SEVERITY = "com.infinity.yogacorrectionapp.EXTRA_SEVERITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_severity_level);

        mBeginnerBtn=findViewById(R.id.beginnerBtn);
        mIntermediateBtn=findViewById(R.id.intermediateBtn);
        mExpertBtn=findViewById(R.id.expertBtn);


        mBeginnerBtn.setOnClickListener((View.OnClickListener) this);
        mIntermediateBtn.setOnClickListener((View.OnClickListener) this);
        mExpertBtn.setOnClickListener((View.OnClickListener) this);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        WindowManager.LayoutParams params=getWindow().getAttributes();

        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);

    }

    @Override
    public void onClick(View v) {

        int level;

        if(v==mBeginnerBtn){
            level=1;
        }else if(v==mIntermediateBtn){
            level=2;
        }else{
            level=3;
        }

        beginSession(level);
    }

    public void beginSession(int level){

        Intent i = new Intent(this, LiveVideoBroadcasterActivity.class);
        i.putExtra(EXTRA_SEVERITY,level);
        startActivity(i);
    }
}
