package com.example.visitor.tests;

import com.example.visitor.pages.VisitorHomePage;
import com.example.visitor.pages.VisitStatusPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VisitStatusTest {

    WebDriver driver;

    VisitorHomePage visitorHomePage;
    VisitStatusPage visitStatusPage;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://localhost:8080");

        visitorHomePage = new VisitorHomePage(driver);
        visitStatusPage = new VisitStatusPage(driver);
    }

    @Test
    public void visitStatusTest() {

        // Step 1: Create visit

        visitorHomePage.enterPhone("9876543210");

        visitorHomePage.clickContinue();

        visitorHomePage.selectExistingVisitorHost("Azeem");

        visitorHomePage.enterExistingVisitorPurpose("Interview");

        visitorHomePage.clickCreateVisit();

        // Step 2: Verify WAITING

        visitStatusPage.waitForStatus("WAITING");

        String status = visitStatusPage.getVisitStatus();

        System.out.println("Initial Status: " + status);

        Assert.assertTrue(
                status.contains("WAITING"),
                "Visit status should be WAITING"
        );

        // Step 3: Start Meeting

        visitStatusPage.clickStartMeeting();

        visitStatusPage.waitForStatus("MEETING");

        status = visitStatusPage.getVisitStatus();

        System.out.println("After Start Meeting: " + status);

        Assert.assertTrue(
                status.contains("MEETING"),
                "Visit status should be MEETING"
        );

        // Step 4: Complete Meeting

        visitStatusPage.clickCompleteMeeting();

        visitStatusPage.waitForStatus("COMPLETED");

        status = visitStatusPage.getVisitStatus();

        System.out.println("After Complete Meeting: " + status);

        Assert.assertTrue(
                status.contains("COMPLETED"),
                "Visit status should be COMPLETED"
        );

        // Step 5: Visitor Exit

        visitStatusPage.clickExit();

        visitStatusPage.waitForStatus("EXITED");

        status = visitStatusPage.getVisitStatus();

        System.out.println("After Exit: " + status);

        Assert.assertTrue(
                status.contains("EXITED"),
                "Visit status should be EXITED"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}