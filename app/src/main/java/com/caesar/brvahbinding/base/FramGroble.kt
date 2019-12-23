package com.caesar.brvahbinding.base

import android.app.Activity
import android.app.Application
import java.lang.ref.WeakReference

/**
 * fram的全局工具类
 * created by Caesar on 2019/1/25
 * email : 15757855271@163.com
 */
object FramGroble {
    private var application: Application? = null
    private var mTopActivity: WeakReference<Activity>? = null
    fun setApp(application: Application) {
        FramGroble.application = application
    }

    fun getApp(): Application? {
        return application
    }

    fun setTopActivity(activity: Activity) {
        mTopActivity = WeakReference(activity)
    }

    fun getTopActivity(): Activity? {
        return mTopActivity?.get()
    }

    fun getValueString(resId:Int):String?{
        return application?.getString(resId)
    }



}
