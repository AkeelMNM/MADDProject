package DataBase;

import android.provider.BaseColumns;

public class DBMaster {

    private DBMaster() {
    }

    public static class Resume implements BaseColumns {

        public static final String TABLE_NAME ="Resume";
        public static final String COLUMN_NAME_RESUME_ID ="ResId";
        public static final String COLUMN_NAME_USER_ID ="userId";
        public static final String COLUMN_NAME_FIRST_NAME ="firstName";
        public static final String COLUMN_NAME_LAST_NAME ="lastName";
        public static final String COLUMN_NAME_LOCATION ="location";

        public static final String COLUMN_NAME_PHONE ="phone";

        public static final String COLUMN_NAME_EMAIL ="email";
        public static final String COLUMN_NAME_JOB_CATEGORY ="jobCat";
        public static final String COLUMN_NAME_JOB_TITLE ="jobTit";
        public static final String COLUMN_NAME_JOB_ABOUT_ME ="aboutMe";
        public static final String COLUMN_NAME_JOB_WORK_EXPERIENCE ="workExp";
        public static final String COLUMN_NAME_JOB_EDUCTION ="eduction";





    }

}
