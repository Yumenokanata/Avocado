package indi.yume.tools.avocado;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.junit.Test;

import indi.yume.tools.avocado.functional.patternmatch.PatternMatch;
import indi.yume.tools.avocado.model.tuple.Tuple2;
import indi.yume.tools.avocado.model.tuple.Tuple3;
import lombok.val;

import static indi.yume.tools.avocado.functional.Utils.const1;
import static indi.yume.tools.avocado.functional.ifexpression.IfExpression.if_;
import static indi.yume.tools.avocado.functional.patternmatch.PatternMatch.*;

/**
 * Created by yume on 16-7-22.
 */

public class FunctionalWhenTest {

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
    public void testWhen() {
        val f = new FModel();

        Integer e = when(f,
                c_pd(const1(false),
                        i -> 4),
                c_eq(new S2Model(),
                        i -> 5),
                c_is(SModel.class,
                        const1(8)),
                c_el(i -> 6));

        System.out.println(e);

        Integer d = when(3f,
                c_pd(i -> i == 1,
                        i -> 4),
                c_eq(2f,
                        i -> 5),
                c_is(Float.class,
                        const1(8)),
                c_in(2f, 6f,
                        i -> 7),
                c_el(i -> 6));

        System.out.println(d);
    }

    static class FModel{}

    static class SModel extends FModel{}

    static class S2Model extends FModel{}

    static class SSModel extends SModel{}
}
