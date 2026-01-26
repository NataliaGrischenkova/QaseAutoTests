package pages.pageElements;

import static com.codeborne.selenide.Selenide.$x;

public class Input {

    public static void enter(String placeholder, String value) {
        $x("//input[@placeholder = '" + placeholder + "']").setValue(value);
    }
}
