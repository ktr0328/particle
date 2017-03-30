package components.obj;

import pub.controll.setting.Setting;

import javax.swing.JFrame;
import java.awt.*;

/**
 * Created by ktr on 2017/03/18.
 */
public abstract class FrameAbstract extends JFrame {

    public FrameAbstract(String title) {
        super(title);
    }

    protected void isSetFullScreen(boolean isFullScreen) {
        if (isFullScreen) {
            setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
            GraphicsDevice display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            display.setFullScreenWindow(getOwner());
            setUndecorated(true);
        } else {
            setLocationRelativeTo(null);
            // TODO マジックナンバー
            setSize(new Dimension(1000, 1000));
        }
    }
}
