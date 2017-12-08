package com.edu.bunz.ftapp.pushMessage.message;

import java.util.Date;
import java.util.UUID;

/**
 * Created by asuss on 2017/9/23.
 */

public class MessageData {
    public UUID mId;
    private String mTtitle;
    private Date mDate;


    public MessageData(){
        this (UUID.randomUUID());
    }

    public MessageData(UUID id){
        mId = id;
        mDate = new Date();
    }

    public String getTtitle() {
        return mTtitle;
    }

    public UUID getId() {
        return mId;
    }


    public void setTtitle(String ttitle) {
        this.mTtitle = ttitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }



    public String getPhotoFilename(){
        return "IMG_" + getId().toString() + ".jpg";
    }


}
