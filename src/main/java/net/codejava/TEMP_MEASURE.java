package net.codejava;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TEMP_MEASURE {
private int ID;
private int SENCORE_NUMBER;
private float HUMIDITY;
private float TEMPERATURE;
private Date MEASURE_DATE;

    public TEMP_MEASURE() {
        super();
    }


    public TEMP_MEASURE(int SENCORE_NUMBER, float HUMIDITY, float TEMPERATURE, Date MEASURE_DATE) {
        this.SENCORE_NUMBER = SENCORE_NUMBER;
        this.HUMIDITY = HUMIDITY;
        this.TEMPERATURE = TEMPERATURE;
        this.MEASURE_DATE = MEASURE_DATE;
    }
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
@SequenceGenerator(name = "generator", sequenceName = "SENCORESEQUENCE", allocationSize = 1)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSENCORE_NUMBER() {
        return SENCORE_NUMBER;
    }

    public void setSENCORE_NUMBER(int SENCORE_NUMBER) {
        this.SENCORE_NUMBER = SENCORE_NUMBER;
    }

    public float getHUMIDITY() {
        return HUMIDITY;
    }

    public void setHUMIDITY(float HUMIDITY) {
        this.HUMIDITY = HUMIDITY;
    }

    public float getTEMPERATURE() {
        return TEMPERATURE;
    }

    public void setTEMPERATURE(float TEMPERATURE) {
        this.TEMPERATURE = TEMPERATURE;
    }

    public Date getMEASURE_DATE() {
        return MEASURE_DATE;
    }

    public void setMEASURE_DATE(Date MEASURE_DATE) {
        this.MEASURE_DATE = MEASURE_DATE;
    }
}
