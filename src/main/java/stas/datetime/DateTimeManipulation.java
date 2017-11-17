package stas.datetime;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.Locale;

public class DateTimeManipulation {

	public static void main(String[] args) throws InterruptedException {
//		http://docs.oracle.com/javase/tutorial/datetime/TOC.html
//		http://docs.oracle.com/javase/tutorial/datetime/iso/overview.html
////////   LESSON 206 ==============
//		LocalDate Contains just a dateЧno time and no time zone. A good example of
//		LocalDate is your birthday this year. It is your birthday for a full day regardless of what
//		time it is.
//
//		LocalTime Contains just a timeЧno date and no time zone. A good example of
//		LocalTime is midnight. It is midnight at the same time every day.
//		
//		LocalDateTime Contains both a date and time but no time zone. A good example of
//		LocalDateTime is Уthe stroke of midnight on New YearТs.Ф Midnight on January 2 isnТt
//		nearly as special, and clearly an hour after midnight isnТt as special either.
		
		
		System.out.println("==========================DATE OBJECTS CREATION===============================");

		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());
		System.out.println(ZonedDateTime.now());
		
		
//		The method signatures are as follows:
//			public static LocalDate of(int year, int month, int dayOfMonth)
//			public static LocalDate of(int year, Month month, int dayOfMonth)
		LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
		LocalDate date2 = LocalDate.of(2015, 1, 20);
	
//		LocalDate has toEpochDay() , which is the number of days since January 1, 1970.
		System.out.println(date2.toEpochDay()); // prints 16455
		
		
//		The method signatures are as follows:
//			public static LocalTime of(int hour, int minute)
//			public static LocalTime of(int hour, int minute, int second)
//			public static LocalTime of(int hour, int minute, int second, int nanos)
		LocalTime time1 = LocalTime.of(6, 15); // hour and minute
		LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
		LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds
//		time3 = LocalTime.of(6, 443, 30, 200); // RUNTIME EXCEPTION
		
		
//		Finally, we can combine dates and times:
		LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
		LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);		
			
	

//		LocalDate d = new LocalDate(); // DOES NOT COMPILE  нет публичного конструктора
		
//		LocalDate.of(2015, Month.JANUARY, 32) // throws DateTimeException  несуществующа€ дата.
		
		
//		The date and time classes are immutable, just like String was.
//		This means that we need to remember to assign the results of these methods to a reference
//		variable so they are not lost.
		System.out.println("================================PLUS date methods==================================");
		
		LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
		System.out.println(date); // 2014-01-20
		date = date.plusDays(2);
		System.out.println(date); // 2014-01-22
		date = date.plusWeeks(1);
		System.out.println(date); // 2014-01-29
		date = date.plusMonths(1);
		System.out.println(date); // 2014-02-28
		date = date.plusYears(5);
		System.out.println(date); // 2019-02-28
		date = date.plusMonths(1);
		System.out.println(date); // 2019-03-28
		
		date = date.plus(2, ChronoUnit.CENTURIES).plus(3, ChronoUnit.MONTHS); // +200 years and 3 months
		System.out.println(date); // 2219-06-28
		
		System.out.println("================================MINUS date methods==================================");
		
		date = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time = LocalTime.of(5, 15);
		
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		System.out.println(dateTime); // 2020-01-20T05:15
		dateTime = dateTime.minusDays(1);
		System.out.println(dateTime); // 2020-01-19T05:15
		dateTime = dateTime.minusHours(10);
		System.out.println(dateTime); // 2020-01-18T19:15
		dateTime = dateTime.minusSeconds(30);
		System.out.println(dateTime); // 2020-01-18T19:14:30
		
		dateTime = LocalDateTime.of(date2, time).minusDays(1).minusHours(10).minusSeconds(30);
		
		date = LocalDate.of(2020, Month.JANUARY, 20);
//		date = date.plusMinutes(1); // DOES NOT COMPILE в LocalDate нет минут!
		
		
		LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate end = LocalDate.of(2015, Month.MARCH, 30);

		
		LocalDate anotherDate = LocalDate.of(2015, Month.JANUARY, 1); 
		LocalDate ld = LocalDate.from(anotherDate);  //using other date object
		
		LocalDateTime ldt = LocalDateTime.of(2015, Month.JANUARY, 1, 21, 10); //9.10 PM
