package tk.mdogx.rzd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tk.mdogx.rzd.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchedulePage extends BasePage {

    private String title = "Пассажирам";
    private By dataLocator = By.xpath("//div[@class='route-item']");
    private String trainLocatorTemplate = "//div[@class='route-item' and *//span[contains(text(),'<trainNumber>')]]//div[contains(text(),'<trainType>')]";
    private By seatDescriptionLocator = By.xpath("//div[@class='col-xs-5 text-center']");
    private By carNumberLocator = By.xpath("//div[@class='col-xs-4']//span[@class='route-car-num']");
    private By seatVacantLocator = By.xpath("//*[@class='s-cell s-type-seat']");
    private By seatBookedLocator = By.xpath("//*[@class='s-cell s-type-seat s-occup']");
    private By seatForDisabledPersonVacantLocator = By.xpath("//*[@class='s-cell s-type-dn s-clickable']");
    private By seatForDisabledPersonBookedLocator = By.xpath("//*[@class='s-cell s-type-dn s-occup s-has-info']");
    private By carSeatsLocator = By.xpath("//div[@class='route-car-scheme-wrap']");

    private final WebDriver driver;

    public SchedulePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        Assert.assertEquals(title, driver.getTitle(), ">>> Wrong page title!");

        // Waiting for train data loaded
        waitUntilElementIsVisibility(dataLocator);
    }

    public SchedulePage selectTrain(String trainNumber, String carType) {
        driver.findElement(getTrainLocator(trainNumber, carType)).click();
        Utils.addLogs("Select train " + trainNumber + " and cars type " + carType + ".");
        return this;
    }

    private By getTrainLocator(String trainNumber, String carType) {
        String trainLocatorString = trainLocatorTemplate;
        trainLocatorString = trainLocatorString.replaceAll("<trainNumber>", trainNumber);
        trainLocatorString = trainLocatorString.replaceAll("<trainType>", carType);
        return By.xpath(trainLocatorString);
    }

    private int vacantSeatsInSelectedTrain() {
        waitUntilElementIsVisibility(seatDescriptionLocator);
        List<WebElement> vacantSeatsElementList = driver.findElements(seatDescriptionLocator);
        List<String> vacantSeatsStringList = new ArrayList<>();
        int availablePlaces = 0;

        vacantSeatsStringList.addAll(vacantSeatsElementList.stream().map(e -> e.getText().replaceAll("[\\D]+", "")).collect(Collectors.toList()));

        for (String aVacantSeatsStringList : vacantSeatsStringList) {
            {
                availablePlaces += Integer.parseInt(aVacantSeatsStringList);
            }
        }
        return availablePlaces;
    }

    public SchedulePage countVacantSeatsOfSelectedTrain() {
        System.out.println("> Available " + vacantSeatsInSelectedTrain() + " place(s) in selected train.");
        Utils.addLogs("Total available " + vacantSeatsInSelectedTrain() + " seat in selected train.");
        return this;
    }

    public SchedulePage countVacantSeatsInAllCarsOfSelectedTrain() {
        List<WebElement> carsNumber = driver.findElements(carNumberLocator);
        for (WebElement c : carsNumber) {
            c.click();
            waitUntilElementIsVisibility(carSeatsLocator);
            String message;
            message = "> In car " + c.getText() + " is available " + getNumberOfElements(seatVacantLocator) + " seat(s)";
            if (getNumberOfElements(seatForDisabledPersonVacantLocator) > 0)
                message += " and " + getNumberOfElements(seatForDisabledPersonVacantLocator) + " place(s) for disabled people";
            message += ".";
            c.click();
            waitUntilElementIsInvisibility(carSeatsLocator);
            System.out.println(message);
            Utils.addLogs(message);
        }
        return this;
    }
}
