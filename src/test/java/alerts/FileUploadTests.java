package alerts;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import base.BaseTests;

public class FileUploadTests extends BaseTests {

    @Test
    public void testFileUpload() {
        var uploadPage = homePage.clickFileUpload();
        uploadPage.uploadFile("C:\\Users\\celso.ito\\WS\\webriver.demo\\demo\\resources");
        assertEquals(uploadPage.getUploadedFiles(), "chromedriver.exe", "uploaded");
    }

}
