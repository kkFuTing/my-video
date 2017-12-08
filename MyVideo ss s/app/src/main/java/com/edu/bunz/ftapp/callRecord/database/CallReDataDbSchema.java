package com.edu.bunz.ftapp.callRecord.database;

/**
 * Created by asuss on 2017/10/12.
 */

public class CallReDataDbSchema {
    //描述数据表的policeReDataTable 内部类
    //目的：定义描述数据表元素的string常量
        public static final class callReDataTable{
        //NAME：数据库 表名
        public static final String NAME = "callReDatas";

//         CallReDataDbSchema.Cols.TITLE 表字段
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String PHONE = "phone";
            public static final String DATE = "date";

        }
    }
}
