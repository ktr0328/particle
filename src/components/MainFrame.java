package components;

import components.obj.FrameAbstract;
import pub.controll.setting.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Dimension2D;

/**
 * Created by ktr on 2017/03/18.
 */
public class MainFrame extends FrameAbstract {

    public MainFrame() {
        super();
        // TODO マジックナンバー
        setLayout(new GridLayout(1, 1));
        isSetFullScreen(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CustomMenubar cmbar = new CustomMenubar();
        setJMenuBar(cmbar);

        CanvasArea canvas = new CanvasArea(this.getWidth(), this.getHeight());
        add(canvas);
    }

}


