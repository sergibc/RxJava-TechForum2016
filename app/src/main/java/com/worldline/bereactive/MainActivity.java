package com.worldline.bereactive;

import com.worldline.bereactive.data.PoiRepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "RX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //==================================================================
    // Subscription - Slides code
    //==================================================================

    private void subscribe(Observable<Long> observable, DemoSubscriber subscriber) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    //==================================================================
    // Observables
    //==================================================================


    private Observable createObservable() {
        Observable<Integer> demoObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    for (int i = 0; i < 10; i++) {
                        observer.onNext(i);
                    }
                } catch (Exception e) {
                    observer.onError(e);
                } finally {
                    observer.onCompleted();
                }
            }
        });
        return demoObservable;
    }

    private Observable<Integer> fromObservable() {
        List<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);
        return Observable.from(collection);
    }

    private Observable justObservable() {
        List<Integer> collection = new ArrayList<>();
        collection.add(4);
        collection.add(3);
        collection.add(2);
        collection.add(1);
        return Observable.just(1, 2, 3, 4, "Hola", collection);
    }

    private Observable emptyObservable() {
        return Observable.empty();
    }

    private Observable mergeObservable() {
        Observable<Long> observable1 = Observable.interval(500, TimeUnit.MILLISECONDS).take(20);
        Observable<Long> observable2 = Observable.interval(200, TimeUnit.MILLISECONDS).take(20);

        return Observable.merge(observable1, observable2);
    }

    private Observable<Integer> zipObservable() {
        return Observable.zip(fromObservable(), fromObservable(), new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer number, Integer number2) {
                return number * number2;
            }
        });
        // Using lambdas
        //        return Observable.zip(fromObservable(), fromObservable(), (number, number2) -> number * number2);
    }

    private Observable concatObservable() {
        return Observable.concat(createObservable(), fromObservable());
    }

    //==================================================================
    // OnClick methods
    //==================================================================

    public void doCreate(View view) {
        Observable<Integer> demoObservable = createObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }


    public void doFrom(View view) {
        Observable<Integer> demoObservable = fromObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }

    public void doJust(View view) {
        Observable<Object> demoObservable = justObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Object value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }

    public void doEmpty(View view) {
        Observable<Object> demoObservable = emptyObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Object value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }

    public void doMerge(View view) {
        Observable<Long> demoObservable = mergeObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }

    public void doZip(View view) {
        Observable<Integer> demoObservable = zipObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }

    public void doConcat(View view) {
        Observable<Integer> demoObservable = concatObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }

    public void doFirst(View view) {
        Observable<Integer> demoObservable = fromObservable().first();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }

    public void doFilter(View view) {
        Observable<Integer> demoObservable = fromObservable().filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer value) {
                return value >= 3;
            }
        });
        // Using lambdas
        //        Observable<Integer> demoObservable = fromObservable().filter(value -> value >= 3);

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext: " + value);
                    }
                });
    }

    public void doMap(View view) {
        PoiRepository repository = new PoiRepository();
        repository.getPoiDtoMap();
    }

    public void doFlatMap(View view) {
        PoiRepository repository = new PoiRepository();
        repository.getPoiDtoFlatMap();
    }

    //==================================================================
    // Subscriber - Slides code
    //==================================================================

    private class DemoSubscriber extends Subscriber<Long> {
        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage());
        }

        @Override
        public void onNext(Long value) {
            Log.d(TAG, "onNext: " + value);
        }
    }

}
