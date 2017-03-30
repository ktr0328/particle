package components;

import components.act.Drawer;
import components.obj.CanvasAreaAbstract;
import pub.controll.Manager;
import pub.controll.setting.Setting;
import pub.controll.util.Util;
import pub.dot.Particle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by ktr on 2017/03/18.
 */
public class CanvasArea extends CanvasAreaAbstract implements MouseListener, MouseMotionListener {
    private Color bgColor;
    private static Manager m;
    private static Point mousePoint;
    private Drawer drawer;

    private ArrayList<Particle> particles;

    CanvasArea(int width, int height) {
        super(width, height);

        m = new Manager(this);

        drawer = new Drawer(this);
        particles = m.generateParticles(Setting.getSetting("dot_num"), true);

        mousePoint = new Point(getX() / 2, getY() / 2);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFont(new Font("Ricty Diminished", Font.BOLD, Setting.getSetting("font_size")));

        m.timerStart();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // TEST
        // Util.testStart();
        drawer.execute(g2);
        // Util.testEnd(new int[]{Util.MILI});
    }

    public ArrayList<Particle> getParticles() { return particles; }

    public static Manager getM() { return m; }

    public static Point getMousePoint() { return mousePoint; }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePoint = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
