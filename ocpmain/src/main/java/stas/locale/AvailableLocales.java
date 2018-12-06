package stas.locale;

import java.util.Locale;

//language + "_" + country + "_" + (variant + "_#" | "#") + script + "-" + extensions
//For the locale code of th_TH_TH_#u-nu-thai,
//The language code is th (Thai) and it is always written in lowercase.
//The country code is TH (Thailand) and it is always written in uppercase.
//The variant name is TH; here it repeats the country code, but it could be any string.
//The script name is an empty string here; if given, it will be a four-letter string with the first letter in uppercase and the rest in lowercase (e.g., Latn).
//The extension follows the # or _# character; it is u-nu-thai in this example.
class AvailableLocales {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ITALIAN);
        System.out.println("The default locale is: " + Locale.getDefault());

        Locale[] locales = Locale.getAvailableLocales();
        System.out.printf("No. of other available locales is: %d, and they are: %n", locales.length);

        for (Locale locale : locales) {
            System.out.printf("Locale code: %s and it stands for %s %n", locale, locale.getDisplayName());

        }
    }
}
