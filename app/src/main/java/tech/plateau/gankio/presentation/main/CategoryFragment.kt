package tech.plateau.gankio.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tech.plateau.gankio.R
import tech.plateau.gankio.databinding.FragmentCategoryBinding
import tech.plateau.gankio.presentation.ImageCateFragment
import tech.plateau.gankio.retrofit.*

/**
 * Created by sakura on 2017/12/3.
 */
class CategoryFragment: Fragment() {

    private var mBinding: FragmentCategoryBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        initComponent()
        loadData()
        return mBinding?.root
    }

    private fun initComponent() {
        mBinding?.vp?.adapter = CategoryAdapter(fragmentManager)

        mBinding?.stl?.setCustomTabView { container, position, adapter ->
            var view = layoutInflater.inflate(R.layout.item_category_stl, container, false)
            var tvTitleName = view.findViewById<TextView>(R.id.tv_title_name)
            tvTitleName.text = Category.values()[position].category
            return@setCustomTabView view
        }
        mBinding?.stl?.setViewPager(mBinding?.vp)
    }

    private fun loadData() {
    }

    inner class CategoryAdapter(fm: FragmentManager?): FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return ImageCateFragment.newInstance(Category.values()[position])
        }

        override fun getCount(): Int {
            return Category.values().size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return Category.values()[position].category
        }
    }

}


