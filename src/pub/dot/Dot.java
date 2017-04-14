package pub.dot;

import data.Data;

import java.awt.*;

/**
 * Created by ktr on 2017/03/23.
 */
public class Dot extends Particle {
    private String symbol;

    public Dot(Dimension dimension, Data data) {
        super(dimension, data);
        this.symbol = ".";
    }

    public String getSymbol() {
        return symbol;
    }
}
