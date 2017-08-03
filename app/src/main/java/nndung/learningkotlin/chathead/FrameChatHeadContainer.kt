package nndung.learningkotlin.chathead

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * Created by nndun on 7/26/2017.
 */
abstract class FrameChatHeadContainer : IChatHeadContainer {
    private var mFrameLayout: HostFrameLayout
    private var mContext: Context

    private lateinit var manager: IChatHeadManager

    constructor(context: Context) {
        this.mContext = context
        mFrameLayout = HostFrameLayout(context)
        mFrameLayout.isFocusable = true
        mFrameLayout.isFocusableInTouchMode = true
        //Thêm mới một hostFrameLayout để add các chathead
        addContainer(mFrameLayout, false)
    }

    fun getContext(): Context {
        return mContext
    }


    override fun onInitialized(chatHeadManager: IChatHeadManager) {
        this.manager = chatHeadManager
    }

    override fun addView(view: View, layoutParams: ViewGroup.LayoutParams) {
        mFrameLayout.addView(view, layoutParams)
    }

    override fun createLayoutParams(height: Int, width: Int, gravity: Int, bottomMargin: Int): ViewGroup.LayoutParams {
        var layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.gravity = gravity
        layoutParams.bottomMargin = bottomMargin
        return layoutParams
    }

    fun getFrameLayout(): HostFrameLayout {
        return this.mFrameLayout
    }

    override fun setViewX(view: View, xPosition: Int) {
        view.translationX = xPosition.toFloat()

    }

    override fun setViewY(view: View, yPosition: Int) {
        view.translationY = yPosition.toFloat()
    }

    override fun getDisplayMetrics(): DisplayMetrics {
        var displayMetrics = DisplayMetrics()
        var windowManager = this.mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics
    }

    abstract fun addContainer(container: View, focusable: Boolean)
}