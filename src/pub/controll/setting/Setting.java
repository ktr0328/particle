package pub.controll.setting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by ktr on 2017/03/18.
 */
public class Setting {
    private static HashMap<String, String> s;

    public Setting(String path) {
        s = readProperties(path);
    }

    public static String getSetting(String key) {
        return s.get(key);
    }

    private HashMap<String, String> readProperties(String path) {
        HashMap<String, String> tempMap = new HashMap<>();
        Properties p = new Properties();

        try {
            p.load(this.getClass().getResourceAsStream(path));
            setConfigData(tempMap, p);
        } catch (IOException e) {
            // TODO デフォルトの設定ファイルを渡す
            // TODO 書き換えも追加したい
            e.printStackTrace();
        }

        return tempMap;
    }

    private void setConfigData(Map<String, String> map, Properties p) {
        for (String key : p.stringPropertyNames()) {
            map.put(key, p.getProperty(key));
        }
    }
}
