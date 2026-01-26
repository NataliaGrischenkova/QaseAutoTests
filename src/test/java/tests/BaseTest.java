package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.PasswordResetPage;
import pages.ProjectsPage;
import pages.SignInPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class BaseTest {

    SignInPage signInPage;
    ProjectsPage projectsPage;
    PasswordResetPage passwordResetPage;

    @BeforeAll
    public static void before() {
    }

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://app.qase.io";

        signInPage = new SignInPage();
        projectsPage = new ProjectsPage();
        passwordResetPage = new PasswordResetPage();
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @AfterAll
    public static void after() {
    }
}
