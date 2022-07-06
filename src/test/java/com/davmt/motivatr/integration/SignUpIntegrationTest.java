package com.davmt.motivatr.integration;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

public class SignUpIntegrationTest {

    WebDriver driver;
    Faker faker;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulSignUpRedirectsToLogin() {
        driver.get("http://localhost:8080");
        driver.findElement(By.id("btn_signup")).click();
        driver.findElement(By.id("firstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("email")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.id("username")).sendKeys(faker.name().username());
        driver.findElement(By.id("password")).sendKeys(faker.internet().password());
        driver.findElement(By.id("submit")).click();
        WebElement title = driver.findElement(By.tagName("h2"));
        System.out.println(title);
        Assert.assertEquals("Login", title);
    }
}