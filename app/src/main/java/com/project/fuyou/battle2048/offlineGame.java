package com.project.fuyou.battle2048;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.project.fuyou.battle2048.database.DatebaseOperate;
import com.project.fuyou.battle2048.database.MyDatabaseHelper;
import com.project.fuyou.battle2048.view.Offline2048Layout;

import java.io.IOException;
import java.util.Date;

public class offlineGame extends AppCompatActivity {

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Offline2048Layout offline2048Layout;
    Intent intent;
    String zqqj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game);
        offline2048Layout=(Offline2048Layout) findViewById(R.id.offlinegameboard);
        intent=getIntent();
        zqqj=intent.getStringExtra("history");
        offline2048Layout.resumeBoard(zqqj);
    }

    /**
     * 返回主界面之前将棋盘情况保存至数据库
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            String str=offline2048Layout.getBoard();
            Date date=new Date();
            String createTime=date.toString();
            History history=new History(0,intent.getStringExtra("userName"),offline2048Layout.score+"",""+offline2048Layout.steps,str,createTime,0);
            DatebaseOperate.insert(MyDatabaseHelper.db,history);
            finish();
            return true;
        }
        return false;
    }
}
