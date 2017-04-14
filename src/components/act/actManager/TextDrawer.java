package components.act.actManager;

import pub.controll.setting.Setting;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by ktr on 2017/04/08.
 */
public class TextDrawer {

    public enum Fonts {
        N_PLAIN(new Font("Ricty Diminished", Font.PLAIN, Setting.getSetting("font_size"))),
        N_BOLD(new Font("Ricty Diminished", Font.BOLD, Setting.getSetting("font_size"))),
        N_ITALIC(new Font("Ricty Diminished", Font.ITALIC, Setting.getSetting("font_size"))),

        B_PLAIN(new Font("Ricty Diminished", Font.PLAIN, (int) (Setting.getSetting("font_size") * 1.5))),
        B_BOLD(new Font("Ricty Diminished", Font.BOLD, (int) (Setting.getSetting("font_size") * 1.5))),
        B_ITALIC(new Font("Ricty Diminished", Font.ITALIC, (int) (Setting.getSetting("font_size") * 1.5)));

        private final Font font;

        Fonts(Font font) {
            this.font = font;
        }

        public Font getFont() {
            return font;
        }
    }

    private ColorManager cm;

    public TextDrawer(ColorManager cm) {
        this.cm = cm;
    }

    public void drawText(Graphics2D g2, String txt, Point2D.Double p, Fonts font, Color color) {
        g2.setFont(font.getFont());
        g2.setColor(color);
        Rectangle2D rt = getTextRectangle(g2, txt);
        double[] xy = {p.x - rt.getWidth() / 2, p.y + rt.getHeight() / 2};
        g2.translate(xy[0], xy[1]);

        g2.fill(getFontShape(g2, txt));
        g2.translate(-xy[0], -xy[1]);
    }

    private Rectangle2D getTextRectangle(Graphics2D g2, String txt) {
        FontMetrics fm = g2.getFontMetrics();
        return fm.getStringBounds(txt, g2).getBounds2D();
    }

    private Shape getFontShape(Graphics2D g2, String txt) {
        Font f = g2.getFont();
        GlyphVector v = f.createGlyphVector(g2.getFontMetrics(f).getFontRenderContext(), txt);

        return v.getOutline();
    }
}
