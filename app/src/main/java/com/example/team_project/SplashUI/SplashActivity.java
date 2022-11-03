package com.example.team_project.SplashUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.team_project.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_splash);
        moveMain(2);
    }


    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                //new Intent(현재 context, 이동할 activity)
                Intent intent = new Intent(getApplicationContext(), Splash2Activity.class);
                startActivity(intent);	//intent 에 명시된 액티비티로 이동
                finish();	//현재 액티비티 종료
            }
        }, 1000 * sec); // sec초 정도 딜레이를 준 후 시작
    }
}