package tk.mdogx.rzd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SchedulePage extends BasePage {

    String title = "Пассажирам";
    By pageLocator = By.id("Page0");
    String trainLocatorTemplate = "//div[@class='route-item' and *//span[contains(text(),'<trainNumber>')]]//div[contains(text(),'<trainType>')]";
    By placesLocator = By.xpath("//div[@class='col-xs-5 text-center']");

    private final WebDriver driver;

    public SchedulePage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        Assert.assertEquals(title,driver.getTitle(),">>> Wrong page title!");
    }

    public SchedulePage selectTrain(String trainNumber, String trainType) {
        waitUntilElementLoaded(getTrainLocator(trainNumber, trainType));
        driver.findElement(getTrainLocator(trainNumber, trainType)).click();
        System.out.println(">>> Select " + trainType + " in " + trainNumber + " train.");
        return this;
    }

    public By getTrainLocator(String trainNumber, String trainType) {
        String trainLocatorString = trainLocatorTemplate;
        trainLocatorString = trainLocatorString.replaceAll("<trainNumber>", trainNumber);
        trainLocatorString = trainLocatorString.replaceAll("<trainType>", trainType);
        By trainLocator = By.xpath(trainLocatorString);
        return trainLocator;
    }

    public SchedulePage countAvailablePlaces() {
        waitUntilElementLoaded(placesLocator);
        List<WebElement> availablePlacesElementList = driver.findElements(placesLocator);
        List<String> availablePlacesStringList = new ArrayList<>();
        int availablePlaces = 0;

        for (WebElement e : availablePlacesElementList) {
            availablePlacesStringList.add(e.getText().replaceAll("[\\D]+", ""));
        }

        for (int i = 0; i < availablePlacesStringList.size(); i++) {
            {
                availablePlaces += Integer.parseInt(availablePlacesStringList.get(i));
            }
        }
        System.out.println(">>> Availvable " + availablePlaces + " place(s) in selected train.");
        return this;
    }

    public SchedulePage waitUntilPageLoaded() {
        waitUntilElementLoaded(pageLocator);
        return this;
    }


}
