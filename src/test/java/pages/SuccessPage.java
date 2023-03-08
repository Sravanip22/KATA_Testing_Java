package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Calendar;
import java.util.List;

public class SuccessPage extends HomePage {
    @FindBy(xpath="//div[@class='ReactModalPortal']//following::h3[text()='Booking Successful!']")
    private WebElement successMessage;
    @FindBy(xpath="//div[@class='col-sm-6 text-center']/p")
    private List<WebElement> successDate;

    public void assertSuccessMessage(){
        Assert.assertTrue(successMessage.isDisplayed());
    }
    public void assertBookedDates(int bookingDays, String month){
        Calendar cal = Calendar.getInstance();
        String firstDay=String.valueOf(cal.get(Calendar.DAY_OF_MONTH)+1);
        String lastDay=String.valueOf(cal.get(Calendar.DAY_OF_MONTH)+1+bookingDays);
        String year=String.valueOf(cal.get(Calendar.YEAR));
        String actaulMessage="";
        String expectedMessage="Congratulations! Your booking has been confirmed for:"+year+"-"+month+"-"+firstDay+" - "+year+"-"+month+"-"+lastDay;
        System.out.println(expectedMessage);
        for(WebElement e:successDate)
            actaulMessage=actaulMessage + e.getText();
        System.out.println(actaulMessage);
        Assert.assertEquals(expectedMessage, actaulMessage);
    }
}
