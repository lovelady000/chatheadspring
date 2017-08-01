package nndung.learningkotlin.chathead

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView

/**
 * Created by nndun on 7/26/2017.
 */
class ChatHead : ImageView {

    private var mContext : Context
    private var mChatHeadManager : IChatHeadManager
    constructor(context: Context, chatHeadManager: IChatHeadManager) : super(context) {
        this.mContext = context
        this.mChatHeadManager = chatHeadManager

    }


    private var downX: Float = -1f
    private var downY: Float = -1f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        var action: Int = event!!.action
//        var rawX: Float = event.rawX
//        var rawY: Float = event.rawY
//
//        var offsetX = rawX - downX;
//        var offsetY = rawY - downY;
//        when (action) {
//            MotionEvent.ACTION_DOWN -> {
//                downX = rawX
//                downY = rawY
//                Log.i("DUNGNN", "${this.layoutParams.height}")
//            }
//            MotionEvent.ACTION_UP -> {
//
//            }
//            MotionEvent.ACTION_MOVE -> {
//                val p = WindowManager.LayoutParams(
//                        // Shrink the window to wrap the content rather than filling the screen
//                        WindowManager.LayoutParams.WRAP_CONTENT,
//                        WindowManager.LayoutParams.WRAP_CONTENT,
//                        // Display it on top of other application windows, but only for the current user
//                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//                        // Don't let it grab the input focus
//                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                        // Make the underlying application window visible through any transparent parts
//                        PixelFormat.TRANSLUCENT)
//                p.gravity = Gravity.TOP or Gravity.LEFT
//                //p.x = event.rawX.toInt() - ChatHeadConfig.headWidth / 2
//                //p.y = event.rawY.toInt() - ChatHeadConfig.headHeight / 2
//
//
//                p.x = 500
//                p.y = 500
//
//                //Log.i("DUNGNN","X: ${event.x.toInt()} - Y: ${event.y.toInt()}" )
//                //Log.i("DUNGNN","rX: ${event.rawX.toInt()} - rY: ${event.rawY.toInt()}" )
//
//                val windowManager = this.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//                windowManager.updateViewLayout(this, p)
//            }
//        }
        Log.i("DUNGNN", "TOUCH")
        return true
    }
}