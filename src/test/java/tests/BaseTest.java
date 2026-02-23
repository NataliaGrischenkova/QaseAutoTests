package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.Credentials;
import driver.UIDriver;
import io.qameta.allure.selenide.AllureSelenide;
import models.ProjectFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.ProjectsPage;
import pages.LoginPage;
import pages.SuitePage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;


public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;
    protected ProjectFactory projectFactory;
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
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        projectFactory = new ProjectFactory();
        suitePage = new SuitePage();
    }

    @BeforeEach
    void deleteAllProjectsIfNeeded() {
        step("Удалить все проекты",
                ()-> projectsPage.deleteAllProjects());
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
