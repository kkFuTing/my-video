package com.edu.bunz.ftapp.policeRecord;

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

public class PoliceReFragment extends Fragment {
    private PoliceReData mPoliceReData;

    private TextView mDateTextView;
    private  TextView mDeteilDataTextView;
    private  TextView mPhoneTextView;
    private static  final String ARG_POLICE_RECORD_ID=
            "com.edu.bunz.notification.policeData_id";

    public  static PoliceReFragment newInstance(UUID policeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_POLICE_RECORD_ID,policeId);
        PoliceReFragment fragment= new PoliceReFragment();
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPoliceReData = new PoliceReData();

////        获取extra 数据并取得其对象
//        UUID dataId = (UUID) getActivity().getIntent()
//                .getSerializableExtra(PoliceReActivity.EXTRA_POLICE_DATA_ID);

        UUID dataId = (UUID) getArguments().getSerializable((ARG_POLICE_RECORD_ID));
        mPoliceReData = PoliceReDataLab.get(getActivity()).getData(dataId);

    }

    @Nullable
    @Override
    //显示图片
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //1.传入布局资源id；2视图的父视图；3是否将生成的视图给父视图
        View v = inflater.inflate(R.layout.fragment_police_record,container,false);
        mDateTextView =(TextView) v.findViewById(R.id.police_record_date);
        mDeteilDataTextView = (TextView) v.findViewById(R.id.police_record_detail_date);
        mPhoneTextView = (TextView) v.findViewById(R.id.record_phone);

        mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy", mPoliceReData.getDate()));
        mDeteilDataTextView.setText(DateFormat.format("a  hh :mm : ss",mPoliceReData.getDate()));
        mPhoneTextView.setText(mPoliceReData.getPhone());


        return  v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
