package components.obj;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ktr on 2017/03/18.
 */
public abstract class CanvasAreaAbstract extends JPanel {
    private int width;
    private int height;

    public CanvasAreaAbstract(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(this.width, this.height));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
