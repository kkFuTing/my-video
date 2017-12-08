package com.edu.bunz.ftapp.callRecord;

import android.support.v4.app.Fragment;

import com.edu.bunz.ftapp.SingleFragmentActivity;

/**
 * Created by asuss on 2017/11/28.
 */

public class CallReListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CallReListFragment();
    }
//    @Override
//    public void onDataSelected(CallReData crime){
//        if (findViewById(R.id.detail_fragment_container) == null){
////            Intent intent = CrimePagerActivity.newIntent(this,crime.getId());
////            startActivity(intent);
//
//
//            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
//            intent.putExtra(CrimeListFragment.SAVED_SUBTITLE_VISIBLE,  ((CrimeListFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.fragment_container)).mSubtitleVisible);
//            startActivityForResult(intent, CrimeListFragment.START_CRIME_DETAILS);
//        }else {
//
//            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.detail_fragment_container, newDetail)
//                    .commit();
//        }
//
//    }
}

