package stas.locale;

import java.nio.charset.Charset;
import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

public class SortingLocalizedStrings {

    public static void main(String[] args) {

        Locale loc = new Locale("uk", "UA");

        Collator collator = Collator.getInstance(loc);

        // Locale[] locs = Locale.getAvailableLocales();
        // Arrays.stream(locs).forEach(x -> System.out.println(x));

        String text = "Держсекретар США Рекс Тіллерсон руйнує держапарат. "
                + "Про це йдеться в матеріалі журналіста Макса Бергмана для видання Politico. "
                + "За словами автора, держапарат перебува" + "є в хаосі, а багато службовців були"
                + "звільнені відтоді як прийшов Тіллерсон." + "Зайшовши до одного з моїх старих офісів, я "
                + "сподівався побачити колишнього колегу -"
                + "старшого офіцера зовнішньої служби, але був приголомшений,"
                + "коли дізнався, що вона раптово була вимушена піти на пенсію й залишила офіс минулого тижня."
                + "У цьому кабінеті, який колись був галасливий, була присутня лише одна особа, яка вмикала"
                + "та вимикала світло, - йдеться у матеріалі. "
                + "Більше читайте тут: https://tsn.ua/svit/tillerson-ruynuye-amerikansku-diplomatiyu-politico-953840.html";

        String tokenized[] = text.split(" ");
        /*
         * Collation Strength You can set a collator’s strength to select how
         * selective it should be. Character differences are classified as
         * primary, secondary, tertiary, and identical. For example, in English,
         * the difference between “A” and “Z” is considered primary, the
         * difference between “A” and “Å” is secondary, and between “A” and “a”
         * is tertiary. By setting the strength of the collator to
         * Collator.PRIMARY, you tell it to pay attention only to primary
         * differences. By setting the strength to Collator.SECONDARY, you
         * instruct the collator to take secondary differences into account.
         * That is, two strings will be more likely to be considered different
         * when the strength is set to “secondary” or “tertiary,”
         */
        Arrays.stream(tokenized).sorted().forEach(System.out::println);
        System.out.println("=======================Collator.TERTIARY========================");
        Arrays.stream(tokenized).sorted(collator::compare).forEach(System.out::println);
        System.out.println("======================Collator.PRIMARY=========================");
        collator.setStrength(Collator.PRIMARY);
        Arrays.stream(tokenized).sorted(collator::compare).forEach(System.out::println);
        System.out.println(collator.getStrength());


        System.out.println(Charset.availableCharsets());
        System.out.println(Charset.defaultCharset());
        /*
         * DECOMPOSITION Occasionally, a character or sequence of characters can
         * be described in more than one way in Unicode. For example, an “Å” can
         * be Unicode character U+00C5, or it can be expressed as a plain A
         * (U+0065) followed by a ° (“combining ring above”; U+030A). Perhaps
         * more surprisingly, the letter sequence “ffi” can be described with a
         * single character “Latin small ligature ffi” with code U+FB03. (One
         * could argue that this is a presentation issue that should not have
         * resulted in different Unicode characters, but we don’t make the
         * rules.) The Unicode standard defines four normalization forms (D, KD,
         * C, and KC) for strings. See
         * www.unicode.org/unicode/reports/tr15/tr15-23.html for the details.
         * Two of them are used for collation. In the normalization form D,
         * accented characters are decomposed into their base letters and
         * combining accents. For example, Å is turned into a sequence of an A
         * and a combining ring above °. Normalization form KD goes further and
         * decomposes compatibility characters such as the ffi ligature or the
         * trademark symbol ™. You can choose the degree of normalization that
         * you want the collator to use. The value Collator.NO_DECOMPOSITION
         * does not normalize strings at all. This option is faster, but it
         * might not be appropriate for text that expresses characters in
         * multiple forms.Collator.CANONICAL_DECOMPOSITION, uses the
         * normalization form D. This is the most useful form for text that
         * contains accents but not ligatures. Finally, “full decomposition”
         * uses normalization form KD
         */
    }

}
