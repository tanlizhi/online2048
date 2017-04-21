package com.project.fuyou.battle2048;

/**
 * Created by fuyou on 2017/4/17.
 */

public class History {
    private int id;
    private String userName;
    private String score;
    private String step;
    private String history;
    private String createTime;
    private int isDel;
    public History(int id,String userName,String score,String step,String history,String createTime,int isDel){
        this.id=id;
        this.userName=userName;
        this.score=score;
        this.step=step;
        this.history=history;
        this.createTime=createTime;
        this.isDel=isDel;
    }

    public int getId() {
        return id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getScore() {
        return score;
    }

    public String getStep() {
        return step;
    }

    public String getUserName() {
        return userName;
    }

    public int getIsDel() {
        return isDel;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}
