package components.act.actManager;

import java.awt.*;

/**
 * Created by ktr on 2017/04/07.
 */
class Rect extends Rectangle {
    private Color color;

    Rect(Rectangle rect, int row, int col, int[] div) {
        super(rect);
        // FIXME どっちがいいのか
        // float[] hsb = {1f - col / (float) div[1], 1f - row / (float) div[0], 1f - 1f * (col + row) / (div[0] + div[1])};
        float[] hsb = {1f - col / (float) div[1], 1f - row / (float) div[0], 1f - col / (float) div[1]};
        this.color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }

    Color getColor() {
        return color;
    }
}
