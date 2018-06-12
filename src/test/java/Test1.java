import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tk.mdogx.rzd.Utils;
import tk.mdogx.rzd.pages.PassengersPage;

public class Test1 {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println(">>> Launching " + Utils.BROWSER + " browser.");
        driver = Utils.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void countAvailablePlaces() {
        Utils.openPage(Utils.BASEURL);
        PassengersPage page = new PassengersPage(driver);
        page.enterRouteDate("Июнь", "16")
                .enterRoute("Москва", "Тула")
                .pressRouteSubmit()
                .countAvailvablePlacesInTrain("141Ч", "Купе");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println(">>> Close browser.");
            driver.quit();
        }
    }
}
