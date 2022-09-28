package AutomationProject1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;

public class TestCase1 {
    public static void main(String[] args) throws InterruptedException {
//        STEPS:
//        1. Navigate to http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php
        System.setProperty("webdriver.chrome.driver", "/Users/zayasaikhanchuluunbaatar/Downloads/BrowserDriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");

        Thread.sleep(1000);
//        2. Verify the the title is "Welcome to Duotify!"
        String expectedTitle = "Welcome to Duotify!";
        if(expectedTitle.equals(driver.getTitle())){
            System.out.println("TITLE PASSED");
        }else{
            System.out.println("TITLE FAILED");
        }
//        3. Click on Signup here
        driver.findElement(By.id("hideLogin")).click();
//        4. Fill out the form with the required info
        driver.findElement(By.name("username")).sendKeys("Username123");
        driver.findElement(By.name("firstName")).sendKeys("Duotech");
        driver.findElement(By.name("lastName")).sendKeys("Batch");
        driver.findElement(By.name("email")).sendKeys("dtacademy6789@gmail.com");
        driver.findElement(By.name("email2")).sendKeys("dtacademy6789@gmail.com");
        driver.findElement(By.name("password")).sendKeys("dtacademy123");
        driver.findElement(By.name("password2")).sendKeys("dtacademy123");
//        5. Click on Sign up
        driver.findElement(By.name("registerButton")).click();

//        6. Once logged in to the application, verify that the URL is:
//        http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?
        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.equals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?")){
            System.out.println("URL PASSED");
        }else{
            System.out.println("URL FAILED");
        }
//        7. In the left navigation bar, verify that your first and last name matches the first and last name that you used when signing up.

        if(driver.getPageSource().contains("Duotech Batch")){
            System.out.println("Name PASSED");
        }else{
            System.out.println("Name FAILED");

        }
//        8. Click on the username on the left navigation bar and verify the username on the main window is correct and then click logout.
        driver.findElement(By.id("nameFirstAndLast")).click();

        if(driver.getPageSource().contains("Duotech Batch")){
            System.out.println("Name on the main window PASSED");
        }else{
            System.out.println("Name on the main window FAILED");

        }

        driver.findElement(By.id("rafael")).click();
//        9. Verify that you are logged out by verifying the URL is:
//        http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php

        Thread.sleep(500);
        String loggedOutUrl = "http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php";
        if(loggedOutUrl.equals(driver.getCurrentUrl())){
            System.out.println("Logged out link PASSED");
        }else{
            System.out.println("Logged out link FAILED");

        }
//        10. Login using the same username and password when you signed up.
        driver.findElement(By.id("loginUsername")).sendKeys("Username123");
        driver.findElement(By.id("loginPassword")).sendKeys("dtacademy123", Keys.ENTER);


//        11. Verify successful login by verifying that the home page contains the text "You Might Also Like".

        Thread.sleep(500);
        if(driver.getPageSource().contains("You Might Also Like")){

            System.out.println("Login PASSED");
        }else{
            System.out.println("Login FAILED");

        }
    }
}
