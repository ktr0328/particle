/**
 * Created by ktr on 2017/03/18.
 */

import components.MainFrame;
import pub.controll.setting.Setting;
import pub.controll.util.Util;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start!");

        Util.testStart();

        Setting s = new Setting("/config.properties");
        MainFrame frame = new MainFrame("Particle");
        frame.setVisible(true);

        Util.testEnd(new int[]{Util.NANO, Util.SEC, Util.MILI});
    }
}
