package com.firstapp.jobfixer;

import com.firstapp.jobfixer.Model.Advertisement;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class IT19153414 {

    Advertisement ad;

    @BeforeClass
    public static void initObject() {

    }

    @Before
    public void setUp(){
        ad = new Advertisement("AD01", "J01", "Information Technology", "Software Engineer", "IFS", "Colombo 10", "Full Time", "78000", "Degree in Bsc in Software Engineer");
    }

    @Test
    public void checkAD_ID() {
        assertEquals("AD01",ad.getAdID());
    }

    @Test
    public void checkJob_ID() {
        assertEquals("J01",ad.getJobID());
    }

    @Test
    public void checkJobCat() {
        assertEquals("Information Technology",ad.getJobCategory());
    }

    @Test
    public void checkJobTitle() {
        assertEquals("Software Engineer",ad.getJobTitle());
    }

    @Test
    public void checkCompanyName() {
        assertEquals("IFS",ad.getCompanyName());
    }

    @Test
    public void checkCompanySal() {
        assertEquals("78000",ad.getJobSalary());
    }

    @Test
    public void checkJobAddress() {
        assertEquals("Colombo 10",ad.getCompanyAddress());
    }

    @Test
    public void checkJobType() {
        assertEquals("Full Time",ad.getJobType());
    }

    @After
    public void clearAll(){
        ad = null;
    }




}
