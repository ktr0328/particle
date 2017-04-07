package components.act;

import components.CanvasArea;
import components.act.actManager.ColorManager;
import components.act.actManager.Rect;
import components.act.actManager.SquareManager;
import pub.controll.setting.Setting;
import pub.dot.Dot;
import pub.dot.Particle;
import pub.dot.Star;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static pub.controll.util.Util.isWithInRange;

/**
 * Created by ktr on 2017/03/30.
 */
public class Drawer {
    private CanvasArea canvas;
    private ColorManager cm;
    private SquareManager sm;

    public Drawer(CanvasArea canvas) {
        this.canvas = canvas;
        this.cm = new ColorManager();
        this.sm = new SquareManager(canvas);
    }

    public void execute(Graphics2D g2) {
//        Util.testStart();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // background
        fillBackGround(g2);

        // TEST text
        String txt = "Particle";
        Point2D.Double point = new Point2D.Double(canvas.getWidth() / 2, canvas.getHeight() / 2);
        drawText(g2, txt, point);

        // particles
        drawParticles(g2);
//        Util.testEnd(new int[]{1,2,3});
    }

    private void drawParticles(Graphics2D g2) {
        canvas.getParticles().forEach((e) -> {
            if (e instanceof Dot) {
                int dotSize = Setting.getSetting("dot_size");
                Ellipse2D.Double shape = new Ellipse2D.Double(e.getPoint().x - dotSize / 2, e.getPoint().y - dotSize / 2, dotSize, dotSize);
                g2.setColor(cm.getColorMap().get("particle_c"));
                g2.fill(shape);

                g2.setColor(cm.getColorMap().get("line_c"));
                drawLines(g2, e);
            } else if (e instanceof Star) {
                drawText(g2, "★", e.getPoint());
            }
        });
    }

    private void drawLines(Graphics2D g2, Particle e1) {
        canvas.getParticles().stream().skip(canvas.getParticles().indexOf(e1)).forEach(e2 -> {
            if (isWithInRange(e1.getPoint(), e2.getPoint(), Setting.getSetting("line_range"))) {
                g2.setColor(cm.setColorAlpha(g2.getColor(), e1.getPoint(), e2.getPoint()));

                Line2D.Double line = new Line2D.Double(e1.getPoint(), e2.getPoint());
                g2.draw(line);
            }
        });
    }

    private void drawText(Graphics2D g2, String txt, Point2D.Double p) {
        g2.setColor(cm.getColorMap().get("text_c"));
        Rectangle2D rt = getTextRectangle(g2, txt);
        double[] xy = {p.x - rt.getWidth() / 2, p.y + rt.getHeight() / 2};
        g2.translate(xy[0], xy[1]);

        g2.fill(getFontShape(g2, txt));
        g2.translate(-xy[0], -xy[1]);
    }

    private void fillBackGround(Graphics2D g2) {
        g2.setColor(cm.getColorMap().get("bg_c"));
        g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Rect[][] rects = sm.rects;
        for(int i = 0; i < sm.rects.length; i++) {
            for(int j = 0; j < sm.rects[0].length; j++) {
                g2.setColor(rects[i][j].getColor());
                g2.fill(rects[i][j]);
            }
        }
    }

    private Rectangle2D getTextRectangle(Graphics2D g2, String txt) {
        FontMetrics fm = g2.getFontMetrics();
        return fm.getStringBounds(txt, g2).getBounds2D();
    }

    private Shape getFontShape(Graphics2D g2, String txt) {
        Font f = g2.getFont();
        GlyphVector v = f.createGlyphVector(g2.getFontMetrics(f).getFontRenderContext(), txt);

        return v.getOutline();
    }
}
