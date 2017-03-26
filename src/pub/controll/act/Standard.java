package pub.controll.act;

import pub.Dot.Particle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public class Standard extends Status{
    int coefficient;

    public Standard() {
        super();
        this.coefficient = 1;
    }

    @Override
    public void move(Particle p, Dimension canvasSize) {
        double[] xy = {p.getPoint().getX(), p.getPoint().getY()};
        p.setVector(reflect(new Point2D.Double(p.getPoint().getX(), p.getPoint().getY()),
                new Point2D.Double(p.getVector().getX(), p.getVector().getY()),
                canvasSize));

        p.setPoint(new Point2D.Double(xy[0] + p.getVector().getX() * this.coefficient,
                xy[1] + p.getVector().getY() * this.coefficient));
    }
}
