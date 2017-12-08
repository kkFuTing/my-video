package com.edu.bunz.ftapp.pushMessage.message;

import android.support.v4.app.Fragment;

import com.edu.bunz.ftapp.SingleFragmentActivity;


public class HandleActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MessageDataListFragment();
    }

}
