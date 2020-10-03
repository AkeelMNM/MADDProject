package com.firstapp.jobfixer.Database;

import android.provider.BaseColumns;

public final class DBMaster {


    private DBMaster() {

    }

    public static class Advertisement implements BaseColumns{

        public static final String TABLE_NAME ="Advertisement";
        public static final String COLUMN_NAME_JOB_ID ="jobID";
        public static final String COLUMN_NAME_JOB_CATEGORY ="jobCategory";
        public static final String COLUMN_NAME_JOB_TITLE ="jobTitle";
        public static final String COLUMN_NAME_COMPANY_NAME ="companyName";
        public static final String COLUMN_NAME_COMPANY_ADDRESS ="companyAddress";
        public static final String COLUMN_NAME_JOB_TYPE ="jobType";
        public static final String COLUMN_NAME_JOB_SALARY ="jobSalary";
        public static final String COLUMN_NAME_JOB_QUALIFICATION ="qualification";

    }

    public static class Register implements BaseColumns{

        public static final String TABLE_NAME ="Register";
        public static final String COLUMN_NAME_USER_NAME ="username";
        public static final String COLUMN_NAME_USER_PASSWORD ="password";
        public static final String COLUMN_NAME_USER_EMAIL ="email";
        public static final String COLUMN_NAME_USER_TYPE ="type";

    }

    public static class SendRequestToCompany implements BaseColumns{

        public static final String TABLE_NAME ="CompViewApplicantRequest";
        public static final String COLUMN_NAME_JOB_ID ="jobID";
        public static final String COLUMN_NAME_RESUME_ID ="resumeID";
        public static final String COLUMN_NAME_JOB_NAME ="jobName";
        public static final String COLUMN_NAME_COMPANY_NAME ="compName";
        public static final String COLUMN_NAME_APPLICANT_NAME ="applicantName";
        public static final String COLUMN_NAME_APPLICANT_EMAIL ="applicantEmail";
        public static final String COLUMN_NAME_APPLY_DATE ="applyDate";


    }

    public static class SendReplyToApplicant implements BaseColumns{

        public static final String TABLE_NAME ="UserViewCompRequest";
        public static final String COLUMN_NAME_USER_ID ="userID";
        public static final String COLUMN_NAME_JOB_NAME ="jobName";
        public static final String COLUMN_NAME_COMPANY_NAME ="compName";
        public static final String COLUMN_NAME_COMPANY_EMAIL ="compEmail";
        public static final String COLUMN_NAME_SEND_DATE ="sendDate";
        public static final String COLUMN_NAME_APPLICANT_NAME ="applicantName";
        public static final String COLUMN_NAME_APPLICANT_EMAIL ="applicantEmail";
        public static final String COLUMN_NAME_COMPANY_MESSAGE ="compMsg";



    }

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
