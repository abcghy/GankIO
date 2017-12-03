package tech.plateau.gankio.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by sakura on 16/11/14.
 */
object HttpMethods {

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()

    private val mRetrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://gank.io/api/")
//                .baseUrl("http://test.daolema.me/core/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
    }

    private fun <T> create(clazz: Class<T>): T {
        return mRetrofitBuilder.build().create(clazz)
    }

    fun gank(): GankService {
        return create(GankService::class.java)
    }

    fun client(): ClientService {
        return create(ClientService::class.java)
    }

}


//public class HttpMethods {
//
//    private final int DEFAULT_TIMEOUT = 5;
//
//    private Retrofit retrofit;
//    private ClientService clientService;
//    private CommonService commonService;
//
//    private HttpMethods() {
//        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
//                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//
//        retrofit = new Retrofit.Builder()
//                .client(httpClientBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        clientService = retrofit.create(ClientService.class);
//        commonService = retrofit.create(CommonService.class);
//    }
//
//    private static class SingletonHolder {
//        private static final HttpMethods INSTANCE = new HttpMethods();
//    }
//
//    public static HttpMethods getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//
//    public void deleteReceiverAddress(HttpSubscriber<Void> subscriber) {
//        clientService.deleteReceiverAddress()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//}
