package nndung.learningkotlin.chathead

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import nndung.learningkotlin.R

/**
 * Created by nndun on 7/26/2017.
 */
class ChatHeadManager : IChatHeadManager {
    var chatHearContainer : IChatHeadContainer
    var context : Context
    constructor(context: Context) {
        this.context = context
        chatHearContainer = WindowManagerContainer(context)
    }

    //Thêm mới 1 chat head vào FRAME
    override fun addChatHead(isSticky: Boolean, animated: Boolean): ChatHead {
        var chatHead : ChatHead = ChatHead(this.context)
        var layoutParams : ViewGroup.LayoutParams = chatHearContainer.createLayoutParams(200,200,Gravity.START or Gravity.TOP,0)
        chatHearContainer.addView(chatHead, layoutParams)
        setDrawable(chatHead)
        return chatHead
    }

    fun setDrawable(chatHead : ChatHead) {
        chatHead.setImageResource(R.drawable.image_chat_head)
    }

    private class MotionCaptureView (context: Context): View (context)

}