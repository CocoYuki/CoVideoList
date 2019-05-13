package com.yirong.videolist

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yirong.videolist.adapter.MyReGifAdapter
import com.yirong.videolist.utils.MPermissionHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.recyclerview.widget.RecyclerView
import android.util.Log


class MainActivity : AppCompatActivity(),MPermissionHelper.PermissionCallBack {
    private lateinit var permissionHelper:MPermissionHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPermission()
        initView()

    }

    private fun initView() {
        recycley_view.setHasFixedSize(true)
        val manager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
            2,
            androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
        )
        manager.gapStrategy = androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_NONE;
        recycley_view.layoutManager = manager
        recycley_view.itemAnimator = null
        recycley_view.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //防止第一行到顶部有空白区域
                manager.invalidateSpanAssignments()
            }
        })

        var dataLists = initData()
        val adapter = MyReGifAdapter(this,dataLists)
        Log.i("datalist",dataLists.size.toString())
        adapter.setHasStableIds(true)
        recycley_view.adapter = adapter

    }

    private fun initData(): ArrayList<MyReGifAdapter.DataBean> {
        var list = ArrayList<MyReGifAdapter.DataBean>()
        for(i in 0 until imageUrls.size){
            var bean = MyReGifAdapter.DataBean(imageUrls[i],names[i])
            list.add(bean)
        }

        return list
    }

    private fun initPermission() {
        permissionHelper = MPermissionHelper(this)
        permissionHelper.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun permissionRegisterSuccess(vararg permissions: String?) {
        System.out.println("permissions = " + Arrays.toString(permissions));
        System.out.println("注册权限成功");

    }

    override fun permissionRegisterError(vararg permissions: String?) {
        permissionHelper.showGoSettingPermissionsDialog("存储");

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper.handleRequestPermissionsResult(requestCode, this, grantResults)
    }

    override fun onDestroy() {
        super.onDestroy()
        permissionHelper.destroy();
    }

    private val names = arrayOf(
        "连玩三次，工作人员还夸我臂力不错",
        "确实好吃",
        "想去这家网红店很久了，今天终于拔草了",
        "朝鲜人开的店，接待我们的是朝鲜人",
        "三星米其林店哦",
        "一盒八个，吃到爽",
        "开业搞活动，原价29元",
        "排队三个多小时，有人买几十袋",
        "这杯也太诱人了吧",
        "一锅88，够4，5个人吃了"
    )
    private val imageUrls = arrayOf(
        "http://192.168.12.18:8080/gif/gif1.gif",
        "http://192.168.12.18:8080/gif/gif2.gif",
        "http://192.168.12.18:8080/gif/gif3.gif",
        "http://192.168.12.18:8080/gif/gif4.gif",
        "http://192.168.12.18:8080/gif/gif5.gif",
        "http://192.168.12.18:8080/gif/gif6.gif",
        "http://192.168.12.18:8080/gif/gif1.gif",
        "http://192.168.12.18:8080/gif/gif2.gif",
        "http://192.168.12.18:8080/gif/gif3.gif",
        "http://192.168.12.18:8080/gif/gif4.gif"

    )
}
