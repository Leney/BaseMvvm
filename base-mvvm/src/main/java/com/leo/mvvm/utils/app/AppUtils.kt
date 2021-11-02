package com.leo.mvvm.utils.app

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import com.leo.mvvm.utils.DLog
import java.io.File

/**
 * desc   : 特别注意：Android 10及以上，不能保存的apk文件不能在外部sdcard了
 *          只能放在app内部私有文件夹下，否则会没有读写外部磁盘权限，导致找不到apk文件，解析出问题，不能安装
 * date   : 2020/12/11
 * version: 1.0
 */
fun installApp(apkFilePath: String) {
    DLog.i("llj", "本地文件路径--->>$apkFilePath")
    getFileByPath(apkFilePath)?.let { installApp(it) }
}

fun installApp(apkFile: File) {
    if (!isFileExists(apkFile)) {
        DLog.i("llj", "apk文件不存在--->>${apkFile.absolutePath}")
        return
    }
    Utils.getApp().startActivity(getInstallAppIntent(apkFile, true))
}

private fun getFileByPath(filePath: String): File? {
    return if (isSpace(filePath)) null else File(filePath)
}

private fun isSpace(s: String?): Boolean {
    if (s == null) {
        return true
    }
    var i = 0
    val len = s.length
    while (i < len) {
        if (!Character.isWhitespace(s[i])) {
            return false
        }
        ++i
    }
    return true
}

private fun isFileExists(file: File?): Boolean {
    return file != null && file.exists()
}

private fun getInstallAppIntent(file: File): Intent {
    return getInstallAppIntent(file, false)
}

private fun getInstallAppIntent(file: File, isNewTask: Boolean): Intent {
    val intent = Intent(Intent.ACTION_VIEW)
    val data: Uri
    val type = "application/vnd.android.package-archive"
    when {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.N -> {
            // 6.0 及 以下
            data = Uri.fromFile(file)
        }
        else -> {
            DLog.i("llj", "package----->>${Utils.getApp().packageName}")
//        val authority: String = Utils.getApp().packageName + ".utilcode.provider"
            // authority 的值适合AndroidManifest.xml中配置的 authorities 要保持一致
            val authority = "com.leo.mvvm.utilcode.provider"
            data = FileProvider.getUriForFile(Utils.getApp(), authority, file)
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
    }
    Utils.getApp().grantUriPermission(
        Utils.getApp().packageName,
        data,
        Intent.FLAG_GRANT_READ_URI_PERMISSION
    )
    intent.setDataAndType(data, type)
    return if (isNewTask) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) else intent
}
