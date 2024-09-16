package cgg.a05;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

    public class Ray {
    public final Point x0;
    public final Direction d;
    public final double t_min;
    public final double t_max;

    public    Ray( Direction d, double t_min, double t_max) {
        this.x0 = new Point(0,0,0);
        this.d = d;
        this.t_min = t_min;
        this.t_max = t_max;
    }

    public Point pointAt(double t) {
        return Vector.add(Vector.multiply(t, d), x0);
    }

    public boolean isValid(double t) {
        return t_min <= t && t <= t_max; //simplified
    }
}