package nndung.learningkotlin.chathead.interfaces

import nndung.learningkotlin.chathead.ChatHead

/**
 * Created by nndun on 8/1/2017.
 */
abstract class IChatHeadArrangement {
    abstract fun handleTouchUp(activeChatHead : ChatHead, xVelocity : Int, yVelocity : Int, wasDragging : Boolean)
    abstract fun handleTouchUp(activeChatHead: ChatHead)
}