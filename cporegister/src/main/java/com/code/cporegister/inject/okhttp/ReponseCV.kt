package com.code.crregister

import com.code.cporegister.inject.okhttp.HttpCheck
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 *  author : balance
 *  date : 2020/8/13 10:12 PM
 *  description :
 */
class ReponseCV(api: Int, cv: ClassVisitor?) : ClassVisitor(api, cv), Opcodes {

    override fun visitMethod(
        access: Int,
        name: String,
        desc: String?,
        signature: String?,
        exceptions: Array<String?>?
    ): MethodVisitor? {
        val mv: MethodVisitor = cv.visitMethod(access, name, desc, signature, exceptions)
        if (HttpCheck.realCallMethodName.equals(name)) {
            return ReponseMV(api, mv)
        }
        return mv
    }

    override fun visitEnd() {
        //注入injectResponseStr变量
        cv.visitField(Opcodes.ACC_PUBLIC, HttpCheck.injectResponseStr, "Ljava/lang/String;", null, null);
        super.visitEnd()
    }
}