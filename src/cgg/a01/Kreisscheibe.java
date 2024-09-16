package cgg.a01;

import cgtools.Color;

import static cgtools.Vector.black;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Kreisscheibe {
    private Double r ;
    private Color color;
    private double centrex;
    private double centrey;


    Kreisscheibe(Color color,double r,double centrex,double centrey ){
       this.color = color;
        this.r = r;
        this.centrex = centrex;
        this.centrey = centrey;
    }

    public Color getColor(double x, double y) {
        double centre_x =256;
        double centre_y =128;
        double n1 = x - centre_x;
        double n2 = y - centre_y ;
        double distance =sqrt(pow(n1,2)+pow(n2,2));
        if(distance > r)
            return black;
        return color;
    }
}
