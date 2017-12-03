package tech.plateau.gankio

import android.content.Context

/**
 * Created by sakura on 2017/12/3.
 */
fun Context.dp2px(dp: Float): Int = (this.resources.displayMetrics.density * dp + 0.5f).toInt()