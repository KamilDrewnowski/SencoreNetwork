package net.codejava;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.StrictMath.abs;

public class SetData extends SencoreNetworkMain {


    private static int id;
    private static int sencoreNumber;
    private static float humidity;
    private static float temperature;
    private static int year;
    private static int month;
    private static int day;
    private static int hour;
    private static int minute;
    private static Date date = null;

public static int sencoreNumber(Scanner scan){
    System.out.println("Enter the sencore number");
    boolean rightSencoreNumber;
    do {
        try {
            sencoreNumber = scan.nextInt();
            rightSencoreNumber = true;
        } catch (InputMismatchException e) {
            System.out.println("Wrong writing");
            rightSencoreNumber = false;
        }
    } while (!rightSencoreNumber);
    return sencoreNumber;
}

    public static float humidity(Scanner scan){
        System.out.println("Enter the humidity");
        boolean rightHumidity;
        do {
            try {
                humidity = scan.nextFloat();
                rightHumidity = true;
            } catch (InputMismatchException e) {
                System.out.println("Wrong writing");
                rightHumidity = false;
            }
        } while (!rightHumidity);
        return humidity;
    }


    //method that reads temperature values from the keyboard and assigns them to variables
    public static float temperature(Scanner scan) {
        System.out.println("Enter the temperature");
        boolean rightTemperature;
        do {
            try {
                temperature = scan.nextFloat();
                rightTemperature = true;
            } catch (InputMismatchException e) {
                System.out.println("Wrong writing");
                rightTemperature = false;
            }
        } while (!rightTemperature);
        return temperature;
    }

    //method that reads date values from the keyboard and assigns them to variables
    public static Date convertToDate(Scanner scan) {
        boolean rightDate;
        do {
            System.out.println("Enter the year");
            String stringYear = scan.next();
            System.out.println("Enter the month");
            String stringMonth = scan.next();
            System.out.println("Enter the day");
            String stringDay = scan.next();
            System.out.println("Enter the hour");
            String stringHour = scan.next();
            System.out.println("Enter the minute");
            String stringMinute = scan.next();

            try {
                year = Integer.parseInt(stringYear) - 1900;
                month = Integer.parseInt(stringMonth) - 1;
                day = Integer.parseInt(stringDay) +3;
                hour = Integer.parseInt(stringHour);
                minute = Integer.parseInt(stringMinute);
                date = new Date(year, month, day, hour, minute);
                rightDate = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong writing, try again");
                rightDate = false;
            }
        } while (!rightDate);

        return date;
    }


    //Reading from file

public static void setValueFile(Session session, String nameFile) {
    //give the path of file
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File(nameFile));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        System.out.println("The file could not be found, check that the file name is valid");
    }
    //making a pattern
    Pattern p = Pattern.compile("-?\\d+(,\\d+)*?\\.?\\d+?");

    //loop through all lines of the file
    while (scanner.hasNextLine()) {
        //adding to list
        List<String> numbers = new ArrayList<>();
        Matcher m = p.matcher(scanner.nextLine());
        while (m.find()) {
            numbers.add(m.group());
        }
        System.out.println(numbers);

        //parsing the first number in the list to int as id
        //id is not used in program
        try {
            id = Integer.parseInt(numbers.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Wrong writing ID");
        }
        //parsing the second number in the list to int as temperature
        try {
            temperature = Float.parseFloat(numbers.get(1));
        } catch (NumberFormatException e) {
            System.out.println("Wrong writing temperature");
        }
        //parsing the date number in the list to date
        try {
            year = (abs(Integer.parseInt(numbers.get(2))) - 1900);
            month = abs(Integer.parseInt(numbers.get(3))) - 1;
            day = abs(Integer.parseInt(numbers.get(4)))+3;
            hour = abs(Integer.parseInt(numbers.get(5)));
            minute = abs(Integer.parseInt(numbers.get(6)));
            date = new Date(year, month, day, hour, minute);
        } catch (NumberFormatException e) {
            System.out.println("Wrong writing date");
        }
        //checking for the developer, not for the user
//        System.out.println(id);
//        System.out.println(temperature);
//        System.out.println(date);
//        System.out.println("");
        setValue(session, sencoreNumber, humidity, temperature, date);
    }
}

    public static void setValue(Session session, int sencoreNumber, float humidity, float temperature, Date date) {
        // set data to Database
        Transaction transaction = session.beginTransaction();
        session.save(new TEMP_MEASURE(sencoreNumber, humidity, temperature, date));
        transaction.commit();
    }

}




