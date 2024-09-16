package cgg.a08;

import cgtools.Matrix;

public class Transformation {

        Matrix orignal;
        Matrix invert;
        Matrix transpose;

    public Transformation(Matrix m) {
        this.orignal = m;
        this.invert = Matrix.invert(m);
        this.transpose = Matrix.transpose(m);
    }

        public Ray transform(Ray r){
        return new Ray(Matrix.multiply(invert, r.x0), Matrix.multiply(invert, r.d), r.t_min, r.t_max);
    }

        public Hit transform(Hit h){
        return new Hit(h.t(), Matrix.multiply(orignal, h.x()), Matrix.multiply(orignal, h.n()), h.m());
    }


}
