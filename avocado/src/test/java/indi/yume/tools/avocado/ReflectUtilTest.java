package indi.yume.tools.avocado;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.junit.Test;

import indi.yume.tools.avocado.reflect.ReflectUtil;
import indi.yume.tools.avocado.util.Timer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yume on 16-7-22.
 */

public class ReflectUtilTest {

    @Test
    public void testFieldSetter() {
        assertTrue("isAutoLogin -> " + Stream.of(ReflectUtil.toSetter("isAutoLogin")).collect(Collectors.joining(",")), ReflectUtil.toSetter("isAutoLogin").contains("setAutoLogin"));
        assertTrue("IsAutoLogin", ReflectUtil.toSetter("IsAutoLogin").contains("setIsAutoLogin"));
        assertTrue("ISAutoLogin", ReflectUtil.toSetter("ISAutoLogin").contains("setISAutoLogin"));
        assertTrue("iSAutoLogin", ReflectUtil.toSetter("iSAutoLogin").contains("setISAutoLogin"));
    }

    @Test
    public void testModelCopy() {
        TestModel1 source = TestModel1.of(true, "Name1", "Name2", new TestSubModel("ModelName"), 1, 0.62f);
        TestModel2 target = new TestModel2();

        Timer timer = new Timer();
        timer.start();
        ReflectUtil.copyModel(source, target);
        timer.stopAndPrint();

        assertEquals(source.getIsAutoLogin(), target.isAutoLogin());
        assertEquals(source.getName(), target.getName());
        assertEquals(source.getName2(), target.getName2());
        assertEquals(source.getName5(), target.getName5());

        assertNotEquals(source.getName6(), target.getName3());
        assertNotEquals(source.getName7(), target.getName4());
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    public static class TestModel1 {
        private Boolean isAutoLogin;
        private String name;
        private String name2;
        private TestSubModel name5;

        private int name6;
        private Float name7;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    public static class TestModel2 {
        private boolean isAutoLogin;
        private String name;
        private String name2;
        private TestSubModel name5;

        private int name3;
        private Float name4;
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    public static class TestSubModel {
        private final String name;
    }
}
