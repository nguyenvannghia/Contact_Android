package nit.contact.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Base64
import android.view.inputmethod.InputMethodManager
import nit.contact.ui.activities.MainActivity
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by NIT Admin on 04/03/2016
 */
fun checkInternet(context: Context): Boolean {
    var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting;
}




fun hashingToMD5(value: String): String? {
    var m: MessageDigest?
    try {
        m = MessageDigest.getInstance("MD5")
        m!!.update(value.toByteArray(), 0, value.length)
        val valueStep1 = BigInteger(1, m.digest()).toString(16)

        val valueMD5 = String(Base64.encode(valueStep1.toByteArray(), Base64.NO_WRAP))

        return valueMD5
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        return null
    }

}

fun createCodeActive(email: String): String? {
    return hashingToMD5(email + Date())
}


fun createNameImage(): String {
    return "NIT_" + Date().time + ".jpg"
}



fun convertStringToDate(timeUTC: String): Date? {
    var date: Date? = null
    val format = SimpleDateFormat("d MMM yyyy HH:mm:ss Z", Locale.US)
    try {
        date = format.parse(timeUTC)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return date

}

fun openCamera(activity: Activity, code: Int) {
    val toCamera = Intent(activity, MainActivity::class.java)
    //    val toCamera = Intent(activity, ActivitySnapPicture::class.java)
    activity.startActivityForResult(toCamera, code)
}

fun openFromGallery(activity: Activity, code: Int) {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    activity.startActivityForResult(intent, code)

}

/**
 * replay method toGCMString don't support in java
 */
fun convertToGCMString(dateConvert: Date): String {
    var dfDate = SimpleDateFormat("dd/MMM/yyyy HH:mm:ss")
    var strResult: String?;
    strResult = dfDate.format(dateConvert.time);
    return strResult
}

fun hiddenKeyboard(activity: Activity) {
    if (activity.currentFocus != null) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

}

interface DialogCallBack<T> {
    fun evenDialogListener(even: T)
}

interface ImageLoaderCallBack {
    fun loadingCompleted()
    fun loadingFail()
}