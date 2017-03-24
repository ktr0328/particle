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
    public int index;
    private int size;
    private Random rnd;
    Point2D.Double p;

    Point vector;

    Particle(Dimension canvas_size) {
        this.index = ++serial;
        rnd = new Random();
        this.size = size;
        this.p = new Point.Double(rnd.nextInt((int) canvas_size.getWidth()), rnd.nextInt((int) canvas_size.getHeight()));
        this.vector = setVector(6);
    }

    private Point setVector(int limit) {
        Point vector = new Point(0, 0);
        while (vector.x == 0 || vector.y == 0) {
            vector.x = rnd.nextInt(limit) - rnd.nextInt(limit);
            vector.y = rnd.nextInt(limit) - rnd.nextInt(limit);
        }

        return vector;
    }

    public void setVector(Point vector) {
        this.vector = vector;
    }

    public abstract void setPoint(Point2D p);

    public abstract Object getSymbol();

    public Point2D getPoint() {
        return this.p;
    }

    public Point getVector() {
        return vector;
    }

}
