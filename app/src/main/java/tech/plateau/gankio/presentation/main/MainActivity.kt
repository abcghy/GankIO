package tech.plateau.gankio.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import tech.plateau.gankio.R
import tech.plateau.gankio.databinding.ActivityMainBinding
import tech.plateau.gankio.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    var mBinding: ActivityMainBinding? = null

    override fun initComponent() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun createHandler() {
        mBinding?.bnv?.setOnNavigationItemSelectedListener { listener ->
            when (listener.itemId) {
                R.id.nav_all -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                R.id.nav_category -> {
                    var ft = supportFragmentManager.beginTransaction()
                    ft.replace(R.id.main_container, CategoryFragment())
                    ft.commit()
                }
                else -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun loadData(savedInstanceState: Bundle?) {

    }

}
