package components.act.actManager;

import components.CanvasArea;

import java.awt.*;

/**
 * Created by ktr on 2017/04/08.
 */
public class BackGroundDrawer extends Thread{
    private ColorManager cm;
    private CanvasArea canvas;
    private SquareManager sm;

    public BackGroundDrawer(ColorManager cm, CanvasArea canvas, SquareManager sm) {
        this.cm = cm;
        this.canvas = canvas;
        this.sm = sm;
    }

    public void fillBackGround(Graphics2D g2) {
        g2.setColor(cm.getColorMap().get("bg_c"));
        g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Rect[][] rects = sm.rects;
        for (int i = 0; i < sm.rects.length; i++) {
            for (int j = 0; j < sm.rects[0].length; j++) {
                g2.setColor(rects[i][j].getColor());
                // TODO 回転動作を入れる
                // g2.rotate(Math.toRadians(45), rects[i][j].x, rects[i][j].y);

                g2.fill(rects[i][j]);
                // g2.rotate(Math.toRadians(-45), rects[i][j].x, rects[i][j].y);
            }
        }
    }
}
