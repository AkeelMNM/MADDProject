package DataBase;

import android.provider.BaseColumns;

public class DBMaster {

    private DBMaster() {
    }

    public static class Resume implements BaseColumns {

        public static final String TABLE_NAME ="Resume";
        public static final String COLUMN_NAME_RESUME_ID ="ResId";
        public static final String COLUMN_NAME_USER_ID ="UserId";
        public static final String COLUMN_NAME_FIRST_NAME ="fName";
        public static final String COLUMN_NAME_LAST_NAME ="LName";
        public static final String COLUMN_NAME_LOCATION ="location";

        public static final String COLUMN_NAME_PHONE ="phone";

        public static final String COLUMN_NAME_EMAIL ="email";
        public static final String COLUMN_NAME_JOB_CATEGORY ="jobCat";
        public static final String COLUMN_NAME_JOB_TITLE ="jobTit";
        public static final String COLUMN_NAME_JOB_ABOUT_ME ="aboutMe";
        public static final String COLUMN_NAME_JOB_WORK_EXPERIENCE ="WorkEx";
        public static final String COLUMN_NAME_JOB_EDUCTION ="eduction";

        /** login table **/

        public static final String TABLE_NAME_LOGIN ="Login";

    }
    public static class UserLogin implements BaseColumns{

        /** login table **/

        public static final String TABLE_NAME_USER_LOGIN ="userLogin";
        public static final String COLUMN_NAME_USER_TYPE ="userType";
        public static final String COLUMN_NAME_USERNAME ="username";
        public static final String COLUMN_NAME_USER_EMAIL ="userEmail";
        public static final String COLUMN_NAME_PASSWORD ="password";
    }
}
