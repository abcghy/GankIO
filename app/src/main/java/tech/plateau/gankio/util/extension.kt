package tech.plateau.gankio.util

import android.content.Context
import android.widget.Toast
import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

/**
 * Created by sakura on 2017/12/3.
 */
fun Context.dp2px(dp: Float): Int = (this.resources.displayMetrics.density * dp + 0.5f).toInt()

fun <T> Observable<T>.network(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

//fun <T> List<T>?.isRealEmpty(): Boolean {
//    return this == null || this.isEmpty()
//}

fun <T> isEmpty(list: List<T>?): Boolean {
    return list == null || list.isEmpty()
}

fun Context.showShort(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun SPUtils.put(name: String, value: Serializable) {
    put(name, Gson().toJson(value))
}

fun <T> SPUtils.getSerializable(name: String, clazz: Class<T>): T? {
    var string: String? = getString(name) ?: return null
    return try {
        Gson().fromJson(string, clazz)
    } catch (e: Exception) {
        null
    }
}