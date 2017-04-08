package pub.dot;

import data.Data;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by ktr on 2017/03/23.
 */
public abstract class Particle {
    private static int serial;

    private int index;
    private Random rnd;
    private Point2D.Double p;
    private Point2D.Double vector;
    private Data data;

    Particle(Dimension canvas_size, Data data) {
        this.index = ++serial;
        rnd = new Random();
        this.p = new Point.Double(rnd.nextDouble() * canvas_size.getWidth(), rnd.nextDouble() * canvas_size.getHeight());
        this.vector = setVector(6d);
        this.data = data;
    }

    /**
     * ベクトルの初期設定
     *
     * @param limit キャンバスのサイズ
     * @return ベクトル
     */
    private Point2D.Double setVector(double limit) {
        Point2D.Double vector = new Point2D.Double(0, 0);
        while (Math.abs(vector.x) >= 0 && Math.abs(vector.x) < 1 || Math.abs(vector.y) >= 0 && Math.abs(vector.y) < 1) {
            vector.setLocation(rnd.nextDouble() * limit - rnd.nextDouble() * limit,
                rnd.nextDouble() * limit - rnd.nextDouble() * limit);
        }

        return vector;
    }

    // Setter
    public void setVector(Point2D.Double vector) {
        this.vector = vector;
    }

    public Point2D.Double getVector() {
        return vector;
    }

    public void setPoint(Point2D.Double point) {
        p = point;
    }

    public int getIndex() {
        return index;
    }

    public abstract Object getSymbol();

    public Point2D.Double getPoint() {
        return this.p;
    }

    public Data getData() {
        return data;
    }
}
