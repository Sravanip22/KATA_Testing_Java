package features.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.Assert;
import pages.BookingPage;
import pages.HomePage;
import pages.SuccessPage;
import utils.CommonUtils;
import utils.DriversUtils;

import java.awt.*;

public class BookingSteps{

    //public static WebDriver driver;
    HomePage homePage = new HomePage();
    BookingPage bookingPage= new BookingPage();
    SuccessPage successPage= new SuccessPage();
    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.navigateToHomePage();

    }

    @When("I browse through the page")
    public void i_browse_through_the_page() {
        homePage.goToRoomsCategory();
    }
    @Then("I have the option to book a room")
    public void i_have_the_option_to_book_a_room() {
       homePage.assertBookButtonDisplayed();
    }

    @Given("I open booking form")
    public void iOpenBookingForm() {
        homePage.openBookingForm();
    }

    @And("book the room")
    public void bookTheRoom() throws InterruptedException {
        bookingPage.bookRoom();
        Thread.sleep(5000);
    }

    @And("button to open booking form is clickable")
    public void buttonToOpenBookingFormIsClickable() {
        homePage.assertBookButtonClickable();
    }

    @Then("booking form appear with required fields")
    public void bookingFormAppearWithRequiredFields() {
        bookingPage.assertFirstNameDisplayed();
        bookingPage.assertLastNameDisplayed();
        bookingPage.assertEmailDisplayed();
        bookingPage.assertPhoneDisplayed();
        bookingPage.assertBookButtonDisplayed();
    }

    @And("book button is clickable")
    public void bookButtonIsClickable() {
        bookingPage.assertBookButtonClickable();
    }

    @When("I fill in form with details {string}, {string}, {string}, {string}")
    public void iFillInFormWithDetails(String firstName, String lastName, String email, String phone) {
        bookingPage.fillFirstName(firstName);
        bookingPage.fillLastName(lastName);
        bookingPage.fillEmail(email);
        bookingPage.fillPhone(phone);
    }

    @And("select {int} days in future")
    public void selectDaysInFuture(int bookingDays) throws InterruptedException, AWTException {
        bookingPage.gotoNextMonth();
        bookingPage.gotoNextMonth();
        bookingPage.gotoNextMonth();
        bookingPage.selectDates(bookingDays, "June");
        Thread.sleep(3000);
    }
    @After()
    public void tearDown(){
        DriversUtils.tearDown();
    }

    @Then("error message {string} appears")
    public void errorMessageAppears(String error) {
        String errorMessage="";
        switch(error){
            case "firstNameError":
                errorMessage="invalid";
                break;
            case "firstNameLength":
                errorMessage="size must be between 3 and 18";
                break;
            case "emptyFirstName":
                errorMessage="Firstname should not be blank";
                break;
            case "lastNameError":
                errorMessage="invalid";
                break;
            case "lastNameLength":
                errorMessage="size must be between 3 and 30";
                break;
            case "lastNameEmpty":
                errorMessage="Lastname should not be blank";
                break;
            case "emailError":
                errorMessage="must be a well-formed email address";
                break;
            case "emailEmpty":
                errorMessage="must not be empty";
                break;
            case "phoneError":
                errorMessage="size must be between 11 and 21";
                break;
            case "phoneEmpty":
                errorMessage="must not be empty";
                break;

            default:
                Assert.fail("Given error message key is incorrect");
        }
        bookingPage.assertError(errorMessage);
    }

    @And("booking dates for {int} days appear in the success message")
    public void bookingDatesForDaysAppearInTheSuccessMessage(int bookingDays) {
        successPage.assertBookedDates(bookingDays,"06");
    }

    @Then("booking is successful with success message")
    public void bookingIsSuccessfulWithSuccessMessage() {
        successPage.assertSuccessMessage();
    }

    @And("select {int} days in past")
    public void selectDaysInPast(int bookingDays) throws AWTException {
        bookingPage.gotoPreviousMonth();
        bookingPage.gotoPreviousMonth();
        bookingPage.selectDates(bookingDays,"January");
    }
}
