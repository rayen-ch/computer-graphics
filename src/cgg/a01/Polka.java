package cgg.a01;

import cgtools.*;

import static cgtools.Vector.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Polka {

  private Color color;
  private double width;
  private double height;
  private double r;
  private double vertikal;
  private double horizontal;
  public Polka(Color color, double width, double height, double r, double vertikal, double horizontal) {
    this.color = color;
    this.width = width;
    this.height = height;
    this.r = r;
    this.vertikal = vertikal;
    this.horizontal = horizontal;
  }

  public Color getColor(double x, double y) {
    //The width and height of each field (wv and hh)
    double wv = width / vertikal;
    double hh = height / horizontal;

    // Berechne die Position des Feldmittelpunkts, zu dem der Punkt (x, y) gehört
    int fieldX = (int) Math.floor(x / wv);//s used in the code to find the index of the field to which a given point (x, y) belongs in the grid.
    int fieldY = (int) Math.floor(y / hh);
    double midX = (fieldX + 0.5) * wv;
    double midY = (fieldY + 0.5) * hh;


    // Berechne die Entfernung zwischen dem Punkt (x, y) und dem Mittelpunkt des Feldes
    double distance = Math.sqrt(Math.pow(x - midX, 2) + Math.pow(y - midY, 2));

    // Überprüfe, ob der Punkt innerhalb des Kreises pro Feld liegt
    double d = x/width; //verlauf x wird größer
    if (distance < r) {

      double a = Math.random();
      double b = Math.random();
      double c = Math.random();
      //System.out.println(a+" "+b+" "+c);
      return color(a,b,d);
      //return color(d,0.3,0.4); //d helligkeit ist red  1
    } else {
      // Rückgabe einer anderen Farbe, wenn der Punkt außerhalb des Kreises liegt
        return color(d,0.3,0.4);
      //return black;
    }
  }


}