package data;

import java.util.Arrays;

/**
 * Created by ktr on 2017/04/08.
 */
public class Data {
    public String[] columns;

    public Data(String... args) {
        columns = args;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.columns);
    }
}
