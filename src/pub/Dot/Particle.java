package pub.Dot;

import pub.controll.act.Status;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by ktr on 2017/03/23.
 */
public abstract class Particle {
    private static int serial;

    private int index;
    private int size;
    private Random rnd;
    Point2D.Double p;

    Point2D.Double vector;

    Particle(Dimension canvas_size) {
        this.index = ++serial;
        rnd = new Random();
        this.size = size;
        this.p = new Point.Double(rnd.nextDouble() * canvas_size.getWidth(), rnd.nextDouble() * canvas_size.getHeight());
        this.vector = setVector(6d);
    }

    private Point2D.Double setVector(double limit) {
        Point2D.Double vector = new Point2D.Double(0, 0);
        while (Math.abs(vector.getX()) >= 0 && Math.abs(vector.getX()) < 1 || Math.abs(vector.getY()) >= 0 && Math.abs(vector.getY()) < 1) {
            vector.setLocation(rnd.nextDouble() * limit - rnd.nextDouble() * limit,
                rnd.nextDouble() * limit - rnd.nextDouble() * limit);
        }

        return vector;
    }

    public void setVector(Point2D.Double vector) {
        this.vector = vector;
    }

    public abstract void setPoint(Point2D.Double p);

    public int getIndex() { return index; }

    public abstract Object getSymbol();

    public Point2D.Double getPoint() {
        return this.p;
    }

    public Point2D.Double getVector() {
        return vector;
    }

}
