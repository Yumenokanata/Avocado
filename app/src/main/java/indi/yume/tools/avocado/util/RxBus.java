package indi.yume.tools.avocado.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by yume on 15/11/5.
 */
public class RxBus {
    private Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    private static RxBus rxBus;
    private RxBus(){}

    public static RxBus getInstance(){
        if(rxBus == null)
            synchronized (RxBus.class){
                if(rxBus == null)
                    rxBus = new RxBus();
            }
        return rxBus;
    }

    public void send(Object event){
        bus.onNext(event);
    }

    public <T> Observable<T> toObservable(Class<T> clazz){
        return bus.asObservable()
                .filter(o -> clazz.getName().equals(o.getClass().getName()))
                .cast(clazz)
                .doOnError(LogUtil::e)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
