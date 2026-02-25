package driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserFactory {

    public static MutableCapabilities getCapabilities(BrowserType browserType) {

        switch (browserType) {

            case CHROME:
                return new ChromeOptions()
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-infobars")
                        .addArguments("--lang=en-US");

            case SAFARI:
                return new SafariOptions();

            default:
                throw new IllegalArgumentException("Browser not supported: " + browserType);
        }
    }
}
