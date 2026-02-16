package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.Credentials;
import driver.UIDriver;
import io.qameta.allure.selenide.AllureSelenide;
import models.CreateProjectFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.ProjectsPage;
import pages.SignInPage;
import pages.SuitePage;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class BaseTest {

    protected SignInPage signInPage;
    protected ProjectsPage projectsPage;
    protected CreateProjectFactory projectFactory;
    protected SuitePage suitePage;
    protected String email = Credentials.config.getEmail();
    protected String password = Credentials.config.getPassword();

    @BeforeAll
    public static void configuration() {
        UIDriver.configuration();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        signInPage = new SignInPage();
        projectsPage = new ProjectsPage();
        projectFactory = new CreateProjectFactory();
        suitePage = new SuitePage();
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
