package com.project.fuyou.battle2048.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.fuyou.battle2048.History;

import java.util.ArrayList;

/**
 * Created by fuyou on 2017/4/17.
 */

public class DatebaseOperate {

    //插入历史纪录
    public static boolean insert(SQLiteDatabase sqLiteDatabase,History history){
        String sql="insert into history(userName,score,steps,history,createTime,isDel) values (?,?,?,?,?,0)";
        Object bindArgs[]=new Object[]{history.getUserName(),history.getScore(),history.getStep(),history.getHistory(),history.getCreateTime()};
        sqLiteDatabase.execSQL(sql,bindArgs);
        return true;
    }

    //删除历史纪录
    public static boolean delete(SQLiteDatabase sqLiteDatabase,int id){
        String sql = "update history set isDel=1 where id=?";
        Object bindArgs[] = new Object[] { id };
        sqLiteDatabase.execSQL(sql, bindArgs);
        return true;
    }

    //查找某个记录
    public static History search(SQLiteDatabase sqLiteDatabase,int id){
        History history=null;
        String sql = "select * from history where id=? and isDel=0";
        String[] selectionArgs = new String[] { id+"" };
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            // 如果有记录，则把查到的值填充这个记录实体
            history = new History(cursor.getInt(cursor.getColumnIndex("id"))
                    ,cursor.getString(cursor.getColumnIndex("userName"))
                    ,cursor.getString(cursor.getColumnIndex("score"))
                    ,cursor.getString(cursor.getColumnIndex("steps"))
                    ,cursor.getString(cursor.getColumnIndex("history"))
                    ,cursor.getString(cursor.getColumnIndex("createTime"))
                    ,cursor.getInt(cursor.getColumnIndex("isDel")));
        }
        return history;// 返回一个用户给前台
    }

    //查找所有记录
    public static ArrayList<History> searchAll(SQLiteDatabase sqLiteDatabase){
        ArrayList<History> historiesArrayList = new ArrayList<History>();
        String sql = "select * from history where isDel=0";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        // 游标从头读到尾
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex("isDel")) == 0) {
                History history = new History(cursor.getInt(cursor.getColumnIndex("id"))
                        ,cursor.getString(cursor.getColumnIndex("userName"))
                        ,cursor.getString(cursor.getColumnIndex("score"))
                        ,cursor.getString(cursor.getColumnIndex("steps"))
                        ,cursor.getString(cursor.getColumnIndex("history"))
                        ,cursor.getString(cursor.getColumnIndex("createTime"))
                        ,cursor.getInt(cursor.getColumnIndex("isDel")));
                historiesArrayList.add(0,history);
            }
        }
        return historiesArrayList;

    }


}
