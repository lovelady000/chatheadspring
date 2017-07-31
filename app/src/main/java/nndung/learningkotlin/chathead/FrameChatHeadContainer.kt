package nndung.learningkotlin.chathead

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by nndun on 7/26/2017.
 */
open abstract class FrameChatHeadContainer : IChatHeadContainer {
    private var frameLayout: HostFrameLayout
    var context: Context

    private lateinit var manager : IChatHeadManager

    constructor(context: Context) {
        this.context = context
        frameLayout = HostFrameLayout(context)
        //Thêm mới một hostFrameLayout để add các chathead
        addContainer(frameLayout, false)
    }



    override fun onInitialized(chatHeadManager: IChatHeadManager) {
        this.manager = chatHeadManager
    }
    override fun addView(view: View, layoutParams: ViewGroup.LayoutParams) {
        if (frameLayout != null) {
            frameLayout.addView(view, layoutParams)
        }
    }
    override fun createLayoutParams(height : Int, with : Int, gravity : Int, bottomMargin : Int) : ViewGroup.LayoutParams {
        var layoutParams = FrameLayout.LayoutParams(with, height)
        layoutParams.gravity = gravity
        layoutParams.bottomMargin = bottomMargin
        return layoutParams
    }


    abstract fun addContainer(container: View, focusable: Boolean)
}