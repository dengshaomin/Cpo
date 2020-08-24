package com.code.cporegister.inject.okhttp

import com.code.cporegister.CpoPlugin
import com.code.cporegister.inject.InjectTools
import com.code.cporegister.inject.InjectType

/**
 *  author : balance
 *  date : 2020/8/21 2:05 PM
 *  description :
 */
class HttpCheck {
    companion object {
        //builder中注入eventfactory
        val okhttpClassName = "okhttp3/OkHttpClient\$Builder.class"

        //注入的Factory所在的完整路径
        val eventListenerFullName = "com.code.cpo.http.HttpEventListener"

        //builder构造函数中给eventfactory赋值:httpeventlistener
        val eventListenerMethodName = "<init>"

        //OkHttpClient所在路径
        var okhttpPath: String? = null

        //realcall中注入变量injectResponseStr
        val realCallClassName = "okhttp3/RealCall.class";

        //在此函数中给jnjectResponseStr赋值
        val realCallMethodName = "getResponseWithInterceptorChain"

        //RealCall所在的路径
        var realCallPath: String? = null
        //注入到realcall中的字段，截取response值
        var injectResponseStr = "injectResponseStr"
        fun check(name: String, targetPath: String) {
            if (name.contains(okhttpClassName)) {
                okhttpPath = targetPath
            } else if (name.contains(realCallClassName)) {
                realCallPath = targetPath
            }
        }

        fun inject() {
            okhttpPath?.let {
                InjectTools.inject(
                    InjectType.OKHTTPCLIENT,
                    okhttpPath,
                    okhttpClassName
                )
            }
            realCallPath?.let {
                InjectTools.inject(
                    InjectType.OKHTTPCLIENT,
                    realCallPath,
                    realCallClassName
                )
            }

        }
    }
}