package cgg.a01;

import static cgtools.Vector.*;
import cgg.*;
import cgtools.Color;

public class Main {

  public static void main(String[] args) {
    final int width = 512;
    final int height = 256;

    // This class instance defines the contents of the image.
    ConstantColor content = new ConstantColor(gray);
    double centrex= width / 2;
    double centrey = height / 2;
    double radius = 16;
    // kreisscheibe kreis = new kreisscheibe(red,radius,centrex,centrey);
    Polka polka =new Polka(red,width, height,radius,11,6);

    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    Color red =new Color(1,0,0);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        // Sets the color for one particular pixel.
        //image.setPixel(i, j,kreis.getColor(i,j));
        image.setPixel(i, j,polka.getColor(i,j));

      }
    }

    // Write the image to disk.
    final String filename = "doc/a01-image.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}