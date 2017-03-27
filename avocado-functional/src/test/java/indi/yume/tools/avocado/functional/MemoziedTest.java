package indi.yume.tools.avocado.functional;

import org.junit.Test;

import indi.yume.tools.avocado.functional.Memoization;
import lombok.val;
import io.reactivex.functions.Function;

/**
 * Created by yume on 16-7-13.
 */

public class MemoziedTest {
    @Test
    public void testMemozied() throws  Exception {
        Function<Integer, String> fun = MemoziedTest::functionTest;
        Function<Integer, String> mFun = Memoization.memoize(fun);

        testFun(fun);
        testFun(mFun);
    }

    private static void testFun(Function<Integer, String> f) throws Exception {
        val start = System.currentTimeMillis();
        for(int i = 0; i < 10; i++)
            System.out.println(f.apply(2));
        val end = System.currentTimeMillis();

        System.out.println("Spend time: " + (end - start));
    }

    public static String functionTest(int count) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Count: " + count;
    }
}
