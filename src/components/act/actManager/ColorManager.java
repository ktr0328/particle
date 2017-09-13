package components.act.actManager;

import pub.controll.setting.Setting;
import pub.controll.util.Util;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;

/**
 * Created by ktr on 2017/03/31.
 */
public class ColorManager {
    private HashMap<String, Color> colorMap;

    public ColorManager() {
        this.colorMap = generateColorMap();
    }

    private HashMap<String, Color> generateColorMap() {
        HashMap<String, Color> map = new HashMap<>();

        map.put("bg_c", new Color(Setting.get("color_red"),
            Setting.get("color_green"),
            Setting.get("color_blue"))
        );
        map.put("particle_c", Color.PINK);
        map.put("line_c", Color.LIGHT_GRAY);
        map.put("text_c", Color.LIGHT_GRAY);

        return map;
    }

    public Color setColorAlpha(Color nowColor, Point2D.Double p, Point2D.Double p2) {
        return new Color(
            nowColor.getRed(), nowColor.getGreen(), nowColor.getBlue(),
            Util.getAlphaAccordingToDistance(p.x, p.y, p2.x, p2.y)
        );
    }

    public HashMap<String, Color> getColorMap() {
        return colorMap;
    }
}
