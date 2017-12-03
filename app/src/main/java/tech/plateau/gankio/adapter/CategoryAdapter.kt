package tech.plateau.gankio.adapter

import android.content.Context
import com.github.markzhai.recyclerview.MultiTypeAdapter
import tech.plateau.gankio.R

/**
 * Created by sakura on 2017/12/3.
 */
class CategoryAdapter(context: Context): MultiTypeAdapter(context) {
    init {
        addViewTypeToLayoutMap(CategoryAdapterType.IMAGE.ordinal, R.layout.item_category_image)
    }
}

enum class CategoryAdapterType {
    IMAGE
}