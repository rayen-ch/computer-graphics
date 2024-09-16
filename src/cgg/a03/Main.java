package cgg.a03;

import cgg.Image;
import cgtools.Vector;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    final int width = 512;
    final int height = 256;

    Image image = new Image(width, height);
    Camera camera = new Camera(width, height, Math.PI /2);
    Sphere s0 = new Sphere(0.25, Vector.magenta, Vector.point(-0.8, 0.3, -1.5));
    Sphere s1 = new Sphere(0.5, Vector.blue, Vector.point(0.0, -0.15, -3.0));
    Sphere s2 = new Sphere(1.3, Vector.green, Vector.point(1.5, -0.15, -3.0));
    Sphere s3 = new Sphere(0.8, Vector.red, Vector.point(-1.2,-1, -3.0));
    Sphere s4 = new Sphere(1.6, Vector.yellow, Vector.point(1.0,-2, -1.0));
    Sphere s5 = new Sphere(0.1, Vector.gray30, Vector.point(-0.1,0.4, -1.5));
    List<Sphere> spheres = new ArrayList<>();
    spheres.add(s0);
    spheres.add(s1);
    spheres.add(s2);
    spheres.add(s3);
    spheres.add(s4);
    spheres.add(s5);

    Raytrace raytrace = new Raytrace(camera, spheres);
    image.sample(raytrace);

    // Write the image to disk.
    final String filenameThreeSpheres = "doc/a03-spheres.png";
    image.write(filenameThreeSpheres);
    System.out.println("Wrote image: " + filenameThreeSpheres);
  }
}
