package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.PasswordResetPage;
import pages.ProjectsPage;
import pages.SignInPage;

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

        signInPage = new SignInPage();
        projectsPage = new ProjectsPage();
        passwordResetPage = new PasswordResetPage();
    }

    @AfterAll
    public static void after() {
    }
}
