package stas.datetime;

import java.time.*;

public class Between_DurationPeriod {

    public static void main(String[] args) {


        LocalDate d2 = LocalDate.now();

        LocalDate d3 = d2.plusDays(1);

        LocalTime t1 = LocalTime.now();
        LocalDateTime td = LocalDateTime.now().plusSeconds(334);


        System.out.println(Period.between(d2, d3));//P1D
//		System.out.println(Duration.between(d2, d3));//UnsupportedTemporalTypeException: 
        //Unsupported unit: Seconds
//		System.out.println(Period.between(t1, d3));//Does not compile. Only LocalDate allowed

        System.out.println(Duration.between(t1, td));//PT5M34.001S  == 334 seconds
//		System.out.println(Duration.between(td, t1)); //Unable to obtain LocalDateTime from TemporalAccessor

        td = LocalDateTime.now();
        LocalDateTime td2 = td.plusDays(55555).plusSeconds(44);
        System.out.println(Duration.between(td, td2));//PT1333320H44S == 55555 Days and 44 Seconds
        System.out.println(Duration.between(ZonedDateTime.now(), Instant.now()));

    }

}
