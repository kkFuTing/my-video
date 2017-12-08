package com.edu.bunz.ftapp.callRecord;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.edu.bunz.ftapp.SingleFragmentActivity;

import java.util.UUID;


public class CallReActivity extends SingleFragmentActivity {
    public static  final String EXTRA_POLICE_DATA_ID=
            "com.edu.bnuz.recorddemo.policeData_id";
    @Override
    protected Fragment createFragment() {
        UUID policeReData = (UUID) getIntent()
                .getSerializableExtra(EXTRA_POLICE_DATA_ID);
        return CallReFragment.newInstance(policeReData);
    }
//
//    p169
    public static Intent newIntent(Context packageContext, UUID dataId){
        Intent intent = new Intent(packageContext,CallReActivity.class);
        intent.putExtra(EXTRA_POLICE_DATA_ID,dataId);
        return intent;
    }


}
