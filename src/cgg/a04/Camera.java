package cgg.a04;

import cgtools.Direction;
import cgtools.Vector;

public class Camera {

    public double angle;
    public int w;
    public int h;
    public double z;

    public Camera(int w, int h, double angle) {
        this.angle = angle;
        this.w=w;
        this.h=h;
    }

    public Direction rayDirection (double x, double y){
        double xd = x - (w/2);
        double yd = -(y - (h/2));
        double z = -(w/2)/Math.tan(angle/2);
        return Vector.normalize(Vector.direction(xd, yd, z));
    }
}