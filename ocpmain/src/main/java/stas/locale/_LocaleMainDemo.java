package stas.locale;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class _LocaleMainDemo {

    public static void main(String[] args) {

        Locale locale = Locale.getDefault();
        System.out.println(locale); // en_US
        /*
         * The underscore and country code are optional. It is valid for a
         * Locale to be only a language.
         */

        // US // can have a language without a country, but not the reverse
        // enUS // missing underscore
        // US_en // the country and language are reversed
        // EN // language must be lowercase
        //
        // The corrected versions are en and en_US .

        System.out.println(Locale.GERMAN); // de
        System.out.println(Locale.GERMANY); // de_DE

        System.out.println(new Locale("fr")); // fr
        System.out.println(new Locale("hi", "IN")); // hi_IN

        // Java will let you create a Locale with an invalid language or
        // country. However, it will
        // not match the Locale that you want to use and your program will not
        // behave as expected.

        Locale l1 = new Locale.Builder().setLanguage("en").setRegion("US").build(); // en_US
        Locale l2 = new Locale.Builder().setRegion("US").setLanguage("en").build();// en_US

        System.out.println("========================Setting DEFAULT locale================================");
        System.out.println(Locale.getDefault()); // en_US
        locale = new Locale("fr");
        Locale.setDefault(locale);
        // change the default
        System.out.println(Locale.getDefault()); // fr

        System.out.println("========================Using Resource Bundles================================");
        /*
         * A resource bundle contains the local specific objects to be used by a
         * program. It is like a map with keys and values. The resource bundle
         * can be in a property file or in a Java class. A property file is a
         * file in a specific format with key/value pairs
         */

        Locale us = new Locale("en", "US");
        Locale france = new Locale("fr", "FR");
        Locale englishCanada = new Locale("en", "CA");
        Locale frenchCanada = new Locale("fr", "CA");

        printProperties(us);
        System.out.println();
        printProperties(france);

        /*
         * Properties file format: animal=dolphin animal:dolphin animal dolphin
         * - If a line begins with # or ! , it is a comment. - Spaces before or
         * after the separator character are ignored. - Spaces at the beginning
         * of a line are ignored. - Spaces at the end of a line are NOT ignored.
         * - End a line with a backslash if you want to break the line for
         * readability. - You can use normal Java escape characters like \t and
         * \n .
         */

        // Convert ResourceBundle to Properties
        final ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
        Properties props = new Properties();
        rb.keySet().stream().forEach(k -> props.put(k, rb.getString(k)));
        System.out.println(props);

        // we have Properties available, we can get a default value:
        System.out.println(props.getProperty("notReallyAProperty"));
        System.out.println(props.getProperty("notReallyAProperty", "123"));

        /*
         * Key Found? Yes No getProperty("key") Value null getProperty("key",
         * "default") Value "default"
         */

        System.out.println("========================ResourceBundle search order=====================");
        /*
         * -- Always look for the property file after the matching Java class.
         * -- Drop one thing at a time if there are no matches. -- First drop
         * the country and then the language. -- Look at the default locale and
         * the default resource bundle last.
         */
        Locale.setDefault(new Locale("hi"));
        ResourceBundle rb1 = ResourceBundle.getBundle("Zoo", new Locale("en"));
        /*
         * 1. Zoo_hi.java java is looking for the files in such order: 2.
         * Zoo_hi.properties 3. Zoo_en.java 4. Zoo_en.properties 5. Zoo.java 6.
         * Zoo.properties
         */

//		Enumerating contents of properties files.
        Locale myloc = new Locale.Builder().setLanguage("en").setRegion("UK").build();
        ResourceBundle msgs = ResourceBundle.getBundle("stas.locale.mymsgs", myloc);
        Enumeration<String> en = msgs.getKeys();

        while (en.hasMoreElements()) { //Bundle takes only en_UK and default properties files.
            //Japanese entries are ignored
            String key = en.nextElement();
            String val = msgs.getString(key);
            System.out.println(key + " : " + val);
        }

    }

    public static void printProperties(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
        System.out.println(rb.getString("hello"));
        System.out.println(rb.getString("open"));
    }
}
