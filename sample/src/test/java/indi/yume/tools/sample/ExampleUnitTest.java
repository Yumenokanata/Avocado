package indi.yume.tools.sample;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import rx.Observable;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Observable<Long> values = Observable.interval(500, TimeUnit.MILLISECONDS);

        Future<Long> future = values.take(5).toBlocking().toFuture();


//        Iterable<Long> iterable = values.take(5).toBlocking().toIterable();
//        for (long l : iterable) {
//            System.out.println(l);
//            Thread.sleep(750);
//        }
    }

    public void testJoda() {
        LocalDate now = LocalDate.now();
        now.minusMonths(1).dayOfMonth().withMaximumValue();
    }

    @Test
    public void testStream() {
        System.out.println(Stream.range(0, 10)
                .dropWhile(i -> i > 5)
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]")));
    }
}