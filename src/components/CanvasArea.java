package components;

import components.act.Drawer;
import components.obj.CanvasAreaAbstract;
import pub.controll.Manager;
import pub.controll.setting.Setting;
import pub.dot.Particle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by ktr on 2017/03/18.
 */
public class CanvasArea extends CanvasAreaAbstract implements MouseListener, MouseMotionListener {
    private static Manager m; // FIXME File Menuを動かすためにstatic 正直心底staticにしたくない
    private static Point mousePoint;
    private String inputText;
    private Drawer drawer;
    public boolean barrierFlag = false;

    private ArrayList<Particle> particles;

    CanvasArea(int width, int height) {
        super(width, height);
        this.inputText = "";
        m = new Manager(this);

        drawer = new Drawer(this);
        particles = m.generateParticles(Setting.get("dot_num"), true);

        mousePoint = new Point(getWidth() / 2, getHeight() / 2);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFont(new Font("Ricty Diminished", Font.PLAIN, Setting.get("font_size")));
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

    public static Manager getManager() { return m; }

    public static Point getMousePoint() { return mousePoint; }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        barrierFlag = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        barrierFlag = true;
        mousePoint = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String text) {
        this.inputText = text;
    }
}
