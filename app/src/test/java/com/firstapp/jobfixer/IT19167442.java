package com.firstapp.jobfixer;
import org.junit.Test;
import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Helpcenter;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IT19167442 {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testGetUserId(){
        Helpcenter hp=new Helpcenter();
        hp.setUserId("UI001");
        assertEquals("UI001",hp.getUserId());

    }

    @Test
    public void testGetHCId(){
        Helpcenter hp=new Helpcenter();
        hp.setHCId("HC01");
        assertEquals("HC01",hp.getHCId());

    }

    @Test
    public void testName(){
        Helpcenter hp=new Helpcenter();
        hp.setName("Sunil");
        assertEquals("Sunil",hp.getName());

    }

    @Test
    public void testGetEmail(){
        Helpcenter hp=new Helpcenter();
        hp.setEmail("Sunil@gmail.com");
        assertEquals("Sunil@gmail.com",hp.getEmail());

    }

    @Test
    public void testGetAdminmessage(){
        Helpcenter hp=new Helpcenter();
        hp.setAdminmsg("Approved");
        assertEquals("Approved",hp.getAdminmsg());

    }

    @Test
    public void Adminname(){
        Helpcenter hp=new Helpcenter();
        hp.setAdminmsg("Admin");
        assertEquals("Approved",hp.getAdminmsg());

    }

    @Test
    public void testGetAdminEmail(){
        Helpcenter hp=new Helpcenter();
        hp.setAdminmsg("Approved");
        assertEquals("Approved",hp.getAdminmsg());

    }

    @Test
    public void testGetAdminUserID(){
        Helpcenter hp=new Helpcenter();
        hp.setAdminmsg("Approved");
        assertEquals("Approved",hp.getAdminmsg());

    }






}
