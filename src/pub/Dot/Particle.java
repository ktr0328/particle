package pub.Dot;

import pub.controll.act.Status;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by ktr on 2017/03/23.
 */
public abstract class Particle {
    private int size;
    private Point2D p;

    Particle(int size, Dimension canvas_size) {
        this.size = size;
        this.p = setPosition(canvas_size);
        // TODO ベクトル
    }

    private Point setPosition(Dimension dimension) {
        Random rnd = new Random();
        return new Point(rnd.nextInt((int) dimension.getWidth()), rnd.nextInt((int) dimension.getHeight()));
    }

    private void move(Status s) {
        s.move();
    }

    public Point2D getPoint() {
        return this.p;
    }
}
