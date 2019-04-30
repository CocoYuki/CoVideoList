package com.yirong.videolist.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log

import com.orhanobut.logger.*

import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates


class MyApplication: Application() {
    private var mRefWatcher: RefWatcher? = null

    companion object{

        private var mContext: Context by Delegates.notNull()
        private const val TAG = "MyApplication"


        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.mRefWatcher
        }
    }


    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        mRefWatcher = setLeakCanary()
        initConfig()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    private fun setLeakCanary(): RefWatcher? {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    private fun initConfig() {
        //Logger
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
            .methodCount(0)         // (Optional) How many method line to show. Default 2
            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
            // .logStrategy(customLog) (Optional) Changes the log strategy to print out. Default LogCat
            .tag("videoList")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    //Activity创建监听
    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }
        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }

    }



}