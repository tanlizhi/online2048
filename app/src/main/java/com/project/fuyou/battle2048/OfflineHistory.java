package com.project.fuyou.battle2048;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.fuyou.battle2048.database.DatebaseOperate;
import com.project.fuyou.battle2048.database.MyDatabaseHelper;

import java.util.List;

public class OfflineHistory extends AppCompatActivity {

    ListView listView;
    private List<History> histories;
    History history;
    private MyDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_history);
        listView=(ListView)findViewById(R.id.allhistory);
        histories= DatebaseOperate.searchAll(MyDatabaseHelper.db);
//        ArrayAdapter<History> adapter=new ArrayAdapter<History>(this,R.layout.historyitem,histories);
//        listView.setAdapter(adapter);
        HistoryAdapter adapter=new HistoryAdapter(this,R.layout.historyitem,histories);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                history=histories.get(position);
                DatebaseOperate.delete(MyDatabaseHelper.db,history.getId());
                Intent intent=new Intent("offlinegame");
                intent.putExtra("id",history.getId());
                intent.putExtra("userName",history.getUserName());
                intent.putExtra("score",history.getScore());
                intent.putExtra("step",history.getStep());
                intent.putExtra("history",history.getHistory());
                startActivity(intent);
            }
        });
    }
}
