package com.project.fuyou.battle2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.fuyou.battle2048.database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Button create2048,offline2048,offlinehistory;
    private EditText editText;
    private MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.editText);
        create2048=(Button) findViewById(R.id.button1);
        myDatabaseHelper=new MyDatabaseHelper(this,"store.db",null,2);
        MyDatabaseHelper.db=myDatabaseHelper.getWritableDatabase();
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
                intent.putExtra("history","");
                intent.putExtra("userName",editText.getText().toString()==""?"black":editText.getText().toString());
                startActivity(intent);
            }
        });
        offlinehistory=(Button)findViewById(R.id.button2);
        offlinehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("offlinehistory");
                startActivity(intent);
            }
        });
    }
}
