package com.example.visitor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VisitStatusPage {

    WebDriver driver;
    WebDriverWait wait;

    By visitStatus = By.id("visitStatus");
    By startMeetingButton = By.id("startMeetingButton");
    By completeMeetingButton = By.id("completeMeetingButton");
    By exitButton = By.id("exitButton");

    public VisitStatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getVisitStatus() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(visitStatus)
        ).getText();
    }

    public void waitForStatus(String expectedStatus) {

        wait.until(driver -> {

            String status = driver.findElement(visitStatus).getText();

            System.out.println("Current Status: " + status);

            return status.contains(expectedStatus);
        });
    }

    public void clickStartMeeting() {

        wait.until(
                ExpectedConditions.elementToBeClickable(startMeetingButton)
        ).click();
    }

    public void clickCompleteMeeting() {

        wait.until(
                ExpectedConditions.elementToBeClickable(completeMeetingButton)
        ).click();
    }

    public void clickExit() {

        wait.until(
                ExpectedConditions.elementToBeClickable(exitButton)
        ).click();
    }
}