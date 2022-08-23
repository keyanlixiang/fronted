package com.assistant.fronted.Tools

import android.content.Context
import android.widget.Toast

/**
 * 快捷显示Toast提示的工具方法
 * 示例：“ABCD”.showToast()   R.String.app_theme.showToast(Toast.LENGTH_LONG)
 */
fun String.showToast(context: Context,duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}
fun Int.showToast(context: Context,duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}