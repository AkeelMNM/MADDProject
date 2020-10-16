package com.firstapp.jobfixer;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Resume;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IT19111070 {
   Resume res ;

   @BeforeClass
    public static void  initObject(){

   }
    @Before
    public  void setUp(){
       res = new Resume("U01","Re1","Kumar","Kanth","Kandy","0789214563","kumar@gmail.com","Software","Softeware eng","SoftwareEng","Im ","4 years","Bsc in It");
    }
    @Test
    public void CheckId(){
       assertEquals("Re01",res.getResId());
    }
    @Test
    public void Check_FName(){
       assertEquals("Kumar",res.getFirstName());
    }
    @Test
    public void Check_LName(){
        assertEquals("Kanth",res.getLastName());
    }
    @Test
    public void Check_loCName(){
        assertEquals("Kandy",res.getLocation());
    }
    @Test
    public void Check_gmail(){
        assertEquals("Kumar@gmail.com",res.getEmail());
    }
    @Test
    public void Check_Edu(){
        assertEquals("Bsc in It",res.getEducation());
    }
    @Test
    public void Check_Jobcat(){
        assertEquals("Softeware eng",res.getJobCat());
    }
    @After
    public void clearAll(){
       res = null ;
    }
}
