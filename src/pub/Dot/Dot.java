package pub.Dot;

import pub.controll.act.Status;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public class Dot extends Particle {
    private String symbol;

    public Dot(int size, Dimension dimension) {
        super(dimension);
        this.symbol = ".";
    }

    @Override
    public void setPoint(Point2D point) {
        p = (Point2D.Double) point;
    }

    public String getSymbol() {
        return symbol;
    }
}
