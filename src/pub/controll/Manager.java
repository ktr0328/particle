package pub.controll;

import components.CanvasArea;
import data.Data;
import pub.controll.act.CircleMoving;
import pub.controll.act.HighSpeed;
import pub.controll.act.MovingAbstract;
import pub.controll.act.Standard;
import pub.controll.setting.Setting;
import pub.controll.util.DataManager;
import pub.dot.Dot;
import pub.dot.Particle;
import pub.dot.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ktr on 2017/03/23.
 */
public class Manager {
    private CanvasArea canvas;
    private Timer timer;

    private MovingAbstract status;
    private LinkedHashMap<String, MovingAbstract> status_list;
    private DataManager dm;

    public Manager(CanvasArea canvas) {
        this.canvas = canvas;
        int FPS = Setting.get("fps");
        timer = new Timer(1000 / FPS, new CanvasTimer());

        status_list = new LinkedHashMap<>();
        status_list.put("standard", new Standard(canvas));
        status_list.put("high_speed", new HighSpeed(canvas));
        status_list.put("circle_moving", new CircleMoving(canvas));

        this.status = status_list.get("standard");
        dm = new DataManager();
    }

    public ArrayList<Particle> generateParticles(int num, boolean isDot) {
        return IntStream.rangeClosed(0, num).boxed()
            .map(e -> createEach(isDot, dm.list.get(e)))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private Particle createEach(boolean isDot, Data data) {
        if (isDot) return new Dot(new Dimension(canvas.getWidth(), canvas.getHeight()), data);
        else return new Star(new Dimension(canvas.getWidth(), canvas.getHeight()), data);
    }

    public MovingAbstract getStatus() {
        return status;
    }

    public void changeStatus(String status) {
        Runtime.getRuntime().gc();

        this.status = status_list.get(status);
        canvas.getParticles().forEach(e -> e.setVectorAgain(6d)); // マジックナンバー
    }

    public void timerStart() {
        timer.start();
    }

    class CanvasTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.getParticles().forEach((elem) -> status.move(elem, canvas.barrierFlag));
            canvas.repaint();
        }
    }
}
