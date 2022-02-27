import com.digdes.school.DatesToCronConvertException;
import com.digdes.school.DatesToCronConverter;

import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.stream.Collectors;


public class Converter implements DatesToCronConverter {
    @Override
    public String convert(List<String> dates) throws DatesToCronConvertException {
        SimpleDateFormat formater = new SimpleDateFormat(DATE_FORMAT);
        formater.setLenient(false);
        ArrayList<Integer> year = new ArrayList<>();
        ArrayList<Integer> month = new ArrayList<>();
        ArrayList<Integer> day_of_month = new ArrayList<>();
        ArrayList<Integer> day_of_week = new ArrayList<>();
        ArrayList<Integer> hour = new ArrayList<>();
        ArrayList<Integer> min = new ArrayList<>();
        ArrayList<Integer> sec = new ArrayList<>();

        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        // VALID
        for (String date : dates) {
            Date d = null;
            try {
                d = formater.parse(date);
            } catch (ParseException e) {
                throw new DatesToCronConvertException();
            }

            assert d != null;
            calendar.setTime(d);
            year.add(calendar.get(Calendar.YEAR));
            month.add(calendar.get(Calendar.MONTH) + 1);
            day_of_month.add(calendar.get(Calendar.DAY_OF_MONTH));
            day_of_week.add(calendar.get(Calendar.DAY_OF_WEEK) - 1);
            hour.add(calendar.get(Calendar.HOUR_OF_DAY));
            min.add(calendar.get(Calendar.MINUTE));
            sec.add(calendar.get(Calendar.SECOND));
        }

        // EXTERNAL ELEMENTS
        List<Integer> year1 =
                year
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());
        List<Integer> month1 =
                month
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());
        List<Integer> day_of_month1 =
                day_of_month
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());
        List<Integer> day_of_week1 =
                day_of_week
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());
        List<Integer> hour1 =
                hour
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());
        List<Integer> min1 =
                min
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());
        List<Integer> sec1 =
                sec
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());



        //sorting
//        Collections.sort (year);
//        Collections.sort (month);
//        Collections.sort (day_of_month);
//        Collections.sort (day_of_week);
//        Collections.sort (hour);
//        Collections.sort (min);
//        Collections.sort (sec);

        // result
        StringBuilder result = new StringBuilder();


//----------------------------------------------------------------------------------------------------------------------
       // “0 * * * * MON”.
        // * * * * *
        // | | | | |
        // | | | | +----- Дни недели (диапазон: 1-7)
        // | | | +------- Месяцы     (диапазон: 1-12)
        // | | +--------- Дни месяца (диапазон: 1-31)
        // | +----------- Часы       (диапазон: 0-23)
        // +------------- Минуты     (диапазон: 0-59)

        REsec(sec1, result);
        REmin(min1, result);
        REhour(hour1, day_of_month1, result);
        REday_of_month(day_of_month1, year1, result);
        REmonth(year1, month1, result);
        REday_of_week(day_of_week1, day_of_month1,result);


        return result.toString();



    }

    // SECONDS
    private void REsec(List<Integer> s, StringBuilder res)
    {
        if (s.contains(0)) {
            res.append("0 ");
        }

            else {
               res.append("*/").append(s.get(0)).append(" ");

        }
    }
    // MINUTES
    private void REmin(List<Integer> m, StringBuilder res)
    {

        if (m.size() == 1 && m.contains(0)) {
            res.append("0 ");
        } else if (m.size() == 2) {
            res.append(m.get(0)).append("/").append(m.get(m.size() - 1)).append(" ");
        } else {
            res.append("* ");
        }
    }



    // HOURS
    private void REhour(List<Integer> h, List<Integer> d, StringBuilder res)
    {
        if (h.size() == 1) {
            if (h.contains(0)) {
                res.append("0 ");
            } else {
                res.append(h.get(0)).append(" ");
            }
        } else if (d.size() > 1) {
            res.append(h.get(0)).append("-").append(h.get(h.size() - 1)).append(" ");
        } else {
            res.append("* ");
        }
    }
    // DAYS_OF_MONTH
    private void REday_of_month(List<Integer> d, List<Integer> mon, StringBuilder res)
    {
        if (d.size() == 1 && mon.size() > 1) {
            res.append(d.get(0)).append(" ");
        }
        else {
            res.append("* ");
        }
    }
    // MONTHS
    private void REmonth(List<Integer> y, List<Integer> mon, StringBuilder res)
    {
        if (mon.size() == 1 && y.size() > 1) {
            res.append(mon.get(mon.size() - 1)).append(" ");
        } else {
            res.append("* ");
        }
    }
    // DAYS_OF_WEEK
    private void REday_of_week(List<Integer> dow, List<Integer> d, StringBuilder res) throws DatesToCronConvertException {
        if (dow.size() == 1) {
            res.append(day_to_string_EN(dow.get(0)));
        }
        else if (dow.size() > 1){
            res.append("* ");
        }
        else if (d.size() > 7){
            res.append(day_to_string_EN(dow.get(0))).append("-").append(day_to_string_EN(dow.get(dow.size() - 1)));
        }
        else {
            throw new DatesToCronConvertException();
        }

    }

//
//    private boolean isValid(List<String> dates_list, String datePattern) throws DatesToCronConvertException {
//        for (int i = 0; i < dates_list.size(); i++) {
//            if (dates_list.get(i) == null || datePattern == null || datePattern.length() <= 0) {
//                return false;
//            }
//
//            SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
//            formatter.setLenient(false);
//
//            try {
//                formatter.parse(dates_list.get(i));
//            } catch (ParseException e) {
//                throw new DatesToCronConvertException();
//            }
//        }
//        return true;
//
//    }

    private String day_to_string_EN(int day) {
        String result = "";
        switch (day) {
            case 1:
                result = "MON";
                break;
            case 2:
                result = "TUE";
                break;
            case 3:
                result = "WED";
                break;
            case 4:
                result = "THU";
                break;
            case 5:
                result = "FRI";
                break;
            case 6:
                result = "SUT";
                break;
            case 7:
                result = "SUN";
                break;
            default:
                break;
        }
        return result;
    }

    private String day_to_string_RU(int day) {
        String result = null;
        switch (day) {
            case 1:
                result = "ПН";
                break;
            case 2:
                result = "ВТ";
                break;
            case 3:
                result = "СР";
                break;
            case 4:
                result = "ЧТ";
                break;
            case 5:
                result = "ПТ";
                break;
            case 6:
                result = "СБ";
                break;
            case 7:
                result = "ВС";
                break;
            default:
                break;
        }
        return result;
    }



   @Override
    public String getImplementationInfo() {
        // ФИО, имя класса реализации, пакет, ссылка на github
        return "Парамин Данила Андреевич" + " " + this.getClass().getSimpleName() + " "
                + getClass().getPackage().getName() + " "
                + "https://github.com/Neverfan1/CronConverter";


    }
}



