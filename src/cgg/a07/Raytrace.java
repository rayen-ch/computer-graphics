package cgg.a07;

import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;


public record Raytrace (Camera camara, Group scene) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
        Ray ray = camara.generateRay(x, y);
        Hit hit = scene.intersect(ray);
        Color color = null;

        if (hit != null) {
            color = calcRadiance(scene, ray, 5);
        }
        return color;
    }

    // public Color shade(Direction n, Color c) {
    // Direction d = Vector.normalize(Vector.direction(1, 1, 0.5));
    // Color ambient = Vector.multiply(0.2, c);
    // Color diffuse = Vector.multiply(0.8 * Math.max(0, Vector.dotProduct(d, n)),
    // c);
    // return Vector.add(ambient, diffuse);
    // }
    static Color calcRadiance(Shape scene, Ray ray, int depth) {
        if(depth == 0) {
            return Vector.black;
        }

        Hit hit = scene.intersect(ray);

        Ray scattered = hit.m().scatteredRay(ray, hit);
        if (scattered != null){
            Color ea = Vector.add(hit.m().emissoin(ray, hit), hit.m().albedo(ray, hit));
            return Vector.multiply(ea, calcRadiance(scene, scattered, depth-1));
        }else {
            return hit.m().emissoin(ray, hit);
        }
    }

}