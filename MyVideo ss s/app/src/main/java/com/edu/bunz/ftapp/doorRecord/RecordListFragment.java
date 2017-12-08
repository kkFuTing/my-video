package com.edu.bunz.ftapp.doorRecord;

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

public class RecordListFragment extends Fragment {

    private RecyclerView mRecordRecycleView;
    private  RecordAdapter mAdapter;
    private Button mAddButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_record_list,container,false);

        mRecordRecycleView = (RecyclerView) view
                .findViewById(R.id.record_recycler_view);
        mRecordRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAddButton= (Button) view.findViewById(R.id.add_crime_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCrime();
            }
        });
        updateUI();
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class RecordHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private  Data mData;
        private TextView mDateTextView;

        //构造方法
        public RecordHolder(LayoutInflater inflater, ViewGroup parent){
            //先实例化布局，然后传给 super方法  super(参数1，参数2);   这种对应着父类ViewHolder中的有参构造方法
            super(inflater.inflate(R.layout.list_item_record,parent,false));
            itemView.setOnClickListener(this);
            mDateTextView = (TextView) itemView.findViewById(R.id.door_record_date);
        }

        public void bind(Data data){
            mData = data;
            mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy", data.getDate()));
        }
        @Override
        public  void  onClick(View view){

            Intent intent =  RecordActivity.newIntent(getActivity(),mData.getId());
            startActivity(intent);
        }
    }
    private class RecordAdapter extends RecyclerView.Adapter<RecordHolder>{
        private List<Data> mDatas;
        public RecordAdapter(List<Data> datas){
            mDatas = datas;
        }
        @Override
        public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return   new RecordHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(RecordHolder holder, int position) {
            Data data = mDatas.get(position);
            holder.bind(data);

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

    private void addCrime() {
        Data data = new Data();
        DataLab.get(getActivity()).addData(data);

        updateUI();
    }
    private void updateUI(){
        DataLab crimeLab = DataLab.get(getActivity());
        List<Data> datas = crimeLab.getDatas();
        //反转数据
        LinearLayoutManager layout= new LinearLayoutManager(getActivity());
        layout.setStackFromEnd(true);
        layout.setReverseLayout(true);
        mRecordRecycleView.setLayoutManager(layout);


        mAdapter = new RecordAdapter(datas);
        mRecordRecycleView.setAdapter(mAdapter);
    }
}
