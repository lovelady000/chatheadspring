package nndung.learningkotlin.chathead

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.rebound.SpringSystem
import nndung.learningkotlin.R

/**
 * Created by nndun on 7/26/2017.
 */
class ChatHeadManager : IChatHeadManager {


    private var mChatHearContainer : IChatHeadContainer
    private var mSpringSystem : SpringSystem

    private var mContext : Context
    constructor(context: Context) {
        this.mContext = context
        mChatHearContainer = WindowManagerContainer(context)
        mSpringSystem = SpringSystem.create()
    }

    override fun getChatHeadContainer(): IChatHeadContainer {
        return mChatHearContainer
    }

    //Thêm mới 1 chat head vào FRAME
    override fun addChatHead(isSticky: Boolean, animated: Boolean): ChatHead {
        var chatHead : ChatHead = ChatHead(this.mContext, this,mSpringSystem)
        chatHead.setOnClickListener {
            Toast.makeText(this.mContext, "Hello", Toast.LENGTH_SHORT).show()
        }
        var layoutParams : ViewGroup.LayoutParams = mChatHearContainer.createLayoutParams(200,200,Gravity.START or Gravity.TOP,0)
        mChatHearContainer.addView(chatHead, layoutParams)
        setDrawable(chatHead)
        return chatHead
    }

    fun setDrawable(chatHead : ChatHead) {
        chatHead.setImageResource(R.drawable.image_chat_head)
    }

    private class MotionCaptureView (context: Context): View (context)

}