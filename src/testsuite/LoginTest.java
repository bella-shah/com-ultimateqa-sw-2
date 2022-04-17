package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        WebElement signInLink = driver.findElement(By.xpath("//li[@class='header__nav-item header__nav-sign-in']"));
        signInLink.click();

        String expectedMessage = "Welcome Back!";

        //Find the welcome text Element and get the text
        WebElement actualMessageElement = driver.findElement(By.xpath("//h1[@class='page__heading']"));
        String actualMessage = actualMessageElement.getText();
        System.out.println(actualMessage);
        //Validate actual and expected message
        Assert.assertEquals("Welcome Back Text is not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyTheErrorMessage() {
        WebElement signInLink = driver.findElement(By.xpath("//li[@class='header__nav-item header__nav-sign-in']"));
        signInLink.click();

        WebElement usernameField = driver.findElement(By.id("user[email]"));
        // sending username to username field element
        usernameField.sendKeys("prime123@gmail.com");

        WebElement passwordField = driver.findElement(By.id("user[password]"));
        // sending password to password field element
        passwordField.sendKeys("prime123");

        WebElement signInButton = driver.findElement(By.xpath("//input[@class='button button-primary g-recaptcha']"));
        signInButton.click();

        //This is from requirement
        String expectedMessage = "Invalid email or password.";

        String actualMessage = driver.findElement(By.xpath("//ul[@class='form-error__list']")).getText();

        //Validate actual and expected message
        Assert.assertEquals("Unable to sign in ", expectedMessage, actualMessage);
    }

    @After
    //close the driver
    public void tearDown() {
        closeBrowser();
    }

}

