package com.edu.bunz.ftapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends  AppCompatActivity{

    private TextView mTextMessage;
    FragmentManager fm= getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.fragment_content);
//    @Override
//    protected Fragment createFragment(){
//        return new MyMesFragment();
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                  //  mTextMessage.setText(R.string.title_home);
                    //添加fragement



                        fragment = new HomeFragment();
                        fm.beginTransaction().replace(R.id.fragment_content,fragment)
                                .commit();


                    return true;
                case R.id.navigation_dashboard:
                  //  mTextMessage.setText(R.string.title_key);
                    //添加fragement


                        fragment = new KeyFragment();
                        fm.beginTransaction().replace(R.id.fragment_content,fragment)
                                .commit();

                    return true;
                case R.id.navigation_notifications:
              //      mTextMessage.setText(R.string.title_mymes);


                        fragment = new MyMesFragment();
                        fm.beginTransaction().replace(R.id.fragment_content,fragment)
                                .commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        Intent Service = new Intent(this,MyService.class);
//        暂时不开启后台服务；
//        startService(Service);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //添加fragement

        if (fragment == null){
            fragment = new HomeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_content,fragment)
                    .commit();
        }


    }

}
