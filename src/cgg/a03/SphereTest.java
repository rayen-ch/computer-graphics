package cgg.a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTest {

    @Test
    public void testIntersect() {
        // Define sphere parameters
        double radius = 1.0;
        Color color = new Color(1.0, 0.0, 0.0); // Red color
        Point centerPoint = new Point(0.0, 0.0, 0.0); // Center at origin

        // Create the sphere
        Sphere sphere = new Sphere(radius, color, centerPoint);

        // Define a ray that intersects the sphere
        Point origin1 = new Point(0.0, 0.0, -3.0); // Origin of the ray
        Direction direction1 = new Direction(0.0, 0.0, 1.0); // Direction of the ray
        Ray ray1 = new Ray(origin1, direction1, 0.0, Double.POSITIVE_INFINITY);

        // Test intersection
        Hit hit1 = sphere.intersect(ray1);
        assertNotNull(hit1); // Assert that there is an intersection
        assertEquals(2.0, hit1.getT()); // Check the distance of intersection
        assertEquals(color, hit1.getHitColor()); // Check the color at the intersection

        // Define a ray that doesn't intersect the sphere
        Point origin2 = new Point(0.0, 0.0, -3.0); // Origin of the ray
        Direction direction2 = new Direction(0.0, 1.0, 0.0); // Direction of the ray
        Ray ray2 = new Ray(origin2, direction2, 0.0, Double.POSITIVE_INFINITY);

        // Test non-intersection
        Hit hit2 = sphere.intersect(ray2);
        assertNull(hit2); // Assert that there is no intersection
    }
}

