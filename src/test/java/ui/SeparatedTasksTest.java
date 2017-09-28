package ui;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.PlaygroundPage;
import pageobjects.PlaygroundPage.Occupation;

/**
 * Created by mpaszek on 9/28/2017.
 */
public class SeparatedTasksTest extends UIBaseTestCase {

    private PlaygroundPage playgroundPage = new PlaygroundPage();

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        Selenide.open("/");
    }

    @Test(groups = "tasks")
    public void task1_titleTest() {
        playgroundPage.setTitleAnswer();
    }

    @Test(groups = "tasks")
    public void task2_nameTest() {
        playgroundPage.setName("Kilgore Trout");
    }

    @Test(groups = "tasks")
    public void task3_occupationTest() {
        playgroundPage.setOccupation(Occupation.SCI_FI_AUTHOR);
    }

    @Test(groups = "tasks")
    public void task4_blueBoxesTest() {
        playgroundPage.setBlueBoxesAnswer();
    }

    @Test(groups = "tasks")
    public void task5_linkTest() {
        playgroundPage.clickLink("click me");
    }

    @Test(groups = "tasks")
    public void task6_redBoxClassTest() {
        playgroundPage.setRedBoxClassAnswer();
    }

    @Test(groups = "tasks")
    public void task7_jsTest() {
        playgroundPage.runJavaScript("ran_this_js_function()");
    }

    @Test(groups = "tasks")
    public void task8_jsTest2() {
        playgroundPage.setJsAnswer("return got_return_from_js_function()");
    }

    @Test(groups = "tasks")
    public void task9_wroteBookTest() {
        playgroundPage.wasTheBookWritten(true);
    }

    @Test(groups = "tasks")
    public void task10_redBoxTextTest() {
        playgroundPage.setRedBoxTextAnswer();
    }

    @Test(groups = "tasks")
    public void task11_topBoxTest() {
        playgroundPage.setTopBoxAnswer();
    }

    @Test(groups = "tasks")
    public void task12_browserSizeTest() {
        playgroundPage.changeBrowserSize(850, 650);
    }

    @Test(groups = "tasks")
    public void task13_isItemOnThePageTest() {
        playgroundPage.setIsItemOnThePageAnswer();
    }

    @Test(groups = "tasks")
    public void task14_isItemVisibleOnThePageTest() {
        playgroundPage.setIsItemVisibleOnThePageAnswer();
    }

    @Test(groups = "tasks")
    public void task15_16_waitingGameTest() {
        playgroundPage.playTheGame();
        playgroundPage.endTheGame();
    }

    @Test(groups = "tasks")
    public void task17_submitFormTest() {
        playgroundPage.submitForm();
    }

    @Test(dependsOnGroups = "tasks")
    public void checkResults() {
        playgroundPage.verifyResults();
    }
}
