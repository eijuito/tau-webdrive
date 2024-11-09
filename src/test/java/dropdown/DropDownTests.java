package dropdown;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTests;

public class DropDownTests extends BaseTests {

    @Test
    public void testselectOption() {
        var dropDownPage = homePage.clickDropDown();

        String option = "Option 1";
        dropDownPage.selectFromDropDown(option);
        var selectedOptions = dropDownPage.getSelectedOption();
        assertEquals(selectedOptions.size(),1, "incorrect number of selections");
        assertTrue(selectedOptions.contains(option), "Option not selected");
    }
}
 