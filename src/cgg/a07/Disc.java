package cgg.a07;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Disc implements Shape {

    private Point anker;
    private Direction normalvektor;
    private double radius;
    private Material material;

    public Disc(Point p, Direction n, double r, Material m) {
        this.anker = p;
        this.normalvektor = n;
        this.radius = r;
        this.material = m;
    }

    @Override
    public Hit intersect(Ray ray) {
        double zeile1 = Vector.dotProduct(Vector.subtract(anker, ray.x0), normalvektor);
        double zeile2 = Vector.dotProduct(normalvektor, ray.d);
        double t = zeile1 / zeile2;

        if (ray.isValid(t)) {
            Point position = ray.pointAt(t);
            double s = Vector.length(Vector.subtract(anker, ray.x0));//d zw ray-c and disc-c

            if (radius > s) {
                return new Hit(t, position, normalvektor, material);
            }
        }
        return null;
    }

}
