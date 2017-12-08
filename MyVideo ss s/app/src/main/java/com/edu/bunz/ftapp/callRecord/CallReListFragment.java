package com.edu.bunz.ftapp.callRecord;

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

public class CallReListFragment extends Fragment {

    private RecyclerView mRecordRecycleView;
    private  RecordAdapter mAdapter;
    private Button mAddButton;

    private TextView mPhoneTextView;
    //2 回调
    private Callbacks mCallbacks;
    public interface  Callbacks{
        void onDataSelected(CallReData CallReData);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_call_record_list,container,false);

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
        private CallReData mCallReData;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        //构造方法
        public RecordHolder(LayoutInflater inflater, ViewGroup parent){
            //先实例化布局，然后传给 super方法  super(参数1，参数2);   这种对应着父类ViewHolder中的有参构造方法
            super(inflater.inflate(R.layout.list_item_call_record,parent,false));
            itemView.setOnClickListener(this);
            mDateTextView = (TextView) itemView.findViewById(R.id.call_record_date);
            mPhoneTextView = (TextView) itemView.findViewById(R.id.record_call_list);
        }

        public void bind(CallReData CallReData){
            mCallReData = CallReData;
//            mDateTextView.setText(mCallReData.getDate().toString());
            mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy", mCallReData.getDate()));
            mPhoneTextView.setText(mCallReData.getPhone());
        }
        @Override
        public  void  onClick(View view){
//            Toast.makeText(getActivity(),mCallReData.getTtitle()+"clicked!",Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(getActivity(),CallReActivity.class);
            Intent intent =  CallReActivity.newIntent(getActivity(), mCallReData.getId());
            startActivity(intent);
        }
    }
    private class RecordAdapter extends RecyclerView.Adapter<RecordHolder>{
        private List<CallReData> mCallReDatas;
        public RecordAdapter(List<CallReData> callReDatas){
            mCallReDatas = callReDatas;
        }
        @Override
        public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return   new RecordHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(RecordHolder holder, int position) {
            CallReData CallReData = mCallReDatas.get(position);
            holder.bind(CallReData);

        }

        @Override
        public int getItemCount() {
            return mCallReDatas.size();
        }
    }

    private void addPoliceData() {
        CallReData CallReData = new CallReData();
//        p218
        CallReData.setPhone("13798970921");
        CallReDataLab.get(getActivity()).addData(CallReData);



        updateUI();
    }
    private void updateUI(){
        CallReDataLab crimeLab = CallReDataLab.get(getActivity());
        List<CallReData> callReDatas = crimeLab.getDatas();

        //反转数据
        LinearLayoutManager layout= new LinearLayoutManager(getActivity());
        layout.setStackFromEnd(true);
        layout.setReverseLayout(true);
        mRecordRecycleView.setLayoutManager(layout);

        mAdapter = new RecordAdapter(callReDatas);
        mRecordRecycleView.setAdapter(mAdapter);
    }
}
