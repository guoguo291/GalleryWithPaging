package com.example.gallerywithpagingdone

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService


object Utils{
    @JvmStatic
    fun sayHello(msg:String?){
        println("$msg")
    }
    @JvmStatic
    fun hideSoftKeyBoard(context: Context,view: View?) {
        if (view != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
