package cgg.a05;

import cgg.*;
import cgtools.*;

import java.util.ArrayList;
import java.util.List;

import static cgtools.Vector.*;

public class Main {

  public static void main(String[] args) {

      final int width = 1920;
      final int height = 1080;
      EmitterMaterial graublau = new EmitterMaterial(Vector.color(0.0, 0.02, 0.02));
      EmitterMaterial grün = new EmitterMaterial(Vector.color(0, 0.7, 0.1));
      EmitterMaterial grün2 = new EmitterMaterial(Vector.color(0.35, 0.8, 0));
      EmitterMaterial grün3 = new EmitterMaterial(Vector.color(0.1, 0.4, 0.4));
      EmitterMaterial red = new EmitterMaterial(Vector.color(1, 0.0, 0.0));
      EmitterMaterial gray = new EmitterMaterial(Vector.color(0.5, 0.5, 0.5));
      Camera camera = new Camera(width, height, Math.PI / 3);
      List<Shape> shapes = new ArrayList<>();

      shapes.add(new Background(new Backgroundmaterial(gray30)));

     shapes.add(new Disc(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), 2, red));
     shapes.add(new Sphere(new Point(0, 0.0, -3.0), 0.5, grün3));
     shapes.add(new Sphere(new Point(-1, 0, -3.0), 0.6, grün2));
     shapes.add(new Sphere(new Point(0.5, 0.4, -2.5), 0.3, grün));
     shapes.add(new Sphere(new Point(-0.4, -0.3, -2.0), 0.2, grün2));
      shapes.add(new Sphere(new Point(0.13, -0.35, -2.0), 0.18, grün));
      // ------------

      var scene = new Group(shapes);

      Raytrace content = new Raytrace(camera, scene);

      // Creates an image and iterates over all pixel positions inside the image.
      var image = new Image(width, height);
      image.sample(content,1);

      // Write the image to disk.
      final String filename = "doc/a05-own-*-scene.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);
    }




}