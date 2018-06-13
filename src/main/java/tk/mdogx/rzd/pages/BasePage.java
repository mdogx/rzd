package tk.mdogx.rzd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tk.mdogx.rzd.Utils;

public class BasePage {
    private final WebDriver driver;
    protected By dataLocator;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void checkTitle(String t){
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase(t),"Page title doesn't match");
    }

    protected void waitUntilElementIsVisibility(By by){
        WebDriverWait wait = new WebDriverWait(driver, Utils.TIMEOUT, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated((by)));
    }

    protected void waitUntilElementIsInvisibility(By by){
        WebDriverWait wait = new WebDriverWait(driver, Utils.TIMEOUT, 1000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated((by)));
    }

    protected int getNumberOfElements (By by) {
        int result;
        result = driver.findElements(by).size();
        return result;
    }
}
