package pub.dot;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public class Star extends Particle {
    private int symbol;

    public Star(Dimension dimension) {
        super(dimension);
        this.symbol = 0;
    }

    @Override
    public void setPoint(Point2D.Double point) {
        p = point;
    }

    public Integer getSymbol() {
        return symbol;
    }
}
