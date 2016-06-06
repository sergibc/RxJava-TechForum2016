package com.worldline.bereactive.data;

import android.util.Log;

import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Repository for poi
 */
public class PoiRepository {

    private static final String ENDPOINT = "http://t21services.herokuapp.com";

    private static final String TAG = "RX";

    /**
     * Retrofit stuff
     */
    private Retrofit getApi() {
        // Logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    /**
     * Call server to get an observable that emmits a {@link PoiDTO}
     */
    private Observable<PoiDTO> getPoiFromServer() {
        PoiService poiService = getApi().create(PoiService.class);
        return poiService.getPOIList();
    }

    //==================================================================
    // Map methods
    //==================================================================

    private Observable<Collection<PoiDTO.Poi>> getMapObservable() {
        return getPoiFromServer().map(new Func1<PoiDTO, Collection<PoiDTO.Poi>>() {
            @Override
            public Collection<PoiDTO.Poi> call(PoiDTO poiDTO) {
                return poiDTO.getList();
            }
        });
        // With lambdas
        //        return getPoiFromServer().map((Func1<PoiDTO, Collection<PoiDTO.Poi>>) poiDTO -> poiDTO.getList());
    }

    public void getPoiDtoMap() {
        Observable<Collection<PoiDTO.Poi>> demoObservable = getMapObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Collection<PoiDTO.Poi>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onNext(Collection<PoiDTO.Poi> pois) {
                        Log.d(TAG, "onNext: " + pois);
                    }
                });
    }

    //==================================================================
    // Flatmap methods
    //==================================================================

    private Observable<PoiDTO.Poi> getFlatMapObservable() {
        return getPoiFromServer().flatMap(new Func1<PoiDTO, Observable<PoiDTO.Poi>>() {
            @Override
            public Observable<PoiDTO.Poi> call(PoiDTO poiDTO) {
                return Observable.from(poiDTO.getList());
            }
        });
        // With lambdas
        //        return getPoiFromServer().flatMap(poiDTO -> Observable.from(poiDTO.getList()));
    }

    public void getPoiDtoFlatMap() {
        Observable<PoiDTO.Poi> demoObservable = getFlatMapObservable();

        demoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PoiDTO.Poi>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(PoiDTO.Poi o) {
                        Log.d(TAG, "onNext: " + o);
                    }
                });
    }

}