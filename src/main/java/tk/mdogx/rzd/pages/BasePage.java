package tk.mdogx.rzd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tk.mdogx.rzd.Utils;

public class BasePage {
    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTitle(String t){
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase(t),"Page title doesn't match");
    }

    public void waitUntilElementLoaded(By by){
        WebDriverWait wait = new WebDriverWait(driver, Utils.getTimeout(), 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated((by)));
    }
}
