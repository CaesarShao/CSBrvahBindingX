package com.caesar.brvahbinding.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * viewmodel的基类
 * created by Caesar on 2019/1/25
 * email : 15757855271@163.com
 */
abstract class BaseViewModel : AndroidViewModel(FramGroble.getApp() as Application)
