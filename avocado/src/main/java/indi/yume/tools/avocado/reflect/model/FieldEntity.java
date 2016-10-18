package indi.yume.tools.avocado.reflect.model;

import lombok.Data;

/**
 * Created by yume on 15/8/25.
 */
@Data
public class FieldEntity {
    private String fieldName;
    private Object value;
    private Class type;
    private Class genericType;
}
