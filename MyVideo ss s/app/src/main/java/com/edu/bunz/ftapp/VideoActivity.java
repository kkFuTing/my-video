package com.edu.bunz.ftapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONObject;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener{
    private VideoView videoView ;
    private Button mYesBtn;
    private Button mNoBtn;
    private  String sendMes;
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //本地的视频  需要在手机SD卡根目录添加一个 fl1234.mp4 视频
        String videoUrl1 = Environment.getExternalStorageDirectory().getPath()+"/movie.mp4" ;
        //网络视频
        String videoUrl2 = Utils.videoUrl ;
        Uri uri = Uri.parse( videoUrl2 );
        videoView = (VideoView)this.findViewById(R.id.videoView );
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());
        //设置视频路径
        videoView.setVideoURI(uri);
        // videoView.setVideoPath(videoUrl1);
        //开始播放视频
        Log.d("MainSActivity","ID is "+LoginActivity.muserId);
        Log.d("MainSActivity","ID is "+LoginActivity.mpassword);
        mYesBtn = (Button) findViewById(R.id.yes_btn);
        mNoBtn = (Button)  findViewById(R.id.no_btn);
        responseText = (TextView) findViewById(R.id.response_text);
        mNoBtn.setOnClickListener(this);
        mYesBtn.setOnClickListener(this);
        videoView.start();
    }
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(VideoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }

    }

    //选择是否给来访者开门的按钮
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yes_btn:
                sendMes = "T";
                selectURL(sendMes);
                break;
            case R.id.no_btn:
                sendMes = "F";
                selectURL(sendMes);
                break;
            default:
                break;

        }
    }

    //选择需要请求的网络
    private void selectURL(String mes){
        String url = "http://vediotrans.applinzi.com/index.php?account="+LoginActivity.muserId
                +"&password="+LoginActivity.mpassword+"&setLockState="+mes;
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

    //在text上显示网络上的获取回来的内容
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
    private void parseJSONWithJSONObject(String jsonData){
        try{

            JSONObject jsonObject = new JSONObject(jsonData);
            String setLockState = jsonObject.getString("setLockState");

            Log.d("MainSActivity","setLockState is "+setLockState);
            if ("success".equals(setLockState)){
               // showResponse("您已经成功给您的来访者开门！");
            }
            Intent Service = new Intent(this,MyService.class);
            startService(Service);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
