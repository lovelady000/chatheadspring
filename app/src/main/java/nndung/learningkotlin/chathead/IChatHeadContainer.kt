package nndung.learningkotlin.chathead

import android.view.View
import android.view.ViewGroup



/**
 * Created by nndun on 7/26/2017.
 */
interface IChatHeadContainer {
    fun addView(view: View, layoutParams: ViewGroup.LayoutParams)
    fun createLayoutParams(height : Int, width : Int, gravity : Int, bottomMargin :Int) : ViewGroup.LayoutParams
    fun onInitialized(chatHeadManager: IChatHeadManager)
}