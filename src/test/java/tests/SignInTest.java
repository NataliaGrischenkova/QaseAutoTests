package tests;

import org.junit.jupiter.api.Test;


public class SignInTest extends BaseTest {

    @Test
    public void userShouldBeLoginWithValidLoginAndPassword() {
        signInPage.openPage()
                .setEmail("hf1bg@virgilian.com")
                .setPassword("gdft1ywbo123")
                .clickSignInButton();

        projectsPage.shouldBeOpened();
    }

    @Test
    void shouldShowErrorWhenEmailIsEmpty() {
        signInPage.openPage()
                .setPassword("gdft1ywbo123")
                .clickSignInButton()
                .emailFieldShouldShowRequiredError("This field is required");
    }

    @Test
    void shouldShowErrorForEmailWithoutDomain() {
        signInPage.openPage()
                .setEmail("hf1bg@")
                .setPassword("gdft1ywbo123")
                .clickSignInButton()
                .getErrorMessage("Value 'hf1bg@' does not match format email of type string");
    }

    @Test
    void shouldShowErrorForEmailWithoutUsername() {
        signInPage.openPage()
                .setEmail("@virgilian.com")
                .setPassword("gdft1ywbo123")
                .clickSignInButton()
                .getErrorMessage("Value '@virgilian.com' does not match format email of type string");
    }

    @Test
    void shouldShowErrorForEmailWithoutSymbolAt() {
        signInPage.openPage()
                .setEmail("hf1bgvirgilian.com")
                .setPassword("gdft1ywbo123")
                .clickSignInButton()
                .getErrorMessage("Value 'hf1bgvirgilian.com' does not match format email of type string");
    }

    @Test
    void shouldShowErrorForEmailWithLeadingSpace() {
        signInPage.openPage()
                .setEmail(" hf1bg@virgilian.com")
                .setPassword("gdft1ywbo123")
                .clickSignInButton()
                .getErrorMessage("Value ' hf1bg@virgilian.com' does not match format email of type string");
    }

    @Test
    void shouldShowErrorForEmailWithTrailingSpace() {
        signInPage.openPage()
                .setEmail("hf1bg@virgilian.com ")
                .setPassword("gdft1ywbo123")
                .clickSignInButton()
                .getErrorMessage("Value 'hf1bg@virgilian.com ' does not match format email of type string");
    }

    @Test
    void shouldShowErrorWhenPasswordIsEmpty() {
        signInPage.openPage()
                .setEmail("hf1bg@virgilian.com")
                .clickSignInButton()
                .passwordFieldShouldShowRequiredError("This field is required");
    }

    @Test
    void shouldShowErrorForInvalidPassword() {
        signInPage.openPage()
                .setEmail("hf1bg@virgilian.com")
                .setPassword("wrongPassword")
                .clickSignInButton();

        passwordResetPage.shouldBeOpened();
    }

    @Test
    void shouldShowErrorWhenBothFieldsAreEmpty() {
        signInPage.openPage()
                .clickSignInButton()
                .emailFieldShouldShowRequiredError("This field is required")
                .passwordFieldShouldShowRequiredError("This field is required");
    }
}
