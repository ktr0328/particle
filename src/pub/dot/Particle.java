package pub.dot;

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

    private Point2D.Double vector;

    Particle(Dimension canvas_size) {
        this.index = ++serial;
        rnd = new Random();
        this.p = new Point.Double(rnd.nextDouble() * canvas_size.getWidth(), rnd.nextDouble() * canvas_size.getHeight());
        this.vector = setVector(6d);
    }

    /**
     * ベクトルの初期設定
     *
     * @param limit キャンバスのサイズ
     * @return ベクトル
     */
    private Point2D.Double setVector(double limit) {
        Point2D.Double vector = new Point2D.Double(0, 0);
        while (Math.abs(vector.getX()) >= 0 && Math.abs(vector.getX()) < 1 || Math.abs(vector.getY()) >= 0 && Math.abs(vector.getY()) < 1) {
            vector.setLocation(rnd.nextDouble() * limit - rnd.nextDouble() * limit,
                rnd.nextDouble() * limit - rnd.nextDouble() * limit);
        }

        return vector;
    }

    /**
     * Setter
     */
    public void setVector(Point2D.Double vector) {
        this.vector = vector;
    }

    public Point2D.Double getVector() {
        return vector;
    }

    public abstract void setPoint(Point2D.Double p);

    public int getIndex() {
        return index;
    }

    public abstract Object getSymbol();

    public Point2D.Double getPoint() {
        return this.p;
    }
}
