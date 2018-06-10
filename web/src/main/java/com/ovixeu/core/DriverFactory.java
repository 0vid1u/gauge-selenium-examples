package com.ovixeu.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

public class DriverFactory {
    final static Logger logger = Logger.getLogger(DriverFactory.class);
    private final BrowserType browserType;

    public DriverFactory() {
        String browserType = System.getenv("browser");
        checkNotNull(browserType);
        for (BrowserType browserTypeEnum : BrowserType.values()) {
            if (StringUtils.equalsIgnoreCase(browserTypeEnum.getBrowserTypeString(), browserType)) {
                this.browserType = browserTypeEnum;
                return;
            }
        }
        logger.error("Invalid browser type set in properties " + browserType);
        throw new IllegalArgumentException("Invalid browser type set in properties " + browserType);
    }

    public WebDriver getDriver() throws Exception {

        final WebDriver webDriver;
        DesiredCapabilities capabilities;

        switch (browserType) {
            case CHROME:
                webDriver = new ChromeDriver();
                break;
            case EDGE:
                webDriver = new EdgeDriver();
                break;
            case FIREFOX:
                webDriver = new FirefoxDriver();
                break;
            case IE:
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability("webdriver.ie.driver", "C:\\selenium\\IEDriverServer.exe");
                webDriver = new RemoteWebDriver(new URL("http://10.10.22.113:4444/wd/hub"), capabilities);
                break;
            case SAFARI:
                webDriver = new SafariDriver();
                break;
            default:
                logger.error("Invalid browser type set " + browserType.getBrowserTypeString());
                throw new IllegalArgumentException("Invalid browser type set " + browserType.getBrowserTypeString());
        }

        initWebDriver(webDriver);
        return webDriver;
    }

    private void initWebDriver(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(Long.parseLong(System.getenv("default_timeout")), TimeUnit.SECONDS);
    }

    public Class<?> getObjectType() {
        return WebDriver.class;
    }

    private enum BrowserType {

        FIREFOX("firefox"),
        IE("ie"),
        SAFARI("safari"),
        CHROME("chrome"),
        EDGE("edge");

        private final String browserType;

        BrowserType(final String browserType) {
            this.browserType = browserType;
        }

        public String getBrowserTypeString() {
            return browserType;
        }
    }

}
