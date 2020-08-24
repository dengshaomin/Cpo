package com.code.cporegister

import com.android.build.api.transform.*
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import com.code.cporegister.inject.okhttp.HttpCheck
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.compress.utils.IOUtils
import org.apache.commons.io.FileUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.io.FileOutputStream
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 *  author : balance
 *  date : 2020/8/17 5:14 PM
 *  description :
 */
class CpoPlugin : Transform(), Plugin<Project> {
    companion object {

        var cacheList = mutableListOf<String>()

    }

    override fun getName(): String {
        return CpoPlugin::class.java.name
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun apply(p0: Project) {
        val android = p0.extensions.getByType(AppExtension::class.java)
        //注入transform
        android.registerTransform(this)
    }

    override fun transform(transformInvocation: TransformInvocation?) {
//        super.transform(transformInvocation)
        println("********" + this@CpoPlugin.javaClass.simpleName + "  start")
        var isIncremental = transformInvocation?.isIncremental()
        if (!isIncremental!!) {
            //如果不是增量编译，清除所有数据
            transformInvocation?.outputProvider?.deleteAll()
            cacheList.clear()
        }
        var startTime = System.currentTimeMillis()
        transformInvocation?.inputs?.forEach {
            //扫描所有jar
            it.jarInputs.forEach {
                var path = it.file.absolutePath
                when (it.status) {
                    Status.NOTCHANGED -> {
                        //当jar没有变更且缓存中有该路径，跳过扫描
                        if (cacheList.contains(path)) {
                            return@forEach
                        } else {
                            cacheList.add(path)
                        }
                    }
                    Status.REMOVED -> {
                        //当jar被移除时，移除扫描数据
                        it.file.delete()
                        if (cacheList.contains(path)) {
                            cacheList.remove(path)
                        }
                        return@forEach
                    }
                }
                if (!cacheList.contains(path)) {
                    cacheList.add(path)
                }
                scanJar(it, transformInvocation.outputProvider)
            }
            //扫描所有目录
            it.directoryInputs.forEach {
                scanDirectory(it, transformInvocation.outputProvider)
            }
        }

        println("isIncremental:" + isIncremental)
        HttpCheck.inject()
        println("inject finish const:" + (System.currentTimeMillis() - startTime))
        println("********" + this@CpoPlugin.javaClass.simpleName + "  end")
    }

    fun scanJar(jarInput: JarInput, outputProvider: TransformOutputProvider) {
        if (jarInput.file.getAbsolutePath().endsWith(".jar")) {
            var jarName = jarInput.name
            var md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
            if (jarName.endsWith(".jar")) {
                jarName = jarName.substring(0, jarName.length - 4)
            }
            var jarFile = JarFile(jarInput.file)
            var enumeration = jarFile.entries()
            var tmpFile = File(jarInput.file.getParent() + File.separator + "classes_temp.jar")
            if (tmpFile.exists()) {
                tmpFile.delete()
            }
            var dest = outputProvider.getContentLocation(
                jarName + md5Name,
                jarInput.contentTypes, jarInput.scopes, Format.JAR
            )

            var jarOutputStream = JarOutputStream(FileOutputStream(tmpFile))
            while (enumeration.hasMoreElements()) {
                var jarEntry = enumeration.nextElement()
                var entryName = jarEntry.getName()
                var zipEntry = ZipEntry(entryName)
                var inputStream = jarFile.getInputStream(jarEntry)
                var bytes = IOUtils.toByteArray(inputStream)
                HttpCheck.check(entryName,dest.path)
                jarOutputStream.putNextEntry(zipEntry)
                jarOutputStream.write(bytes)
                jarOutputStream.closeEntry()
            }
            jarOutputStream.close()
            jarFile.close()
            FileUtils.copyFile(tmpFile, dest)
            tmpFile.delete()
        }

    }

    fun scanDirectory(input: DirectoryInput, outputProvider: TransformOutputProvider) {
        var dest = outputProvider.getContentLocation(
            input.name,
            input.contentTypes,
            input.scopes,
            Format.DIRECTORY
        )
        if (input.file.isDirectory()) {
            input.file.walk().iterator().forEach {
                HttpCheck.check(it.name,it.path)
            }
        }
        FileUtils.copyDirectory(input.file, dest)
    }



}