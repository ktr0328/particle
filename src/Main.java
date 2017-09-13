/**
 * Created by ktr on 2017/03/18.
 */

import components.CanvasArea;
import components.MainFrame;
import pub.controll.setting.Setting;
import pub.controll.util.Util;

public class Main {

    private enum Paths {
        defaultPath(""),
        userPath("/config.properties");

        // TODO CSV 言語情報を入れる
        final String path;

        Paths(String path) {
            this.path = path;
        }
    }

    public static void main(String[] args) {
        System.out.println("Start!");

        Util.testStart();

        Setting s = new Setting(Paths.userPath.path);
        MainFrame frame = new MainFrame("Particle");

        Runtime.getRuntime().gc();

        frame.setVisible(true);
        CanvasArea.getManager().timerStart();

        Util.testEnd(new int[]{Util.NANO, Util.SEC, Util.MILI});
    }
}
