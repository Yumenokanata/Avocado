package indi.yume.tools.avocado.model.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by yume on 16/3/8.
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> {
    private T1 data1;
    private T2 data2;
    private T3 data3;
    private T4 data4;
    private T5 data5;
    private T6 data6;
    private T7 data7;
    private T8 data8;
}
