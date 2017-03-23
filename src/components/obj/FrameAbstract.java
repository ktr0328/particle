package components.obj;

import pub.controll.setting.Setting;

import javax.swing.JFrame;
import java.awt.*;

/**
 * Created by ktr on 2017/03/18.
 */
public abstract class FrameAbstract extends JFrame{

    public FrameAbstract() {
        super(Setting.getSetting("title"));
    }

    protected void isSetFullScreen(boolean isFullScreen) {
        if(isFullScreen) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Rectangle rect = env.getMaximumWindowBounds();
            setBounds(rect);
            GraphicsDevice display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            display.setFullScreenWindow(getOwner());
            setUndecorated(true);
        } else {
            setLocationRelativeTo(null);
            // TODO マジックナンバー
            setSize(new Dimension(500, 500));
        }
    }

    private void setScreenSize(int posX, int posY ,int width, int height) {
        setBounds(posX, posY, width, height);
    }
}
