package pub.dot;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public class Star extends Particle {
    private String symbol;

    public Star(Dimension dimension) {
        super(dimension);
        this.symbol = "★";
    }

    public String getSymbol() {
        return symbol;
    }
}
