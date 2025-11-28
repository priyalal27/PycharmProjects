package com.misc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class dependsOnGroupMethod {


    // Database setup group
    @Test(groups = "db_setup")
    public void connectToDatabase() {
        System.out.println("Connecting to database");
    }

    @Test(groups = "db_setup", dependsOnMethods = "connectToDatabase")
    public void createTestData() {
        System.out.println("Creating test data");
    }

    // Application setup group - depends on database setup
    @Test(groups = "app_setup", dependsOnGroups = "db_setup")
    public void launchApplication() {
        System.out.println("Launching application");
    }

    @Test(groups = "app_setup", dependsOnGroups = "db_setup")
    public void loginToApplication() {
        System.out.println("Logging into application");
    }

    // Test execution groups - depend on application setup
    @Test(groups = "functional", dependsOnGroups = "app_setup")
    public void testUserManagement() {
        System.out.println("Testing user management");
    }

    @Test(groups = "functional", dependsOnGroups = "app_setup")
    public void testOrderProcessing() {
        System.out.println("Testing order processing");
    }

    // UI tests - depend on functional tests
    @Test(groups = "ui", dependsOnGroups = "functional")
    public void testUIComponents() {
        System.out.println("Testing UI components");
    }

    @Test(groups = "ui", dependsOnGroups = "functional")
    public void testResponsiveDesign() {
        System.out.println("Testing responsive design");
    }

    // Cleanup - depends on all test groups
    @Test(groups = "cleanup", dependsOnGroups = {"functional", "ui"})
    public void deleteTestData() {
        System.out.println("Deleting test data");
    }

    @Test(groups = "cleanup", dependsOnGroups = {"functional", "ui"})
    public void closeDatabaseConnection() {
        System.out.println("Closing database connection");
    }




//    public void test1(){
//        WebDriver driver=new chromedriver();
//        driver.get("abc.com");
//       Set<String> s=driver.getWindowHandles();
//
//       for(String val:s){
//           driver.switchTo().window(val);
//           String text=driver.findElement(By.xpath("deded")).getText();
//           Assert.assertEquals(text,"Automating","Window found with the text");
//       }
//
//
//    }


















}
