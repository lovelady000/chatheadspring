package nndung.learningkotlin.chathead.interfaces

import android.util.DisplayMetrics
import nndung.learningkotlin.chathead.ChatHead

/**
 * Created by nndun on 7/26/2017.
 */
interface IChatHeadManager {
    fun getChatHeadContainer() : IChatHeadContainer
    fun addChatHead(isSticky : Boolean, animated : Boolean) : ChatHead
    fun getDisplayMetrics() : DisplayMetrics
    fun getArrangement(): IChatHeadArrangement
    fun onClick()
}