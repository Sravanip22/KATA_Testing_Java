package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.DriversUtils.*;

import javax.swing.*;

import java.awt.*;

import static utils.DriversUtils.getDriver;

public class CommonUtils {

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,250)");
    }

    public static void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void clickAndDrag(WebElement fromElement, WebElement toElement) throws AWTException {
        Actions act= new Actions(getDriver());
        act.dragAndDrop(fromElement,toElement).perform();

        //Robot robot = new Robot();
        //robot.mouseMove(fromElement.getLocation().getX() + (fromElement.getSize().getWidth()/2), fromElement.getLocation().getY() + (fromElement.getSize().getWidth()/2));
        //robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        //robot.mouseMove(toElement.getLocation().getX() + (toElement.getSize().getWidth()/2), toElement.getLocation().getY() + (toElement.getSize().getWidth()/2));
        //robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
