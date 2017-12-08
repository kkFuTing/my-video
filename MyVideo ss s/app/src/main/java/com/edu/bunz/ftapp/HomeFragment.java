package com.edu.bunz.ftapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.edu.bunz.ftapp.ImageSlideshow.ImageSlideshow;
import com.edu.bunz.ftapp.callRecord.CallReListActivity;
import com.edu.bunz.ftapp.doorRecord.RecordListActivity;
import com.edu.bunz.ftapp.policeRecord.PoliceReListActivity;
import com.edu.bunz.ftapp.pushMessage.message.HandleActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private  String HomeFragment;
    private Button mRecordButton;
    private Button mCallRecordButton;
    private Button mCallPoliceButton;
    private Button mPushMessageButton;

    //
    private ImageSlideshow imageSlideshow;
    private List<String> imageUrlList;
    private List<String> titleList;
    private ImagesUri mImagesUri;

    private String imagesUri1,imagesUri2,imagesUri3,imagesUri4,imagesUri5,imagesUri6
            ,tiltle1,title2,title3,title4,title5,title6;
    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImagesUri = new ImagesUri();


    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(HomeFragment,"onCreateView");

        imageSlideshow = (ImageSlideshow) v.findViewById(R.id.is_gallery);
        imageUrlList = new ArrayList<>();
        titleList = new ArrayList<>();
        if (mImagesUri.getImagesUri1() == null){
            mImagesUri.init();
        }
        // 初始化数据
        initData();

        // 为ImageSlideshow设置数据
        imageSlideshow.setDotSpace(12);
        imageSlideshow.setDotSize(12);
        imageSlideshow.setDelay(3000);
        imageSlideshow.setOnItemClickListener(new ImageSlideshow.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Uri uri = Uri.parse("http://www.baidu.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                switch (position) {
                    case 0:
                        startActivity(intent);
                        break;
                    case 1:
                        startActivity(intent);
                        break;
                    case 2:
                        startActivity(intent);
                        break;
                    case 3:
                        startActivity(intent);
                        break;
                    case 4:
                        startActivity(intent);
                        break;
                }
            }
        });
        imageSlideshow.commit();





//        按键监听以及事件处理begin
        mRecordButton = (Button) v.findViewById(R.id.doorRecord_Button);
       mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RecordListActivity.class);
                startActivity(intent);
            }
        });

        mCallRecordButton = (Button) v.findViewById(R.id.call_Record_Button);
        mCallRecordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),CallReListActivity.class);
                startActivity(intent);
            }
        });

        mCallPoliceButton = (Button) v.findViewById(R.id.call_the_police_record);
        mCallPoliceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),PoliceReListActivity.class);
                startActivity(intent);
            }
        });

        mPushMessageButton = (Button) v.findViewById(R.id.push_message);
        mPushMessageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HandleActivity.class);
                startActivity(intent);
            }
        });

        //        按键监听以及事件处理end
        return v;
    }
    /**
     * 初始化数据
     */
//    private void initData() {
//        String[] imageUrls = {mImagesUri.getImagesUri1(),mImagesUri.getImagesUri2()
//                ,mImagesUri.getImagesUri3(),mImagesUri.getImagesUri4(),mImagesUri.getImagesUri5()};
//        String[] titles = {mImagesUri.getTitle1(),mImagesUri.getTitle2(),mImagesUri.getTitle3()
//                ,mImagesUri.getTitle4(),mImagesUri.getTitle5()};
//        for (int i = 0; i < 5; i++) {
//            imageSlideshow.addImageTitle(imageUrls[i], titles[i]);
//        }
//    }
    private void initData() {
        String[] imageUrls = {
                "http://pic3.zhimg.com/b5c5fc8e9141cb785ca3b0a1d037a9a2.jpg",
                "http://sinacloud.net/dianzixiehui/IMG_0992.JPG?KID=sina,v1tsqef1pjeNQ8LPOeO1&Expires=1512533831&ssig=%2Fe5sySFf8A",
                "http://sinacloud.net/dianzixiehui/IMG_7004.JPG?KID=sina,v1tsqef1pjeNQ8LPOeO1&Expires=1512533831&ssig=EtR4fWDxCH",
                "http://sinacloud.net/dianzixiehui/four.JPG?KID=sina,v1tsqef1pjeNQ8LPOeO1&Expires=1512534287&ssig=njUspMj42M",
                "http://sinacloud.net/dianzixiehui/IMG_9678.JPG?KID=sina,v1tsqef1pjeNQ8LPOeO1&Expires=1512533831&ssig=n6atHZLxWZ"};
        String[] titles = {"什么",
                "gooed study",
                "简单",
                "？？？？？？？",
                "沉迷"};
        for (int i = 0; i < 5; i++) {
            imageSlideshow.addImageTitle(imageUrls[i], titles[i]);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("HomeFragment","执行了吗");
    }

    @Override
    public void onDestroyView() {
        imageSlideshow.releaseResource();
        super.onDestroyView();
    }





}
