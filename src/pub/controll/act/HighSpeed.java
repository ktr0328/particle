package pub.controll.act;

import components.CanvasArea;

/**
 * Created by ktr on 2017/03/23.
 */
public class HighSpeed extends Standard {

    public HighSpeed(CanvasArea canvas) {
        super(canvas);
        this.coefficient *= 5;
        this.name = "HighSpeed";
    }
}
