import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.util.ArrayList;

public class Polygon {
    private PVector[] points;
    private PShape myShape;

    private PApplet a;

    public Polygon(PVector... points) {
        this.points = new PVector[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i].copy();
        }

        this.a = Main.pApplet();

        myShape = a.createShape();
        myShape.beginShape();
        for (PVector point : points) {
            myShape.vertex(point.x, point.y);
        }
        myShape.endShape();
    }

    public Polygon(Polygon p) {
        this(p.points);
    }

    public Polygon(Polygon p, PVector position) {

        this.points = new PVector[p.points.length];
        for (int i = 0; i < points.length; i++) {
            PVector oldVec = p.points[i];
            this.points[i] = new PVector(oldVec.x + position.x, oldVec.y + position.y);
        }
    }

    public Rect getBounds() {
        float bottom = Float.MIN_VALUE;
        float top = Float.MAX_VALUE;
        float left = Float.MAX_VALUE;
        float right = Float.MIN_VALUE;
        for (PVector p : points) {
            bottom = Math.max(bottom, p.y);
            top = Math.min(top, p.y);
            left = Math.min(left, p.x);
            right = Math.max(right, p.x);
        }
        return new Rect(new PVector(left, top), new PVector(right, bottom));
    }

    public PVector getCenter() {
        PVector total = new PVector();
        for (PVector point : points) {
            total.add(point);
        }
        total.div(points.length);
        return total;
    }

    public PVector[] getAxes() {
        PVector[] axes = new PVector[points.length];
        for (int idx = 0; idx < points.length; idx++) {
            PVector pt1 = points[idx];
            PVector pt2 = points[(idx + 1) % points.length];
            PVector diff = pt1.copy().sub(pt2);
            PVector normal = new PVector(diff.y, -diff.x);
            normal.normalize();
            axes[idx] = normal;
        }
        return axes;
    }

    public float[] projectOntoAxis(PVector axis) {
        float minDot = axis.dot(points[0]);
        float maxDot = minDot;

        for (int i = 1; i < points.length; i++) {
            float value = axis.dot(points[i]);
            minDot = Math.min(minDot, value);
            maxDot = Math.max(maxDot, value);
        }
        return new float[]{minDot, maxDot};
    }

    public IntersectInfo intersects(Polygon p) {
        ArrayList<PVector> axes = new ArrayList<PVector>();
        for (PVector axis : getAxes()) axes.add(axis);
        for (PVector axis : p.getAxes()) axes.add(axis);

        PVector minAxis = null;
        float minOverlap = Float.MAX_VALUE;

        for (int i = 0; i < axes.size(); i++) {
            PVector axis = axes.get(i);
            float[] proj1 = projectOntoAxis(axis);
            float[] proj2 = p.projectOntoAxis(axis);

            float A = PApplet.min(proj1);
            float B = PApplet.max(proj1);
            float C = PApplet.min(proj2);
            float D = PApplet.max(proj2);

            if (B - C >= 0 && D - A >= 0) {
                float overlap = Math.abs(Math.max(A, C) - Math.min(B, D));

                if (overlap < minOverlap) {
                    minOverlap = overlap;
                    minAxis = axis;
                }
            } else {
                return new IntersectInfo(false, new PVector(0, 0));
            }
        }

        if (minAxis == null) {
            System.out.println("WHY IS THE MINIMUM AXIS NULL");
            System.exit(0);
        }

        minAxis.mult(minOverlap);

        PVector d = getCenter().copy().sub(p.getCenter());
        if (d.dot(minAxis) < 0) {
            minAxis.mult(-1);
        }

        return new IntersectInfo(true, minAxis);
    }

    public PShape getShape() {
        return myShape;
    }

    public void setFill(int c) {
        myShape.setFill(c);
    }

    public String toString() {
        String temp = "[";
        for (PVector point : points) {
            temp += point;
        }
        temp += "]";
        return temp;
    }

    static class IntersectInfo {
        private boolean collided;
        private PVector reverseForce;

        public IntersectInfo(boolean collided, PVector reverseForce) {
            this.collided = collided;
            this.reverseForce = reverseForce;
        }

        public boolean hasCollided() {
            return collided;
        }

        public PVector getReverseForce() {
            return reverseForce;
        }

        public String toString() {
            return "Collide: " + collided + ", Force: " + getReverseForce();
        }
    }
}
