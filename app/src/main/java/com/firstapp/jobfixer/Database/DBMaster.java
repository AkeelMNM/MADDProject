package com.firstapp.jobfixer.Database;

import android.provider.BaseColumns;

public class DBMaster {

    private DBMaster() {}

    public static class Job implements BaseColumns {

        public static final String TABLE_NAME = "Job";
        public static final String COLUMN_NAME_JOB_ID = "jobID";
        public static final String COLUMN_NAME_USER_ID = "userID";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMPANY_NAME = "companyName";
        public static final String COLUMN_NAME_COMPANY_ADDRESS = "companyAddress";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_SALARY = "salary";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

}
