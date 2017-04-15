package pub.controll.act;

import components.CanvasArea;
import pub.controll.setting.Setting;
import pub.controll.util.Util;
import pub.dot.Particle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public abstract class MovingAbstract {
    CanvasArea canvas;

    MovingAbstract(CanvasArea canvas) {
        this.canvas = canvas;
    }

    public abstract void move(Particle particle, boolean barrierFlag);

    /**
     * 壁反射。particleの座標に応じてベクトルを反転させる。
     *
     * @param position 現在座標
     * @param vector 進行方向
     * @return 反転させたベクトル
     */
    Point2D.Double reflect(Point2D.Double position, Point2D.Double vector) {
        double x = vector.x;
        double y = vector.y;

        if (position.x >= canvas.getSize().getWidth()) x *= -1;
        else if (position.y >= canvas.getSize().getHeight()) y *= -1;
        else if (position.x <= 0) x *= -1;
        else if (position.y <= 0) y *= -1;

        return new Point2D.Double(x, y);
    }

    /**
     * 壁の端まで行くと逆側から出てくるようにする。
     *
     * @param p 現在座標
     * @return 逆サイドへ移動したベクトル
     */
    Point2D.Double dotLoop(Point2D.Double p) {
        double[] xy = {p.x, p.y};

        if (p.x > canvas.getSize().getWidth()) xy[0] = 0;
        else if (p.y > canvas.getSize().getHeight()) xy[1] = 0;
        else if (p.x < 0) xy[0] = canvas.getSize().getWidth();
        else if (p.y < 0) xy[1] = canvas.getSize().getHeight();

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

