package tech.plateau.gankio.presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import tech.plateau.gankio.R
import tech.plateau.gankio.databinding.ActivityWebViewBinding
import tech.plateau.gankio.presentation.base.BaseActivity

/**
 * Created by sakura on 2018/1/6.
 */
class WebViewActivity: BaseActivity() {

    private lateinit var mBinding: ActivityWebViewBinding

    override fun initComponent() {
        mBinding = DataBindingUtil.setContentView(mContext, R.layout.activity_web_view)
        setSupportActionBar(mBinding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun createHandler() {

    }

    override fun loadData(savedInstanceState: Bundle?) {
        var url = intent.getStringExtra("url")
        var title = intent.getStringExtra("title")

        mBinding.webView.loadUrl(url)
        supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}