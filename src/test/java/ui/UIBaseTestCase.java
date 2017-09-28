package ui;

import org.testng.annotations.BeforeSuite;
import utils.SelenidePropertiesLoader;

/**
 * Created by mpaszek on 9/28/2017.
 */
public class UIBaseTestCase {

    @BeforeSuite
    public void prepareEnvironment() {
        new SelenidePropertiesLoader(true);
    }

}
