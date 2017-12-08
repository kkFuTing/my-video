package com.edu.bunz.ftapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import org.json.JSONObject;

public class MyService extends Service {
    private  DownloadBinder mBinder = new DownloadBinder();
    private String flag = "false"; // 判断返回的值是什么
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService","startDownload executed");
        }
        public int getProgress(){
            Log.d("MyService","getProgress executed");
            return 0;
        }
    }
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("Myservice","onCreate executed");

        //创建一个前台服务，似乎是为了让他在内存不足的情况下还能继续运行
//        Intent intent = new Intent(this,MainSActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
//        Notification notification = new NotificationCompat.Builder(this)
//                .setContentTitle("云访问")
//                .setContentText("运行中")
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//                .setContentIntent(pi)
//                .build();
//        startForeground(1,notification);
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d("Myservice","onStartCommand executed");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //处理 具体的逻辑
              String url = "http://vediotrans.applinzi.com/index.php?setVisitState=T&doorPlate=001";
                while("false".equals(flag)) {
                    try {
                        Log.d("Myservice", "睡眠");
                        //每个10秒向服务器发送一次请求 
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Utils.sendHttpRequest(url, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            parseJSONWithJSONObject(response);
                        }

                        @Override
                        public void onError(Exception e) {
                            //  Toast.makeText(LoginActivity.this,"连接超时，请重新登录",Toast.LENGTH_SHORT);

                        }
                    });

                }
                stopSelf();
            }
        }).start();

        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("Myservice","onDestroy executed");
    }



    private void parseJSONWithJSONObject(String jsonData){
        try{



            JSONObject jsonObject = new JSONObject(jsonData);
            String lockState = jsonObject.getString("lockState");
            Log.d("Myservice","checkVisit is "+lockState);
            flag = lockState;
            if ("true".equals(lockState)){

                Intent intent = new Intent(MyService.this,VideoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                PendingIntent pi = PendingIntent.getActivity(MyService.this,0,intent,0);

                NotificationManager manager = (NotificationManager) getSystemService
                        (NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(MyService.this)
                        .setContentTitle("有访客")
                        .setContentText("快来看看是谁吧")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true )
                        .build();
                manager.notify(1,notification);


            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
