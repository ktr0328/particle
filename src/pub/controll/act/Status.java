package pub.controll.act;

import pub.Dot.Particle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public abstract class Status {

    private static Status status;

    Status() {

    }

    public abstract void move(Particle particle, Dimension canvasSize);

    public static Status getStatus() {
        return status;
    }

    /**
     * 壁反射。particleの座標に応じてベクトルを反転させる。
     *
     * @param position
     * @param vector
     * @param size
     * @return Point2D vector
     */
    Point2D.Double reflect(Point2D.Double position, Point2D vector, Dimension size) {
        double x = vector.getX();
        double y = vector.getY();

        if (position.getX() >= size.getWidth()) x *= -1;
        else if (position.getY() >= size.getHeight()) y *= -1;
        else if (position.getX() <= 0) x *= -1;
        else if (position.getY() <= 0) y *= -1;

        return new Point2D.Double(x, y);
    }

}

