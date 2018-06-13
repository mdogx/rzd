package tk.mdogx.rzd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;

public class Utils {

    private static WebDriver webDriver;

    public final static String BROWSER = getPropertyByKey("browser").toLowerCase();
    public final static String BASEURL = getPropertyByKey("baseUrl").toLowerCase();
    public final static Long TIMEOUT = Long.parseLong(getPropertyByKey("timeout"));

    public static WebDriver getDriver() {
        if (webDriver != null) {
            return webDriver;
        }
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

    public static void openPage(String URL) {
        getDriver().get(URL);
        System.out.println(">>> Open " + Utils.BASEURL + " page.");
    }

    public static String getPropertyByKey(String key) {
        // Take properties from file
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

    public static void addLogs(String text) {

        File file = new File("src\\main\\java\\tk\\mdogx\\rzd\\log.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.newLine();
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
