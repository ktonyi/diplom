package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateGenerator {
    static Faker faker = new Faker();
    private LocalDate today = LocalDate.now();
    private DateTimeFormatter formatterYears = DateTimeFormatter.ofPattern("yy");
    private DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");

    @Value
    public static class Year {
        private String year;
    }

    public Year shiftYear(int numberOfYears) {
        LocalDate newDate = today.plusYears(numberOfYears);
        return new Year(formatterYears.format(newDate));
    }

    public Year wrongYear() {
        return new Year(Integer.toString(faker.number().numberBetween(88, 99)));
    }

    @Value
    public static class Month {
        private String month;
    }

    public Month shiftMonth(int numberOfMonths) {
        LocalDate newDate = today.plusMonths(numberOfMonths);
        return new Month(formatterMonth.format(newDate));
    }

    public Month wrongMonth() {
        return new Month(Integer.toString(faker.number().numberBetween(13, 99)));
    }
}
