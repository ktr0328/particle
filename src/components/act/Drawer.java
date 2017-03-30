package components.act;

import components.CanvasArea;
import pub.controll.setting.Setting;
import pub.controll.util.Util;
import pub.dot.Dot;
import pub.dot.Particle;
import pub.dot.Star;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;

import static pub.controll.util.Util.isWithInRange;

/**
 * Created by ktr on 2017/03/30.
 */
public class Drawer {
    private CanvasArea canvas;
    private HashMap<String, Color> colorMap;

    public Drawer(CanvasArea canvas) {
        this.canvas = canvas;
        this.colorMap = generateColorMap();
    }

    private HashMap<String, Color> generateColorMap() {
        HashMap<String, Color> map = new HashMap<>();

        map.put("bg_c", new Color(Setting.getSetting("color_red"),
            Setting.getSetting("color_green"),
            Setting.getSetting("color_blue")));
        map.put("particle_c", Color.WHITE);
        map.put("line_c", Color.CYAN);

        return map;
    }

    public void execute(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 背景
        g2.setColor(colorMap.get("bg_c"));
        g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        g2.setColor(colorMap.get("line_c"));
        g2.drawString("テスト", canvas.getWidth() / 2, canvas.getHeight() / 2);

        drawParticles(g2);
    }

    private void drawParticles(Graphics2D g2) {
        canvas.getParticles().forEach((e) -> {
            if (e instanceof Dot) {
                int dotSize = Setting.getSetting("dot_size");
                Ellipse2D.Double shape = new Ellipse2D.Double(e.getPoint().getX() - dotSize / 2, e.getPoint().getY() - dotSize / 2, dotSize, dotSize);
                g2.setColor(colorMap.get("particle_c"));
                g2.fill(shape);

                g2.setColor(colorMap.get("line_c"));
                drawLines(g2, e);
            } else if (e instanceof Star) {
                g2.drawString(Integer.toString((int) e.getSymbol()),
                    (int) e.getPoint().getX(),
                    (int) e.getPoint().getY());
            }
        });
    }

    private void drawLines(Graphics2D g2, Particle e1) {
        canvas.getParticles().stream().skip(canvas.getParticles().indexOf(e1)).forEach(e2 -> {
            if (isWithInRange(e1.getPoint(), e2.getPoint(), Setting.getSetting("line_range"))) {
                g2.setColor(setColorAlpha(g2.getColor(), e1.getPoint(), e2.getPoint()));

                Line2D.Double line = new Line2D.Double(e1.getPoint(), e2.getPoint());
                g2.draw(line);
            }
        });
    }

    private Color setColorAlpha(Color nowColor, Point2D.Double p, Point2D.Double p2) {
        return new Color(nowColor.getRed(), nowColor.getGreen(), nowColor.getBlue(),
            Util.getAlphaAccordingToDistance(p.getX(), p.getY(), p2.getX(), p2.getY()));
    }
}
