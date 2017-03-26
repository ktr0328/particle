package components;

import components.obj.CanvasAreaAbstract;
import pub.Dot.Dot;
import pub.Dot.Particle;
import pub.Dot.Star;
import pub.controll.Manager;
import pub.controll.setting.Setting;
import pub.controll.util.Util;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedHashMap;
import java.util.Map;

import static pub.controll.util.Util.*;

/**
 * Created by ktr on 2017/03/18.
 */
public class CanvasArea extends CanvasAreaAbstract {
    private Color bgColor;
    private Font font;
    private static Manager m;

    private LinkedHashMap<String, Particle> particles;

    CanvasArea(int width, int height) {
        super(width, height);

        m = new Manager(this);
        this.bgColor = new Color(Integer.parseInt(Setting.getSetting("color_red")),
            Integer.parseInt(Setting.getSetting("color_green")),
            Integer.parseInt(Setting.getSetting("color_blue")));
        this.font = new Font("Ricty Diminished", Font.BOLD, Integer.parseInt(Setting.getSetting("font_size")));

        particles = m.generateParticles(Integer.parseInt(Setting.getSetting("dot_num")), true);

        m.timerStart();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // 背景
        g2.setColor(bgColor);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2.setColor(Color.WHITE);
        g2.setFont(this.font);
        g2.drawString("テスト", getWidth() / 2, getHeight() / 2);

        drawParticles(g2);

    }

    private void drawParticles(Graphics2D g2) {
        particles.forEach((key, value) -> {
            if (particles.get(key) instanceof Dot) {
                g2.setColor(Color.WHITE);

                // TODO 円の中心から線が伸びるようにする w: ? h: ? / 2 とかで。
                Ellipse2D.Double shape = new Ellipse2D.Double(particles.get(key).getPoint().getX(),
                    particles.get(key).getPoint().getY(), 3, 3);
                g2.fill(shape);

                drawLine(g2, particles.get(key));
            } else if (particles.get(key) instanceof Star) {
                g2.drawString(Integer.toString((int) particles.get(key).getSymbol()),
                    (int) particles.get(key).getPoint().getX(),
                    (int) particles.get(key).getPoint().getY());
            }
        });
    }

    private void drawLine(Graphics2D g2, Particle p) {
        particles.entrySet().stream().skip(p.getIndex()).map(Map.Entry::getKey).forEach(e -> {
            if (isWithInRange(p.getPoint(), particles.get(e).getPoint())) {
                g2.setColor(new Color(g2.getColor().getRed(), g2.getColor().getGreen(), g2.getColor().getBlue(),
                    (int) setAlpha(p.getPoint().getX(), p.getPoint().getY(), particles.get(e).getPoint().getX(), particles.get(e).getPoint().getY())));

                g2.drawLine((int) p.getPoint().getX(), (int) p.getPoint().getY(),
                    (int) particles.get(e).getPoint().getX(), (int) particles.get(e).getPoint().getY());
            }
        });
    }

    private double setAlpha(double x1, double y1, double x2, double y2) {
        return 255 - distance(x1, y1, x2, y2) / 150 * 255;
    }

    public LinkedHashMap<String, Particle> getParticles() {
        return particles;
    }

    public static Manager getM() {
        return m;
    }

}
