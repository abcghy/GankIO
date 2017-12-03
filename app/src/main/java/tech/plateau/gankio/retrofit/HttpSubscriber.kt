package tech.plateau.gankio.retrofit

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by sakura on 19/09/2017.
 */
abstract class HttpSubscriber<T>: Observer<Response<T>> {
    override fun onError(e: Throwable) {
        onFailure(parseThrowable(e), null)
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: Response<T>) {
        when (t.error) {
            false -> onSuccess(t)
            true -> onFailure("", t)
        }
    }

    abstract fun onSuccess(response: Response<T>)
    abstract fun onFailure(errMsg: String?, response: Response<T>?)

    fun parseThrowable(e: Throwable?): String {
        // TODO: ghy
        return "网络链接错误"
    }
}