//		ldt = LocalDateTime.from(anotherDate); // Runtime EXCEPTION! нельз€ создать localDateTime из LocalDate
		
		
		System.out.println("===============Usage of PERIOD and DURATION types========================");
		//////			Period and Duration are not equivalent.
		
		Period annually = Period.ofYears(1); // every 1 year
		Period quarterly = Period.ofMonths(3); // every 3 months
		Period everyThreeWeeks = Period.ofWeeks(3); // every 3 weeks   stored as 21 days, ofWeeks- just utility method
		Period everyOtherDay = Period.ofDays(2); // every 2 days
		Period everyYearAndAWeek = Period.of(1, 0, 7); // every year and 7 days

		Period wrong = Period.ofYears(1).ofWeeks(1); // every week, т.к. method CHAINING Ќ≈ –јЅќ“ј≈“!!!
		
		System.out.println(start); //2015-01-01
		start = start.plus(quarterly); //adds period to the date
		System.out.println(start);//2015-04-01
		start = start.plus(Period.of(4, -1, 3));  // может быть отрицательным
		System.out.println(start);//2019-03-04
		
		Duration.ofDays(3).ofHours(32); //chaining не работает!!!! сработает только ѕќ—Ћ≈ƒЌ»… ћ≈“ќƒ!
		Duration durationDays = Duration.ofDays(5); // более мелкий период ƒень-->Ќаносекунда
		
		
		start = LocalDate.of(2015, Month.JANUARY, 1);
		end = LocalDate.of(2015, Month.MARCH, 30);
		
		performAnimalEnrichment(start, end);
		performAnimalEnrichmentPeriod(start, end, Period.ofMonths(3));
		
		
		
		date = LocalDate.of(2015, 1, 20);
		time = LocalTime.of(6, 15);
		dateTime = LocalDateTime.of(date, time);
		Period period = Period.ofMonths(1);
		System.out.println(date.plus(period)); // 2015-02-20
		System.out.println(dateTime.plus(period)); // 2015-02-20T06:15
//		System.out.println(time.plus(period)); // UnsupportedTemporalTypeException
		
		Period p = Period.of(10, 38, 40);
		p = p.normalized(); 
		//Returns a copy of this period with the years and months normalized. 
		//This normalizes the years and months units, leaving the days unit unchanged. 
		//The months unit is adjusted to have an absolute value less than 11, with the years unit 
		//being adjusted to compensate. 
		System.out.println("Period is :" + p); //Period is :P13Y2M40D

		dateTime.plus(2, ChronoUnit.CENTURIES);
		

		Duration daily =		 Duration.ofDays(1);       //  PT24H
				 daily =		 Duration.ofDays(3);       //  PT72H      !!!!!!!!!!!!!!!!!!!!!
//		ћетод 		Duration.ofDays(3); переводит Days в Hours!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		Duration 	Ќ≈ имеет пол€ DAY, только hours minutes и seconds
				 
		Duration hourly = 		 Duration.ofHours(1);      		  //  PT1H   
				 hourly = 		 Duration.of(1, ChronoUnit.HOURS);//  PT1H  
		Duration everyMinute = 	 Duration.ofMinutes(1);    //  PT1M            
		Duration everyTenSeconds = Duration.ofSeconds(10); //  PT10S           
		Duration everyMilli = 	 Duration.ofMillis(1);     //  PT0.001S        
		Duration everyNano = 	 Duration.ofNanos(1);      //  PT0.000000001S  
		//Duration doesnТt have a constructor that takes multiple units like Period does.

		daily 			= Duration.of(1, ChronoUnit.DAYS);
		hourly 			= Duration.of(1, ChronoUnit.HOURS);
		everyMinute 	= Duration.of(1, ChronoUnit.MINUTES);
		everyTenSeconds = Duration.of(10, ChronoUnit.SECONDS);
		everyMilli 		= Duration.of(1, ChronoUnit.MILLIS);
		everyNano 		= Duration.of(1, ChronoUnit.NANOS);
		
		date = LocalDate.of(2015, 1, 20);
		time = LocalTime.of(6, 15);
		dateTime = LocalDateTime.of(date, time);
			Duration duration = Duration.ofHours(6);
			System.out.println(dateTime.plus(duration)); // 2015Ц01Ц20T12:15
			System.out.println(time.plus(duration));	 // 12:15
