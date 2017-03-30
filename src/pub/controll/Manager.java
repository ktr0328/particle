package pub.controll;

import components.CanvasArea;
import pub.controll.act.HighSpeed;
import pub.controll.act.MovingAbstract;
import pub.controll.act.Standard;
import pub.controll.setting.Setting;
import pub.dot.Dot;
import pub.dot.Particle;
import pub.dot.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

/**
 * Created by ktr on 2017/03/23.
 */
public class Manager {
    private CanvasArea canvas;
    private Timer timer;
    private final int FPS;

    private MovingAbstract s;
    private LinkedHashMap<String, MovingAbstract> status_list;

    public Manager(CanvasArea canvas) {
        this.canvas = canvas;
        this.FPS = Setting.getSetting("fps");
        timer = new Timer(1000 / this.FPS, new CanvasTimer());

        status_list = new LinkedHashMap<>();
        status_list.put("standard", new Standard());
        status_list.put("high_speed", new HighSpeed());

        this.s = status_list.get("standard");
    }

    public ArrayList<Particle> generateParticles(int num, boolean isDot) {
        ArrayList<Particle> arr = new ArrayList<>();
        Stream.iterate(0, i -> ++i).limit(num).forEach(i -> arr.add(createEach(isDot)));

        return arr;
    }

    private Particle createEach(boolean isDot) {
        if (isDot)
            return new Dot(new Dimension(canvas.getWidth(), canvas.getHeight()));
        else
            return new Star(new Dimension(canvas.getWidth(), canvas.getHeight()));
    }

    public MovingAbstract getS() {
        return s;
    }

    public void change_status(String status) {
        this.s = status_list.get(status);
    }

    public void timerStart() {
        timer.start();
    }

    class CanvasTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.getParticles().forEach((elem) -> s.move(elem, canvas.getSize()));
            canvas.repaint();
        }
    }
}
