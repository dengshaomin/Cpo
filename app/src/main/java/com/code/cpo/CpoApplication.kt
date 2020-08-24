package com.code.cpo

import android.app.Application

/**
 *  author : balance
 *  date : 2020/8/17 1:37 PM
 *  description :
 */
class CpoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CpoTool.init(this)

    }

}