//			System.out.println(date.plus(duration));	 // UnsupportedTemporalException
			
	/*		Period and Duration are not equivalent. This example shows a Period
			and Duration of the same length:*/
					 date 	= 		LocalDate.of(2015, 5, 25);
					 period = 		Period.ofDays(1);
			Duration days 	= 		Duration.ofDays(1);
			System.out.println(date.plus(period)); // 2015Ц05Ц26
//			System.out.println(date.plus(days)); // Unsupported unit: Seconds
			
		System.out.println("=======================USING INSTANTS===============================");	
		//The Instant class represents a specific moment in time in the GMT TIME ZONE
		
		Instant now = Instant.now();
		
		Thread.sleep(323);// do something time consuming
		Instant later = Instant.now();
		
		duration = Duration.between(now, later);
		System.out.println(duration.toMillis()); // prints 1123  
		
		
		date = LocalDate.of(2015, 5, 25);
		time = LocalTime.of(11, 55, 00);
		ZoneId zone = ZoneId.of("US/Eastern");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zone);
		
		Instant instant = zonedDateTime.toInstant() ; // 2015Ц05Ц25T15:55:00Z
		System.out.println(zonedDateTime); 			  // 2015Ц05Ц25T11:55Ц04:00[US/Eastern]
		System.out.println(instant); 				  // 2015Ц05Ц25T15:55:00Z
		/*The last two lines represent the same moment in time. The ZonedDateTime includes a time
		zone. The Instant gets rid of the time zone and turns it into an Instant of time in GMT.
	You cannot convert a LocalDateTime to an Instant!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
		Remember that an Instant is a point in time. A LocalDateTime does not contain a time zone,
		 and it is therefore not universally recognized around the world as the same moment in time.*/
		
		
	//	If you have the number of seconds since 1970, you can also create an Instant that way:
			instant = Instant.ofEpochSecond(121212121233453L);
			System.out.println(instant);// 2015Ц05Ц25T15:55:00Z
//		Using that Instant , you can do math. Instant allows you to add any unit day or smaller:
			Instant nextDay = instant.plus(1, ChronoUnit.DAYS);
			System.out.println(nextDay); // 2015Ц05Ц26T15:55:00Z
			Instant nextHour = instant.plus(1, ChronoUnit.HOURS);
			System.out.println(nextHour); // 2015Ц05Ц25T16:55:00Z
//			Instant nextWeek = instant.plus(1, ChronoUnit.WEEKS); // exception
//			ItТs weird that an Instant displays a year and month while preventing you from doing
//			math with those fields.
		
		
		System.out.println("=====================USING ChronoUnit class=======================");		
		start = LocalDate.of(2015, Month.FEBRUARY, 27);
		end = LocalDate.of(2015, Month.MAY, 30);
		
		long between = ChronoUnit.MONTHS.between(start, end);
		System.out.println("Between " + start + " and " + end + " is " + between + " months.");
		
		between = ChronoUnit.DAYS.between(start, end);
		System.out.println("Between " + start + " and " + end + " is " + between + " days.");
		//===================================================================================
		LocalTime one = LocalTime.of(5, 15);
		LocalTime two = LocalTime.of(6, 30);
				 date = LocalDate.of(2016, 1, 20);
		System.out.println(ChronoUnit.HOURS.between(one, two)); 	// 1
		System.out.println(ChronoUnit.MINUTES.between(one, two)); // 75
		System.out.println(ChronoUnit.MINUTES.between(two, one)); // -75
