package cgg.a02;

import cgtools.Color;

import static cgtools.Vector.black;

public class Disc implements Comparable<Disc>{

    private double posX;
    private double posY;
    private double r;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
    public Disc(double posX, double posY, double r, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.r = r;
        this.color = color;
    }

    public boolean isPointInDisc(double x, double y){
        return Math.sqrt(Math.pow(posX - x, 2) + Math.pow(posY - y, 2)) < r;
    }

    @Override
    public int compareTo(Disc disc){

        int comp = 0;

        if(r < disc.getR()){
            comp = -1;

        }else if(r > disc.getR()){
            comp = 1;
        }
        return comp;
    }
}
