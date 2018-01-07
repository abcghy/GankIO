package tech.plateau.gankio

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * Created by sakura on 2017/12/3.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        Utils.init(this)
    }

    private lateinit var INSTANCE: App
    fun getInstance(): App {
        return INSTANCE
    }
}