//		System.out.println(ChronoUnit.MINUTES.between(one, date)); // DateTimeException
		
		/*The implementation will convert the second type to be an instance of the first 
		type before the calculating the amount. 
		The result will be negative if the end is before the start.*/
		
		
		System.out.println("=====================Formatting Dates and Times=======================");
		date = LocalDate.of(2020, Month.JANUARY, 20);
		System.out.println(date.getDayOfWeek()); // MONDAY
		System.out.println(date.getMonth()); // JANUARY
		System.out.println(date.getYear()); // 2020
		System.out.println(date.getDayOfYear()); // 20
		
		System.out.println("=====================Using DateTimeFormatter class=======================");
		date = LocalDate.of(2020, Month.JANUARY, 20);
		time = LocalTime.of(11, 12, 34);
		dateTime = LocalDateTime.of(date, time);
		System.out.println(date.toString());//LocalDateTime's toString method generates the String in ISO 8601 format.
		System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		
		
		DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		System.out.println(shortDateTime.format(dateTime)); // 1/20/20
		System.out.println(shortDateTime.format(date)); // 1/20/20
//		System.out.println(shortDateTime.format(time)); // UnsupportedTemporalTypeException
		
//		этот кусок дает такой же результат, как и предыдущий, но исп. метод format самого		
		shortDateTime =	DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		System.out.println(dateTime.format(shortDateTime));
		System.out.println(date.format(shortDateTime));
//		System.out.println(time.format(shortDateTime));
		
		System.out.println("=================================DATE PARSING================================");
		// http://docs.oracle.com/javase/tutorial/datetime/iso/format.html
		
//		https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#patterns
		LocalDate d1 = LocalDate.parse("2015-01-01", DateTimeFormatter.ISO_LOCAL_DATE);

		String input = "01 01 2014";
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd' 'MM' 'yyyy");//пробелы заключены в ' '
			
			//—имволы, не €вл€ющиес€ символами форматировани€, должны быть заключены в ' '
			// например "'—егодн€' dd' 'MM' 'yyyy"
			LocalDate ddf = LocalDate.parse(input, formatter); //ћетод parse объекта LocalDate возвращает LocalDate
			
			TemporalAccessor ta =  formatter.parse(input); //ћетод parse форматтера возвращает TemporalAccessor
			
			LocalDate dateFromTempAccessor = LocalDate.from(ta);  // создаем новый объект LocalDate из TemporalAccessor
			System.out.printf("ddf: %s%n", ddf); 
			System.out.printf("Temporal Accessor object: %s%n", ta);
			System.out.printf("dateFromTempAccessor: %s%n", dateFromTempAccessor);
			
		} catch (DateTimeParseException exc) {
			System.out.printf("%s is not parsable!%n", input);
			throw exc; // Re-throw the exception.
		}
		// 'date' has been successfully parsed
		
		
		System.out.println("==================================TEMPORAL ADJUSTERS=======================");
		
		ld = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate sunday = ld.with(java.time.temporal.TemporalAdjusters.next(DayOfWeek.SUNDAY)); //2015-01-04
		System.out.println(sunday);
		
		
		System.out.println("===================================Zoned time and date=======================");
		
		System.out.println(ZonedDateTime.now());//2017-05-14T13:12:49.717+03:00[Europe/Kiev]
//			2015Ц06Ц20T07:50+02:00[Europe/Paris] 				// GMT 2015Ц06Ц20 5:50
//			2015Ц06Ц20T06:50+05:30[Asia/Kolkata] 				// GMT 2015Ц06Ц20 1:20
		zone = ZoneId.of("US/Eastern");
		System.out.println(zone);// prints US/Eastern
		ZonedDateTime zoned1 = ZonedDateTime.of(2015, 1, 20, 6, 15, 30, 200, zone);
		ZonedDateTime zoned2 = ZonedDateTime.of(date1, time1, zone);
		ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1, zone);
		System.out.println(zoned1);//2015-01-20T06:15:30.000000200-05:00[US/Eastern]
		System.out.println(zoned2);//2015-01-20T06:15-05:00[US/Eastern]
		System.out.println(zoned3);//2015-01-20T06:15:30-05:00[US/Eastern]
		
		
		
		//Print  LIST OF available ZoneIds
		ZoneId.getAvailableZoneIds().stream()
		.filter(z -> z.contains("US") || z.contains("America"))
		.sorted().forEach(System.out::println);
	
		
		
		System.out.println("=============================  Date-time ENUMS ============================");
		System.out.printf("%s%n", DayOfWeek.MONDAY.plus(3));
		DayOfWeek dow = DayOfWeek.MONDAY;
		Locale locale = Locale.getDefault();
		System.out.println(dow.getDisplayName(TextStyle.FULL, locale));
		System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));
		System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));
		
		
		System.out.printf("%d%n", Month.FEBRUARY.maxLength());
		Month month = Month.AUGUST;
		System.out.println(month.getDisplayName(TextStyle.FULL, locale));
		System.out.println(month.getDisplayName(TextStyle.NARROW, locale));
		System.out.println(month.getDisplayName(TextStyle.SHORT, locale));
		
		Month month1 = Month.JANUARY;
