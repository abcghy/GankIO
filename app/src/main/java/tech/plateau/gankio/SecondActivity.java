package tech.plateau.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sakura on 2017/12/2.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        HttpMethods.INSTANCE.gank().getDataByCategory("", 1, 1).subscribe(new HttpSubscriber<Void>() {
//            @Override
//            public void onSuccess(@NotNull Response<Void> response) {
//
//            }
//
//            @Override
//            public void onFailure(@org.jetbrains.annotations.Nullable String errMsg, @org.jetbrains.annotations.Nullable Response<Void> response) {
//
//            }
//        });

//        Observable.just(1).subscribe();

//        Flowable.just(1).subscribe(new HttpSubscriber<Integer>() {
//            @Override
//            public void onSuccess(@NotNull Response<Integer> response) {
//
//            }
//
//            @Override
//            public void onFailure() {
//
//            }
//        });
//
//        Flowable.just(1).subscribe()

//        HttpMethods.INSTANCE.gank().getDataByCategory(Category.fortune.getCategory(), 10, 1).subscribe(new Observer<Void>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Void aVoid) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

}
