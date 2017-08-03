package nndung.learningkotlin.chathead

import android.util.DisplayMetrics

/**
 * Created by nndun on 7/26/2017.
 */
interface IChatHeadManager {
    fun getChatHeadContainer() : IChatHeadContainer
    fun addChatHead(isSticky : Boolean, animated : Boolean) : ChatHead
    fun getDisplayMetrics() : DisplayMetrics
    fun getArrangement(): IChatHeadArrangement
}