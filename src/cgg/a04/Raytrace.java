package cgg.a04;
import cgtools.*;


public class Raytrace implements Sampler {
    private final Camera camera;
    private final Group scene;

    public Raytrace(Camera camera, Group scene) {
        this.camera = camera;
        this.scene = scene;
    }

    @Override
    public Color getColor(double x, double y) {
        Direction direction = camera.rayDirection(x, y);
        Ray r = new Ray(new Point(0, 0, 0), direction, 0, Double.POSITIVE_INFINITY);
        Hit hit = scene.intersect(r);
        return shade(hit.getNormalVector(),hit.getHitColor());
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
