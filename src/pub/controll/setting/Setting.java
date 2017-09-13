package pub.controll.setting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by ktr on 2017/03/18.
 */
public class Setting {
    private static HashMap<String, Integer> s;

    public Setting(String path) {
        s = readProperties(path);
    }

    public static int get(String key) {
        return s.get(key);
    }

    private HashMap<String, Integer> readProperties(String path) {
        HashMap<String, Integer> tempMap;
        Properties p = new Properties();

        try {
            p.load(this.getClass().getResourceAsStream(path));
            tempMap = setConfigData(p);
        } catch (IOException e) {
            // TODO デフォルトの設定ファイルを渡す
            // TODO 書き換えも追加したい
            tempMap = new HashMap<>();
            e.printStackTrace();
        }

        return tempMap;
    }

    private HashMap<String, Integer> setConfigData(Properties p) {
        return p.stringPropertyNames().stream()
            .collect(HashMap::new, (m, key) -> m.put(key, Integer.parseInt(p.getProperty(key))), HashMap::putAll);
//
//        for (String key : p.stringPropertyNames()) {
//            map.put(key, Integer.parseInt(p.getProperty(key)));
//        }
    }
}
