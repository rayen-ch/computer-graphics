package cgg.a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

    public class Sphere {
    public double radius;
    public Color color;
    public Point centerPoint;

    public Sphere(double radius, Color color, Point centerPoint) {
        this.radius = radius;
        this.color = color;
        this.centerPoint = centerPoint;
    }
    public Hit intersect(Ray r) { //intersection Sphere w ray = hit
        Direction x0 = Vector.subtract(r.x0, centerPoint);//direction to Sphere center
        double a = Vector.dotProduct(r.d, r.d);
        double b = 2 * Vector.dotProduct(r.d, x0);
        double c = Vector.dotProduct(x0, x0) - radius * radius;
        double dis = b * b - 4 * a * c;//Δ = b2 - 4ac
//https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FLine%25E2%2580%2593sphere_intersection&psig=AOvVaw0WbyIC00-84O5M7Dq72TUz&ust=1714648021368000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJCI9eOn7IUDFQAAAAAdAAAAABAE
        if (dis >= 0) {//gibt inter
            double smallT = (-b - Math.sqrt(dis)) / 2 * a;
            if (r.isValid(smallT)) {
                Direction nVector = Vector.divide(Vector.subtract(r.pointAt(smallT), centerPoint), radius);
                return new Hit(smallT, r.pointAt(smallT), Vector.normalize(nVector), color);
            }
        }
        double t = Vector.dotProduct(Vector.subtract(centerPoint, r.x0), x0)/Vector.dotProduct(r.d, x0);
        
        if(Vector.length(Vector.subtract(r.pointAt(t), centerPoint)) > radius) {//tangente
            return null; // outside
        } else if(r.isValid(t)){
            return new Hit(t, r.pointAt(t), x0, color );//yes
        }
        return null;
    }
}