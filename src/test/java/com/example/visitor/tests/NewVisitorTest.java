package com.example.visitor.tests;

import com.example.visitor.pages.VisitorHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewVisitorTest {

    WebDriver driver;
    VisitorHomePage visitorHomePage;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://localhost:8080");

        visitorHomePage = new VisitorHomePage(driver);
    }

    @Test
    public void registerNewVisitor() {

        // Enter phone number
        visitorHomePage.enterPhone("9876543210");

        // Click Continue
        visitorHomePage.clickContinue();

        // Enter visitor details
        visitorHomePage.enterName("Saleem Automation");

        visitorHomePage.enterNewPhone("9876543210");

        visitorHomePage.enterEmail("saleemtest@gmail.com");

        // Select host
        visitorHomePage.selectHost("Azeem");

        // Enter purpose
        visitorHomePage.enterPurpose("Interview");

        // Register visitor
        visitorHomePage.clickRegisterVisitor();

        // Verify visit status
        String status = visitorHomePage.getVisitStatus();

        System.out.println("Visit Status: " + status);

        Assert.assertEquals(status, "WAITING");
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}