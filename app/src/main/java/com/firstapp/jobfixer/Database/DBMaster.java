package com.firstapp.jobfixer.Database;

import android.provider.BaseColumns;

public final class DBMaster {



    private DBMaster() {

    }

    public static class Advertisement implements BaseColumns{

        public static final String TABLE_NAME ="Advertisement";
        public static final String COLUMN_NAME_JOB_ID ="jobId";
        public static final String COLUMN_NAME_JOB_CATEGORY ="jobCategory";
        public static final String COLUMN_NAME_JOB_TITLE ="jobTitle";
        public static final String COLUMN_NAME_COMPANY_NAME ="companyName";
        public static final String COLUMN_NAME_COMPANY_ADDRESS ="companyAddress";
        public static final String COLUMN_NAME_JOB_TYPE ="jobType";
        public static final String COLUMN_NAME_JOB_SALARY ="jobSalary";
        public static final String COLUMN_NAME_JOB_QUALIFICATION ="qualification";

    }
}
