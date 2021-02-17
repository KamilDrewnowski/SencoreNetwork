package net.codejava;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class GetData extends SencoreNetworkMain {

    public static void getData(Session session) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TEMP_MEASURE> criteria = builder.createQuery(TEMP_MEASURE.class);
        criteria.from(TEMP_MEASURE.class);
        List<TEMP_MEASURE> data = session.createQuery(criteria).getResultList();

        PrintWriter printWriter = null;
        try {
        File newFile = new File("GetSencoreNetworkLogs.txt");
        printWriter = new PrintWriter("GetSencoreNetworkLogs.txt");
        } catch (FileNotFoundException e) {
            System.out.println("You don't have file like GetSencoreNetworkLogs.txt");
        }
        for (TEMP_MEASURE tempMeasure : data) {
            System.out.print("Measure ID= " + tempMeasure.getID());
            printWriter.print("Measure ID= " + tempMeasure.getID());
            System.out.print("   Sencore number= " + tempMeasure.getSENCORE_NUMBER());
            printWriter.print("   Sencore number= " + tempMeasure.getSENCORE_NUMBER());
            System.out.print("   Humidity value= " + tempMeasure.getHUMIDITY());
            printWriter.print("   Humidity value= " + tempMeasure.getHUMIDITY());
            System.out.print("   Temperature value= " + tempMeasure.getTEMPERATURE());
            printWriter.print("   Temperature value= " + tempMeasure.getTEMPERATURE());
            System.out.println("   Date of measure= " + tempMeasure.getMEASURE_DATE());
            printWriter.println("   Date of measure= " + tempMeasure.getMEASURE_DATE());

        }
        printWriter.close();
    }



}
