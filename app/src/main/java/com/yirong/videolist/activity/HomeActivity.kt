package com.yirong.videolist.activity


import android.content.Intent
import androidx.core.os.HandlerCompat.postDelayed
import com.yirong.videolist.R
import com.yirong.videolist.base.BaseActivity
import com.yirong.videolist.ui.BottomBarTab
import kotlinx.android.synthetic.main.activity_home.*
import java.util.logging.Handler

/**
 *Created by ${USER_NAME}
 *on 2019/5/5.
 */

class HomeActivity :BaseActivity(){


    override fun layoutId(): Int {
        return R.layout.activity_home
    }

    override fun initData() {
        val tabArray = arrayOf("首页","攻略","喜欢","我的")
    }

    override fun initView() {
        bottombar1.addItem(BottomBarTab(this,R.mipmap.home，tabArray[0]))
    }

    override fun start() {

    }



}