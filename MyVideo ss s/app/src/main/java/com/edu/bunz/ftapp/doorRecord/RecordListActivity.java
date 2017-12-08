package com.edu.bunz.ftapp.doorRecord;

import android.support.v4.app.Fragment;

import com.edu.bunz.ftapp.SingleFragmentActivity;

/**
 * Created by asuss on 2017/11/28.
 */

public class RecordListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RecordListFragment();
    }

}

