package com.appyhigh.dynamicLinking

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.ShortDynamicLink

fun createShortLink(link: String, domain: String, packageName: String?, listener: onGetDataListener) {
    Log.d("DynamicLink", "$link $domain")
    val shortLinkTask: Task<ShortDynamicLink> =
        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(Uri.parse(link))
            .setDomainUriPrefix(domain) // Open links with this app on Android
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder(packageName!!).build()
            ) // Open links with com.example.ios on iOS
            .setIosParameters(DynamicLink.IosParameters.Builder(packageName).build())
            .buildShortDynamicLink()
            .addOnSuccessListener { result ->
                listener.onSuccess(result.shortLink)
            }.addOnFailureListener {
                listener.onFailure(it);
            }
}


fun getDynamicLink(intent: Intent,listener: onGetDataListener) {
    FirebaseDynamicLinks.getInstance()
        .getDynamicLink(intent)
        .addOnSuccessListener { pendingDynamicLinkData -> // Get deep link from result (may be null if no link is found)
            var deepLink: Uri? = null
            if (pendingDynamicLinkData != null) {
                deepLink = pendingDynamicLinkData.link
                listener.onSuccess(deepLink)
            }
        }
        .addOnFailureListener { e -> listener.onFailure(e)}
}


fun shareLink(myDynamicLink: Uri?, linkMessage: String, context: Context) {
    val sendIntent = Intent()
    val msg = linkMessage + myDynamicLink
    sendIntent.action = Intent.ACTION_SEND
    sendIntent.putExtra(Intent.EXTRA_TEXT, msg)
    sendIntent.type = "text/plain"
    context.startActivity(sendIntent)
}
