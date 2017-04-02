package pub.controll.act;

import components.CanvasArea;
import pub.dot.Particle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public class Standard extends MovingAbstract {
    double coefficient;

    public Standard() {
        super();
        this.coefficient = 0.1;
    }

    @Override
    public void move(Particle p, Dimension canvasSize) {
        double[] xy = {p.getPoint().x, p.getPoint().y};

        p.setPoint(new Point2D.Double(xy[0] + p.getVector().x * this.coefficient,
            xy[1] + p.getVector().y * this.coefficient));

        p.setPoint(barrier(p.getPoint(), CanvasArea.getMousePoint()));

        p.setPoint(dotLoop(p.getPoint(), canvasSize));
    }
}
