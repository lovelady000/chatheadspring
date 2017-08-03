package nndung.learningkotlin.chathead

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager



/**
 * Created by nndun on 8/2/2017.
 */
open class ChatHeadUtils {
    companion object {
        fun dpToPx(metrics: DisplayMetrics, dp: Int): Int {
            var px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), metrics)
            if (px < 1.0f) {
                px = 1f
            }
            return px.toInt()
        }

        fun dpToPx(context: Context, dp: Int): Int {
            val metrics = DisplayMetrics()
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(metrics)
            return dpToPx(metrics, dp)
        }

        fun pxToDp(context: Context, px: Int): Int {
            val displayMetrics = context.resources.displayMetrics
            val dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
            return dp
        }
    }
}