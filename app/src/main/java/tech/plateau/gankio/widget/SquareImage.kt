package tech.plateau.gankio.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by sakura on 2017/12/3.
 */
class SquareImageView : ImageView {

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}