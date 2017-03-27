package indi.yume.tools.avocado.util;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by yume on 15/11/5.
 */
public class RxBus {
    private Subject<Object> bus = PublishSubject.create().toSerialized();

    private static RxBus rxBus;
    private RxBus(){}

    public static synchronized RxBus getInstance(){
        if(rxBus == null)
            rxBus = new RxBus();
        return rxBus;
    }

    public void send(Object event){
        bus.onNext(event);
    }

    public <T> Flowable<T> toObservable(Class<T> clazz){
        return bus.toFlowable(BackpressureStrategy.BUFFER)
                .filter(o -> clazz.isInstance(o))
                .cast(clazz)
                .doOnError(LogUtil::e)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public <T> Flowable<T> toMainObservable(Class<T> clazz){
        return toObservable(clazz)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
