package tech.plateau.gankio.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by sakura on 2018/1/6.
 * 如果是正常 fragment 直接就初始化所有
 * 如果是 viewpager fragment， 需要 lazyLoad()，先初始化，再请求数据
 */
abstract class BaseFragment : Fragment() {

    private var mRootView: View? = null

    // 下面三个只对 lazyLoad 有用
    private var isInited: Boolean = false
    private var isLoaded: Boolean = false
    private var isVisi: Boolean = false

    protected lateinit var mContext: Context

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = getContext()!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("BaseFragment", "onCreateView")
        if (mRootView == null) {
            mRootView = initComponent(inflater, container)
        }
        createHandler()

        if (isLazyLoad()) {
            if (isVisi && !isInited && !isLoaded) {
                isInited = true
                isLoaded = true
                loadData()
            } else {
                // else 未初始化的 fragment
                isInited = true
            }
        } else {
            loadData()
        }
        return mRootView
    }

    open protected fun isLazyLoad(): Boolean {
        return false
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        Log.d("BaseFragment", "setUserVisibleHint: $isVisibleToUser")
        if (isLazyLoad()) {
            if (isVisibleToUser) {
                isVisi = true
                if (isInited && !isLoaded) {
                    isLoaded = true
                    loadData()
                }
            } else {
                isVisi = false
            }
        }
    }

    protected abstract fun initComponent(inflater: LayoutInflater?, container: ViewGroup?): View?
    protected abstract fun createHandler()
    protected abstract fun loadData()

}