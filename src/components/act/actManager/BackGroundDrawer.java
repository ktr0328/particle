package components.act.actManager;

import components.CanvasArea;

import java.awt.*;

/**
 * Created by ktr on 2017/04/08.
 */
public class BackGroundDrawer extends Thread {
    private ColorManager cm;
    private CanvasArea canvas;
    private Rect[][] rects;
    private RectManager sm;

    public BackGroundDrawer(ColorManager cm, CanvasArea canvas, Rect[][] rects) {
        this.cm = cm;
        this.canvas = canvas;
        this.rects = rects;
    }

    @Override
    public void run() {
//        fillBackGround();
    }

    public void fillBackGround(Graphics2D g2) {
        // TODO Rect globalColorを使って背景一色を表現する 同じcolorを参照させる -> Stateパターン？ ColorMode的な

        for (int i = 0; i < rects.length; i++) {
            for (int j = 0; j < rects[0].length; j++) {
                g2.setColor(rects[i][j].getColor());
                // TODO 回転動作を入れる
                // g2.rotate(Math.toRadians(45), rects[i][j].x, rects[i][j].y);

                g2.fill(rects[i][j]);
                // g2.rotate(Math.toRadians(-45), rects[i][j].x, rects[i][j].y);
            }
        }
    }
}
