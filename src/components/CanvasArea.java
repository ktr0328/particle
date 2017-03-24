package components;


import components.obj.CanvasAreaAbstract;
import pub.Dot.Dot;
import pub.Dot.Particle;
import pub.Dot.Star;
import pub.controll.Manager;
import pub.controll.setting.Setting;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by ktr on 2017/03/18.
 */
public class CanvasArea extends CanvasAreaAbstract {
    private Color bgColor;
    private Font font;
    private static Manager m;

    private HashMap<String, Particle> particles;

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

        g2.setColor(bgColor);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2.setColor(Color.WHITE);
        g2.setFont(this.font);
        g2.drawString("テスト", getWidth() / 2, getHeight() / 2);

        drawParticles(g2);

    }

    private void drawParticles(Graphics2D g2) {
        particles.entrySet().forEach(e -> {
            if (particles.get(e.getKey()) instanceof Dot) {
                g2.drawString((String) particles.get(e.getKey()).getSymbol(),
                        (int)particles.get(e.getKey()).getPoint().getX(),
                        (int) particles.get(e.getKey()).getPoint().getY());
            } else if (particles.get(e.getKey()) instanceof Star) {
                g2.drawString(Integer.toString((int)particles.get(e.getKey()).getSymbol()),
                        (int)particles.get(e.getKey()).getPoint().getX(),
                        (int) particles.get(e.getKey()).getPoint().getY());
            }
        });
    }

    public HashMap<String, Particle> getParticles() {
        return particles;
    }

    public static Manager getM() {
        return m;
    }

}
