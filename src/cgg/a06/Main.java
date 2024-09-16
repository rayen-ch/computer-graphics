package cgg.a06;

import cgg.Image;


import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;
import cgtools.Matrix.*;

import java.util.ArrayList;
import java.util.List;

import static cgtools.Vector.gray30;
import static java.lang.Math.toRadians;

public class Main {

    public static void main(String[] args) {
        {
            final int width = 1920;
            final int height = 1080;
            EmitterMaterial graublau = new EmitterMaterial(Vector.color(0.0, 0.02, 0.02));
            EmitterMaterial grün = new EmitterMaterial(Vector.color(0, 0.7, 0.1));
            EmitterMaterial grün2 = new EmitterMaterial(Vector.color(0.35, 0.8, 0));
            EmitterMaterial grün3 = new EmitterMaterial(Vector.color(0.1, 0.4, 0.4));
            EmitterMaterial pink = new EmitterMaterial(Vector.color(2, 0.2, 0.3));
            EmitterMaterial bleu = new EmitterMaterial(Vector.color(0, 0, 1));
            EmitterMaterial shiny = new EmitterMaterial(Vector.color(2));
            EmitterMaterial yello = new EmitterMaterial(Vector.color(2, 2, 0));
//            double angleInRadians = Math.toRadians(20);
            Camera camera = new Camera(width, height, toRadians(70),
                    // Matrix.multiply(Matrix.translation(0, 0.2, 2.5),
                    Matrix.rotation(Vector.xAxis,20 )
                    //     Matrix.translation(0, 2, 0))
            );


            List<Shape> shapes = new ArrayList<>();
            shapes.add(new Background(new Backgroundmaterial(gray30)));
            shapes.add(new Disc(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), 2, yello));

            //--- Kugeln
            shapes.add(new Sphere(new Point(0, 1.0, -10.0), 0.5, grün3));
            shapes.add(new Sphere(new Point(-1, 0.5, -10.0), 0.6, pink));
            shapes.add(new Sphere(new Point(0.5, 0.4, -12.5), 0.3, grün));
            shapes.add(new Sphere(new Point(-0.4, -0.3, -12.0), 0.2, pink));
            shapes.add(new Sphere(new Point(0.13, -0.35, -12.0), 0.18, grün));

            shapes.add(new Sphere(new Point(2, 1.0, -9.0), 0.5, grün));
            shapes.add(new Sphere(new Point(1, 0.5, -10.0), 0.6, grün));
            shapes.add(new Sphere(new Point(-3.5, 0.4, -8.5), 0.3, pink));
            shapes.add(new Sphere(new Point(-4, 1, -12.0), 0.8, grün3));
            shapes.add(new Sphere(new Point(5, -0.35, -12.0), 0.18, grün2));

            //--- Laternen
            for(int i = -60; i < 0; i+= 2.5){//24-mal
                shapes.add((new Zylinder(new Point(-2, 1.5, i), 1, 0.5, shiny)));
                shapes.add((new Zylinder(new Point(-2, -0.5, i), 2, 0.15, graublau)));

                shapes.add((new Zylinder(new Point(2, 1.5, i), 1, 0.5, shiny)));
                shapes.add((new Zylinder(new Point(2, -0.5, i), 2, 0.15, graublau)));
            }

            //--- Pyramiden
            shapes.add(new Pyramide(new Point(0, 4.5, -80), 35, 30, pink));
            shapes.add(new Pyramide(new Point(-4, 1, -8), 3.5, 3, grün));
            shapes.add(new Pyramide(new Point(4, 1, -8), 3.5, 3, bleu));
            // ------------

            var scene = new Group(shapes);

            Raytrace content = new Raytrace(camera, scene);

            // Creates an image and iterates over all pixel positions inside the image.
            var image = new Image(width, height);
            image.sample(content,2);

            // Write the image to disk.
            final String filename = "doc/a06-20--plus.png";
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        }
    }

}
