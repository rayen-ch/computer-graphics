package cgg.a05;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class Backgroundmaterial implements Material{

    private Color color;


    public Backgroundmaterial (Color color){
        this.color = color;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return null;
    }

    @Override
    public Color emissoin(Ray r, Hit h) {
        return color;
    }


    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }
}

