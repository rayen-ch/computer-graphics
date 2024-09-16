package cgg.a08;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;


public record Zylinder(Point point, double height, double radius, Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        Direction oc = Vector.subtract(ray.x0, point);
        double a = Math.pow(ray.d.x(), 2) + Math.pow(ray.d.z(), 2);
        double b = 2 * (oc.x() * ray.d.x() + oc.z() * ray.d.z());
        double c = Math.pow(oc.x(), 2) + Math.pow(oc.z(), 2) - Math.pow(radius, 2);
        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (discriminant >= 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

            double y1 = ray.x0.y() + t1 * ray.d.y();
            double y2 = ray.x0.y() + t2 * ray.d.y();
            boolean hit1 = (y1 >= point.y() && y1 <= point.y() + height);
            boolean hit2 = (y2 >= point.y() && y2 <= point.y() + height);

            if (hit1 && ray.isValid(t1)) {
                Direction normal = Vector.normalize(new Direction(oc.x(), 0, oc.z()));
                Point intersectionPoint = ray.pointAt(t1);
                return new Hit(t1, intersectionPoint, normal, material);
            } else if (hit2 && ray.isValid(t2)) {
                Direction normal = Vector.normalize(new Direction(oc.x(), 0, oc.z()));
                Point intersectionPoint = ray.pointAt(t2);
                return new Hit(t2, intersectionPoint, normal, material);
            }
        }

        return null;
    }
}