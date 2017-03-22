package indi.yume.tools.avocado.string;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yume on 17-3-14.
 */
public class FormatUtilTest {
    @Test
    public void formatBuilderText() {
        String result = FormatUtil.build()
                .addTexts()
                .addTexts(null, "1111", "")
                .addText("\nTel.%s", "2222")
                .addText("1111")
                .addText(null)
                .addText("")
                .addText("\nSAL.%s%s%s", "333", "444", null)
                .addText("\nDCL.%s%s%s", null, "", null)
                .build();

        assertEquals(
                "1111\nTel.22221111\nSAL.333444",
                result);
    }
}