package cgg.a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Sampler;
import cgtools.Vector;

import java.util.List;

public class Raytrace implements Sampler {
    private final Camera camera;
    private final List<Sphere> spheres;

    public Raytrace(Camera camera, List<Sphere> spheres) {
        this.camera = camera;
        this.spheres = spheres;
    }

    @Override
    public Color getColor(double x, double y) {
        Direction direction = camera.rayDirection(x, y);
        Ray ray = new Ray(Vector.point(0, 0, 0), direction, 0, Double.POSITIVE_INFINITY);

        Hit closestHit = null;
        double closestT = Double.POSITIVE_INFINITY;

        for (Sphere sphere : spheres) {//closest sphere
            Hit hit = sphere.intersect(ray);
            if (hit != null && hit.getT() < closestT) {
                closestHit = hit;
                closestT = hit.getT();
            }
        }

        if (closestHit != null) {
            Direction normal = closestHit.getNormalVector();
            Color color = closestHit.getHitColor();
            return shade(normal, color);
        } else {
            return new Color(0.5,0.5,0.5);
        }
    }

    // Function to calculate shading
    private Color shade(Direction normal, Color color) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        double cos_angle = Math.max(0, Vector.dotProduct(lightDir, normal));
        Color ambient = Vector.multiply(0.1, color);
        Color diffuse = Vector.multiply(0.9 * cos_angle, color);
        return Vector.add(ambient, diffuse);
    }
}
