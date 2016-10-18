package indi.yume.tools.sample;

import com.googlecode.totallylazy.Strings;
import com.googlecode.totallylazy.collections.TreeList;

import org.junit.Test;

import fj.data.Java;
import rx.Observable;

import static com.googlecode.totallylazy.collections.TreeList.treeList;

/**
 * Created by yume on 16-6-7.
 */

public class JavaHaskell {
    @Test
    public void testRPN() {
        Double result = solveRPN("12 23 + 3 * 3 /");
        System.out.println("Result= " + result);
        assert result == 35;
        Observable.just(1);

        ((JavaHaskell)null).hello();

        main();
    }

    public static void hello() {
        System.out.println("hello");
    }

    public static boolean foo(char c) {
        System.out.println(c);
        return true;
    }

    public static void main() {
        int i = 0;

        for(foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }
    }

    public Double solveRPN(String expr) {
        return Strings.split(" ").apply(expr).foldLeft(treeList(), JavaHaskell::foldingFun).head();
    }

    public static TreeList<Double> foldingFun(TreeList<Double> data, String expr) {
        switch (expr) {
            case "+": return data.tail().cons(data.head() + data.tail().head());
            case "-": return data.tail().cons(data.head() - data.tail().head());
            case "*": return data.tail().cons(data.head() * data.tail().head());
            case "/": return data.tail().cons(data.tail().head() / data.head());
            default: return data.cons(Double.valueOf(expr));
        }
    }
}
