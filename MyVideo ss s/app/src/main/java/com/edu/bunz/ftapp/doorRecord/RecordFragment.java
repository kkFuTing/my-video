package com.edu.bunz.ftapp.doorRecord;

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

public class RecordFragment extends Fragment {
    private Data  mData;
    private TextView mDoorReDateTextView;
    private static  final String ARG_DOOR_RECORD_ID=
            "com.edu.bunz.notification.doorData_id";

    public  static RecordFragment newInstance(UUID doorId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DOOR_RECORD_ID,doorId);
        RecordFragment fragment= new RecordFragment();
        fragment.setArguments(args);
        return  fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = new Data();

        UUID dataId = (UUID) getArguments().getSerializable((ARG_DOOR_RECORD_ID));
        mData = DataLab.get(getActivity()).getData(dataId);

    }

    @Nullable
    @Override
    //显示界面
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //1.传入布局资源id；2视图的父视图；3是否将生成的视图给父视图
        View v = inflater.inflate(R.layout.fragment_record,container,false);
        mDoorReDateTextView = (TextView) v.findViewById(R.id.record_date);
        mDoorReDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy  a  hh :mm : ss", mData.getDate()));
        return  v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
