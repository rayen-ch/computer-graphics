package cgg.a04;

import cgg.Image;
import cgtools.Point;
import cgtools.Vector;

import java.util.ArrayList;
import java.util.List;

import static cgtools.Vector.yellow;

public class Main {

  public static void main(String[] args) {
    final int width = 1920;
    final int height = 1080;

     List<Shape> shapeList = new ArrayList<>();

     var camera = new Camera(width, height, Math.PI/2 );
     Group scene = new Group();
    /* Group x=new Group();
     x.add(new Background(Vector.gray30));
     x.add(new Disc(new Point(0.0, -0.5, 0.0),new Direction(0, 1, 0), 100, Vector.yellow));
     x.add(new Sphere( 0.7, Vector.red,new Point(-1.0, -0.25, -2.5)));
     x.add(new Sphere( 0.5, Vector.green,new Point(0.0, -0.25, -2.5)));
     x.add(new Sphere( 0.7, Vector.blue,new Point(1.0, -0.25, -2.5)));
*/
//      Background p = new Background(Vector.gray30);
//      Shape d = new Disc(new Point(0.0, -0.5, 0.0),new Direction(0, 1, 0), 100, Vector.yellow);
//      Shape a = new Sphere( 0.7, Vector.red,new Point(-1.0, -0.25, -2.5));
//      Shape b =new Sphere( 0.5, Vector.green,new Point(0.0, -0.25, -2.5));
//      Shape c =new Sphere( 0.7, Vector.blue,new Point(1.0, -0.25, -2.5));
//      Collections.addAll(shapeList,a,b,c,d,p);
//      scene =new Group(shapeList);
      List<Shape> shapes2 = new ArrayList<>();

      shapes2.add(new Background(Vector.color(0.451,0.608,0.816)));//ice bleu
      double y = 0.05;
      for (var i = 1.8; i >= -1.8; i -= 0.1) {
          shapes2.add(new Sphere( y, yellow,new Point(i + 1.7, 0, -5)));
          y += 0.03;
      }

      shapes2.add(new Sphere( 2.5, Vector.black,new Point(-1.8, 0, -7)));
      shapes2.add(new Sphere( 0.7, Vector.white,new Point(-1.8, 0.4, -5)));
      shapes2.add(new Sphere( 0.3, Vector.black,new Point(-1.6, 0.4, -4)));
      shapes2.add(new Sphere( 2.5, Vector.black,new Point(-1.8, -4, -7)));
      shapes2.add(new Sphere( 1.95, Vector.white,new Point(-1.7, -4, -6)));


      var x = new Group(shapes2);

    Raytrace content = new Raytrace(camera,x);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content);

    // Write the image to disk.
    final String filename = "doc/a04_own-scene.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }

}