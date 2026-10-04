package com.example.visitor.tests;

import com.example.visitor.pages.VisitorHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReturningVisitorTest {

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
    public void returningVisitorTest() {

        // Step 1: Enter phone number of an existing visitor

        visitorHomePage.enterPhone("9876543210");

        // Step 2: Check visitor

        visitorHomePage.clickContinue();

        // Step 3: Select host for the new visit

        visitorHomePage.selectExistingVisitorHost("Azeem");

        // Step 4: Enter purpose for the new visit

        visitorHomePage.enterExistingVisitorPurpose("Follow-up Meeting");

        // Step 5: Create a new visit

        visitorHomePage.clickCreateVisit();

        // Step 6: Verify that the new visit is WAITING

        String status = visitorHomePage.getVisitStatus();

        System.out.println("Returning Visitor Visit Status: " + status);

        String actualStatus = status.replace("Status: ", "").trim();

        Assert.assertEquals(
                actualStatus,
                "WAITING",
                "Returning visitor visit status should be WAITING"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}