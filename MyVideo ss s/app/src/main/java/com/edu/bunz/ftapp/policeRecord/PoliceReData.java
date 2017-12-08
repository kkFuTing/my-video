package com.edu.bunz.ftapp.policeRecord;

import java.util.Date;
import java.util.UUID;

/**
 * Created by asuss on 2017/9/23.
 */

public class PoliceReData {
    public UUID mId;
    private String mPhone;
    private Date mDate;


    public PoliceReData(){
        this (UUID.randomUUID());
    }

    public PoliceReData(UUID id){
        mId = id;
        mDate = new Date();

    }

    public String getPhone() {
        return mPhone;
    }

    public UUID getId() {
        return mId;
    }


    public void setPhone(String phone) {
        this.mPhone =phone;
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
