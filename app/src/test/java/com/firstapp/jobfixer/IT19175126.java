package com.firstapp.jobfixer;

import com.firstapp.jobfixer.Model.Jobs;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IT19175126 {

    Jobs job;

    @BeforeClass
    public static void initObject() {
    }

    @Before
    public void setUp(){
        job = new Jobs("job01","","Information Technology","Software Engineer","IFS","Colombo","Full time","75000","Degree In Bsc IT , 2 years experience in Software Architect");
    }

    @Test
    public void check_JobID() { assertEquals("job01",job.getJobID());}

    @Test
    public void check_UserId() { assertEquals("",job.getUserID()); }

    @Test
    public void check_Cat() { assertEquals("Information Technology",job.getCategory());}

    @Test
    public void check_Title() { assertEquals("Software Engineer",job.getTitle());}

    @Test
    public void check_Type() { assertEquals("Full time",job.getTitle());}

    @Test
    public void check_Name() { assertEquals("IFS",job.getCompanyName());}

    @Test
    public void check_Add() { assertEquals("Colombo",job.getCompanyAddress());}

    @Test
    public void check_Sal() { assertEquals("75000",job.getSalary());}

    @Test
    public void check_Des() { assertEquals("Degree In Bsc IT , 2 years experience in Software Architect",job.getDescription());}

    @After
    public void clearAll(){
        job = null;
    }

}
