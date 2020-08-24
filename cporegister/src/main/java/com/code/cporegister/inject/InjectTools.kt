package com.code.cporegister.inject

import com.code.cporegister.inject.okhttp.HttpCheck
import com.code.crregister.FactoryCV
import com.code.crregister.ReponseCV
import jdk.internal.org.objectweb.asm.Opcodes
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 *  author : balance
 *  date : 2020/8/19 1:22 PM
 *  description :
 */
class InjectTools {
    companion object {
        fun inject(injectType: InjectType, jDPath: String?, targetName: String?) {
            println("path:" + jDPath)
            println("name:" + targetName)
            if (jDPath.isNullOrEmpty() || targetName.isNullOrEmpty()) {
                return
            }
            if (jDPath.endsWith(".jar")) {
                injectJar(
                    injectType,
                    jDPath,
                    targetName
                )
            } else {
                jDPath?.let {
                    injectDirectory(
                        injectType,
                        jDPath
                    )
                }
            }
        }

        private fun injectJar(injectType: InjectType, jDPath: String, targetName: String) {
            var jarFile = File(jDPath)
            var tempJar = File(jarFile.getParent(), jarFile.name + ".temp")
            if (tempJar.exists())
                tempJar.delete()
            val file = JarFile(jarFile)
            var enumeration = file.entries()
            var jarOutputStream = JarOutputStream(FileOutputStream(tempJar))
            while (enumeration.hasMoreElements()) {
                var jarEntry = enumeration.nextElement()
                var entryName = jarEntry.getName()
                var zipEntry = ZipEntry(entryName)
                var inputStream = file.getInputStream(jarEntry)
                jarOutputStream.putNextEntry(zipEntry)
                jarOutputStream.write(
                    if (entryName.contains(targetName)) {
                        injectCode(targetName, injectType, inputStream)
                    } else {
                        IOUtils.toByteArray(inputStream)
                    }
                )
                inputStream.close()
                jarOutputStream.closeEntry()
            }
            jarOutputStream.close()
            file.close()

            if (jarFile.exists()) {
                jarFile.delete()
            }
            tempJar.renameTo(jarFile)
        }

        private fun injectDirectory(injectType: InjectType, path: String) {
            var file = File(path)
            var tempClass = File(file.getParent(), file.name + ".temp")
            var inputStream = FileInputStream(file)
            var outputStream = FileOutputStream(tempClass)
            outputStream.write(injectCode(path, injectType, inputStream))
            inputStream.close()
            outputStream.close()
            if (file.exists()) {
                file.delete()
            }
            tempClass.renameTo(file)
        }

        private fun injectCode(
            path: String,
            injectType: InjectType,
            inputStream: InputStream
        ): ByteArray {
            var cr = ClassReader(inputStream)
            var cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
            var cv: ClassVisitor? = null
            when (injectType) {
                InjectType.OKHTTPCLIENT -> {
                    if (path.contains(HttpCheck.okhttpClassName)) {
                        cv = FactoryCV(Opcodes.ASM5, cw)
                    }else if(path.contains(HttpCheck.realCallClassName)){
                        cv = ReponseCV(Opcodes.ASM5, cw)
                    }
                }
            }
            cr.accept(cv, ClassReader.EXPAND_FRAMES)
            return cw.toByteArray()
        }
    }
}