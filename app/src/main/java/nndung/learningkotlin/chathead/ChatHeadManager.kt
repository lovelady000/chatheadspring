package nndung.learningkotlin.chathead

import android.content.Context
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.rebound.SpringSystem
import nndung.learningkotlin.R
import nndung.learningkotlin.chathead.interfaces.IChatHeadArrangement
import nndung.learningkotlin.chathead.interfaces.IChatHeadContainer
import nndung.learningkotlin.chathead.interfaces.IChatHeadManager

/**
 * Created by nndun on 7/26/2017.
 */
class ChatHeadManager : IChatHeadManager {
    private var mChatHearContainer: IChatHeadContainer
    private var mSpringSystem: SpringSystem
    private var mDisplayMetrics: DisplayMetrics
    private var mChatHeadArrangement : IChatHeadArrangement

    private var mContext: Context

    private var listChatHead : ArrayList<ChatHead> = ArrayList()

    constructor(context: Context) {
        this.mContext = context
        mChatHearContainer = WindowManagerContainer(context)
        mSpringSystem = SpringSystem.create()
        mDisplayMetrics = mChatHearContainer.getDisplayMetrics()
        mChatHeadArrangement = MinimizedArrangement(this@ChatHeadManager)
    }

    override fun onClick() {
        var chatHead = listChatHead.get(0)
        chatHead.getHorizontalSpring().velocity = -4000.0
        chatHead.getVerticalSpring().velocity = 0.0
    }
    override fun getArrangement(): IChatHeadArrangement {
        return mChatHeadArrangement
    }

    override fun getChatHeadContainer(): IChatHeadContainer {
        return mChatHearContainer
    }

    //Thêm mới 1 chat head vào FRAME
    override fun addChatHead(isSticky: Boolean, animated: Boolean): ChatHead {
        var chatHead: ChatHead = ChatHead(this.mContext, this, mSpringSystem)
        chatHead.setOnClickListener {
            Toast.makeText(this.mContext, "Hello", Toast.LENGTH_SHORT).show()
        }
        var layoutParams: ViewGroup.LayoutParams = mChatHearContainer.createLayoutParams(200, 200, Gravity.START or Gravity.TOP, 0)
        mChatHearContainer.addView(chatHead, layoutParams)
        setDrawable(chatHead)
        listChatHead.add(chatHead)
        return chatHead
    }

    fun setDrawable(chatHead: ChatHead) {
        chatHead.setImageResource(R.drawable.image_chat_head)
    }

    override fun getDisplayMetrics(): DisplayMetrics {
        return mDisplayMetrics
    }


    private class MotionCaptureView(context: Context) : View(context)

}