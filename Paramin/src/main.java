import com.digdes.school.DatesToCronConvertException;

import java.util.ArrayList;
import java.util.List;

public class main {
    private static final List<String> date1 = new ArrayList<>();
    private static final List<String> date2 = new ArrayList<>();

    public static void main (String[] args) throws DatesToCronConvertException, DatesToCronConvertException {
        date1.add("2022-01-25T08:00:00");
        date1.add("2022-01-25T08:30:00");
        date1.add("2022-01-25T09:00:00");
        date1.add("2022-01-25T09:30:00");
        date1.add("2022-01-26T08:00:00");
        date1.add("2022-01-26T08:30:00");
        date1.add("2022-01-26T09:00:00");
        date1.add("2022-01-26T09:30:00");

        date2.add("2022-01-24T19:53:00");
        date2.add("2022-01-24T19:54:00");
        date2.add("2022-01-24T19:55:00");
        date2.add("2022-01-24T19:56:00");
        date2.add("2022-01-24T19:57:00");
        date2.add("2022-01-24T19:58:00");
        date2.add("2022-01-24T19:59:00");
        date2.add("2022-01-24T20:00:00");
        date2.add("2022-01-24T20:01:00");
        date2.add("2022-01-24T20:02:00");

        Converter test = new Converter();

        System.out.println("test1 = " + test.convert(date1) + "\n");

        System.out.println("test2 = " + test.convert(date2));

    }
}
