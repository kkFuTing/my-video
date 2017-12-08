package com.edu.bunz.ftapp;

/**
 * Created by asuss on 2017/10/17.
 */

public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);
}
