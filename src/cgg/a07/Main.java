/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a07;

import cgg.*;

import cgtools.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.lang.Math.*;
import cgtools.Matrix;

public class Main {

    static List<Shape> shapes = new ArrayList<>();

    public static void main(String[] args) {
        final int width = 1920;
        final int height = 1080;

        EmitterMaterial graublau = new EmitterMaterial(Vector.color(0.0, 0.02, 0.02));
        EmitterMaterial grün = new EmitterMaterial(Vector.color(0, 0.7, 0.1));
        EmitterMaterial grün2 = new EmitterMaterial(Vector.color(0.35, 0.8, 0));
        EmitterMaterial grün3 = new EmitterMaterial(Vector.color(0.1, 0.4, 0));
        EmitterMaterial braun = new EmitterMaterial(Vector.color(0.9, 0.5, 0.05));
        EmitterMaterial braun2 = new EmitterMaterial(Vector.color(0.5, 0.25, 0.03));
        EmitterMaterial braun3 = new EmitterMaterial(Vector.color(0.6, 0.4, 0.02));
        EmitterMaterial grün4 = new EmitterMaterial(Vector.color(0.139, 0.69, 0.19));

        List<EmitterMaterial> grünss = new ArrayList<EmitterMaterial>();
        Collections.addAll(grünss, grün, grün2, grün3, grün4);
        List<EmitterMaterial> braunss = new ArrayList<EmitterMaterial>();
        Collections.addAll(braunss, braun, braun2, braun3);

        Camera camera = new Camera(width, height, toRadians(70),
                // Matrix.multiply(Matrix.translation(0, 0.2, 2.5),
                Matrix.rotation(Vector.xAxis, 0)
                // Matrix.translation(0, 2, 0))
        );

        shapes.add(new Background(new Backgroundmaterial(Vector.white)));
        shapes.add(new Disc(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), 2, graublau));

        //-- Baum creator

        int anzahl = 55;
        int counter_kugel = 0;
        int counter_stamm = 0;
        for (int i = 0; i < anzahl; i++) {
            if (counter_kugel == grünss.size())
                counter_kugel = 0;
            if (counter_stamm == braunss.size())
                counter_stamm = 0;

            int x = (int) (Random.random() * 120) + 1;
            x -= 60;
            int z = (int) (Random.random() * -100) - 2;
            double baumradius = (Random.random() * 1.2) + 0.3;

            shapes.add(new Zylinder(new Point(x, -0.5, z), 5, baumradius, braunss.get(counter_stamm)));
            shapes.add(new Sphere(new Point(x, 5, z), baumradius * 3, grünss.get(counter_kugel)));

            counter_kugel++;
            counter_stamm++;
        }



        //---

        var scene = new Group(new Transformation(Matrix.translation(0, 0, 0)), shapes);

        Raytrace content = new Raytrace(camera, scene);

        // Creates an image and iterates over all pixel positions inside the image.
        var image = new Image(width, height);
        image.sample(content, 2);

        // Write the image to disk.
        final String filename = "doc/a07-scene.png";
        image.write(filename);
        System.out.println("Wrote image: " + filename);
    }

}
