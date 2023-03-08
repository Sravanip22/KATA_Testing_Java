package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CommonUtils;

import javax.jws.WebResult;
import java.awt.*;
import java.util.Calendar;
import java.util.List;

public class BookingPage extends HomePage{
    WebDriver driver=HomePage.driver;
    @FindBy(name="firstname")
    private WebElement firstName;
    @FindBy(name="lastname")
    private WebElement lastName;
    @FindBy(name="email")
    private WebElement emailId;
    @FindBy(name="phone")
    private WebElement phone;
    @FindBy(xpath="//span[contains(@class, 'rbc-toolbar-label')]")
    private WebElement monthYear;
    @FindBy(xpath=".//button[contains(text(), 'Back')]")
    private WebElement previousMonth;
    @FindBy(xpath=".//button[contains(text(), 'Next')]")
    private WebElement nextMonth;
    @FindBy(xpath = "//button[contains(@class,'btn btn-outline-primary float-right book-room')]")
    private WebElement submit;
    @FindBy(xpath= ".//div[contains(@class, 'alert')]/p")
    private List<WebElement> errors;

    public void fillFirstName(String name){
        firstName.sendKeys(name);
    }
    public void fillLastName(String name){
        lastName.sendKeys(name);
    }
    public void fillEmail(String email){
        emailId.sendKeys(email);
    }
    public void fillPhone(String phnNumber){
        phone.sendKeys(phnNumber);
    }

    public void bookRoom(){
        submit.click();
    }

    public void assertBookButtonClickable(){
        submit.isEnabled();
    }

    public void assertBookButtonDisplayed(){
        submit.isDisplayed();
    }
    public void assertFirstNameDisplayed(){
        firstName.isDisplayed();
    }
    public void assertLastNameDisplayed(){
        lastName.isDisplayed();
    }
    public void assertEmailDisplayed(){
        emailId.isDisplayed();
    }
    public void assertPhoneDisplayed(){
        phone.isDisplayed();
    }
   public String getDateXpath(int date){
      return "//div/button[text()="+date+"]";
   }
   public void assertError(String errorText) {
       int temp = 0;
       if (errors.size() == 1) {
           Assert.assertEquals(errorText, errors.get(0).getText());
       } else if (errors.size() > 1) {
           for (WebElement err : errors) {
               if (errorText.contains(err.getText())) {
                   Assert.assertEquals(errorText, err.getText());
                   temp=1;
               }
           }
           if(temp!=1)
               Assert.fail("given error not found");
       }
   }

   public void assertMonth(String month){
        Assert.assertTrue(monthYear.getText().contains(month));
   }
   public void gotoPreviousMonth(){
       previousMonth.click();
   }
    public void gotoNextMonth(){
        nextMonth.click();
    }
   public void selectDates(int bookingDays, String month) throws AWTException {
       Calendar cal = Calendar.getInstance();
       int i=cal.get(Calendar.DAY_OF_MONTH) +1;
       //i=17;
       assertMonth(month);
       CommonUtils.clickAndDrag(driver.findElement(By.xpath(getDateXpath(i))),driver.findElement(By.xpath(getDateXpath(i+bookingDays))) );
   }

}
