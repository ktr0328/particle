package components;

import components.obj.FrameAbstract;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ktr on 2017/03/29.
 */
public class ConfigFrame extends FrameAbstract implements KeyListener {
    public ConfigFrame(String title) {
        super(title);
        // TODO 真ん中に来るように修正
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setBounds(0, 0, 600, 400);

        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) this.setVisible(false);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
