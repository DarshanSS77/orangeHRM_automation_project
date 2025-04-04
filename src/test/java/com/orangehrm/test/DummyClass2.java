package com.orangehrm.test;

import com.orangehrm.base.BaseClass;
import org.testng.annotations.Test;

public class DummyClass2 extends BaseClass {
    @Test
    public void dummyTest2() {
        String title = driver.getTitle();

        assert title.equals("OrangeHRM") : "Test failed : Title is not mathching with 'OrangeHRM'";
        System.out.println("Test is passed and title is " + title);
    }
}
