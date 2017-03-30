package pub.dot;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public class Dot extends Particle {
    private String symbol;

    public Dot(Dimension dimension) {
        super(dimension);
        this.symbol = ".";
    }

    @Override
    public void setPoint(Point2D.Double point) {
        p = point;
    }

    public String getSymbol() {
        return symbol;
    }
}
