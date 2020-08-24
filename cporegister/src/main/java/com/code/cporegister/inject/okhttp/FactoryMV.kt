package com.code.crregister

import com.code.cporegister.inject.okhttp.HttpCheck
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 *  author : balance
 *  date : 2020/8/13 9:56 PM
 *  description :OkhttpClient/Builder构造函数中给eventfactory赋值
 */
class FactoryMV(api: Int, mv: MethodVisitor?) : MethodVisitor(api, mv) {
    override fun visitCode() {
        super.visitCode()
    }

    override fun visitInsn(opcode: Int) {
        if (opcode == Opcodes.RETURN) {
            mv.visitCode()
            val l0 = Label()
            val l1 = Label()
            val l2 = Label()
            mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception")
            val l3 = Label()
            mv.visitLabel(l3)
            mv.visitLineNumber(23, l3)
//            mv.visitVarInsn(Opcodes.ALOAD, 0)
//            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
            mv.visitLabel(l0)
            mv.visitLineNumber(25, l0)
            mv.visitLdcInsn(HttpCheck.eventListenerFullName)
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Class",
                "forName",
                "(Ljava/lang/String;)Ljava/lang/Class;",
                false
            )
            mv.visitVarInsn(Opcodes.ASTORE, 1)
            val l4 = Label()
            mv.visitLabel(l4)
            mv.visitLineNumber(26, l4)
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            mv.visitLdcInsn("FACTORY")
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/lang/Class",
                "getField",
                "(Ljava/lang/String;)Ljava/lang/reflect/Field;",
                false
            )
            mv.visitLdcInsn("Factory")
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/lang/reflect/Field",
                "get",
                "(Ljava/lang/Object;)Ljava/lang/Object;",
                false
            )
            mv.visitTypeInsn(Opcodes.CHECKCAST, "okhttp3/EventListener\$Factory")
            mv.visitVarInsn(Opcodes.ASTORE, 2)
            val l5 = Label()
            mv.visitLabel(l5)
            mv.visitLineNumber(27, l5)
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitVarInsn(Opcodes.ALOAD, 2)
            mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                "okhttp3/OkHttpClient\$Builder",
                "eventListenerFactory",
                "Lokhttp3/EventListener\$Factory;"
            )
            mv.visitLabel(l1)
            mv.visitLineNumber(30, l1)
            val l6 = Label()
            mv.visitJumpInsn(Opcodes.GOTO, l6)
            mv.visitLabel(l2)
            mv.visitLineNumber(28, l2)
            mv.visitFrame(
                Opcodes.F_FULL,
                1,
                arrayOf<Any>("okhttp3/OkHttpClient\$Builder"),
                1,
                arrayOf<Any>("java/lang/Exception")
            )
            mv.visitVarInsn(Opcodes.ASTORE, 1)
            val l7 = Label()
            mv.visitLabel(l7)
            mv.visitLineNumber(29, l7)
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/lang/Exception",
                "printStackTrace",
                "()V",
                false
            )
            mv.visitLabel(l6)
            mv.visitLineNumber(32, l6)
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
            mv.visitInsn(Opcodes.RETURN)
            val l8 = Label()
            mv.visitLabel(l8)
            mv.visitLocalVariable("cls", "Ljava/lang/Class;", null, l4, l1, 1)
            mv.visitLocalVariable("factory", "Lokhttp3/EventListener\$Factory;", null, l5, l1, 2)
            mv.visitLocalVariable("e", "Ljava/lang/Exception;", null, l7, l6, 1)
            mv.visitLocalVariable("this", "Lokhttp3/OkHttpClient\$Builder;", null, l3, l8, 0)
            mv.visitMaxs(2, 3)
            mv.visitEnd()
        }
        super.visitInsn(opcode)

    }
}