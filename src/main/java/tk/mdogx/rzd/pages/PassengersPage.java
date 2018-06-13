package tk.mdogx.rzd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tk.mdogx.rzd.Utils;

public class PassengersPage extends BasePage {
    private String title = "Пассажирам";
    private By routeFromTextLocator = By.id("name0");
    private By routeToTextLocator = By.id("name1");
    private By submitButtonLocator = By.id("Submit");
    private By calendarButtonLocator = By.xpath("//div[@class='box-form__datetime__calendar sh_calendar']");
    private String dateLocatorTemplate = "//div[@class='month_wrap' and div[@class='month_title']/span[contains(text(),'<month>')]]//span[contains(text(),'<day>')]";

    private final WebDriver driver;

    public PassengersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        Assert.assertEquals(title, driver.getTitle(), ">>> Wrong page title!");
    }

    public PassengersPage enterRoute(String from, String to) {
        enterRouteFromText(from);
        enterRouteToText(to);
        Utils.addLogs("Select route from " + from + " to " + to + ".");
        return this;
    }

    public PassengersPage enterRouteFromText(String from) {
        driver.findElement(routeFromTextLocator).sendKeys(from);
        return this;
    }

    public PassengersPage enterRouteToText(String to) {
        driver.findElement(routeToTextLocator).sendKeys(to);
        return this;
    }

    public PassengersPage enterRouteDate(String day, String month) {
        driver.findElement(calendarButtonLocator).click();
        By dateLocator = getDateLocator(day, month);
        waitUntilElementIsVisibility(dateLocator);
        driver.findElement(dateLocator).click();
        Utils.addLogs("Select route date " + day + " " + month + ".");
        return this;
    }

    public By getDateLocator(String month, String day) {
        String dateLocatorString = dateLocatorTemplate;
        dateLocatorString = dateLocatorString.replaceAll("<month>", month);
        dateLocatorString = dateLocatorString.replaceAll("<day>", day);
        return By.xpath(dateLocatorString);
    }

    public SchedulePage pressRouteSubmit() {
        waitUntilElementIsVisibility(submitButtonLocator);
        driver.findElement(submitButtonLocator).click();
        return new SchedulePage(driver);
    }
}