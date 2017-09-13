package components.act.actManager;

import components.CanvasArea;
import pub.controll.setting.Setting;

import java.awt.*;

/**
 * Created by ktr on 2017/04/07.
 */
public class RectManager {
    private Rect[][] rects;
    private Color globalColor;

    public RectManager(CanvasArea canvas) {
        this.rects = generateRects(canvas.getHeight(), canvas.getWidth());
        this.globalColor = canvas.getBackground();
    }

    private Rect[][] generateRects(int height, int width) {
        int div = Setting.get("div_num");
        int divSize = height > width ? width / div : height / div;
        int[] eachDiv = {width / divSize, height / divSize};
        Rect[][] tempRects = new Rect[eachDiv[0] + 1][eachDiv[1] + 1];

        for (int i = 0; i < tempRects.length; i++) {
            for (int j = 0; j < tempRects[0].length; j++) {
                tempRects[i][j] = new Rect(new Rectangle(i * divSize, j * divSize, divSize, divSize), i, j, eachDiv, globalColor);
            }
        }

        return tempRects;
    }

    public Rect[][] getRects() {
        return rects;
    }
}
