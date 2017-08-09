package nndung.learningkotlin.chathead

/**
 * Created by nndun on 8/1/2017.
 */
abstract class IChatHeadArrangement {
    abstract fun handleTouchUp(activeChatHead : ChatHead, xVelocity : Int, yVelocity : Int,  wasDragging : Boolean)
    abstract fun handleTouchUp(activeChatHead: ChatHead)
}