package com.appyhigh.dynamicLinking

import android.net.Uri
import java.lang.Exception

interface onGetDataListener {
    fun onSuccess(link: Uri?)
    fun onFailure(exception: Exception)
}