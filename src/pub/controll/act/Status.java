package pub.controll.act;

/**
 * Created by ktr on 2017/03/23.
 */
public abstract class Status {

    private static Status status;

    Status() {

    }

    public abstract void move();

    public static Status getStatsu() {
        return status;
    }
}
