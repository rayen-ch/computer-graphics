package cgg.a02;

import cgtools.Color;
import cgtools.Sampler;

import java.util.ArrayList;
import java.util.Collections;

import static cgtools.Vector.black;

public class ColoredDiscs  implements Sampler {
     ArrayList<Disc> discs = new ArrayList<>();
    int anzahlKreise;
    private int height;
    private int width;
    private int r;

    public ColoredDiscs(int height, int width, int anzahlKreise){


        this.width = width;
        this.anzahlKreise = anzahlKreise;
        this.height = height;

        for(int i = 0; i < anzahlKreise; i++){
            Color color =new Color(cgtools.Random.random(),cgtools.Random.random(),cgtools.Random.random());
            double min = 5;
            double max = 140;
            double range = max - min;
            double r = cgtools.Random.random() * range + min;

            Disc newDisc = new Disc(cgtools.Random.random()*width ,cgtools.Random.random()*height,r,color);
            discs.add(newDisc);

        }

    }
    public Color getColor(double x, double y){
    Collections.sort(discs);
        for(Disc d : discs){
            if(d.isPointInDisc(x, y)){
                return d.getColor();
            }
        }
        return black;
    }

}

