package tech.plateau.gankio.presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.SPUtils
import com.github.markzhai.recyclerview.BaseViewAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import org.jetbrains.anko.intentFor
import tech.plateau.gankio.adapter.CategoryAdapter
import tech.plateau.gankio.databinding.FragmentImageCateBinding
import tech.plateau.gankio.databinding.ItemCategoryImageBinding
import tech.plateau.gankio.retrofit.*
import android.content.Intent
import android.net.Uri
import tech.plateau.gankio.bean.OpenMethod
import tech.plateau.gankio.R
import tech.plateau.gankio.presentation.base.BaseFragment
import tech.plateau.gankio.util.getSerializable
import tech.plateau.gankio.util.isEmpty
import tech.plateau.gankio.util.network
import tech.plateau.gankio.util.put


/**
 * Created by sakura on 2018/1/6.
 */
class ImageCateFragment : BaseFragment(), OnRefreshListener, OnLoadmoreListener {
    private var mBinding: FragmentImageCateBinding? = null

    private var mAdapter: CategoryAdapter? = null
    private var mLayoutManager: GridLayoutManager? = null

    private var mQueryParam = QueryParam()
    private var mType: Category = Category.fortune

    companion object {
        fun newInstance(type: Category): ImageCateFragment {
            val fragment = ImageCateFragment()
            val bundle = Bundle()
            bundle.putSerializable("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mType = arguments?.getSerializable("type") as Category
    }

    override fun isLazyLoad(): Boolean {
        return true
    }

    override fun initComponent(inflater: LayoutInflater?, container: ViewGroup?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_cate, container, false)

        mBinding?.recyclerView?.setHasFixedSize(true)
        mAdapter = CategoryAdapter(mContext)
        mAdapter?.setPresenter(Presenter())
        mBinding?.recyclerView?.adapter = mAdapter
        mLayoutManager = GridLayoutManager(context, 2)
        mBinding?.recyclerView?.layoutManager = mLayoutManager

        return mBinding?.root
    }

    override fun createHandler() {
        mBinding?.refreshLayout?.setOnRefreshListener(this)
        mBinding?.refreshLayout?.setOnLoadmoreListener(this)
        mBinding?.refreshLayout?.isEnableLoadmore = false

        mAdapter?.setDecorator { holder, position, viewType ->
            var itemBinding = holder.binding
            if (itemBinding is ItemCategoryImageBinding) {
                itemBinding.llImage.setOnClickListener {
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity!!,
                            itemBinding.llImage,
                            getString(R.string.shared_image)
                    )
                    startActivity(
                            mContext.intentFor<PhotoActivity>(
                                    "url" to itemBinding.item?.url),
                            options.toBundle()
                    )
                }
            }
        }
    }

    override fun loadData() {
        mBinding?.refreshLayout?.autoRefresh(0)
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
        mQueryParam.resetPage()
        HttpMethods.gank()
                .getDataByCategory(mType.category, mQueryParam.count, mQueryParam.page)
                .network()
                .subscribe(object : HttpSubscriber<List<CategoryResponse>>() {
                    override fun onSuccess(response: Response<List<CategoryResponse>>) {
                        mBinding?.refreshLayout?.finishRefresh(0)
                        if (!isEmpty(response.results)) {
                            response.results?.forEachIndexed { index, categoryResponse ->
                                categoryResponse.position = index
                            }
                            mAdapter?.addAll(response.results, mType.ordinal)
                            mBinding?.refreshLayout?.isEnableLoadmore = true
                        } else {
                            // 空状态
                        }
                    }

                    override fun onFailure(errMsg: String?, response: Response<List<CategoryResponse>>?) {
                        mBinding?.refreshLayout?.finishRefresh(0)
                        Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show()
                        mQueryParam.resumePage()
                    }
                })
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        mQueryParam.nextPage()
        HttpMethods.gank()
                .getDataByCategory(mType.category, mQueryParam.count, mQueryParam.page)
                .network()
                .subscribe(object : HttpSubscriber<List<CategoryResponse>>() {
                    override fun onSuccess(response: Response<List<CategoryResponse>>) {
                        mBinding?.refreshLayout?.finishLoadmore(0)
                        if (!isEmpty(response.results)) {
                            response.results?.forEachIndexed { index, categoryResponse ->
                                categoryResponse.position = index
                            }
                            mAdapter?.addAll(response.results, mType.ordinal)
                            mBinding?.refreshLayout?.isEnableLoadmore = true
                        } else {
                            mBinding?.refreshLayout?.isEnableLoadmore = false
                        }
                    }

                    override fun onFailure(errMsg: String?, response: Response<List<CategoryResponse>>?) {
                        mBinding?.refreshLayout?.finishLoadmore(0)
                        Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show()
                        mQueryParam.prePage()
                    }
                })
    }

    inner class Presenter : BaseViewAdapter.Presenter {
        fun onClickArticle(item: CategoryResponse) {
            // 如果是第一次点击 url，询问是用系统的，还是用本应用自带的
            val openMethod = SPUtils.getInstance().getSerializable("openMethod", OpenMethod::class.java)
            if (openMethod == null) {
                MaterialDialog.Builder(mContext)
                        .title("使用何种方式浏览网页")
                        .items(OpenMethod.values().map { it.value })
                        .itemsCallbackSingleChoice(-1) { dialog, view, which, text ->
                            SPUtils.getInstance().put("openMethod", OpenMethod.values()[which])
                            openByMethod(OpenMethod.values()[which], item.url)
                            true
                        }
                        .show()
            } else {
                openByMethod(openMethod, item.url)
            }
        }
    }

    private fun openByMethod(openMethod: OpenMethod, url: String?) {
        when (openMethod) {
            OpenMethod.APP -> startActivity(
                    mContext.intentFor<WebViewActivity>("url" to url)
            )
            OpenMethod.SYSTEM -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }

    object Util {
        @JvmStatic
        fun getLeftMargin(item: CategoryResponse): Float {
            return if (item.position != null) {
                if (item.position!! % 2 == 0) 16f else 8f
            } else {
                8f
            }
        }

        @JvmStatic
        fun getRightMargin(item: CategoryResponse): Float {
            return if (item.position != null) {
                if (item.position!! % 2 == 0) 8f else 16f
            } else {
                8f
            }
        }

        @JvmStatic
        fun getImage(item: CategoryResponse): String? {
            if (!isEmpty(item.images)) {
                return item.images?.get(0)
            }
            return null
        }
    }

}