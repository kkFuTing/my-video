package com.edu.bunz.ftapp.doorRecord;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.edu.bunz.ftapp.SingleFragmentActivity;

import java.util.UUID;


public class RecordActivity extends SingleFragmentActivity {
;
    public static  final String EXTRA_DOOR_DATA_ID=
            "com.edu.bunz.notification.doorData_id";
    @Override
    protected Fragment createFragment() {
        UUID doorReData = (UUID) getIntent()
                .getSerializableExtra(EXTRA_DOOR_DATA_ID);
        return RecordFragment.newInstance(doorReData);
    }
    //    p169
    public static Intent newIntent(Context packageContext, UUID dataId){
        Intent intent = new Intent(packageContext,RecordActivity.class);
        intent.putExtra(EXTRA_DOOR_DATA_ID,dataId);
        return intent;
    }


}
