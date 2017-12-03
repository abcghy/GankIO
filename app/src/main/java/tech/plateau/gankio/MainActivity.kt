package tech.plateau.gankio

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import tech.plateau.gankio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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

//        val date = DateTime.now().withDate(2017, 10, 20)
//        HttpMethods.gank().getDataByDay(date.year, date.monthOfYear, date.dayOfMonth)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : HttpSubscriber<Void>() {
//                    override fun onSuccess(response: Response<Void>) {
//
//                    }
//
//                    override fun onFailure(errMsg: String?, response: Response<Void>?) {
//
//                    }
//                })
    }

}
