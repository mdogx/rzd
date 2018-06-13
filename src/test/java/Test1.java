import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tk.mdogx.rzd.Utils;
import tk.mdogx.rzd.pages.PassengersPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println(">>> Launching " + Utils.BROWSER + " browser.");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Utils.addLogs("Test started at " + dateFormat.format(date) + " on " + Utils.BROWSER + ".");
        driver = Utils.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void countAvailablePlaces() {
        Utils.openPage(Utils.BASEURL);
        PassengersPage page = new PassengersPage(driver);
        page.enterRouteDate("Июль", "26")
                .enterRoute("Москва", "Тула")
                .pressRouteSubmit()
                .selectTrain("741А", "Купе")
                .countVacantSeatsOfSelectedTrain()
                .countVacantSeatsInAllCarsOfSelectedTrain();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println(">>> Close browser.");
            driver.quit();
            Utils.addLogs("");
        }
    }
}
