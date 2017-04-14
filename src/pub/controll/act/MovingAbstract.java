package pub.controll.act;

import pub.controll.setting.Setting;
import pub.controll.util.Util;
import pub.dot.Particle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public abstract class MovingAbstract {

    MovingAbstract() {

    }

    public abstract void move(Particle particle, Dimension canvasSize, boolean barrierFlag);

    /**
     * 壁反射。particleの座標に応じてベクトルを反転させる。
     *
     * @param position
     * @param vector
     * @param size
     * @return Point2D vector
     */
    Point2D.Double reflect(Point2D.Double position, Point2D.Double vector, Dimension size) {
        double x = vector.x;
        double y = vector.y;

        if (position.x >= size.getWidth()) x *= -1;
        else if (position.y >= size.getHeight()) y *= -1;
        else if (position.x <= 0) x *= -1;
        else if (position.y <= 0) y *= -1;

        return new Point2D.Double(x, y);
    }

    Point2D.Double dotLoop(Point2D.Double p, Dimension size) {
        double[] xy = {p.x, p.y};

        if (p.x > size.getWidth()) xy[0] = 0;
        else if (p.y > size.getHeight()) xy[1] = 0;
        else if (p.x < 0) xy[0] = size.getWidth();
        else if (p.y < 0) xy[1] = size.getHeight();

        return new Point2D.Double(xy[0], xy[1]);
    }

    Point2D.Double barrier(Point2D.Double point, Point mPoint) {
        int range = Setting.getSetting("barrier_range");
        if (Util.getDistance(point.x, point.y, mPoint.x, mPoint.y) < range) {
            double theta = Util.getRadian(mPoint.x, mPoint.y, point.x, point.y);
            return new Point2D.Double(mPoint.x + range * Math.cos(theta), mPoint.y + range * Math.sin(theta));
        }

        return point;
    }

}

