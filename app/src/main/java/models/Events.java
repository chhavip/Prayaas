package models;

import java.io.Serializable;

/**
 * Created by chhavi on 8/6/15.
 */
public class Events implements Serializable {

    public String name;
    public String date;
    public String Venue;
    public int imageResource;
    public String description;
    public String time;
    public String status;
    public String organisation;
    public String contact;
    public int seats;



    public Events(String n, String d, String v, int ir){
        this.name = n;
        this.date = d;
        this.Venue = v;
        this.imageResource = ir;


    }
}
