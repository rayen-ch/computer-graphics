package cgg.a02;

import static cgtools.Vector.*;
import cgg.*;
import cgtools.Color;

public class Main {

    public static void main(String[] args) {
        final int width = 512;
        final int height = 256;

        // This class instance defines the contents of the image.
        ConstantColor content = new ConstantColor(gray);
        ColoredDiscs cd =new ColoredDiscs(height,width,48);
        // Creates an image and iterates over all pixel positions inside the image.
        Image image = new Image(width, height);
        image.sample(cd);

        // Write the image to disk.
        final String filename = "doc/a02-image-gamma.png";
        image.write(filename);
        System.out.println("Wrote image: " + filename);

    }
}