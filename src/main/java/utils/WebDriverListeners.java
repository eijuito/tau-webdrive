package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebDriverListeners implements WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
        System.out.println("Clicked the " + element.getText());
        WebDriverListener.super.beforeClick(element);
    }

}
