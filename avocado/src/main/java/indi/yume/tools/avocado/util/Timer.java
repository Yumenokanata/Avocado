package indi.yume.tools.avocado.util;

import indi.yume.tools.avocado.util.LogUtil;

/**
 * Created by yume on 16-5-4.
 */
public class Timer {
    private long time;
    private long period;

    private String prefixMessage;

    public Timer() {
        this("");
    }

    public Timer(String prefixMessage) {
        time = System.currentTimeMillis();
        if(prefixMessage == null || "".equals(prefixMessage))
            this.prefixMessage = "";
        else
            this.prefixMessage = prefixMessage + ": ";
    }

    public void start() {
        time = System.currentTimeMillis();
    }

    public void stop() {
        period = System.currentTimeMillis() - time;
    }

    public void print() {
        System.out.println(prefixMessage + "spend time= " + period);
    }

    public void print(String prefix) {
        System.out.println(prefix + "spend time= " + period);
    }

    public void stopAndPrint() {
        stop();
        print(prefixMessage);
    }

    public void stopAndPrint(String prefix) {
        stop();
        print(prefix);
    }
}
