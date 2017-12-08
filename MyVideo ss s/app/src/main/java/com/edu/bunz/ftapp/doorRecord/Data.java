package com.edu.bunz.ftapp.doorRecord;

import java.util.Date;
import java.util.UUID;

/**
 * Created by asuss on 2017/9/23.
 */

public class Data {
    public UUID mId;
    private String mTtitle;
    private Date mDate;
    private Date mTime;
    private  boolean mSolved;
    private String mSuspect;
    private String mSuspectPN;
    private boolean mRequiresPolice;

    public Data(){
        this (UUID.randomUUID());
    }

    public Data(UUID id){
        mId = id;
        mDate = new Date();
        mTime = new Date();
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

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public String getPhotoFilename(){
        return "IMG_" + getId().toString() + ".jpg";
    }

    public String getSuspectPN() {
        return mSuspectPN;
    }

    public void setSuspectPN(String suspectPN) {
        mSuspectPN = suspectPN;
    }

    public boolean isRequiresPolice() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        mRequiresPolice = requiresPolice;
    }

}
