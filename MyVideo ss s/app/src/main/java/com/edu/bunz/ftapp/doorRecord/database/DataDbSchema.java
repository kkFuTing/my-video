package com.edu.bunz.ftapp.doorRecord.database;

/**
 * Created by asuss on 2017/10/12.
 */

public class DataDbSchema {
    public static final class DataTable{
        public static final String NAME = "datas";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
//            public static final String TIME = "time";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
            public static final String SUSPECT_PN = "suspect_pn";
        }
    }
}
