package net.codejava;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class SencoreNetworkMain {

    public static void main(String[] args) {
        Session session = getDBSession();

        //Reading data from the user's keyboard
        Scanner scan = new Scanner(System.in);
        String choice;

        do {
            ChoiceMainOptions(); // show options (get, set, exit)
            choice = scan.next();
            switch (choice) {
                case "s": //set
                    System.out.println("Choose:\n" +
                    "m - add a value manually\n" +
                    "f - add value from file");
                    choice = saveData(session, scan);
                    break;

                case "g": // get
                    GetData.getData(session);
                    break;

                case "e": // exit
                    System.out.println("Closing the connection");
                    session.close();
                    session.getSessionFactory().close();
                    break;

                default:
                    System.out.println("Wrong choice");
            }
        }while(!choice.equals("e"));
    }

    private static String saveData(Session session, Scanner scan) {
        String choice;
        choice = scan.next();
        if (choice.equals("m")){ // set manually
            int sencoreNumber = SetData.sencoreNumber(scan);
            float humidity = SetData.humidity(scan);
            float temperature = SetData.temperature(scan);
            Date date = SetData.convertToDate(scan);
            SetData.setValue(session, sencoreNumber, humidity, temperature, date);

        } else if (choice.equals("f")){ // set from file
            System.out.println("Enter the name of the file to be loaded");
            String nameFile = scan.next();
            SetData.setValueFile(session, nameFile);

        }else{
            System.out.println("Wrong choice");
        }
        return choice;
    }

    private static Session getDBSession() {
        // set connection properties
        System.out.println("Connection with Oracle DataBase");
        Configuration config = new Configuration();
        Session session = Connection.connectionSettings(config);
        System.out.println("Connected with Oracle DataBase");
        return session;
    }


    //set your choice "s" to set data, "g" to get data, or "e" to exit and close connection
    public static void ChoiceMainOptions(){
        System.out.println("Choose: \n" +
                "s - to set and save data\n"+
                "g - to get data\n"+
                "e - to exit and close connection");
    }
}



