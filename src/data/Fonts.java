package data;

import pub.controll.setting.Setting;

import java.awt.*;

/**
 * Created by ktr on 2017/09/13.
 */
public enum Fonts {
    N_PLAIN(new Font("Ricty Diminished", Font.PLAIN, Setting.get("font_size"))),
    N_BOLD(new Font("Ricty Diminished", Font.BOLD, Setting.get("font_size"))),
    N_ITALIC(new Font("Ricty Diminished", Font.ITALIC, Setting.get("font_size"))),

    B_PLAIN(new Font("Ricty Diminished", Font.PLAIN, (int) (Setting.get("font_size") * 1.5))),
    B_BOLD(new Font("Ricty Diminished", Font.BOLD, (int) (Setting.get("font_size") * 1.5))),
    B_ITALIC(new Font("Ricty Diminished", Font.ITALIC, (int) (Setting.get("font_size") * 1.5)));

    private final Font font;

    Fonts(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
