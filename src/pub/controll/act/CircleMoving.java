package pub.controll.act;

import components.CanvasArea;
import pub.controll.setting.Setting;
import pub.controll.util.Util;
import pub.dot.Particle;

import java.awt.geom.Point2D;
import java.util.stream.Stream;

/**
 * Created by ktr on 2017/04/15.
 */
public class CircleMoving extends MovingAbstract {

    private double div;
    private Point2D.Double[] goals;

    public CircleMoving(CanvasArea canvas) {
        super(canvas);
        this.div = 360d / Setting.getSetting("dot_num");
        this.goals = initGoalPoints();
    }

    @Override
    public void move(Particle p, boolean barrierFlag) {
        goals = moveGoalPoints(goals);
        if(barrierFlag) p.setPoint(barrier(p.getPoint(), CanvasArea.getMousePoint()));

        if (isReached(p))
            p.setPoint(goals[p.getIndex()]);
        else {
            double[] xy = {p.getPoint().x + (goals[p.getIndex()].x - p.getPoint().x) / 20, p.getPoint().y + (goals[p.getIndex()].y - p.getPoint().y) / 20};
            p.setPoint(new Point2D.Double(xy[0], xy[1]));
        }
    }

    private Point2D.Double[] moveGoalPoints(Point2D.Double[] goals) {
        Point2D.Double[] points = new Point2D.Double[goals.length];
        Stream.iterate(0, i -> ++i).limit(goals.length).forEach(i -> {
            points[i] = moveEachGoalPoint(goals[i]);
        });

        return points;
    }

    private Point2D.Double moveEachGoalPoint(Point2D.Double point) {
        double rad = Util.getRadian(canvas.getWidth() / 2, canvas.getHeight() / 2, point.x, point.y);
        double radius = getRadius();
        rad += Math.toRadians(0.0005);

        return new Point2D.Double(canvas.getWidth() / 2 + radius * Math.cos(rad), canvas.getHeight() / 2 + radius * Math.sin(rad));
    }

    private boolean isReached(Particle p) {
        double distance = Util.getDistance(p.getPoint().x, p.getPoint().y, goals[p.getIndex()].x, goals[p.getIndex()].y);
        return distance < 0.5;
    }

    private Point2D.Double[] initGoalPoints() {
        Point2D.Double[] goals = new Point2D.Double[Setting.getSetting("dot_num")];
        Stream.iterate(0, i -> ++i).limit(Setting.getSetting("dot_num"))
            .forEach(i -> goals[i] = createEachGoalPoints(i));

        return goals;
    }

    private Point2D.Double createEachGoalPoints(int index) {
        double radius = getRadius();
        double rad = Math.toRadians(div * index);

        return new Point2D.Double(canvas.getWidth() / 2 + radius * Math.cos(rad), canvas.getHeight() / 2 + radius * Math.sin(rad));
    }

    /**
     * canvasのWidthとHeightの大きい方の1/4の長さを取得する
     *
     * @return キャンバスの1/4の長さを取得
     */
    private double getRadius() {
        return canvas.getWidth() > canvas.getHeight() ? canvas.getWidth() / 4 : canvas.getHeight() / 4;
    }
}
