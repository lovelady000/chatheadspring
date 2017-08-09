package nndung.learningkotlin.chathead.interfaces

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup


/**
 * Created by nndun on 7/26/2017.
 */
interface IChatHeadContainer {
    fun addView(view: View, layoutParams: ViewGroup.LayoutParams)
    fun setViewX(view: View, xPosition: Int)
    fun setViewY(view: View, yPosition: Int)
    fun createLayoutParams(height: Int, width: Int, gravity: Int, bottomMargin: Int): ViewGroup.LayoutParams
    fun onInitialized(chatHeadManager: IChatHeadManager)
    fun getDisplayMetrics() : DisplayMetrics
}