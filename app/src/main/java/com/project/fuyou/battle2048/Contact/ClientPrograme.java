package com.project.fuyou.battle2048.Contact;

/**
 * Created by fuyou on 2017/4/1.
 */

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.project.fuyou.battle2048.view.GetCommand;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientPrograme {
    public static final String ip = "123.207.140.106";
//    public static final String ip = "101.28.124.20";
    public static final int port = 10086;
    private Socket socket=null;
    private OutputStream out=null;
    public GetCommand getCommand;
    public void toGetMessage(GetCommand getCommand){
        this.getCommand=getCommand;
    }

    public Socket getSocket() {
        return socket;
    }

    public ClientPrograme() {
        try {
//            socket=new Socket(ClientPrograme.ip, ClientPrograme.port); //创建socket连接
//            getHandle();//进行信息接收

            new Thread(){
                @Override
                public void run() {
                    try {
                        socket=new Socket(ClientPrograme.ip, ClientPrograme.port); //创建socket连接
                        getHandle();//进行信息接收
                    }catch (Exception e){}
                }
            }.start();
        } catch(Exception e) {
        }
    }

    /**
     * 接收信息
     * @throws Exception
     */
    public void getHandle() throws Exception{
//        Thread thread=new Thread(new GetM(socket));
//        thread.start();
        while(true){
            try {
                InputStream in = socket.getInputStream();//获取socket输入流
                byte[] buf = new byte[1024];
                int len = in.read(buf);//读取字节信息
                String text=new String(buf,0,len,"utf-8");//获取到内容
                if (getCommand!=null){
                    getCommand.getMessage(text);
                }
            }catch (Exception e){

            }
        }
    }

    /**
     * 监听以获得另一个用户发送来的信息，加锁以放并发
     * @throws IOException
     */
    class GetM implements Runnable{
        private Socket s;

        public GetM(Socket s){
            this.s=s;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            strOpe(s);
        }

        public synchronized boolean strOpe(Socket s){//由于服务器和多个客户端公用此函数，所以服务器端加锁以应对并发
            while(true){
                try {
                    InputStream in = s.getInputStream();//获取socket输入流
                    byte[] buf = new byte[1024];
                    int len = in.read(buf);//读取字节信息
                    String text=new String(buf,0,len,"utf-8");//获取到内容
                    if (getCommand!=null){
                        getCommand.getMessage(text);
                    }
                }catch (Exception e){

                }
            }
        }
    }

    /**
     * 向服务器发送信息
     * @param msg 所要发送的信息
     * @throws IOException
     */
    public void sendHandle(String msg) throws IOException{
//        if (socket!=null){
//            Thread thread=new Thread(new SendM(socket,msg));
//            thread.start();
//        }
        try{
            if (socket!=null&&!socket.isClosed()){
                out=socket.getOutputStream();//获取输出流
                Log.d("out",out.toString());
                if (out!=null)
                    out.write(msg.getBytes());//从输出流输出
                if("exit:exit".equals(msg.trim()) || "exit:end".equals(msg.trim()))//如果输出内容为exit则退出聊天室
                    socket.close();//关闭socket
            }
        }catch (IOException e){

        }
    }
    class SendM implements Runnable{
        private String message;
        private Socket s;

        public SendM(Socket s,String str){
            this.s=s;
            this.message=str;
        }

        @Override
        public void run() {
            toSend(message);
        }

        public void toSend(String msg){
            try{
                if (s!=null){
                    out=s.getOutputStream();//获取输出流
                    out.write(msg.getBytes());//从输出流输出
                    if("exit:exit".equals(msg.trim()) || "exit:end".equals(msg.trim()))//如果输出内容为exit则退出聊天室
                        s.close();//关闭socket
                }
            }catch (IOException e){

            }
        }
    }
    // 定义Handler对象
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
