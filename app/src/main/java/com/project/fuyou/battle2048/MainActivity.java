package com.project.fuyou.battle2048;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button create2048,offline2048;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create2048=(Button) findViewById(R.id.button1);
        create2048.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("chessBoard");
                startActivity(intent);
            }
        });
        offline2048=(Button) findViewById(R.id.button);
        offline2048.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("offlinegame");
                startActivity(intent);
            }
        });
    }
}
