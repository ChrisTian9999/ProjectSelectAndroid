package com.chris.pss.widgets.subscribers;

public interface GeneralSubscriber<T> {
    void onNext(T t);

    void onError(Throwable e);
}
