package components;

import components.obj.FrameAbstract;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ktr on 2017/03/18.
 */
public class MainFrame extends FrameAbstract {

    public MainFrame(String title) {
        super(title);
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


