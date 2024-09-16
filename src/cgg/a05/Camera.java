package cgg.a05;

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
    public Ray generateRay(double x, double y) {
        double xv = x - w / 2.0;
        double yv = -(y - h / 2.0);
        double zv = -((w / 2.0) / Math.tan(angle / 2.0));
        Direction u = Vector.normalize(new Direction(xv, yv, zv));
        return new Ray(u, 0, Double.POSITIVE_INFINITY);
    }
}