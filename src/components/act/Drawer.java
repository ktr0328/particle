package components.act;

import components.CanvasArea;
import components.act.actManager.BackGroundDrawer;
import components.act.actManager.ColorManager;
import components.act.actManager.RectManager;
import components.act.actManager.TextDrawer;
import data.Fonts;
import pub.controll.setting.Setting;
import pub.dot.Dot;
import pub.dot.Particle;
import pub.dot.Star;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static pub.controll.util.Util.isWithInRange;

/**
 * Created by ktr on 2017/03/30.
 */
public class Drawer {
    private CanvasArea canvas;
    private ColorManager cm;
    private RectManager sm;
    private BackGroundDrawer bd;
    private TextDrawer td;

    public Drawer(CanvasArea canvas) {
        this.canvas = canvas;
        this.cm = new ColorManager();
        this.sm = new RectManager(canvas);
        this.bd = new BackGroundDrawer(cm, canvas, sm.getRects());
        this.td = new TextDrawer(cm);
    }

    public void execute(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // background
        bd.fillBackGround(g2);

        // text
        String statusName = CanvasArea.getManager().getStatus().getName();
        Point2D.Double point = new Point2D.Double(canvas.getWidth() / 2, canvas.getHeight() / 2);
        td.drawText(g2, statusName, point, Fonts.B_ITALIC, Color.ORANGE, true);
        Point2D.Double point2 = new Point2D.Double(canvas.getWidth() / 4 * 3, canvas.getHeight() / 4);
        td.drawText(g2, canvas.getInputText(), point2, Fonts.B_PLAIN, Color.BLUE, false);

        // particles
        drawParticles(g2);
    }

    private void drawParticles(Graphics2D g2) {
        // FIXME すっげーださい
        canvas.getParticles()
            .forEach(e -> {
                drawLines(g2, e);
                int dotSize = Setting.get("dot_size");

                g2.setColor(cm.getColorMap().get("particle_c"));
                if (e instanceof Dot) {
                    Ellipse2D.Double shape = new Ellipse2D.Double(e.getPoint().x - dotSize / 2, e.getPoint().y - dotSize / 2, dotSize, dotSize);
                    g2.setColor(cm.getColorMap().get("particle_c"));
                    g2.fill(shape);
                } else if (e instanceof Star) {
                    Point2D.Double point = new Point2D.Double(e.getPoint().x - dotSize / 2, e.getPoint().y - dotSize / 2);
                    td.drawText(g2, e.getSymbol(), point, Fonts.N_PLAIN, Color.LIGHT_GRAY, true);
                }

                if (e.getData().columns.length > 0)
                    td.drawText(g2, e.getData().columns[0], e.getPoint(), Fonts.N_PLAIN, Color.WHITE, false);

                g2.setColor(cm.getColorMap().get("line_c"));
            });
    }

    private void drawLines(Graphics2D g2, Particle p1) {
        g2.setColor(cm.getColorMap().get("line_c"));

        canvas.getParticles().stream()
            .skip(canvas.getParticles().indexOf(p1))
            .filter(p2 -> p2.getData().columns.length > 0)
            .filter(p2 -> isWithInRange(p1.getPoint(), p2.getPoint(), Setting.get("line_range")))
            .forEach(p2 -> {
                g2.setColor(cm.setColorAlpha(g2.getColor(), p1.getPoint(), p2.getPoint()));

                Line2D.Double line = new Line2D.Double(p1.getPoint(), p2.getPoint());
                g2.draw(line);
            });
    }
}
