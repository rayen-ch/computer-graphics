package cgg.a06;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public class Camera {

    public double angle;
    public int w;
    public int h;
    public double z;
    //add
   public Matrix v;
//matrix und U
    public Camera(int w, int h, double angle,Matrix v) {
        this.angle = angle;
        this.w=w;
        this.h=h;
        this.v=v;
    }

    public Direction rayDirection (double x, double y){
        double xd = x - (w/2);
        double yd = -(y - (h/2));
        double z = -(w/2)/Math.tan(angle/2);
        return Vector.normalize(Vector.direction(xd, yd, z));
    }
    public Ray generateRay(double x, double y) {
        double xv = x - w / 2.0;
        double yv = -(y - h / 2.0);
        double zv = -((w / 2.0) / Math.tan(angle / 2.0));
        Direction u = Vector.normalize(new Direction(xv, yv, zv));
        //return new Ray(u, 0, Double.POSITIVE_INFINITY);
        return new Ray(Matrix.multiply( v, new Point(0,0,0)),Matrix.multiply(v,u), 0, Double.POSITIVE_INFINITY);
    }
}
//public class Camera {
//
//    public double angle;
//    public int w;
//    public int h;
//    public double z;
//    public Matrix v; // View matrix
//    public Matrix rotationMatrix; // Rotation matrix
//
//    public Camera(int w, int h, double angle, Matrix v, Matrix rotationMatrix) {
//        this.angle = angle;
//        this.w = w;
//        this.h = h;
//        this.v = v;
//        this.rotationMatrix = rotationMatrix;
//    }
//
//    public Direction rayDirection(double x, double y) {
//        double xd = x - (w / 2);
//        double yd = -(y - (h / 2));
//        double z = -(w / 2) / Math.tan(angle / 2);
//        return Vector.normalize(Vector.direction(xd, yd, z));
//    }
//
//    public Ray generateRay(double x, double y) {
//        double xv = x - w / 2.0;
//        double yv = -(y - h / 2.0);
//        double zv = -((w / 2.0) / Math.tan(angle / 2.0));
//        Direction u = Vector.normalize(new Direction(xv, yv, zv));
//        Point eye = new Point(0, 0, 0); // Assuming the camera is at the origin
//        Point eyeTransformed = Matrix.multiply(v, eye); // Apply view transformation
//        Direction uTransformed = Matrix.multiply(v, u); // Apply view transformation
//        return new Ray(eyeTransformed, uTransformed, 0, Double.POSITIVE_INFINITY);
//    }
//
//    // Method to rotate the camera
//    public void rotateCamera(Matrix rotationMatrix) {
//        this.rotationMatrix = rotationMatrix;
//        this.v = Matrix.multiply(rotationMatrix, v); // Apply rotation to the view matrix
//    }
//}
