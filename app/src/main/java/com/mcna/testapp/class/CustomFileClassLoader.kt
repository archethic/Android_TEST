package com.mcna.testapp.`class`

import androidx.annotation.Nullable
import org.jetbrains.annotations.NotNull
import org.mozilla.javascript.ScriptableObject
import org.mozilla.javascript.annotations.JSStaticFunction
import java.io.File
import java.net.URL
import kotlin.jvm.internal.Intrinsics

class CustomFileClassLoader : ScriptableObject() {
    private var scope: ScriptableObject? = null
    private var scriptName: String? = null

    public fun CustomFileClassLoader(@NotNull str: String, @NotNull scriptableObject: ScriptableObject) {
        Intrinsics.checkNotNullParameter(str, "scriptName")
        Intrinsics.checkNotNullParameter(scriptableObject, "scope")
        scriptName = str
        scope = scriptableObject
        super(scope, scriptName)
    }

    @Nullable
    @JSStaticFunction
    @Throws(Exception::class)
    fun loadJar(classPath: String, classNames: String): ArrayList<Class<*>> {
        var ClassNames = arrayOf(classNames)
        val out = ArrayList<Class<*>>()
        val urls: ArrayList<URL> = ArrayList<URL>()
        val urlStHandler: java.net.URLStreamHandler? = null
        val classPathFile = File(classPath)
        urls.add(
            URL(
                null,
                "file:" + classPathFile.getCanonicalPath() + File.separator,
                urlStHandler
            )
        )

        val files = classPathFile.listFiles()
        for(f in files) {
            val fileName = f.name
            if(fileName.endsWith(".jar")) {
                urls.add(
                    URL(
                        null,
                        "file:" + classPathFile.getCanonicalPath() + File.separator,
                        urlStHandler
                    )
                )
            } else {
                throw NoJarException("Please import JarFile");
            }
        }

        val urlLoader =
            java.net.URLClassLoader(urls.toArray(arrayOfNulls<URL>(urls.size)) as Array<URL?>?)
        for (className in ClassNames) {
            val clazz: Class<*> = urlLoader.loadClass(className)
            out.add(clazz)
        }
        for (cz in out) {
            println(cz.name)
        }
        return out;
    }

    override fun getClassName(): String {
        return "CustomFileClassLoader"
    }
}