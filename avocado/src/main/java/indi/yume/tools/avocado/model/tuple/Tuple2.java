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
public class Tuple2<T1, T2> {
    private T1 data1;
    private T2 data2;
}
