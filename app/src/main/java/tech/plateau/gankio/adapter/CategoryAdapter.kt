package tech.plateau.gankio.adapter

import android.content.Context
import com.github.markzhai.recyclerview.MultiTypeAdapter
import tech.plateau.gankio.R
import tech.plateau.gankio.retrofit.Category

/**
 * Created by sakura on 2017/12/3.
 */
class CategoryAdapter(context: Context): MultiTypeAdapter(context) {
    init {
        addViewTypeToLayoutMap(Category.fortune.ordinal, R.layout.item_category_image)
        addViewTypeToLayoutMap(Category.android.ordinal, R.layout.item_category_android)
        addViewTypeToLayoutMap(Category.ios.ordinal, R.layout.item_category_android)
        addViewTypeToLayoutMap(Category.frontEnd.ordinal, R.layout.item_category_android)
        addViewTypeToLayoutMap(Category.rest.ordinal, R.layout.item_category_android)
        addViewTypeToLayoutMap(Category.expand.ordinal, R.layout.item_category_android)
    }
}