package com.example.visitor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class VisitorHomePage {

    WebDriver driver;
    WebDriverWait wait;


    // =========================
    // Common Locators
    // =========================

    By phone = By.id("phone");
    By checkVisitorButton = By.id("checkVisitorButton");


    // =========================
    // New Visitor Locators
    // =========================

    By name = By.id("name");
    By newPhone = By.id("newPhone");
    By email = By.id("email");
    By host = By.id("host");
    By purpose = By.id("purpose");

    By registerVisitorButton = By.id("registerVisitorButton");


    // =========================
    // Existing Visitor Locators
    // =========================

    By existingHost = By.id("existingHost");
    By existingPurpose = By.id("existingPurpose");

    By createVisitButton = By.id("createVisitButton");


    // =========================
    // Visit Status
    // =========================

    By visitStatus = By.id("visitStatus");


    // =========================
    // Constructor
    // =========================

    public VisitorHomePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }


    // =========================
    // Phone Number
    // =========================

    public void enterPhone(String phoneNumber) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(phone)
        ).sendKeys(phoneNumber);
    }


    public void clickContinue() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        checkVisitorButton
                )
        ).click();
    }


    // =========================
    // New Visitor
    // =========================

    public void enterName(String visitorName) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(name)
        ).sendKeys(visitorName);
    }


    public void enterNewPhone(String phoneNumber) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(newPhone)
        ).sendKeys(phoneNumber);
    }


    public void enterEmail(String visitorEmail) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(email)
        ).sendKeys(visitorEmail);
    }


    public void selectHost(String hostName) {

        Select hostDropdown = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(host)
                )
        );

        List<WebElement> options = hostDropdown.getOptions();

        for (WebElement option : options) {

            String optionText = option.getText().trim();

            if (optionText.startsWith(hostName)) {

                option.click();

                return;
            }
        }

        throw new RuntimeException(
                "Host not found in new visitor dropdown: "
                        + hostName
        );
    }


    public void enterPurpose(String visitPurpose) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(purpose)
        ).sendKeys(visitPurpose);
    }


    public void clickRegisterVisitor() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        registerVisitorButton
                )
        ).click();
    }


    // =========================
    // Existing Visitor
    // =========================

    public void selectExistingVisitorHost(String hostName) {

        WebElement dropdownElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        existingHost
                )
        );

        Select hostDropdown = new Select(dropdownElement);

        List<WebElement> options = hostDropdown.getOptions();

        for (WebElement option : options) {

            String optionText = option.getText().trim();

            if (optionText.startsWith(hostName)) {

                option.click();

                return;
            }
        }

        throw new RuntimeException(
                "Host not found in existing visitor dropdown: "
                        + hostName
        );
    }


    public void enterExistingVisitorPurpose(String visitPurpose) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        existingPurpose
                )
        ).sendKeys(visitPurpose);
    }


    public void clickCreateVisit() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        createVisitButton
                )
        ).click();
    }


    // =========================
    // Visit Status
    // =========================

    public String getVisitStatus() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        visitStatus
                )
        ).getText();
    }
}