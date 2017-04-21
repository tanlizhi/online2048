package com.project.fuyou.battle2048.view;

/**
 * Created by fuyou on 2017/4/12.
 */

public class HistoryItem {
    public String userName;
    public String history;
    public String score;
    public String steps;
    public String createTime;
    public HistoryItem(String userName,String history,String score,String steps,String createTime){
        this.userName=userName;
        this.history=history;
        this.score=score;
        this.steps=steps;
        this.createTime=createTime;
    }
}
