/** @author hartmut.schirmacher@bht-berlin.de && henrik.tramberend@bht-berlin.de */
package cgg;

import cgg.a02.ColoredDiscs;
import cgg.a08.OnePixel;
import cgtools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Image  {
  private int width;
  private int height;
 private Color[] pixel;
  private double[] data;

  public Image(int width, int height){
    this.width = width;
    this.height = height;
    this.data = new double[width*height*3];
    //this.pixel = new Color[width*width*width];

  }

  public void setPixel(int i, int j, Color color) {
        if (i >= 0 && i < width && j >= 0 && j < height) {
            int k = (3*((j*width)+i));
            data[k]= color.r();
            data[k+1]= color.g();
            data[k+2]= color.b();
        }
    }

    public void setPixel(double i, double j, Color color) {
        if (i >= 0 && i < width && j >= 0 && j < height) {
            int k = (int) (3*((j*width)+i));
            data[k]= color.r();
            data[k+1]= color.g();
            data[k+2]= color.b();
        }
    }
    public void sample(ColoredDiscs cd) {

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {

                double r = 0;
                double g = 0;
                double b = 0;
                int abtastanzahl = 2;

                for (int xi = 0; xi < abtastanzahl; xi++) {
                    for (int yi = 0; yi < abtastanzahl; yi++) {

                        double rx = Random.random();
                        double ry = Random.random();
                        double xs = x + (xi + rx) / abtastanzahl;
                        double ys = y + (yi + ry) / abtastanzahl;

                        Color supercolor = cd.getColor(xs, ys);
                        r += supercolor.r();
                        g += supercolor.g();
                        b += supercolor.b();
                    }
                }
                // Gammakorrektur mit Gamma = 2.2
                double gamma = 1 / 2.2;
                r = Math.pow(r / (abtastanzahl * abtastanzahl), gamma);
                g = Math.pow(g / (abtastanzahl * abtastanzahl), gamma);
                b = Math.pow(b / (abtastanzahl * abtastanzahl), gamma);

                setPixel(x, y, new Color(r, g, b));
            }
        }
    }
//    public void sample1(RayTracer cd) {
//
//        for (int x = 0; x != width; x++) {
//            for (int y = 0; y != height; y++) {
//
//                double r = 0;
//                double g = 0;
//                double b = 0;
//                int abtastanzahl = 2;
//
//                for (int xi = 0; xi < abtastanzahl; xi++) {
//                    for (int yi = 0; yi < abtastanzahl; yi++) {
//
//                        double rx = cgtools.Random.random();
//                        double ry = cgtools.Random.random();
//                        double xs = x + (xi + rx) / abtastanzahl;
//                        double ys = y + (yi + ry) / abtastanzahl;
//
//                        Color supercolor = cd.getColor(xs, ys);
//                        r += supercolor.r();
//                        g += supercolor.g();
//                        b += supercolor.b();
//                    }
//                }
//                // Gammakorrektur mit Gamma = 2.2
//                double gamma = 1 / 2.2;
//                r = Math.pow(r / (abtastanzahl * abtastanzahl), gamma);
//                g = Math.pow(g / (abtastanzahl * abtastanzahl), gamma);
//                b = Math.pow(b / (abtastanzahl * abtastanzahl), gamma);
//
//                setPixel(x, y, new Color(r, g, b));
//            }
//        }
//    }
    public void sample(Sampler cd) {

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {

                double r = 0;
                double g = 0;
                double b = 0;
                int abtastanzahl = 10;

                for (int xi = 0; xi < abtastanzahl; xi++) {
                    for (int yi = 0; yi < abtastanzahl; yi++) {

                        double rx = Random.random();
                        double ry = Random.random();
                        double xs = x + (xi + rx) / abtastanzahl;
                        double ys = y + (yi + ry) / abtastanzahl;

                        Color supercolor = cd.getColor(xs, ys);
                        r += supercolor.r();
                        g += supercolor.g();
                        b += supercolor.b();
                    }
                }
                // Gammakorrektur mit Gamma = 2.2
                double gamma = 1 / 2.2;
                r = Math.pow(r / (abtastanzahl * abtastanzahl), gamma);
                g = Math.pow(g / (abtastanzahl * abtastanzahl), gamma);
                b = Math.pow(b / (abtastanzahl * abtastanzahl), gamma);

                setPixel(x, y, new Color(r, g, b));
            }
        }
    }
    public void sample(Sampler cd,int n) {

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {

                double r = 0;
                double g = 0;
                double b = 0;

                for (int xi = 0; xi < n; xi++) {
                    for (int yi = 0; yi < n; yi++) {

                        double rx = Random.random();
                        double ry = Random.random();
                        double xs = x + (xi + rx) / n;
                        double ys = y + (yi + ry) / n;

                        Color supercolor = cd.getColor(xs, ys);
                        r += supercolor.r();
                        g += supercolor.g();
                        b += supercolor.b();
                    }
                }
                // Gammakorrektur mit Gamma = 2.2
                double gamma = 1 / 2.2;
                r = Math.pow(r / (n * n), gamma);
                g = Math.pow(g / (n * n), gamma);
                b = Math.pow(b / (n * n), gamma);

                setPixel(x, y, new Color(r, g, b));
            }
        }
    }

  public void write(String filename) {
    // Use cggtools.ImageWriter.write() to implement this.
    ImageWriter.write(filename, data, width, height);
  }

  private void notYetImplemented() {
    System.err.println("Please complete the implementation of class cgg.Image as part of assignment 1.");
    System.exit(1);
  }
    public void sampleParallel(Sampler sum, int n) throws InterruptedException, ExecutionException {
        //int cores = Runtime.getRuntime().availableProcessors();
        int cores = 3;
        System.out.println("Du benutzt " + cores + " Kerne");
        ExecutorService pool = Executors.newFixedThreadPool(cores);
        List<Future<Color>> pixels = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Future<Color> pixel = pool.submit(new OnePixel(sum, x, y, n));
                pixels.add(pixel);
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = pixels.get(x * height + y).get();
                setPixel(x, y, color);
            }
        }
        pool.shutdown();
    }

}
