package pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.*;

import static utils.CommonUtils.*;
import static utils.DriversUtils.*;


public class HomePage {
    public static WebDriver driver;
    @FindBy(tagName = "h2")
    private WebElement roomCategoryIdentifier;

    @FindBy(xpath = "//button[contains(@class,'openBooking')]")
    private WebElement bookButton;

    public HomePage() {
        driver=getDriver();
        PageFactory.initElements(getDriver(), this);
    }

    public void goToRoomsCategory() {
        try {
            scrollToElement(roomCategoryIdentifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error in the rooms category method");
        }
    }

    public void assertBookButtonDisplayed(){
        Assert.assertEquals(true, bookButton.isDisplayed());

    }
    public void assertBookButtonClickable(){
        Assert.assertTrue(bookButton.isEnabled());
    }

    public void openBookingForm(){
        bookButton.click();
    }
    public void navigateToHomePage() {
        getDriver().get("https://automationintesting.online/#/");
    }
}

