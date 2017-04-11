package components.act;

import components.CanvasArea;
import components.act.actManager.BackGroundDrawer;
import components.act.actManager.ColorManager;
import components.act.actManager.SquareManager;
import components.act.actManager.TextDrawer;
import pub.controll.setting.Setting;
import pub.dot.Dot;
import pub.dot.Particle;
import pub.dot.Star;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static pub.controll.util.Util.*;

/**
 * Created by ktr on 2017/03/30.
 */
public class Drawer {
    private CanvasArea canvas;
    private ColorManager cm;
    private SquareManager sm;
    private BackGroundDrawer bd;
    private TextDrawer td;

    public Drawer(CanvasArea canvas) {
        this.canvas = canvas;
        this.cm = new ColorManager();
        this.sm = new SquareManager(canvas);
        this.bd = new BackGroundDrawer(cm, canvas, sm);
        this.td = new TextDrawer(cm);
    }

    public void execute(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // background
        bd.fillBackGround(g2);

        // TEST text
        String txt = "Particle";
        Point2D.Double point = new Point2D.Double(canvas.getWidth() / 2, canvas.getHeight() / 2);
        td.drawText(g2, txt, point, TextDrawer.Fonts.B_ITALIC, Color.ORANGE);

        // particles
        drawParticles(g2);
    }

    private void drawParticles(Graphics2D g2) {
        canvas.getParticles().forEach((e) -> {
            if (e instanceof Dot) {
                int dotSize = Setting.getSetting("dot_size");
                Ellipse2D.Double shape = new Ellipse2D.Double(e.getPoint().x - dotSize / 2, e.getPoint().y - dotSize / 2, dotSize, dotSize);
                g2.setColor(cm.getColorMap().get("particle_c"));
                g2.fill(shape);

                if (e.getData().columns.length > 0)
                    td.drawText(g2, e.getData().columns[0], e.getPoint(), TextDrawer.Fonts.N_PLAIN, Color.WHITE);

//                g2.setColor(cm.getColorMap().get("line_c"));
                drawLines(g2, e);
            } else if (e instanceof Star) {
                td.drawText(g2, "★", e.getPoint(), TextDrawer.Fonts.B_PLAIN, Color.WHITE);
            }
        });
    }

    private void drawLines(Graphics2D g2, Particle e1) {
        g2.setColor(cm.getColorMap().get("line_c"));

        canvas.getParticles().stream().skip(canvas.getParticles().indexOf(e1)).forEach(e2 -> {
            if (isWithInRange(e1.getPoint(), e2.getPoint(), Setting.getSetting("line_range"))) {
                g2.setColor(cm.setColorAlpha(g2.getColor(), e1.getPoint(), e2.getPoint()));

                Line2D.Double line = new Line2D.Double(e1.getPoint(), e2.getPoint());
                g2.draw(line);
            }
        });
    }
}
