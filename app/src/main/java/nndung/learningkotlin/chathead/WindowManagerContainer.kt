package nndung.learningkotlin.chathead

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*


/**
 * Created by nndun on 7/26/2017.
 */
class WindowManagerContainer : FrameChatHeadContainer {


    private var windowManager: WindowManager
    fun getWindowManager(): WindowManager {
        if (windowManager == null) {
            windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        }
        return windowManager
    }

    constructor(context: Context) : super(context) {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    override fun addContainer(container: View, focusable: Boolean) {
        var containerParams = createContainerLayoutParams(focusable)
        addContainer(container, containerParams)
    }

    fun addContainer(container: View, containerLayoutParams: WindowManager.LayoutParams) {
        container.layoutParams = containerLayoutParams
        getWindowManager().addView(container, containerLayoutParams)
    }

    override fun addView(view: View, layoutParams: ViewGroup.LayoutParams) {
        super.addView(view, layoutParams)
    }

    fun createContainerLayoutParams(focusable: Boolean): WindowManager.LayoutParams {
        val focusableFlag: Int
        if (focusable) {
            focusableFlag = FLAG_NOT_TOUCH_MODAL
        } else {
            focusableFlag = FLAG_NOT_TOUCHABLE or FLAG_NOT_FOCUSABLE
        }
        val layoutParams = WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                focusableFlag,
                PixelFormat.TRANSLUCENT)
        layoutParams.x = 0
        layoutParams.y = 0
        layoutParams.gravity = Gravity.TOP or Gravity.START
        return layoutParams
    }


}