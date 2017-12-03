package tech.plateau.gankio.adapter

import android.databinding.BindingAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import tech.plateau.gankio.dp2px

/**
 * Created by sakura on 2017/12/3.
 */
object AppBindingAdapter {
    @JvmStatic
    @BindingAdapter("leftMargin", "rightMargin", requireAll = false)
    fun setLeftMargin(view: View, leftMargin: Float, rightMargin: Float) {
        var lp = view.layoutParams as ViewGroup.MarginLayoutParams
        lp.leftMargin = view.context.dp2px(leftMargin)
        lp.rightMargin = view.context.dp2px(rightMargin)
        view.layoutParams = lp
    }

    @JvmStatic
    @BindingAdapter("imageResourceUrl")
    fun setSrc(imageView: ImageView, imageUrl: Any) {
        var options = RequestOptions().centerCrop()
        Glide.with(imageView.context)
                .load(imageUrl)
                .apply(options)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("square")
    fun setSquare(view: View, isSquare: Boolean) {

    }
}