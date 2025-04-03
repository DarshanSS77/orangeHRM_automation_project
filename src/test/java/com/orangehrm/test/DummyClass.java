package com.orangehrm.test;

import com.orangehrm.base.BaseClass;
import org.testng.annotations.Test;

public class DummyClass extends BaseClass {
    @Test
    public void dummyTest() {
        String title = driver.getTitle();

      assert title.equals("OrangeHRM") : "Test failed : Title is not mathching with 'OrangeHRM'" ;
        System.out.println("Test is passed");
    }
}
