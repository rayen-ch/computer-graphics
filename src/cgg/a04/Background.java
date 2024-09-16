package cgg.a04;

import cgtools.Color;
import cgtools.Vector;

public class Background implements Shape {

    private Color color;

    public Background(Color color){
        this.color = color;
    }

    @Override
    public Hit intersect(Ray ray) {
        double t = Double.POSITIVE_INFINITY;
        return new Hit(t, ray.pointAt(t) ,Vector.negate(ray.d) , color);
    }
}
