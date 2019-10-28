package sherafatpour.entekhab.notify.util

import androidx.appcompat.app.AppCompatActivity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView



class

GlobalClass : Application() {

    companion object {
        private var appContext: Context? = null
        lateinit var iranSans: Typeface

    }


    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        iranSans = Typeface.createFromAsset(appContext!!.assets, "fonts/vazir.ttf")!!

    }



    fun getAppContext(): Context? {
        return appContext
    }

    fun getTypeFace(): Typeface {
        return iranSans
    }

    fun isTablet(): Boolean {
        return appContext!!.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    fun isOnline(): Boolean {
        val cm = appContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun setFont(group: ViewGroup) {

        val count = group.childCount
        var v: View
        for (i in 0 until count) {
            v = group.getChildAt(i)
            if (v is TextView || v is EditText || v is Button) {
                (v as TextView).typeface = iranSans

            } else if (v is ViewGroup)
                setFont(v)
        }
    }


    fun hideKeyboard(activity: AppCompatActivity) {
        val imm = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun statusBarColor(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            // edited here
            window.statusBarColor = Color.WHITE
        }
    }

}