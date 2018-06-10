package tk.mdogx.rzd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Utils {
    private final static String BROWSER = getPropertyByKey("browser").toLowerCase();
    private final static String BASEURL = getPropertyByKey("baseUrl").toLowerCase();
    private final static Long TIMEOUT = Long.parseLong(getPropertyByKey("timeout"));

    public static WebDriver getDriver() {
        WebDriver webDriver = null;
        switch (BROWSER) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
        }
        return webDriver;
    }

    public static String getPropertyByKey(String key) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src\\main\\java\\tk\\mdogx\\rzd\\test.properties");
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {
            prop.load(isr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public static Long getTimeout() {
        return TIMEOUT;
    }

    public static String getBaseurl() {
        return BASEURL;
    }

    public static String getBrowser() {
        return BROWSER;
    }

}
