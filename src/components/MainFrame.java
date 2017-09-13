package components;

import components.obj.FrameAbstract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        CustomMenuBar cmBar = new CustomMenuBar();
        setJMenuBar(cmBar);

        CanvasArea canvas = new CanvasArea(this.getWidth(), this.getHeight());
        addKeyListener(new InputTextListener(canvas));
        add(canvas);
    }

    class InputTextListener implements KeyListener {
        CanvasArea canvas;

        private InputTextListener(CanvasArea canvas) {
            this.canvas = canvas;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO 要リファクタリング
            StringBuilder text = new StringBuilder(canvas.getInputText());

            int keyCode = e.getKeyCode();
            String keyStr = String.valueOf(e.getKeyChar());

            if (keyCode == KeyEvent.VK_BACK_SPACE && text.length() != 0) {
                text.deleteCharAt(text.length() - 1);
            } else if (keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_ENTER) {
                text = new StringBuilder("");
            } else if (keyCode != KeyEvent.VK_BACK_SPACE && keyStr.matches("[A-Z]|[a-z]|\\s|[+#.]")) {
                text.append(keyStr);
            }

            canvas.setInputText(text.toString());
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}


