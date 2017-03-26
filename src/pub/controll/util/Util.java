package pub.controll.util;

import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public class Util {

    public static boolean isWithInRange(Point2D.Double p1, Point2D.Double p2) {
        if (distance(p1.getX(), p1.getY(), p2.getX(), p2.getY()) < 150) {
            return true;
        } else return false;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
