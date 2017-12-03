package tech.plateau.gankio

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.markzhai.recyclerview.BaseViewAdapter
import com.github.markzhai.recyclerview.BindingViewHolder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.intentFor
import tech.plateau.gankio.adapter.CategoryAdapter
import tech.plateau.gankio.adapter.CategoryAdapterType
import tech.plateau.gankio.databinding.FragmentCategoryBinding
import tech.plateau.gankio.databinding.ItemCategoryImageBinding
import tech.plateau.gankio.retrofit.*

/**
 * Created by sakura on 2017/12/3.
 */
class CategoryFragment(): Fragment() {

    private var mBinding: FragmentCategoryBinding? = null
    private var mAdapter: CategoryAdapter? = null
    private var mLayoutManager: GridLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        initComponent()
        loadData()
        return mBinding?.root
    }

    private fun initComponent() {
        mBinding?.recyclerView?.setHasFixedSize(true)

        mAdapter = CategoryAdapter(context)
//        mAdapter?.setDecorator(Decorator())
        mAdapter?.setPresenter(Presenter())
        mBinding?.recyclerView?.adapter = mAdapter

        mLayoutManager = GridLayoutManager(context, 2)
        mBinding?.recyclerView?.layoutManager = mLayoutManager
    }

    private fun loadData() {
//        mAdapter?.add(CategoryResponse(), CategoryAdapterType.IMAGE.ordinal)
        HttpMethods.gank().getDataByCategory(Category.fortune.category, 10, 1).network().subscribe(object: HttpSubscriber<List<CategoryResponse>>() {
            override fun onSuccess(response: Response<List<CategoryResponse>>) {
                response.results?.forEachIndexed { index, categoryResponse ->
                    categoryResponse.position = index
                }
                mAdapter?.addAll(response.results, CategoryAdapterType.IMAGE.ordinal)
            }

            override fun onFailure(errMsg: String?, response: Response<List<CategoryResponse>>?) {
                Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show()
            }
        })
    }

//    class Decorator : BaseViewAdapter.Decorator {
//        override fun decorator(holder: BindingViewHolder<*>?, position: Int, viewType: Int) {
////            when (viewType) {
////                CategoryAdapterType.IMAGE.ordinal -> holder?.binding?.
////            }
//            if (holder?.binding is ItemCategoryImageBinding) {
//                (holder.binding as ItemCategoryImageBinding).item?.position = holder.adapterPosition
//                Log.d("test", ""+(holder.binding as ItemCategoryImageBinding).item?.position)
//            }
//        }
//    }

    inner class Presenter: BaseViewAdapter.Presenter {
        fun onClickPhoto(item: CategoryResponse) {
            startActivity(context.intentFor<PhotoActivity>("url" to item.url))
        }
    }

    object Util {
        @JvmStatic
        fun getLeftMargin(item: CategoryResponse): Float {
            if (item.position != null) {
                return if (item.position!! % 2 == 0) 16f else 8f
            } else {
                return 8f
            }
        }

        @JvmStatic
        fun getRightMargin(item: CategoryResponse): Float {
            if (item.position != null) {
                return if (item.position!! % 2 == 0) 8f else 16f
            } else {
                return 8f
            }
        }
    }

}

private fun <T> Observable<T>.network(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
