package com.example.visitor.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VisitorManagementTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

    }


    @Test
    public void openVisitorManagementSystem() {

        driver.get("http://localhost:8080");

        String title = driver.getTitle();

        System.out.println("Page Title: " + title);

        Assert.assertEquals(
                title,
                "Visitor Management System"
        );
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();

    }
}