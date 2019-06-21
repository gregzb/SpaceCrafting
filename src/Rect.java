import processing.core.PVector;

import java.util.HashSet;
import java.util.Set;

public class Rect {
    private PVector topLeft;
    private PVector botRight;

    public Rect(PVector topLeft, PVector botRight) {
        this.topLeft = topLeft.copy();
        this.botRight = botRight.copy();
    }

    public Rect(Rect r, PVector offset) {
        this(r);
        this.topLeft.add(offset);
        this.botRight.add(offset);
    }

    public Rect(Rect r) {
        this.topLeft = r.topLeft.copy();
        this.botRight = r.botRight.copy();
    }

    public float x() {
        return topLeft.x;
    }

    public float y() {
        return topLeft.y;
    }

    public float width() {
        return botRight.x - topLeft.x;
    }

    public float height() {
        return botRight.y - topLeft.y;
    }

    public boolean collides(Rect r, boolean inclusive) {
        Rect r1 = this;
        Rect r2 = r;

        if (inclusive) {
            return r1.x() <= r2.x() + r2.width() && r1.x() + r1.width() >= r2.x() && r1.y() <= r2.y() + r2.height() && r1.y() + r1.height() >= r2.y();
        } else {
            return r1.x() < r2.x() + r2.width() && r1.x() + r1.width() > r2.x() && r1.y() < r2.y() + r2.height() && r1.y() + r1.height() > r2.y();
        }
    }

    public boolean contains(Rect r) {
        PVector p1 = r.topLeft;
        PVector p2 = r.botRight;

        return collides(new Rect(p1, p1), true) && collides(new Rect(p2, p2), true);
    }

    public int getIntersectPoints(Rect r) {
        return getIntersectPoints(r, true).size();
    }

    public Set<PVector> getIntersectPoints(Rect r, boolean first) {
        Set<PVector> iPoints = new HashSet<>();
        PVector[] points = new PVector[] {
                r.topLeft, new PVector(r.topLeft.x, r.botRight.y), r.botRight, new PVector(r.botRight.x, r.topLeft.y)
        };
        for (PVector p : points) {
            //count += collidesIncludes(new Rect(p, p)) ? 1 : 0;
            if (collides(new Rect(p, p), true)) {
                iPoints.add(p);
            }
        }
        if (first) {
            iPoints.addAll(r.getIntersectPoints(this, false));
            //println(iPoints);
        }
        return iPoints;
    }

    public String toString() {
        return "TL: " + topLeft + "; BR" + botRight;
    }
}