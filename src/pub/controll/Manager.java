package pub.controll;

import components.obj.CanvasAreaAbstract;
import pub.Dot.Dot;
import pub.Dot.Particle;
import pub.Dot.Star;
import pub.controll.act.HighSpeed;
import pub.controll.act.Standard;
import pub.controll.act.Status;
import pub.controll.setting.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ktr on 2017/03/23.
 */
public class Manager {
    private CanvasAreaAbstract canvas;
    private Timer timer;
    private Status s;
    private HashMap<String, Status> status_list;

    public Manager(CanvasAreaAbstract canvas) {
        this.canvas = canvas;
        timer = new Timer(40, new CanvasTimer());

        status_list = new HashMap<>();
        status_list.put("standard", new Standard());
        status_list.put("high_speed", new HighSpeed());

        this.s = status_list.get("standard");
    }

    public List<Particle> generateParticles(int num, boolean isDot) {
        List<Particle> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(createEach(isDot));
        }

        return list;
    }

    private Particle createEach(boolean isDot) {
        if (isDot)
            return new Dot(Integer.parseInt(Setting.getSetting("dot_size")), new Dimension(canvas.getWidth(), canvas.getHeight()));
        else
            return new Star(Integer.parseInt(Setting.getSetting("dot_size")), new Dimension(canvas.getWidth(), canvas.getHeight()));
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
            s.move();
        }
    }
}
