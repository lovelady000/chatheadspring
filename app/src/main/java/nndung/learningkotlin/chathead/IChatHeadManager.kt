package nndung.learningkotlin.chathead

/**
 * Created by nndun on 7/26/2017.
 */
interface IChatHeadManager {
    fun addChatHead(isSticky : Boolean, animated : Boolean) : ChatHead
}