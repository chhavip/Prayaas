package models;

/**
 * Created by chhavi on 8/6/15.
 */
public class Events {

    public String name;
   public   String date;
   public String Venue;
   public int imageResource;

    public Events(String n, String d, String v, int ir){
        this.name = n;
        this.date = d;
        this.Venue = v;
        this.imageResource = ir;


    }
}
