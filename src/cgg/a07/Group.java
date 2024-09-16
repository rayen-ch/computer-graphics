package cgg.a07;


import java.util.List;
public record Group(Transformation trans, Shape... shapes) implements Shape {

    public Group(Transformation trans, List<Shape> list) {
        this(trans, list.toArray(new Shape[list.size()]));
    }

    @Override
    public Hit intersect(Ray r) {
        double tmax = Double.POSITIVE_INFINITY;
        //ohne
        Hit temp = shapes[0].intersect(trans.transform(r));
        for (Shape shape : shapes) {
            //uber for-loop
            Ray transRay = trans.transform(r);
            Hit intersect = shape.intersect(transRay);
            if (intersect != null && intersect.t() < tmax) {
                tmax = intersect.t();
                temp = intersect;
            }
        }
        //normal transformieren
        return temp != null ? trans.transform(temp) : null;
    }

}


