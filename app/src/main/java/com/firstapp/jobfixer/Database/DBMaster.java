package com.firstapp.jobfixer.Database;

import android.provider.BaseColumns;

public class DBMaster {

    private DBMaster() {}

    public static class Job implements BaseColumns {

        public static final String TABLE_NAME = "Job";
        public static final String COLUMN_NAME_JOB_ID = "JobID";
        public static final String COLUMN_NAME_USER_ID = "UserID";
        public static final String COLUMN_NAME_CATEGORY = "Category" ;
        public static final String COLUMN_NAME_TITLE = "Title" ;
        public static final String COLUMN_NAME_COMPANY_NAME = "CompanyName" ;
        public static final String COLUMN_NAME_COMPANY_ADDRESS = "CompanyAddress" ;
        public static final String COLUMN_NAME_TYPE = "Type" ;
        public static final String COLUMN_NAME_SALARY = "Salary" ;
        public static final String COLUMN_NAME_DESCRIPTION = "Description" ;
    }

}
