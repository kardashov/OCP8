package stas.enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EnumSetTest {

    public EnumSetTest() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        final Set<DaysOfTheWeekFields> enumSetSome = EnumSet.of(DaysOfTheWeekFields.SUNDAY,
                DaysOfTheWeekFields.SATURDAY);
        enumSetSome.add(DaysOfTheWeekFields.MONDAY);
        enumSetSome.clear();
        enumSetSome.add(DaysOfTheWeekFields.MONDAY);
        enumSetSome.add(DaysOfTheWeekFields.SATURDAY);

        Map<DaysOfTheWeekFields, String> enumMapSome = new EnumMap<>(DaysOfTheWeekFields.class);
        enumMapSome.put(DaysOfTheWeekFields.FRIDAY, "It's Friday");

        System.out.println(enumSetSome); //[MONDAY, SATURDAY]
        System.out.println(enumMapSome); //{FRIDAY=It's Friday}


        for (Entry<DaysOfTheWeekFields, String> entry : enumMapSome.entrySet()) {
            System.out.println(entry);
            enumMapSome.put(DaysOfTheWeekFields.SATURDAY, "It's SATURDAY");
        }
        System.out.println(enumMapSome);

    }
}
