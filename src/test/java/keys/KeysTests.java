package keys;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import base.BaseTests;

public class KeysTests extends BaseTests {

    @Test
    public void testBackSpace() {
        var keyPage = homePage.clickKeyPresses();
        keyPage.enterText("A" + Keys.BACK_SPACE);
        assertEquals(keyPage.getResult(), "You entered: BACK_SPACE");
    }

    @Test
    public void testPi() {
        var keyPage = homePage.clickKeyPresses();
        keyPage.enterPi();
        assertEquals(keyPage.getResult(), "You entered: 4");
    }

}
