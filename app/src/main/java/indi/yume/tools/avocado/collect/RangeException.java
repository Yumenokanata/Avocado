package indi.yume.tools.avocado.collect;

/**
 * Created by yume on 16-5-4.
 */
public class RangeException extends RuntimeException {
    public RangeException() {
        super("Range has wrong");
    }

    public RangeException(String msg) {
        super("Range has wrong: " + msg);
    }
}
