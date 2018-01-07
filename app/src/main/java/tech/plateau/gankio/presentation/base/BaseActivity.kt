package tech.plateau.gankio.presentation.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by sakura on 2018/1/6.
 */
abstract class BaseActivity: AppCompatActivity() {

    protected lateinit var mContext: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        initComponent()
        createHandler()
        loadData(savedInstanceState)
    }

    abstract fun initComponent()
    abstract fun createHandler()
    abstract fun loadData(savedInstanceState: Bundle?)

}