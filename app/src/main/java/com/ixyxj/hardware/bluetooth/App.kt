package com.ixyxj.hardware.bluetooth

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import com.inuker.bluetooth.library.BluetoothContext


/**
 * For more information, you can visit https://github.com/ixyxj,
 * or contact me by xyxjun@gmail.com
 * @author silen on 2019/4/3 1:04
 * Copyright (c) 2019 in FORETREE
 */
class App : Application() {
    companion object {
        private lateinit var mInstance: App
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        BluetoothContext.set(this)
    }

    /**
     * 判断进程和名字是否为本应用
     */
    private fun shouldInit(): Boolean {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfos = am.runningAppProcesses
        val mainProcessName = packageName
        val myPid = Process.myPid()
        for (info in processInfos) {
            if (info.pid == myPid && mainProcessName == info.processName) {
                return true
            }
        }
        return false
    }
}