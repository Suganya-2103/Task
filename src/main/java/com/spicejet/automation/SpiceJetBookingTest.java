package com.spicejet.automation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SpiceJetBookingTest {
    WebDriver driver;
    WebDriverWait wait,wait_30;

    @BeforeClass
    public void setUp() {
       

        // Initialize Chrome WebDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Initialize WebDriverWait for handling waits
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        driver.get("https://www.spicejet.com/");
    }

    @Test(priority = 1)
    public void loginTest() {
        WebElement loginButton = driver.findElement(By.xpath("//div[contains(text(),'Login')]"));        
        loginButton.click();

        WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-testid='user-mobileno-input-box']")));
        mobile.sendKeys("9025661542");

        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("Suganya@123");

        WebElement loginSubmitButton = driver.findElement(By.cssSelector(".css-1dbjc4n.r-1awozwy.r-184aecr.r-z2wwpe.r-1loqt21.r-18u37iz.r-tmtnm0.r-1777fci.r-1x0uki6.r-1w50u8q.r-ah5dr5.r-1otgn73"));
        loginSubmitButton.click();

        // Verify successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='I Am Here For...']")));
    }

    @Test(priority = 2)
    public void searchFlightsOneWay() {
        WebElement originInput = driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']//input[@type='text']"));        // Click from city tab
        originInput.click();
        driver.findElement(By.xpath("//div[contains(text(),'Dharamshala')]")).click();     // Select India

        WebElement destinationInput = driver.findElement(By.cssSelector(".css-76zvg2.r-cqee49.r-ubezar.r-1kfrs79.r-1ozqkpa"));    // Select To field
        destinationInput.click();
        driver.findElement(By.xpath("//div[normalize-space()='Goa International Airport']")).click();

        WebElement dateSelect = driver.findElement(By.cssSelector("div[class='css-1dbjc4n r-18u37iz'] div:nth-child(1) div:nth-child(1) div:nth-child(3) div:nth-child(3) div:nth-child(5) div:nth-child(1) div:nth-child(1)"));
        dateSelect.click();
        
//        WebElement dateSelect2 = driver.findElement(By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(7) > div:nth-child(1)"));
//        dateSelect2.click();
//
        WebElement searchButton = driver.findElement(By.cssSelector(".css-1dbjc4n.r-1awozwy.r-z2wwpe.r-1loqt21.r-18u37iz.r-1777fci.r-1g94qm0.r-1w50u8q.r-ah5dr5.r-1otgn73"));
        searchButton.click();

        // Wait for search results to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Dharamshala to Goa, 15 Nov 2024')]")));
    }

    @Test(priority = 3)
    public void selectFlightAndBook() throws InterruptedException {
//        WebElement selectFlightButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("selector-for-select-flight")));
//        selectFlightButton.click();
//
        WebElement continueBookingButton = driver.findElement(By.cssSelector("div[class='css-1dbjc4n r-1awozwy r-1xfd6ze r-1loqt21 r-18u37iz r-1777fci r-1w50u8q r-ah5dr5 r-1otgn73']"));
        continueBookingButton.click();

        driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("SUGANYA");
        driver.findElement(By.xpath("(//input[@type='text'])[7]")).sendKeys("SS");


        WebElement bookButton = driver.findElement(By.cssSelector(".css-1dbjc4n.r-1kihuf0.r-ometjm.r-1251kcm.r-13qz1uu"));
        bookButton.click();    
        System.out.println("display tag name as" + bookButton.getTagName());
    }
    @Test(priority = 4)
    public void summaryFlightContinue() {
    	System.out.println("Entered into summary page");
    	
    	WebElement bookButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-1dbjc4n.r-1awozwy.r-19m6qjp.r-z2wwpe.r-1loqt21.r-18u37iz.r-1777fci.r-6ity3w.r-d9fdf6.r-9qu9m4.r-ah5dr5.r-1otgn73")));
    	bookButton1.click();       
        System.out.println("display tag name as" + bookButton1.getTagName());
    }
    
        @Test(priority = 5)
        public void handlePopup() {
        	wait_30 = new WebDriverWait(driver, Duration.ofSeconds(30));
        	WebElement bookbtn = wait_30.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".at_addon_booknowbtn")));
        	bookbtn.click();
        	System.out.println("Clicked on Book Now button.");
        	WebElement bookButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-1dbjc4n.r-1awozwy.r-19m6qjp.r-z2wwpe.r-1loqt21.r-18u37iz.r-1777fci.r-6ity3w.r-d9fdf6.r-9qu9m4.r-ah5dr5.r-1otgn73")));
        	bookButton1.click(); 

        }

        @Test(priority = 6)
        public void paymentScreen() {
        	wait_30 = new WebDriverWait(driver, Duration.ofSeconds(30));
        	WebElement cardnum = wait_30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Card Number Here']")));
        	cardnum.sendKeys("4016333300000026");
        	
        	try {
                WebElement errorMessage = wait_30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'error-message-class')]")));  // Update with the actual XPath or CSS selector for the error message
                System.out.println("Error message displayed: " + errorMessage.getText());
            } catch (NoSuchElementException e) {
                System.out.println("No error message displayed, transaction might have proceeded.");
            }
        	
        }
        
       
        	  

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    
}}
