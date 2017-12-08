package com.edu.bunz.ftapp.pushMessage.message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.edu.bunz.ftapp.pushMessage.message.database.PuDataBaseHelper;
import com.edu.bunz.ftapp.pushMessage.message.database.PuDataCursorWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.edu.bunz.ftapp.pushMessage.message.database.PuDataDbSchema.DataTable.*;

/**
 * Created by asuss on 2017/9/27.
 */

public class MessageDataLab {
    private static MessageDataLab sCrimeLab;

//        private List<Crime> mCrimes;
//    private Map<UUID,Crime> mCrimes;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MessageDataLab get(Context context){

        if (sCrimeLab == null){
            sCrimeLab = new MessageDataLab(context);
        }
        return sCrimeLab;
    }
    private MessageDataLab(Context context){

        mContext =context.getApplicationContext();
        mDatabase = new PuDataBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addData(MessageData d){
        ContentValues values = getContentValues(d);
        mDatabase.insert(NAME,null,values);
    }

    public void deleteCrime(MessageData d) {
        String uuidString = d.getId().toString();
        mDatabase.delete(NAME,
                Cols.UUID + " = ?",
                new String[]{uuidString}
        );

    }

    public  List<MessageData> getDatas(){
        List<MessageData> crimes = new ArrayList<>();

        PuDataCursorWrapper cursor = queryCrimes(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getData());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return crimes;
    }

    public MessageData getData(UUID id){
        PuDataCursorWrapper cursor = queryCrimes(
                Cols.UUID  + " = ?",
                new String[] {id.toString()}
        );
        try {
            if (cursor.getCount() == 0){
                return  null;
            }
            cursor.moveToFirst();
            return  cursor.getData();
        }finally {
            cursor.close();
        }
    }

    public File getPhotoFile(MessageData messageData){
        File filesDir = mContext.getFilesDir();
        if (filesDir  == null) {
            return null;
        }
        return new File(filesDir, messageData.getPhotoFilename());
    }

    public void updateCrime(MessageData messageData){
        String uuidString = messageData.getId().toString();
        ContentValues values = getContentValues(messageData);
        mDatabase.update(NAME,values, Cols.UUID +  "= ?",new String[] {uuidString});
    }

    private PuDataCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                NAME,
                null,
                whereClause,
                whereArgs,
                null,
//                null,
                null,
                null
        );
        return  new PuDataCursorWrapper(cursor);
    }

    public static ContentValues getContentValues(MessageData messageData){
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, messageData.getId().toString());
        values.put(Cols.TITLE, messageData.getTtitle());
        values.put(Cols.DATE, messageData.getDate().getTime());

        return  values;
    }

}
