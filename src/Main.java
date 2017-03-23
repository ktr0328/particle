/**
 * Created by ktr on 2017/03/18.
 */

import components.MainFrame;
import pub.controll.setting.Setting;

public class Main {
    public static void main(String[] args) {
        Setting s = new Setting("/config.properties");
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
