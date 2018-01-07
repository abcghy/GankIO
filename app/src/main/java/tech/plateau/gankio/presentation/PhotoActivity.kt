package tech.plateau.gankio.presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import tech.plateau.gankio.R
import tech.plateau.gankio.databinding.ActivityPhotoBinding

/**
 * Created by sakura on 2017/12/3.
 */
class PhotoActivity: AppCompatActivity() {

    private var mBinding: ActivityPhotoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo)

        Glide.with(this)
                .load(intent.getStringExtra("url"))
                .into(mBinding?.photoView)
    }
}