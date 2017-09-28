package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;

/**
 * Created by mpaszek on 9/28/2017.
 */
public class WebDriverHelper {

    public static String getCurrentPageTitle() {
        return WebDriverRunner.getWebDriver().getTitle();
    }

    public static void changeBrowserSize(int newWidth, int newHeight) {
        Dimension newDimension = new Dimension(newWidth, newHeight);
        WebDriverRunner.getWebDriver().manage().window().setSize(newDimension);
    }

    public static void acceptAlert() {
        WebDriverRunner.getWebDriver().switchTo().alert().accept();
    }
}
