package pub.dot;

import data.Data;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by ktr on 2017/03/23.
 */
public class Star extends Particle {
    private String symbol;

    public Star(Dimension dimension, Data data) {
        super(dimension, data);
        this.symbol = "★";
    }

    public String getSymbol() {
        return symbol;
    }
}
