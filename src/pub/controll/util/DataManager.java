package pub.controll.util;

import data.Data;
import pub.controll.setting.Setting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ktr on 2017/04/08.
 */

public class DataManager {
    private enum FilePath {
        filePath("src/data/data.csv"),
        defaultPath("");

        private final String path;

        FilePath(String path) {
            this.path = path;
        }
    }

    public List<Data> list;

    public DataManager() {
        list = new ArrayList<>();
        List<String> ls = loadFile();

        ls.forEach(e -> {
            String[] arr = e.split(",");
            list.add(new Data(arr));
        });
        adjustDataNums(list);
    }

    private List<String> loadFile() {
        List<String> list = new ArrayList<>();

        Stream<String> stream;
        try {
            stream = Files.lines(Paths.get(FilePath.filePath.path));
            stream.forEach(list::add);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void adjustDataNums(List list) {
        Stream.iterate(list.size(), i -> ++i).limit(Setting.getSetting("dot_num")).forEach(i -> this.list.add(new Data()));
    }

//    private void writeFile() {
//        try {
//            Files.write(Paths.get(FilePath.filePath.path), ls, Charset.forName("UTF-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
