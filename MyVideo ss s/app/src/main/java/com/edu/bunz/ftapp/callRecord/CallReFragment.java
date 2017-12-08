package com.edu.bunz.ftapp.callRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.bunz.ftapp.R;

import java.util.UUID;


/**
 * Created by asuss on 2017/11/28.
 */

public class CallReFragment extends Fragment {
    private CallReData mCallReData;

    private TextView mDateTextView;
    private  TextView mDeteilDataTextView;
    private  TextView mPhoneTextView;
    private static  final String ARG_POLICE_RECORD_ID=
            "com.edu.bnuz.recorddemo.policeData_id";

    public  static CallReFragment newInstance(UUID policeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_POLICE_RECORD_ID,policeId);
        CallReFragment fragment= new CallReFragment();
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallReData = new CallReData();

////        获取extra 数据并取得其对象
//        UUID dataId = (UUID) getActivity().getIntent()
//                .getSerializableExtra(CallReActivity.EXTRA_POLICE_DATA_ID);

        UUID dataId = (UUID) getArguments().getSerializable((ARG_POLICE_RECORD_ID));
        mCallReData = CallReDataLab.get(getActivity()).getData(dataId);

    }

    @Nullable
    @Override
    //显示图片
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //1.传入布局资源id；2视图的父视图；3是否将生成的视图给父视图
        View v = inflater.inflate(R.layout.fragment_call_record,container,false);
        mDateTextView =(TextView) v.findViewById(R.id.call_date);
        mDeteilDataTextView = (TextView) v.findViewById(R.id.call_time);
        mPhoneTextView = (TextView) v.findViewById(R.id.record_call);

        mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy", mCallReData.getDate()));
        mDeteilDataTextView.setText(DateFormat.format("a  hh :mm : ss", mCallReData.getDate()));
        mPhoneTextView.setText(mCallReData.getPhone());


        return  v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
