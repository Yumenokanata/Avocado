package indi.yume.tools.avocado.functional.listmatch;

import java.util.Collections;
import java.util.List;

import lombok.experimental.UtilityClass;

/**
 * Created by yume on 16-8-15.
 */
@UtilityClass
public class CaseUtil {
    public static <C, L extends List<C>> StartWord<C, L> match(L list) {
        if(list == null)
            return new StartWord<C, L>((L) Collections.emptyList());
        return new StartWord<>(list);
    }
}
