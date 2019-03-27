package ru.tw1911.testforsber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.tw1911.testforsber.annotations.PageAction;

public class MainPage extends AbstractPage{

    @FindBy(xpath = "//a[.//text()='Войти в почту']")
    private WebElement loginButton;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void open(){
        driver.get("https://yandex.ru");
    }

    @PageAction("нажимает кнопку \"Войти в почту\"")
    public void clickLoginButton(){

    }

    @PageAction("засовывает палец в жопу")
    public void stickFingerInAss(){
        System.out.println("Палец в жопе!");
    }
}