//		boolean b1 = month1 == 1;// DOES NOT COMPILE,  ENUM is not INT
		boolean b2 = month1 == Month.APRIL;// false
		
		
		System.out.println("============================= DAYLIGHT SAVINGS TIME =======================");
/*		Another way to look at it is that there is one day in March that is 23 hours long and one
		day in November that is 25 hours long. In programming, we call this an edge case. Oracle
		has decided that it is important enough to be on the exam. This means that you have to
		learn about it, even if you live in a country that doesnТt participate in daylight savings time.
		For example, on March 13, 2016, we move our clocks forward an hour and jump from
		2:00 a.m. to 3:00 a.m. This means that there is no 2:30 a.m. that day. If we wanted to
		know the time an hour later than 1:30, it would be 3:30.*/
		date = LocalDate.of(2016, Month.MARCH, 13);
		time = LocalTime.of(1, 30);
		zone = ZoneId.of("US/Eastern");
		
		ZonedDateTime dateTime11 = ZonedDateTime.of(date, time, zone);
		System.out.println(dateTime11); 	//2016Ц03Ц13T01:30Ц05:00[US/Eastern]
		dateTime11 = dateTime11.plusHours(1);
		System.out.println(dateTime11); 	//2016Ц03Ц13T03:30Ц04:00[US/Eastern]
			
		/*Notice that two things change in this example. The time jumps from 1:30 to 3:30. The
		UTC offset also changes. You can see that we went from 6:30 GMT (1:30 minus -5:00) to 7:30
		GMT (3:30 minus -4:00). This shows that the time really did change by one hour from
		GMTТs point of view.
		Similarly in November, an hour after the initial 1:30 is also 1:30 because at 2:00 a.m.
		we repeat the hour. This time, try to calculate the GMT time yourself for all three times to
		confirm that we really do move back only one hour at a time.*/
		date = LocalDate.of(2016, Month.NOVEMBER, 6);
		time = LocalTime.of(1, 30);
		zone = ZoneId.of("US/Eastern");
		
		dateTime11 = ZonedDateTime.of(date, time, zone);
		System.out.println(dateTime11); //2016Ц11Ц06T01:30Ц04:00[US/Eastern]
		dateTime11 = dateTime11.plusHours(1);
		System.out.println(dateTime11); //2016Ц11Ц06T01:30Ц05:00[US/Eastern]
		dateTime11 = dateTime11.plusHours(1);
		System.out.println(dateTime11); //2016Ц11Ц06T02:30Ц05:00[US/Eastern]
//		We went from 5:30 GMT to 6:30 GMT to 7:30 GMT.

		
		//Finally, trying to create a time that doesnТt exist JUST ROLLS FORWARD:
		date = LocalDate.of(2016, Month.MARCH, 13);
		time = LocalTime.of(2, 30);
		zone = ZoneId.of("US/Eastern");
		dateTime11 = ZonedDateTime.of(date, time, zone);
		System.out.println(dateTime11); // 2016Ц03Ц13T03:30Ц04:00[US/Eastern]
/*		Java is smart enough to know that there is no 2:30 a.m. that night and switches over to
		the appropriate GMT offset.*/
		
		
	}

	
	private static void performAnimalEnrichment(LocalDate start, LocalDate end) {
		LocalDate upTo = start;
		while (upTo.isBefore(end)) { // check if still before end
			System.out.println("give new toy: " + upTo);
			upTo = upTo.plusMonths(1); // add a month
		}
	}

	private static void performAnimalEnrichmentPeriod(LocalDate start, LocalDate end, Period period) {
		// uses the generic period
		LocalDate upTo = start;
		while (upTo.isBefore(end)) {
			System.out.println("give new toy: " + upTo);
			upTo = upTo.plus(period);
			
			// adds the period
		}
	
}}
