package com.yirong.videolist.activity


import com.yirong.videolist.R
import com.yirong.videolist.base.BaseActivity
import android.content.Intent
import android.os.Handler


/**
 *Created by YIRONG
 *on 2019/5/5.
 */
class SplashActivity:BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun start() {
        val handler = Handler()
        handler.postDelayed({skipToHome()},1800)
    }

    //跳转
    fun skipToHome(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}