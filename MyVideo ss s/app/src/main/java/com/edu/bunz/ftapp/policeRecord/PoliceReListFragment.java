package com.edu.bunz.ftapp.policeRecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edu.bunz.ftapp.R;

import java.util.List;

/**
 * Created by asuss on 2017/11/28.
 */

public class PoliceReListFragment extends Fragment {

    private RecyclerView mRecordRecycleView;
    private  RecordAdapter mAdapter;
    private Button mAddButton;

    private TextView mPhoneTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_police_record_list,container,false);

        mRecordRecycleView = (RecyclerView) view
                .findViewById(R.id.record_recycler_view);
        mRecordRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAddButton= (Button) view.findViewById(R.id.add_crime_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPoliceData();
            }
        });
        updateUI();
        return view;
    }

    private class RecordHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private PoliceReData mPoliceReData;
        private TextView mDateTextView;

        //构造方法
        public RecordHolder(LayoutInflater inflater, ViewGroup parent){
            //先实例化布局，然后传给 super方法  super(参数1，参数2);   这种对应着父类ViewHolder中的有参构造方法
            super(inflater.inflate(R.layout.list_item_police_record,parent,false));
            itemView.setOnClickListener(this);
            mDateTextView = (TextView) itemView.findViewById(R.id.policeCall_record_date);
            mPhoneTextView = (TextView) itemView.findViewById(R.id.record_phone);
        }

        public void bind(PoliceReData PoliceReData){
            mPoliceReData = PoliceReData;
//            mDateTextView.setText(mPoliceReData.getDate().toString());
            mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy", mPoliceReData.getDate()));
            mPhoneTextView.setText(mPoliceReData.getPhone());
        }
        @Override
        public  void  onClick(View view){

            Intent intent =  PoliceReActivity.newIntent(getActivity(), mPoliceReData.getId());
            startActivity(intent);
        }
    }
    private class RecordAdapter extends RecyclerView.Adapter<RecordHolder>{
        private List<PoliceReData> mPoliceReDatas;
        public RecordAdapter(List<PoliceReData> PoliceReDatas){
            mPoliceReDatas = PoliceReDatas;
        }
        @Override
        public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return   new RecordHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(RecordHolder holder, int position) {
            PoliceReData PoliceReData = mPoliceReDatas.get(position);
            holder.bind(PoliceReData);

        }

        @Override
        public int getItemCount() {
            return mPoliceReDatas.size();
        }
    }

    private void addPoliceData() {
        PoliceReData PoliceReData = new PoliceReData();
//        p218
        PoliceReData.setPhone("13798970921");
        PoliceReDataLab.get(getActivity()).addData(PoliceReData);



        updateUI();
    }
    private void updateUI(){
        PoliceReDataLab crimeLab = PoliceReDataLab.get(getActivity());
        List<PoliceReData> PoliceReDatas = crimeLab.getDatas();

        //反转数据
        LinearLayoutManager layout= new LinearLayoutManager(getActivity());
        layout.setStackFromEnd(true);
        layout.setReverseLayout(true);
        mRecordRecycleView.setLayoutManager(layout);

        mAdapter = new RecordAdapter(PoliceReDatas);
        mRecordRecycleView.setAdapter(mAdapter);
    }
}
