package cgg.a04;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public class Hit {
    private  double t;
    private  Point hit;
    private  Direction normalVector;
    private  Color hitColor;

    public Hit(double t, Point hit, Direction normalVector, Color hitColor) {
        this.t = t;
        this.hit = hit;
        this.normalVector = normalVector;
        this.hitColor = hitColor;
    }

    public double getT() {
        return t;
    }

    public Point getHit() {
        return hit;
    }

    public Direction getNormalVector() {
        return normalVector;
    }

    public Color getHitColor() {
        return hitColor;
    }
}

