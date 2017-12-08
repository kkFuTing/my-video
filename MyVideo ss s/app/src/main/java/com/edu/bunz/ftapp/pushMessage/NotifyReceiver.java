package com.edu.bunz.ftapp.pushMessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import com.edu.bunz.ftapp.HomeFragment;
import com.edu.bunz.ftapp.ImageSlideshow.ImageSlideshow;
import com.edu.bunz.ftapp.ImagesUri;
import com.edu.bunz.ftapp.MainActivity;
import com.edu.bunz.ftapp.pushMessage.message.MessageData;
import com.edu.bunz.ftapp.pushMessage.message.MessageDataLab;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by asuss on 2017/12/3.
 */

public class NotifyReceiver extends BroadcastReceiver {
    private static final String TAG = "NotifyReceiver";
    private ImageSlideshow imageSlideshow;
    public NotifyReceiver() {
    }



    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//
            //   自定义消息不会展示在通知栏，完全要开发者写代码去处理 
            String  content  =  bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String  extra  =  bundle.getString(JPushInterface.EXTRA_EXTRA);

            System.out.println("收到了自定义消息@@消息内容是:"+  content);
            System.out.println("收到了自定义消息@@消息extra是"+  extra);




            ImagesUri imagesUri = new ImagesUri();
            //**************½解析推送过来的json数据并存放到集合中 begin******************
            Map<String,  Object> map  =  new HashMap<String,  Object>();
            JSONObject jsonObject;
            try  {
                jsonObject  =  new  JSONObject(extra);
                imagesUri.setImagesUri1(jsonObject.getString("imageUri1"));
                imagesUri.setImagesUri2(jsonObject.getString("imageUri2"));
                imagesUri.setImagesUri3(jsonObject.getString("imageUri3"));
                imagesUri.setImagesUri4(jsonObject.getString("imageUri4"));
                imagesUri.setImagesUri5(jsonObject.getString("imageUri5"));



                imagesUri.setTitle1(jsonObject.getString("Title1"));
                imagesUri.setTitle2(jsonObject.getString("Title2"));
                imagesUri.setTitle3(jsonObject.getString("Title3"));
                imagesUri.setTitle4(jsonObject.getString("Title4"));
                imagesUri.setTitle5(jsonObject.getString("Title5"));





                Log.d("NotifyReceiver--a ??有吗",imagesUri.getImagesUri1());
                Log.d("NotifyReceiver--a ??有吗",imagesUri.getImagesUri2());
                Log.d("NotifyReceiver--a ??有吗",imagesUri.getImagesUri3());



            }  catch  (JSONException e)  {
                //  TODO  Auto-generated  catch  block
                e.printStackTrace();
            }
            System.out.println("收到了自定义消息@@消息extra1是"+  map);


        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了通知");
            // 在这里可以做些统计，或者做些其他工作


            String  content  =  bundle.getString(JPushInterface.EXTRA_ALERT);
            MessageData d = new MessageData();
            d.setTtitle(content);
            MessageDataLab.get(context).addData(d);
            System.out.println("创建成功？");
            //me
            String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为t
            Intent i = new Intent(context, MainActivity.class);  //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }  else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
