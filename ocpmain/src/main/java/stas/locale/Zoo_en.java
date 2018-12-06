package stas.locale;

import java.util.ListResourceBundle;

public class Zoo_en extends ListResourceBundle {
    /*	The ListResourceBundle
        abstract class leaves one method for subclasses to implement.*/
    protected Object[][] getContents() {
        return new Object[][]{{"hello", "Hello"},
                {"open", "The zoo is open"},
        };
    }
}