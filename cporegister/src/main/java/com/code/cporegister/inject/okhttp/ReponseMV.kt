package com.code.crregister

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 *  author : balance
 *  date : 2020/8/13 9:56 PM
 *  description :在getResponseWithInterceptorChain给injectResponseStr赋值
 */
class ReponseMV(api: Int, mv: MethodVisitor?) : MethodVisitor(api, mv) {
    override fun visitCode() {
        super.visitCode()
        mv.visitCode()
        val l0 = Label()
        val l1 = Label()
        val l2 = Label()
        mv.visitTryCatchBlock(l0, l1, l2, "java/io/IOException")
        val l3 = Label()
        mv.visitTryCatchBlock(l0, l1, l3, null)
        val l4 = Label()
        mv.visitTryCatchBlock(l2, l4, l3, null)
        val l5 = Label()
        mv.visitLabel(l5)
        mv.visitLineNumber(212, l5)
        mv.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList")
        mv.visitInsn(Opcodes.DUP)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false)
        mv.visitVarInsn(Opcodes.ASTORE, 1)
        val l6 = Label()
        mv.visitLabel(l6)
        mv.visitLineNumber(213, l6)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/OkHttpClient",
            "interceptors",
            "()Ljava/util/List;",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/List",
            "addAll",
            "(Ljava/util/Collection;)Z",
            true
        )
        mv.visitInsn(Opcodes.POP)
        val l7 = Label()
        mv.visitLabel(l7)
        mv.visitLineNumber(214, l7)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.NEW, "okhttp3/internal/http/RetryAndFollowUpInterceptor")
        mv.visitInsn(Opcodes.DUP)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        mv.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            "okhttp3/internal/http/RetryAndFollowUpInterceptor",
            "<init>",
            "(Lokhttp3/OkHttpClient;)V",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/List",
            "add",
            "(Ljava/lang/Object;)Z",
            true
        )
        mv.visitInsn(Opcodes.POP)
        val l8 = Label()
        mv.visitLabel(l8)
        mv.visitLineNumber(215, l8)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.NEW, "okhttp3/internal/http/BridgeInterceptor")
        mv.visitInsn(Opcodes.DUP)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/OkHttpClient",
            "cookieJar",
            "()Lokhttp3/CookieJar;",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            "okhttp3/internal/http/BridgeInterceptor",
            "<init>",
            "(Lokhttp3/CookieJar;)V",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/List",
            "add",
            "(Ljava/lang/Object;)Z",
            true
        )
        mv.visitInsn(Opcodes.POP)
        val l9 = Label()
        mv.visitLabel(l9)
        mv.visitLineNumber(216, l9)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.NEW, "okhttp3/internal/cache/CacheInterceptor")
        mv.visitInsn(Opcodes.DUP)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/OkHttpClient",
            "internalCache",
            "()Lokhttp3/internal/cache/InternalCache;",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            "okhttp3/internal/cache/CacheInterceptor",
            "<init>",
            "(Lokhttp3/internal/cache/InternalCache;)V",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/List",
            "add",
            "(Ljava/lang/Object;)Z",
            true
        )
        mv.visitInsn(Opcodes.POP)
        val l10 = Label()
        mv.visitLabel(l10)
        mv.visitLineNumber(217, l10)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.NEW, "okhttp3/internal/connection/ConnectInterceptor")
        mv.visitInsn(Opcodes.DUP)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        mv.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            "okhttp3/internal/connection/ConnectInterceptor",
            "<init>",
            "(Lokhttp3/OkHttpClient;)V",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/List",
            "add",
            "(Ljava/lang/Object;)Z",
            true
        )
        mv.visitInsn(Opcodes.POP)
        val l11 = Label()
        mv.visitLabel(l11)
        mv.visitLineNumber(218, l11)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "forWebSocket", "Z")
        val l12 = Label()
        mv.visitJumpInsn(Opcodes.IFNE, l12)
        val l13 = Label()
        mv.visitLabel(l13)
        mv.visitLineNumber(219, l13)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/OkHttpClient",
            "networkInterceptors",
            "()Ljava/util/List;",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/List",
            "addAll",
            "(Ljava/util/Collection;)Z",
            true
        )
        mv.visitInsn(Opcodes.POP)
        mv.visitLabel(l12)
        mv.visitLineNumber(221, l12)
        mv.visitFrame(
            Opcodes.F_APPEND,
            1,
            arrayOf<Any>("java/util/List"),
            0,
            null
        )
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.NEW, "okhttp3/internal/http/CallServerInterceptor")
        mv.visitInsn(Opcodes.DUP)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "forWebSocket", "Z")
        mv.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            "okhttp3/internal/http/CallServerInterceptor",
            "<init>",
            "(Z)V",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/List",
            "add",
            "(Ljava/lang/Object;)Z",
            true
        )
        mv.visitInsn(Opcodes.POP)
        val l14 = Label()
        mv.visitLabel(l14)
        mv.visitLineNumber(223, l14)
        mv.visitTypeInsn(Opcodes.NEW, "okhttp3/internal/http/RealInterceptorChain")
        mv.visitInsn(Opcodes.DUP)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
            Opcodes.GETFIELD,
            "okhttp3/RealCall",
            "transmitter",
            "Lokhttp3/internal/connection/Transmitter;"
        )
        mv.visitInsn(Opcodes.ACONST_NULL)
        mv.visitInsn(Opcodes.ICONST_0)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
            Opcodes.GETFIELD,
            "okhttp3/RealCall",
            "originalRequest",
            "Lokhttp3/Request;"
        )
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        val l15 = Label()
        mv.visitLabel(l15)
        mv.visitLineNumber(224, l15)
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/OkHttpClient",
            "connectTimeoutMillis",
            "()I",
            false
        )
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        val l16 = Label()
        mv.visitLabel(l16)
        mv.visitLineNumber(225, l16)
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/OkHttpClient",
            "readTimeoutMillis",
            "()I",
            false
        )
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, "okhttp3/RealCall", "client", "Lokhttp3/OkHttpClient;")
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/OkHttpClient",
            "writeTimeoutMillis",
            "()I",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            "okhttp3/internal/http/RealInterceptorChain",
            "<init>",
            "(Ljava/util/List;Lokhttp3/internal/connection/Transmitter;Lokhttp3/internal/connection/Exchange;ILokhttp3/Request;Lokhttp3/Call;III)V",
            false
        )
        mv.visitVarInsn(Opcodes.ASTORE, 2)
        val l17 = Label()
        mv.visitLabel(l17)
        mv.visitLineNumber(227, l17)
        mv.visitInsn(Opcodes.ICONST_0)
        mv.visitVarInsn(Opcodes.ISTORE, 3)
        mv.visitLabel(l0)
        mv.visitLineNumber(229, l0)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
            Opcodes.GETFIELD,
            "okhttp3/RealCall",
            "originalRequest",
            "Lokhttp3/Request;"
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "okhttp3/Interceptor\$Chain",
            "proceed",
            "(Lokhttp3/Request;)Lokhttp3/Response;",
            true
        )
        mv.visitVarInsn(Opcodes.ASTORE, 4)
        //从这里开始注入：this.injectResponseStr = response.body().string();
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitVarInsn(Opcodes.ALOAD, 4)
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/Response",
            "body",
            "()Lokhttp3/ResponseBody;",
            false
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/ResponseBody",
            "string",
            "()Ljava/lang/String;",
            false
        )
        mv.visitFieldInsn(
            Opcodes.PUTFIELD,
            "okhttp3/RealCall",
            "injectResponseStr",
            "Ljava/lang/String;"
        )
        //end

        val l18 = Label()
        mv.visitLabel(l18)
        mv.visitLineNumber(230, l18)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
            Opcodes.GETFIELD,
            "okhttp3/RealCall",
            "transmitter",
            "Lokhttp3/internal/connection/Transmitter;"
        )
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/internal/connection/Transmitter",
            "isCanceled",
            "()Z",
            false
        )
        val l19 = Label()
        mv.visitJumpInsn(Opcodes.IFEQ, l19)
        val l20 = Label()
        mv.visitLabel(l20)
        mv.visitLineNumber(231, l20)
        mv.visitVarInsn(Opcodes.ALOAD, 4)
        mv.visitMethodInsn(
            Opcodes.INVOKESTATIC,
            "okhttp3/internal/Util",
            "closeQuietly",
            "(Ljava/io/Closeable;)V",
            false
        )
        val l21 = Label()
        mv.visitLabel(l21)
        mv.visitLineNumber(232, l21)
        mv.visitTypeInsn(Opcodes.NEW, "java/io/IOException")
        mv.visitInsn(Opcodes.DUP)
        mv.visitLdcInsn("Canceled")
        mv.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            "java/io/IOException",
            "<init>",
            "(Ljava/lang/String;)V",
            false
        )
        mv.visitInsn(Opcodes.ATHROW)
        mv.visitLabel(l19)
        mv.visitLineNumber(234, l19)
        mv.visitFrame(
            Opcodes.F_APPEND,
            3,
            arrayOf(
                "okhttp3/Interceptor\$Chain",
                Opcodes.INTEGER,
                "okhttp3/Response"
            ),
            0,
            null
        )
        mv.visitVarInsn(Opcodes.ALOAD, 4)
        mv.visitVarInsn(Opcodes.ASTORE, 5)
        mv.visitLabel(l1)
        mv.visitLineNumber(239, l1)
        mv.visitVarInsn(Opcodes.ILOAD, 3)
        val l22 = Label()
        mv.visitJumpInsn(Opcodes.IFNE, l22)
        val l23 = Label()
        mv.visitLabel(l23)
        mv.visitLineNumber(240, l23)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
            Opcodes.GETFIELD,
            "okhttp3/RealCall",
            "transmitter",
            "Lokhttp3/internal/connection/Transmitter;"
        )
        mv.visitInsn(Opcodes.ACONST_NULL)
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/internal/connection/Transmitter",
            "noMoreExchanges",
            "(Ljava/io/IOException;)Ljava/io/IOException;",
            false
        )
        mv.visitInsn(Opcodes.POP)
        mv.visitLabel(l22)
        mv.visitLineNumber(234, l22)
        mv.visitFrame(
            Opcodes.F_APPEND,
            1,
            arrayOf<Any>("okhttp3/Response"),
            0,
            null
        )
        mv.visitVarInsn(Opcodes.ALOAD, 5)
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitLabel(l2)
        mv.visitLineNumber(235, l2)
        mv.visitFrame(
            Opcodes.F_FULL,
            4,
            arrayOf(
                "okhttp3/RealCall",
                "java/util/List",
                "okhttp3/Interceptor\$Chain",
                Opcodes.INTEGER
            ),
            1,
            arrayOf<Any>("java/io/IOException")
        )
        mv.visitVarInsn(Opcodes.ASTORE, 4)
        val l24 = Label()
        mv.visitLabel(l24)
        mv.visitLineNumber(236, l24)
        mv.visitInsn(Opcodes.ICONST_1)
        mv.visitVarInsn(Opcodes.ISTORE, 3)
        val l25 = Label()
        mv.visitLabel(l25)
        mv.visitLineNumber(237, l25)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
            Opcodes.GETFIELD,
            "okhttp3/RealCall",
            "transmitter",
            "Lokhttp3/internal/connection/Transmitter;"
        )
        mv.visitVarInsn(Opcodes.ALOAD, 4)
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/internal/connection/Transmitter",
            "noMoreExchanges",
            "(Ljava/io/IOException;)Ljava/io/IOException;",
            false
        )
        mv.visitInsn(Opcodes.ATHROW)
        mv.visitLabel(l3)
        mv.visitLineNumber(239, l3)
        mv.visitFrame(
            Opcodes.F_SAME1,
            0,
            null,
            1,
            arrayOf<Any>("java/lang/Throwable")
        )
        mv.visitVarInsn(Opcodes.ASTORE, 6)
        mv.visitLabel(l4)
        mv.visitVarInsn(Opcodes.ILOAD, 3)
        val l26 = Label()
        mv.visitJumpInsn(Opcodes.IFNE, l26)
        val l27 = Label()
        mv.visitLabel(l27)
        mv.visitLineNumber(240, l27)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
            Opcodes.GETFIELD,
            "okhttp3/RealCall",
            "transmitter",
            "Lokhttp3/internal/connection/Transmitter;"
        )
        mv.visitInsn(Opcodes.ACONST_NULL)
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/internal/connection/Transmitter",
            "noMoreExchanges",
            "(Ljava/io/IOException;)Ljava/io/IOException;",
            false
        )
        mv.visitInsn(Opcodes.POP)
        mv.visitLabel(l26)
        mv.visitFrame(
            Opcodes.F_APPEND,
            3,
            arrayOf(
                Opcodes.TOP,
                Opcodes.TOP,
                "java/lang/Throwable"
            ),
            0,
            null
        )
        mv.visitVarInsn(Opcodes.ALOAD, 6)
        mv.visitInsn(Opcodes.ATHROW)
        val l28 = Label()
        mv.visitLabel(l28)
        mv.visitLocalVariable("response", "Lokhttp3/Response;", null, l18, l2, 4)
        mv.visitLocalVariable("e", "Ljava/io/IOException;", null, l24, l3, 4)
        mv.visitLocalVariable("this", "Lokhttp3/RealCall;", null, l5, l28, 0)
        mv.visitLocalVariable(
            "interceptors",
            "Ljava/util/List;",
            "Ljava/util/List<Lokhttp3/Interceptor;>;",
            l6,
            l28,
            1
        )
        mv.visitLocalVariable("chain", "Lokhttp3/Interceptor\$Chain;", null, l17, l28, 2)
        mv.visitLocalVariable("calledNoMoreExchanges", "Z", null, l0, l28, 3)
        mv.visitMaxs(11, 7)
        mv.visitEnd()
    }

    override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
        super.visitFieldInsn(opcode, owner, name, descriptor)
    }

    override fun visitInsn(opcode: Int) {
        if (opcode == Opcodes.RETURN || opcode == Opcodes.ARETURN) {

        }
        super.visitInsn(opcode)

    }
}