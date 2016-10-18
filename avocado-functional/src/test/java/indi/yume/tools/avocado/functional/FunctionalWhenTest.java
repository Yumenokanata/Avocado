package indi.yume.tools.avocado.functional;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.junit.Test;
import org.pcollections.ConsPStack;
import org.pcollections.PStack;

import java.util.Collections;

import indi.yume.tools.avocado.functional.matching.CaseUtil;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import indi.yume.tools.avocado.model.tuple.Tuple3;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;
import rx.functions.Func1;

import static indi.yume.tools.avocado.functional.Func.st;
import static indi.yume.tools.avocado.functional.Utils.const1;
import static indi.yume.tools.avocado.functional.ifexpression.IfExpression.if_;
import static indi.yume.tools.avocado.functional.listmatch.CaseUtil.match;
import static indi.yume.tools.avocado.functional.matching.Matcher.eq;
import static indi.yume.tools.avocado.functional.matching.Matcher.in;
import static indi.yume.tools.avocado.functional.matching.Matcher.is;
import static indi.yume.tools.avocado.functional.matching.Matcher.pd;

import static org.junit.Assert.*;

/**
 * Created by yume on 16-7-22.
 */

public class FunctionalWhenTest {

    @Test
    public void testExtension() {
        TestMonad monad = st(TestMonad.unit(1))
                .at(TestMonad::map, i -> i + 1)
                .at(TestMonad::flatMap, i -> TestMonad.unit(i + 1))
                .get();

        System.out.println(monad.toString());

        val monadFun = st(TestMonad::unit)
                .at(TestMonad::map, i -> i + 1)
                .at(TestMonad::flatMap, i -> TestMonad.unit(i + 1))
                .get();

        System.out.println(monadFun.call(1).toString());
    }

    @Data
    public static class TestMonad {
        private final Integer i;

        public static TestMonad unit(int i) {
            return new TestMonad(i);
        }

        public static TestMonad map(TestMonad monad, Func1<Integer, Integer> f) {
            return new TestMonad(f.call(monad.getI()));
        }

        public static TestMonad flatMap(TestMonad monad, Func1<Integer, TestMonad> f) {
            return f.call(monad.getI());
        }
    }

    @Test
    public void testGenerateCode_voidFun() {
        String code = Stream.range(1, 7)
                .map(i ->
                        Tuple3.<String, String, Integer>of(
                                Stream.range(0, i)
                                        .map(n -> String.valueOf(n + 1))
                                        .collect(Collectors.joining(", P", "P", "")),
                                Stream.range(0, i)
                                        .map(n -> String.valueOf(n + 1))
                                        .collect(Collectors.joining(", p", "p", "")),
                                i))
                .map(tuple ->
                        String.format("    public static <%1$s> Func%3$d<%1$s, Void> voidFun%3$d(final Action%3$d<%1$s> act) {\n" +
                                "        return (%2$s) -> {\n" +
                                "            act.call(%2$s);\n" +
                                "            return null;\n" +
                                "        };\n" +
                                "    }\n",
                                tuple.getData1(),
                                tuple.getData2(),
                                tuple.getData3()))
                .collect(Collectors.joining("\n"));

        System.out.println(code);
    }

    @Test
    public void testIf() {
        Integer d = if_(1 < 0)
                .do_(() -> 1)
                .else_if(() -> 5 < 9)
                .do_(() -> 2)
                .else_(() -> 4)
                .get();

        System.out.println(d);
    }

    @Test
    public void testWhenCase() {
        val f = (FModel) new S2Model();

        Integer e = CaseUtil.when(f)
                .case_(pd(const1(false)), 4)
                .case_(eq(new S2Model()), i -> 5)
                .case_(is(SModel.class), 8)
                .el_get(6);

        assertEquals((int)e, 5);
        System.out.println(e);

        Integer d = CaseUtil.when(3f)
                .case_pd(i -> i == 1, 4)
                .case_(eq(2f), i -> 5)
                .case_(is(Float.class), const1(8))
                .case_(in(2f, 6f), 7)
                .el_get(6);

        assertEquals((int)d, 8);
        System.out.println(d);
    }

    static class FModel{}

    static class SModel extends FModel{}

    @Data
    @EqualsAndHashCode(callSuper = false)
    static class S2Model extends FModel{}

    static class SSModel extends SModel{}

    @Test
    public void testListMatch() {
        PStack<String> pStack = ConsPStack.singleton("3")
                .plus("2")
                .plus("1");

        String s = match(null)
                .empty(() -> "[]")
                .el_get("123");
        System.out.println(s); // []

        s = match(Collections.emptyList())
                .empty(() -> "[]")
                .el_get("123");
        System.out.println(s); // []

        s = match(pStack)
                .empty(() -> "[]")
                .matchF("1", xs -> "_1")
                .el_get("123");
        System.out.println(s); // _1

        s = match(pStack)
                .empty(() -> "[]")
                .matchL("3", xs -> "_3")
                .el_get("123");
        System.out.println(s); // _3

        s = match(pStack)
                .empty(() -> "[]")
                .matchF((x, xs) -> x + " : " + xs)
                .el_get("123");
        System.out.println(s); // 1 : [2, 3]

        s = match(pStack)
                .empty(() -> "[]")
                .matchL((xs, x) -> xs + " : " + x)
                .el_get("123");
        System.out.println(s); // [1, 2] : 3

        s = match(pStack)
                .empty(() -> "[]")
                .el_get(l -> l.toString());
        System.out.println(s); // [1, 2, 3]
    }
}
