package components.act.actManager;

import components.CanvasArea;
import pub.controll.setting.Setting;

import java.awt.*;

/**
 * Created by ktr on 2017/04/07.
 */
public class SquareManager {
    private CanvasArea canvas;
    Rect[][] rects;

    public SquareManager(CanvasArea canvas) {
        this.canvas = canvas;
        this.rects = generateRects();
    }

    private Rect[][] generateRects() {
        int div = Setting.getSetting("div_num");
        int divSize = canvas.getHeight() > canvas.getWidth() ? canvas.getWidth() / div : canvas.getHeight() / div;
        int[] eachDiv = {canvas.getWidth() / divSize, canvas.getHeight() / divSize};
        Rect[][] tempRects = new Rect[eachDiv[0] + 1][eachDiv[1] + 1];

        for (int i = 0; i < tempRects.length; i++) {
            for (int j = 0; j < tempRects[0].length; j++) {
                tempRects[i][j] = new Rect(new Rectangle(i * divSize, j * divSize, divSize, divSize), i, j, eachDiv);
            }
        }

        return tempRects;
    }
}
