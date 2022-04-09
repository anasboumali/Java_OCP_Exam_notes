package OCP;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Date {


    static void creation() {
        //Zone ids
        System.out.println(ZoneId.of("Europe/Paris"));//Europe/Paris
        //System.out.println(ZoneId.of("ECT"));//.ZoneRulesException: Unknown time-zone ID: ECT
        System.out.println(ZoneId.systemDefault());//Europe/Berlin

        //date time creation
        System.out.println(LocalDateTime.now());//2021-10-15T11:56:49.247720800
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        System.out.println(LocalDateTime.of(2021, 12, 2, 12, 28, 33));//2021-12-02T12:28:33
        System.out.println(LocalDateTime.of(2021, Month.APRIL, 2, 12, 28, 33));//2021-04-02T12:28:33

        //from Clock
        System.out.println("Europe/Berlin : " + LocalDateTime.now(Clock.system(ZoneId.systemDefault())));//2021-10-15T12:05:17.130302200
        System.out.println("Europe/Paris : " + LocalDateTime.now(Clock.system(ZoneId.of("Europe/Paris"))));//2021-10-15T12:05:17.132301500

        //from ZoneId
        System.out.println(LocalDateTime.now(ZoneId.systemDefault()));

        //from Instant
        System.out.println(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        System.out.println(LocalDateTime.ofEpochSecond(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), 0, ZoneOffset.UTC));//2021-10-15T12:09:41

        //from year day
        System.out.println(LocalDate.ofYearDay(20021, 340));

        //from parsing string
//        text string such as 2007-12-03T10:15:30.
//        The string must represent a valid date-time and is parsed using DateTimeFormatter.ISO_LOCAL_DATE_TIME.
//        Throws unchecked exception :   DateTimeParseException
        System.out.println("Parse OK : " + LocalDateTime.parse("2021-10-15T12:05:17.130302200"));
        // System.out.println("Parse KO : " + LocalDateTime.parse("2021-10-17" , DateTimeFormatter.ofPattern("yyyy-MM-dd")));//DateTimeParseException(need time)
        //System.out.println("Parse US KO : " + LocalDate.parse("2021/October/17" , DateTimeFormatter.ofPattern("yyyy/MMMM/dd")));//DateTimeParseException need US local
        System.out.println("Parse US OK : " + LocalDate.parse("2021/October/17", DateTimeFormatter.ofPattern("yyyy/MMMM/dd", Locale.US)));//2021-10-17
        //the above KO if october lower case
        System.out.println("Parse OK : " + LocalDate.parse("2021/octobre/17", DateTimeFormatter.ofPattern("yyyy/MMMM/dd")));//2021-10-17

    }

    static void formatting() {

        //Date formatting

        //ISO
        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_DATE));//2021-10-15
        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));//2021-10-15

        //Format style
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));//vendredi 15 octobre 2021
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));//15 octobre 2021
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));//15 oct. 2021
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));//15/10/2021

        //of pattern
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MMMM dd")));//2021 octobre 15
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MMMM D")));//2021 octobre 288
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MMMM dd")));//2021 octobre 288
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MMMM dd", Locale.US)));//2021 October 15
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yy MM dd")));//21 10 15
        System.out.println(LocalDate.of(2021, 8, 2).format(DateTimeFormatter.ofPattern("y-MM-dd")));//2021-08-02
        System.out.println(LocalDate.of(2021, 8, 2).format(DateTimeFormatter.ofPattern("y/M/D")));//2021/8/2
        //System.out.println(LocalDate.of(2021,8,2).format(DateTimeFormatter.ofPattern("YYYY MM at d")));//IllegalArgumentException

        //Time formatting

        //ISO
        System.out.println(LocalTime.of(2, 3, 5).format(DateTimeFormatter.ISO_LOCAL_TIME));//02:03:05
        //System.out.println(LocalTime.of(2,3,5).format(DateTimeFormatter.ISO_DATE));//.UnsupportedTemporalTypeException: Unsupported field: Year

        System.out.println(LocalTime.of(2, 3, 5).format(DateTimeFormatter.ofPattern("hh:mm:ss")));//02:03:05
        System.out.println(LocalTime.of(2, 3, 5).format(DateTimeFormatter.ofPattern("H:m:S")));//2:3:5
        //System.out.println(LocalTime.of(2,3,5).format(DateTimeFormatter.ofPattern("HH:M:ss")));//UnsupportedTemporalTypeException: Unsupported field: MonthOfYear


        //LocalDateTime
        System.out.println("Locale DateTime");
        //ISO
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));//2021-10-15T11:49:12.6771559

        //Of pattern
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YY/MM/ddTHH:mm::ss")));//IllegalArgumentException: Unknown pattern letter: T
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY/MM/dd , HH:mm::ss")));//2021/10/15 , 11:51::43
    }

    static void information() {
        //dates
        LocalDate toDay = LocalDate.now();
        //get year
        System.out.println(toDay.getYear());
        //get month
        System.out.println(toDay.getMonth());//OCTOBER

        //get numeric month
        System.out.println(toDay.getMonthValue());//10

        //get day of the month
        System.out.println(toDay.getDayOfMonth());//15

        //get day of the week
        System.out.println(toDay.getDayOfWeek());//Friday

        //get day of the year
        System.out.println(toDay.getDayOfYear());//288

        System.out.println(toDay.get(ChronoField.DAY_OF_WEEK));//5
        System.out.println(toDay.get(ChronoField.DAY_OF_YEAR));//288


        //times
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
        System.out.println(time.getNano());


        System.out.println(time.get(ChronoField.SECOND_OF_DAY));//45572
        System.out.println(time.getLong(ChronoField.MICRO_OF_DAY));//45572247083

        //date time
        LocalDateTime localDateTime = LocalDateTime.now();
    }

    static void timeTraveling() {


        LocalDate date = LocalDate.now();

        System.out.println("Epoch Date: " + LocalDate.EPOCH);//1970-01-01
        System.out.println("Max Date : " + LocalDate.MAX);//+999999999-12-31
        System.out.println("Min Date: " + LocalDate.MIN);//-999999999-01-01

        System.out.println("MIDNIGHT Time: " + LocalTime.MIDNIGHT);//00:00
        System.out.println("NOON Time: " + LocalTime.NOON);//12:00
        System.out.println("MAX Time: " + LocalTime.MAX);//23:59:59.999999999
        System.out.println("MIN Time: " + LocalTime.MIN);//00:00

        System.out.println(date.plusYears(2));//2023-10-15
        System.out.println(date.plusMonths(5));//2022-03-15
        System.out.println(date.plusWeeks(40));//2022-07-22
        System.out.println(date.plusDays(23));//2021-11-07
        System.out.println(date.plusYears(1).plusMonths(1));//2022-11-15

        //System.out.println(date.plus(1 , ChronoUnit.MINUTES));//UnsupportedTemporalTypeException: Unsupported unit: Minutes
        System.out.println(date.plus(1, ChronoUnit.YEARS));//2022-10-15
        System.out.println(date.plus(Period.of(1, 1, 1)));//2022-11-16

        //same goes for date.minus(...)

        System.out.println("dates until : " + date.datesUntil(date.plusYears(1)).count());//365
        System.out.println("dates until next year , week period : " + date.datesUntil(date.plusYears(1), Period.ofWeeks(1)).count());//53
    }

    static void conversion() {
        //date to datetime
        LocalDate date = LocalDate.now();
        System.out.println(date.atStartOfDay());//2021-10-15T00:00
        System.out.println(date.atTime(LocalTime.now()));//2021-10-15T13:38:21.930750600
        System.out.println(date.atTime(OffsetTime.now()));//2021-10-15T13:38:21.931751600+02:00

        //date to epoch days
        System.out.println("Epoch days : " + date.toEpochDay());//18915

        //date to epoch seconds
        System.out.println("Epoch seconds : " + date.toEpochSecond(LocalTime.now(), ZoneOffset.UTC));//1634305272 ,since the epoch of 1970-01-01T00:00:00Z.

        //time to date
        LocalTime time = LocalTime.now();
        System.out.println(time.atDate(LocalDate.now()));//2021-10-15T13:43:58.304069700
        System.out.println(time.toEpochSecond(LocalDate.now(), ZoneOffset.UTC));//1634305438

        //seconds of the day to LocalTime
        LocalTime.ofSecondOfDay(LocalDateTime.now().get(ChronoField.SECOND_OF_DAY));

        //instant to LocalTime
        LocalTime.ofInstant(Instant.now(), ZoneOffset.UTC);

        //Local datetime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalDate());
        System.out.println(localDateTime.toLocalTime());

        //to OffsetDateTime
        System.out.println(localDateTime.atOffset(ZoneOffset.UTC));

        //to ZonedDateTime
        System.out.println(localDateTime.atZone(ZoneOffset.UTC));
    }


    static void comparison() {

        System.out.println(LocalDate.now().isEqual(LocalDate.now()));//true
        System.out.println(LocalDateTime.now().isEqual(LocalDateTime.now()));//false
        System.out.println(LocalDateTime.now().isBefore(LocalDateTime.now().plusYears(1)));//true
        System.out.println(LocalDateTime.now().isAfter(LocalDateTime.now().plusYears(1)));//false
    }

    static void periods() {

        System.out.println("zero period : " + Period.ZERO);//P0D
        System.out.println(Period.ofYears(1));//P1Y
        System.out.println(Period.ofMonths(2));//P2M
        System.out.println(Period.ofWeeks(8));//P56D
        System.out.println(Period.ofDays(3));//P3D
        System.out.println(Period.ofDays(-3));//P-3D
        System.out.println(Period.of(1, 3, 4));//P1Y3M4D

        //between to Dates
        System.out.println(Period.between(LocalDate.now(), LocalDate.now().plusYears(1).plusMonths(7).plusDays(3)));//P1Y7M3D

        System.out.println(Period.parse("P2Y3M0D"));//P2Y3M

        System.out.println(Period.parse("P2Y3M0D"));//P2Y3M
        System.out.println(Period.parse("P0Y3M0D"));//P3M

        Period p = Period.ZERO;
        System.out.println(p.isZero());
        System.out.println(p.isNegative());
        System.out.println(p.getDays());
        System.out.println(p.getMonths());
        System.out.println(p.getYears());

        //addition ,subtraction
        System.out.println(p.plus(Period.ofDays(2)));//P2D
        System.out.println(p.plusDays(2).plusDays(3));//P5D
        System.out.println(p.minus(Period.ofDays(2)));//P-2D

        //multiplication & negation
        System.out.println("multiplied : " + p.plusDays(3).multipliedBy(3).negated());//P-9D

    }

    static void durations() {
        System.out.println(Duration.ZERO);//PT0S
        System.out.println(Duration.ofDays(2));//PT48H
        System.out.println(Duration.ofSeconds(4));//PT4S
        System.out.println(Duration.ofMillis(4));//PT0.004S
        System.out.println(Duration.ofNanos(4));//PT0.000000004S
        System.out.println(Duration.ofSeconds(4).dividedBy(2));//PT2S
        System.out.println(Duration.ofMinutes(4).dividedBy(Duration.ofSeconds(30)));//8
        System.out.println(Duration.ofMinutes(60).multipliedBy(100));//PT100H
    }


    public static void main(String... args) {


        //information();
        //timeTraveling();
        //conversion();
        //comparison();
        //periods();
        durations();
    }

}


