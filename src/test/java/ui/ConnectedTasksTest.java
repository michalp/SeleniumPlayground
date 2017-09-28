package ui;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.PlaygroundPage;

/**
 * Created by mpaszek on 9/28/2017.
 */
public class ConnectedTasksTest extends UIBaseTestCase {

    private PlaygroundPage playgroundPage = new PlaygroundPage();

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        Selenide.open("/");
    }

    @Test
    public void allTasksTest() {
        playgroundPage
                .setTitleAnswer()
                .setName("Kilgore Trout")
                .setOccupation(PlaygroundPage.Occupation.SCI_FI_AUTHOR)
                .setBlueBoxesAnswer()
                .clickLink("click me")
                .setRedBoxClassAnswer()
                .runJavaScript("ran_this_js_function()")
                .setJsAnswer("return got_return_from_js_function()")
                .wasTheBookWritten(true)
                .setRedBoxTextAnswer()
                .setTopBoxAnswer()
                .changeBrowserSize(850, 650)
                .setIsItemOnThePageAnswer()
                .setIsItemVisibleOnThePageAnswer()
                .playTheGame()
                .endTheGame()
                .submitForm();
    }

    @Test(dependsOnMethods = "allTasksTest")
    public void checkResults() {
        playgroundPage.verifyResults();
    }
}
