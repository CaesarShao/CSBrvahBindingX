package com.caesar.brvahbinding.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.caesarlib.brvahbinding.CSLog
import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * viewmodel的基类
 * created by Caesar on 2019/1/25
 * email : 15757855271@163.com
 */
abstract class BaseViewModel : AndroidViewModel(FramGroble.getApp() as Application)
