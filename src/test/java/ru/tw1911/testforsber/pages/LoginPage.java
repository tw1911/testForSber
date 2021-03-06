package ru.tw1911.testforsber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.picocontainer.annotations.Inject;
import ru.tw1911.testforsber.annotations.ElementTitle;
import ru.tw1911.testforsber.annotations.PageAction;
import ru.tw1911.testforsber.annotations.PageTitle;
import ru.tw1911.testforsber.elements.WebButton;
import ru.tw1911.testforsber.elements.WebInput;
import ru.tw1911.testforsber.entity.User;

@PageTitle("Вход в Trello")
public class LoginPage extends AbstractPage{

    @Inject
    User user;

    @FindBy(name = "user")
    private WebInput loginInput;

    @FindBy(name = "password")
    private WebInput passwordInput;

    @FindBy(xpath = "//input[@value='Войти']")
    @ElementTitle("Войти")
    private WebButton loginButton;

    @PageAction("вводит логин и пароль из настроек")
    public void enterLoginAndPassword(){
        loginInput.sendKeys(user.getLogin());
        passwordInput.sendKeys(user.getPassword());
        loginButton.click();
    }
}
