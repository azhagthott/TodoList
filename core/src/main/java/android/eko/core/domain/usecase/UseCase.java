package android.eko.core.domain.usecase;

import androidx.core.util.Preconditions;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void execute(UseCaseObserver<T> disposableObserver) {
        Preconditions.checkNotNull(disposableObserver);
        Observable<T> observable = this.createObservableUseCase()
                .subscribeOn(getSubscribeOn())
                .observeOn(getObserveOn());

        UseCaseObserver<T> observer = observable.subscribeWith(disposableObserver);

        compositeDisposable.add(observer);
    }

    public Observable<T> getObservable() {
        return createObservableUseCase();
    }

    protected Scheduler getSubscribeOn() {
        return Schedulers.io();
    }

    protected Scheduler getObserveOn() {
        return AndroidSchedulers.mainThread();
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Observable<T> createObservableUseCase();
}
