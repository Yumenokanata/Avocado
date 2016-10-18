package indi.yume.tools.avocado.functional.listmatch;

import com.annimon.stream.Optional;

import lombok.Data;

/**
 * Created by yume on 16-8-15.
 */
@Data
public class EndCase<T> {
    private final T result;

    public T get() {
        return result;
    }

    public Optional<T> getOptional() {
        return Optional.ofNullable(result);
    }
}
