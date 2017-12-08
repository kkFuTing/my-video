package com.edu.bunz.ftapp.pushMessage.message;

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

public class MessageDataListFragment extends Fragment {

    private RecyclerView mRecordRecycleView;
    private  RecordAdapter mAdapter;
    private Button mAddButton;
    //2 回调
    private Callbacks mCallbacks;
    public interface  Callbacks{
        void onDataSelected(MessageData messageData);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_message_list,container,false);

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
    }

    private class RecordHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private MessageData mMessageData;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mDetailDateTextView;

        //构造方法
        public RecordHolder(LayoutInflater inflater, ViewGroup parent){
            //先实例化布局，然后传给 super方法  super(参数1，参数2);   这种对应着父类ViewHolder中的有参构造方法
            super(inflater.inflate(R.layout.list_handle_message,parent,false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.receive_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.receive_date);
            mDetailDateTextView = (TextView) itemView.findViewById(R.id.receive_time);
        }

        public void bind(MessageData messageData){
            mMessageData = messageData;
            mTitleTextView.setText(mMessageData.getTtitle());
            mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy", messageData.getDate()));
            mDetailDateTextView.setText(DateFormat.format("a  hh :mm : ss", messageData.getDate()));


        }
        @Override
        public  void  onClick(View view){

//            Intent intent =  RecordActivity.newIntent(getActivity(),mMessageData.getId());
//            startActivity(intent);
        }
    }
    private class RecordAdapter extends RecyclerView.Adapter<RecordHolder>{
        private List<MessageData> mMessageDatas;
        public RecordAdapter(List<MessageData> messageDatas){
            mMessageDatas = messageDatas;
        }
        @Override
        public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return   new RecordHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(RecordHolder holder, int position) {
            MessageData messageData = mMessageDatas.get(position);
            holder.bind(messageData);

        }

        @Override
        public int getItemCount() {
            return mMessageDatas.size();
        }
    }

    private void addCrime() {
        MessageData messageData = new MessageData();
        MessageDataLab.get(getActivity()).addData(messageData);

        updateUI();
    }
    private void updateUI(){
        MessageDataLab crimeLab = MessageDataLab.get(getActivity());
        List<MessageData> messageDatas = crimeLab.getDatas();
        mAdapter = new RecordAdapter(messageDatas);
        mRecordRecycleView.setAdapter(mAdapter);
    }
}
