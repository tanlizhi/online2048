package com.project.fuyou.battle2048.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by fuyou on 2017/4/12.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase db;
    public static final String CREATE_BOOK="create table Book("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price real,"
            +"pages integer,"
            +"name text)";
    public static final String CREATE_REM="CREATE TABLE IF NOT EXISTS history ("
            +"id integer primary key autoincrement,"
            +"userName text,"
            +"score text,"
            +"steps integer,"
            +"history text,"
            +"createTime text,"
            +"isDel integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REM);
        MyDatabaseHelper.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        MyDatabaseHelper.db=db;
    }
}
