package ru.tw1911.testforsber.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.MultiInjection;
import ru.tw1911.testforsber.entity.AppConfig;
import ru.tw1911.testforsber.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Init {
    private MutablePicoContainer container;
    private Properties properties;

    public Init(){
        FileInputStream fis;
        properties = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/application.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        this.container = new DefaultPicoContainer(new MultiInjection());
        container.addComponent(createUser());
        container.addComponent(createDriver());
        container.addComponent(createAppConfig());
        container.addComponent(CustomFieldDecorator.class);
        container.addComponent(new SimpleStack<String>());
    }

    public MutablePicoContainer getContainer(){
        return container;
    }

    private User createUser(){
        return new User(properties.getProperty("app.user.login"),properties.getProperty("app.user.password"));
    }

    private AppConfig createAppConfig(){
        AppConfig appConfig = new AppConfig();
        appConfig.setUrl(properties.getProperty("app.url"));
        return appConfig;
    }

    private WebDriver createDriver(){
        String browser = properties.getProperty("test.browser");
        WebDriver driver;
        switch (browser){
            case "chrome":{ driver = new ChromeDriver(); break;}
            case "firefox": { driver = new FirefoxDriver(); break;}
            case "ie": { driver = new InternetExplorerDriver(); break;}
            case "selenoid": {
                DesiredCapabilities capabilitie = new DesiredCapabilities();
                capabilitie.setBrowserName("chrome");
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://localhost:4444/wd/hub").toURL(),
                            capabilitie
                    );
                    return driver;
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            default: driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        return driver;
    }
}
