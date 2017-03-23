package components;


import components.obj.CanvasAreaAbstract;
import pub.Dot.Particle;
import pub.controll.Manager;
import pub.controll.setting.Setting;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ktr on 2017/03/18.
 */
class CanvasArea extends CanvasAreaAbstract {
    private Color bgColor;
    private Font font;
    private static Manager m;

    public void setParticles(ArrayList<Particle> particles) {
        this.particles = particles;
    }

    private ArrayList<Particle> particles;

    CanvasArea(int width, int height) {
        super(width, height);

        m = new Manager(this);
        this.bgColor = new Color(Integer.parseInt(Setting.getSetting("color_red")),
                Integer.parseInt(Setting.getSetting("color_green")),
                Integer.parseInt(Setting.getSetting("color_blue")));
        this.font = new Font("Ricty Diminished", Font.BOLD, Integer.parseInt(Setting.getSetting("font_size")));

        particles = (ArrayList<Particle>) m.generateParticles(Integer.parseInt(Setting.getSetting("dot_num")), true);

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

        particles.forEach(e -> g2.drawString(".", (int)e.getPoint().getX(), (int)e.getPoint().getY()));
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public static Manager getM() {
        return m;
    }
}
