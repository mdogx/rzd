package tk.mdogx.rzd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tk.mdogx.rzd.Utils;

public class PassengersPage extends BasePage {
    String title = "Пассажирам";
    By routeFromTextLocator = By.id("name0");
    By routeToTextLocator = By.id("name1");
    By submitButtonLocator = By.id("Submit");
    By calendarButtonLocator = By.xpath("//div[@class='box-form__datetime__calendar sh_calendar']");
    By calendarLocator = By.id("ticketbuyforma_horizontalTwo");
    String dateLocatorTemplate = "//div[@class='month_wrap' and div[@class='month_title']/span[contains(text(),'<month>')]]//span[contains(text(),'<day>')]";

    private final WebDriver driver;

    public PassengersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        Assert.assertEquals(title,driver.getTitle(),">>> Wrong page title!");
    }

    public PassengersPage enterRouteFromText(String s) {
        driver.findElement(routeFromTextLocator).sendKeys(s);
        System.out.println(">>> Select route from " + s + ".");
        return this;
    }

    public PassengersPage enterRouteToText(String s) {
        driver.findElement(routeToTextLocator).sendKeys(s);
        System.out.println(">>> Select route to " + s + ".");
        return this;
    }

    public PassengersPage enterDate(String day, String month) {
        driver.findElement(calendarButtonLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Utils.getTimeout(), 1000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(getDateLocator(day, month)))).click();
        System.out.println(">>> Select trains for " + day + " " + month + ".");
        return this;
    }

    public By getDateLocator(String month, String day) {
        String dateLocatorString = dateLocatorTemplate;
        dateLocatorString = dateLocatorString.replaceAll("<month>", month);
        dateLocatorString = dateLocatorString.replaceAll("<day>", day);
        By dateLocator = By.xpath(dateLocatorString);
        return dateLocator;
    }

    public boolean calendarIsVisibility() {
        boolean result = false;
        result = driver.findElement(calendarLocator).isDisplayed();
        return result;
    }

    public SchedulePage pressSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, Utils.getTimeout(), 1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(submitButtonLocator)));
        driver.findElement(submitButtonLocator).click();
        System.out.println(">>> Open train schedule.");
        return new SchedulePage(driver);
    }
}