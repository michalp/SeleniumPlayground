package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.WebDriverHelper;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by mpaszek on 9/28/2017.
 */
public class PlaygroundPage extends UIBasePage {

    private SelenideElement checkResultsButton = $(byId("checkresults"));
    private SelenideElement testForm = $(byId("testform"));
    private SelenideElement nameTextbox = testForm.$(byId("name"));
    private SelenideElement occupationDropdown = testForm.$(byId("occupation"));
    private SelenideElement wroteBookRadioButton = testForm.$("input[name='wrotebook'][value='wrotebook']");
    private SelenideElement didntWriteBookRadioButton = testForm.$("input[name='wrotebook'][value='didntwritebook']");
    private SelenideElement submitQueryButton = testForm.$(byId("submitbutton"));

    private SelenideElement redBoxElement = $(byId("redbox"));

    private SelenideElement answerTitleTextbox = $(byId("answer1"));
    private SelenideElement answerBlueBoxesTextbox = $(byId("answer4"));
    private SelenideElement answerRedBoxClassTextbox = $(byId("answer6"));
    private SelenideElement answerJsValueTextbox = $(byId("answer8"));
    private SelenideElement answerRedBoxTextTextbox = $(byId("answer10"));
    private SelenideElement answerTobBoxTextbox = $(byId("answer11"));
    private SelenideElement answerIsItemOnThePageTextbox = $(byId("answer13"));
    private SelenideElement answerIsItemVisibleOnThePageTextbox = $(byId("answer14"));

    public PlaygroundPage setTitleAnswer() {
        String pageTitle = WebDriverHelper.getCurrentPageTitle();
        answerTitleTextbox.val(pageTitle);
        return this;
    }

    public PlaygroundPage setName(String name) {
        nameTextbox.val(name);
        return this;
    }

    public PlaygroundPage setOccupation(Occupation occupation) {
        occupationDropdown.selectOption(occupation.getDescription());
        return this;
    }

    public PlaygroundPage setBlueBoxesAnswer() {
        int blueBoxesCount = $$("*[class='bluebox']").size();
        answerBlueBoxesTextbox.val(String.valueOf(blueBoxesCount));
        return this;
    }

    public PlaygroundPage clickLink(String linkText) {
        return clickLink(linkText, false);
    }

    public PlaygroundPage clickLink(String linkText, boolean waitForLink) {
        if (waitForLink) {
            waitForLink(linkText);
        }
        $(byLinkText(linkText)).click();
        return this;
    }

    private void waitForLink(String linkText) {
        for (int i = 0; i < 10000; i += 100) {
            if ($(byLinkText(linkText)).exists()) {
                break;
            }
            sleep(100);
        }
    }

    public PlaygroundPage setRedBoxClassAnswer() {
        String redBoxClass = redBoxElement.getAttribute("class");
        answerRedBoxClassTextbox.val(redBoxClass);
        return this;
    }

    public PlaygroundPage runJavaScript(String javaScript) {
        executeJs(javaScript);
        return this;
    }

    public PlaygroundPage setJsAnswer(String javaScript) {
        String jsReturnValue = executeJs(javaScript).toString();
        answerJsValueTextbox.val(jsReturnValue);
        return this;
    }

    private <T> T executeJs(String javaScript) {
        return Selenide.executeJavaScript(javaScript);
    }

    public PlaygroundPage wasTheBookWritten(boolean wroteTheBook) {
        if (wroteTheBook) {
            wroteBookRadioButton.click();
        } else {
            didntWriteBookRadioButton.click();
        }
        return this;
    }

    public PlaygroundPage setRedBoxTextAnswer() {
        String redBoxText = redBoxElement.getText();
        answerRedBoxTextTextbox.val(redBoxText);
        return this;
    }

    public PlaygroundPage setTopBoxAnswer() {
        SelenideElement elementOnTop = $(By.xpath("//*[text()='Boxes to check arrangement of']/following-sibling::span[1]"));
        String color = elementOnTop.getText().contains("green") ? "green" : "orange";
        answerTobBoxTextbox.val(color);
        return this;
    }

    public PlaygroundPage changeBrowserSize(int width, int height) {
        WebDriverHelper.changeBrowserSize(width, height);
        return this;
    }

    public PlaygroundPage setIsItemOnThePageAnswer() {
        boolean isElementPresent = $(byId("ishere")).exists();
        String answer = isElementPresent ? "yes" : "no";
        answerIsItemOnThePageTextbox.val(answer);
        return this;
    }

    public PlaygroundPage playTheGame() {
        clickLink("click then wait");
        clickLink("click after wait", true);
        return this;
    }

    public PlaygroundPage endTheGame() {
        WebDriverHelper.acceptAlert();
        return this;
    }

    public PlaygroundPage setIsItemVisibleOnThePageAnswer() {
        boolean isElementVisible = $(byId("purplebox")).isDisplayed();
        String answer = isElementVisible ? "yes" : "no";
        answerIsItemVisibleOnThePageTextbox.val(answer);
        return this;
    }

    public PlaygroundPage submitForm() {
        submitQueryButton.click();
        return this;
    }

    public PlaygroundPage verifyResults() {
        checkResultsButton.click();
        $(byId("showresults")).shouldHave(Condition.text("of 17 tests"));
        ElementsCollection failedTests = $$("span[class='ok fail']");

        if (failedTests.size() != 0) {
            throw new RuntimeException("Not all tests were passed! FAILED TESTS:\n" + prepareErrorMessage(failedTests));
        }
        return this;
    }

    private List prepareErrorMessage(ElementsCollection failedTests) {
        List<String> failedTasksText = new ArrayList<>();

        int i = 1;
        for (SelenideElement element : failedTests) {
            String taskText = element.parent().getText();
            failedTasksText.add(taskText.replace("NOT OK", "\n" + i++ + ". "));
        }
        return failedTasksText;
    }


    public enum Occupation {
        ASTRONAUT("Astronaut"),
        POLITICIAN("Politician"),
        SCI_FI_AUTHOR("Science Fiction Author");

        String description;

        Occupation(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

}
