package driver;

import com.codeborne.selenide.Configuration;
import config.Driver;
import org.openqa.selenium.MutableCapabilities;

public class UIDriver {

    public static void configuration() {

        Configuration.browserSize = Driver.config.getBrowserSize();
        Configuration.baseUrl = Driver.config.getBaseUrl();
        Configuration.browser = Driver.config.getBrowser();
        Configuration.timeout = 10000;
        Configuration.pollingInterval = 200;

        BrowserType browserType =
                BrowserType.valueOf(Driver.config.getBrowser().toUpperCase());

        MutableCapabilities capabilities =
                BrowserFactory.getCapabilities(browserType);

        Configuration.browserCapabilities = capabilities;
    }
}
