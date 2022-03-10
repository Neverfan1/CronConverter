import com.digdes.school.DatesToCronConvertException;

import java.util.ArrayList;
import java.util.List;

public class main {
    private static final List<String> date1 = new ArrayList<>();
    private static final List<String> date2 = new ArrayList<>();

    private static final List<String> date3 = new ArrayList<>();


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
//
//        date3.add("2022-04-11T14:14:10");
//        date3.add("2022-04-21T14:16:50");
//        date3.add("2027-08-11T14:18:30");
//        date3.add("2036-04-01T14:11:12");
//        date3.add("2022-12-01T14:15:20");
//        date3.add("2022-11-11T14:15:50");
//        date3.add("2022-02-22T14:14:12");


        Converter test = new Converter();
//        System.out.println("test3 = " + test.convert(date3) + "\n");

        System.out.println("test1 = " + test.convert(date1) + "\n");

        System.out.println("test2 = " + test.convert(date2));
        System.out.println(test.getImplementationInfo());

    }
}
