package com.netjob.habittracker.data;

import android.provider.BaseColumns;

import com.netjob.habittracker.R;

/**
 * Created by root on 1/26/17.
 */

public class HabitDataContract {

    private HabitDataContract() {}

    public static class HabitEntry implements BaseColumns {

        private HabitEntry() {}


        public static final String TABLE_NAME = "habits";

        public static final int RAN_EQUALS_YES = 1;
        public static final int RAN_EQUALS_NO = 0;

        public static final int RAN_DEFAULT = RAN_EQUALS_NO;
        public static final int MILES_DEFAULT = 0;
        public static final int PUSHUPS_DEFAULT = 0;
        public static final int WATERCUPS_DEFAULT = 0;
        public static final String SYMPTOMS_DEFAULT = "none";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME_WATERCUPS = "watercups";
        public static final String COLUMN_NAME_PUSHUPS = "pushups";
        public static final String COLUMN_NAME_SYMPTOMS = "symptoms";
        public static final String COLUMN_NAME_RAN = "ran";
        public static final String COLUMN_NAME_MILES = "miles";




    }


}
