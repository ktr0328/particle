package pub.controll.util;

import pub.controll.setting.Setting;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by ktr on 2017/03/23.
 */
public class Util {
    public static int NANO = 0;
    public static int MILI = 1;
    public static int SEC = 2;

    private static long nanoTime = 0;

    public static boolean isWithInRange(Point2D.Double p1, Point2D.Double p2, int range) {
        return getDistance(p1.x, p1.y, p2.x, p2.y) < range;
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static int getAlphaAccordingToDistance(double x1, double y1, double x2, double y2) {
        return (int) (255 - getDistance(x1, y1, x2, y2) / Setting.getSetting("line_range") * 255);
    }

    public static double getRadian(double x, double y, double x2, double y2) {
        return Math.atan2(y2 - y, x2 - x);
    }

    public static void testStart() {
        nanoTime = System.nanoTime();
    }

    /**
     * Util.NANO, MILI, SEC で任意の単位にして吐き出す。
     *
     * @param choose : int[0 ~ 2, 0 ~ 2, ...]
     */
    public static void testEnd(int[] choose) {
        Arrays.sort(choose);
        try {
            Stream.iterate(0, i -> ++i).limit(choose.length).forEach(i -> getSecs(choose[i]));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private static void getSecs(int type) {
        if (type == NANO) System.out.println("nano : " + (System.nanoTime() - nanoTime));
        else if (type == MILI) System.out.println("mili : " + (System.nanoTime() - nanoTime) / 1000000);
        else if (type == SEC) System.out.println("sec  : " + (System.nanoTime() - nanoTime) / 1000000000);
    }
}
