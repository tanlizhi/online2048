package com.project.fuyou.battle2048;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.project.fuyou.battle2048.Contact.ClientPrograme;
import com.project.fuyou.battle2048.view.Battle2048Layout;
import com.project.fuyou.battle2048.view.GetCommand;
import com.project.fuyou.battle2048.view.MoveActionListener;

import java.io.IOException;

public class ChessBoard extends AppCompatActivity {

    private Battle2048Layout bl;
    private ClientPrograme clientPrograme;
    private TextView tv2;
    private ProgressDialog pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);
        clientPrograme=new ClientPrograme();
        bl=(Battle2048Layout) findViewById(R.id.gameboard);
        tv2=(TextView) findViewById(R.id.textView2);
        pb=new ProgressDialog(ChessBoard.this);
//        pb.setMessage("正在匹配旗鼓相当的对手...");
//        pb.setCancelable(true);
        pb=pb.show(this,"","正在匹配旗鼓相当的对手...",true,true,new DialogInterface.OnCancelListener(){
            @Override
            public void onCancel(DialogInterface dialog) {
                try {
                    clientPrograme.sendHandle("exit:exit");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        bl.setonFling(new MoveActionListener() {
            @Override
            public void getChangeState(String s) {
                //获取面板发生改变信息
                Toast.makeText(ChessBoard.this,s+"",Toast.LENGTH_SHORT).show();
                try {
//                    tv.setText("正在等待对手操作！");
                    if ("exit:end".equals(s)){
                        winOrFal(false);//如果自己先发送exit信息，则自己输
                    }
                    clientPrograme.sendHandle(s);//发送信息
                }catch (IOException e){}
            }
        });
        clientPrograme.toGetMessage(new GetCommand() {
            @Override
            public void getMessage(String s) {
                Bundle bundle=new Bundle();
                bundle.putString("msg",s);
                Message message=new Message();
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });

    }

    public void winOrFal(boolean flag){
        AlertDialog.Builder dialog=new AlertDialog.Builder(ChessBoard.this);
        if (flag){
            dialog.setMessage("VICTORY!");
        }else{
            dialog.setMessage("DEFEAT");
        }
        dialog.setPositiveButton("restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent("chessBoard");
                startActivity(intent);//重新开始匹配对手
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();//回到主页面
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            try {
                if (clientPrograme.getSocket()!=null)
                    clientPrograme.sendHandle("exit:exit");//发送信息
                finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    // 定义Handler对象
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();            //获取Message中发送过来的数据
            String s = bundle.getString("msg");
            String[] sstr=s.split(";");
            for (int i=0;i<sstr.length;i++){
                if (sstr[i].equals(""))
                    break;
                String[] strs=sstr[i].split(":");
                switch (strs[0]){
                    case "userName"://设置用户名,并且包含初始化信息
                        bl.initBoard();
                        Log.d("err",s);
                        pb.dismiss();
                        bl.mGame2048Items[Integer.valueOf(strs[3])][Integer.valueOf(strs[4])].setNumber(Integer.valueOf(strs[2]));
                        break;
                    case "game"://设置游戏信息
                        switch (strs[1]){
                            case "LEFT":
                                bl.action(Battle2048Layout.ACTION.LEFT);
                                break;
                            case "RIGHT":
                                bl.action(Battle2048Layout.ACTION.RIGHT);
                                break;
                            case "DOWN":
                                bl.action(Battle2048Layout.ACTION.DOWM);
                                break;
                            case "UP":
                                bl.action(Battle2048Layout.ACTION.UP);
                                break;
                            default:
                                Toast.makeText(ChessBoard.this,"Game Start!",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        Battle2048Layout.isYourTurn=true;
//                    tv.setText("轮到你的回合，请操作！");
                        if (strs.length>2)
                            bl.mGame2048Items[Integer.valueOf(strs[3])][Integer.valueOf(strs[4])].setNumber(Integer.valueOf(strs[2]));
                        break;
                    case "message"://用户聊天信息

                        break;
                    case "exit"://用户退出
                        if ("exit".equals(strs[1])){
                            winOrFal(true);//you win

                        }else if("end".equals(strs[1])){
                            winOrFal(true);//game over,exit normal
                        }
                        break;
                    default:
//                        finish();
                        break;
                }
            }

        }
    };


}
