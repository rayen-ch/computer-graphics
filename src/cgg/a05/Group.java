package cgg.a05;

import java.util.List;

public class Group implements Shape {
    private Shape[] shapes;

    public Group(Shape... shapes) {
        this.shapes = shapes;
    }
    /*
 Shape... shapes: This syntax indicates that the constructor can accept zero or more Shape objects as arguments.
 this.shapes = shapes;: Inside the constructor, the shapes parameter, which is an array of Shape objects, is assigned to the shapes field of the Group class.
    */

    public Group(List<Shape> list) {
        this.shapes = list.toArray(new Shape[list.size()]);
    }
    //Sort
    @Override
    public Hit intersect(Ray r) {
        double tmax = Double.POSITIVE_INFINITY;
        Hit temp = shapes[0].intersect(r);
        for (Shape shape : shapes) {
            Hit intersection = shape.intersect(r);
            if (intersection != null) {
                if (tmax >= intersection.t()) {
                    tmax = intersection.t();
                    temp = intersection;
                }
            }
        }
        return temp;
    }
}



/*
    public Group() {
        this.shapes = new ArrayList<>();
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public void addAll(List<Shape> shapeList) {
        shapes.addAll(shapeList);
    }
*/