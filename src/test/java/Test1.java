import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tk.mdogx.rzd.Utils;
import tk.mdogx.rzd.pages.PassengersPage;

public class Test1 {

    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println(">>> Launching " + Utils.getBrowser() + " browser.");
        driver = Utils.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void countAvailablePlaces() {
        driver.get(Utils.getBaseurl());
        System.out.println(">>> Open " + Utils.getBaseurl() + " page.");
        PassengersPage page = new PassengersPage(driver);
        page.enterDate("Июнь", "16")
                .enterRouteFromText("Москва")
                .enterRouteToText("Тула")
                .pressSubmit()
                .waitUntilPageLoaded()
                .selectTrain("743В", "Купе")
                .countAvailablePlaces();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println(">>> Close browser.");
            driver.quit();
        }
    }

}
