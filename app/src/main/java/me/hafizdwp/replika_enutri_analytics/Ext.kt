package me.hafizdwp.replika_enutri_analytics

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @author hafizdwp
 * 13/11/2019
 **/

fun Context.myCircularProgressDrawable(): androidx.swiperefreshlayout.widget.CircularProgressDrawable =
        androidx.swiperefreshlayout.widget.CircularProgressDrawable(this).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

/**
 * Log ext
 * */
fun log(msg: String,
        tag: String? = null) {
    Log.d(tag ?: "mytag", msg)
}


/**
 * Coroutine ext
 * ---------------------------------------------------------------------------------------------
 * */

fun launchIO(todo: suspend CoroutineScope.() -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        todo()
    }
}

fun launchMain(todo: suspend CoroutineScope.() -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        todo()
    }
}

suspend fun onMain(todo: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.Main) {
        todo()
    }
}

suspend fun <T> asyncAwait(context: CoroutineContext = Dispatchers.IO,
                           action: suspend CoroutineScope.() -> T): T =
        CoroutineScope(context).async { action(this) }.await()

inline fun <reified T> getTag(): String {
    return T::class.java.simpleName
}

/**
 * Ez toast
 * */
var mToast: Toast? = null

fun AppCompatActivity.toast(msg: String) {
    if (msg.isNotBlank()) {
        mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        mToast?.show()
    }
}

fun AppCompatActivity.toastSpammable(msg: String?) {
    if (msg != null) {
        if (msg.isNotBlank()) {
            if (mToast != null) mToast?.cancel()
            mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
            mToast?.show()
        }
    }
}

fun Fragment.toast(msg: String) {
    (requireActivity() as? AppCompatActivity)?.toast(msg)
}

fun Fragment.toastSpammable(msg: String?) {
    (requireActivity() as? AppCompatActivity)?.toastSpammable(msg)
}

fun <T : Fragment> T.withArgs(bundle: Bundle.() -> Unit): Fragment {
    return this.apply {
        arguments = Bundle().apply(bundle)
    }
}

/**
 * View visibility utility
 * */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.isInvisible(): Boolean {
    return visibility == View.INVISIBLE
}

fun View.isGone(): Boolean {
    return visibility == View.GONE
}