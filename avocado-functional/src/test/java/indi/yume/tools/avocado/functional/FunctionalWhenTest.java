package indi.yume.tools.avocado.functional;

import com.annimon.stream.Collectors;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;

import org.junit.Test;
import org.pcollections.ConsPStack;
import org.pcollections.PStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import indi.yume.tools.avocado.functional.doexp.DoOption;
import indi.yume.tools.avocado.functional.doexp.DoSteamUtil;
import indi.yume.tools.avocado.functional.doexp.DoUtil;
import indi.yume.tools.avocado.functional.matching.CaseUtil;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import indi.yume.tools.avocado.model.tuple.Tuple3;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

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
        String code = com.annimon.stream.Stream.range(1, 7)
                .map(i ->
                        Tuple3.<String, String, Integer>of(
                                com.annimon.stream.Stream.range(0, i)
                                        .map(n -> String.valueOf(n + 1))
                                        .collect(Collectors.joining(", P", "P", "")),
                                com.annimon.stream.Stream.range(0, i)
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

        Func1<Integer, Func1<Integer, String>> curry = Currying.curried((i1, i2) -> i1 + "+" + i2);
        Func2<Integer, Integer, String> partial = Partial.part2((i1, s2, i3) -> i1 + "+" + s2 + "+" + i3, "2");
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

    public static <E> PStack<E> reverse(PStack<E> list) {
        return match(list)
                .empty(() -> (PStack<E>) Collections.emptyList())
                .matchF((x, xs) -> reverse(xs.plus(x)))
                .el_get(list);
    }

    @Test
    public void testDoUtil() {
        DoUtil
                .do_(() -> Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9))
                .do_(() -> Observable.just(2, 3))
                .do_12((t1, t2) -> Observable.just("" + t1 + t2))
                .yield()
                .subscribe(System.out::println);

        DoOption
                .do_(() -> Optional.ofNullable("1"))
                .do_(() -> Optional.ofNullable("2"))
                .do_12((x, y) -> Optional.ofNullable(x + y))
                .return_123((t1, t2, t3) -> t1 + t2 + t3)
                .ifPresent(System.out::println);
    }
}
