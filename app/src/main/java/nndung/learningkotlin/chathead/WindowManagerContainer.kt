package nndung.learningkotlin.chathead

import android.content.Context
import android.graphics.PixelFormat
import android.view.*
import android.view.WindowManager.LayoutParams.*
import nndung.learningkotlin.R


/**
 * Created by nndun on 7/26/2017.
 */
class WindowManagerContainer : FrameChatHeadContainer {



    private var mWindowManager: WindowManager
    private lateinit var mMotionCaptureView: View

    constructor(context: Context) : super(context) {
        mWindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        onInitialized()
    }

    fun onInitialized() {
        this.mMotionCaptureView = MotionCaptureView(getContext())
        this.mMotionCaptureView.setBackgroundResource(R.color.overlay_color)
        this.mMotionCaptureView.setOnTouchListener(MotionCapturingTouchListener())
    }

    override fun setViewX(view: View, xPosition: Int) {
        super.setViewX(view, xPosition)
        if ( view is ChatHead) {
            setContainerX(mMotionCaptureView,xPosition)
            setContainerWidth(mMotionCaptureView,view.measuredWidth)
        }

    }

    override fun setViewY(view: View, yPosition: Int) {
        super.setViewY(view, yPosition)
        if ( view is ChatHead) {
            setContainerY(mMotionCaptureView,yPosition)
            setContainerHeight(mMotionCaptureView,view.measuredHeight)
        }
    }
    override fun addContainer(container: View, focusable: Boolean) {
        var containerParams = createContainerLayoutParams(focusable)
        addContainer(container, containerParams)
    }
    fun getWindowManager(): WindowManager {
        if (mWindowManager == null) {
            mWindowManager =  getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        }
        return mWindowManager
    }


    fun addContainer(container: View, containerLayoutParams: WindowManager.LayoutParams) {
        container.layoutParams = containerLayoutParams
        getWindowManager().addView(container, containerLayoutParams)
    }

    override fun addView(view: View, layoutParams: ViewGroup.LayoutParams) {
        super.addView(view, layoutParams)
        addContainer(this.mMotionCaptureView, true)
        var motionCaptureParams = getOrCreateLayoutParamsForContainer(this.mMotionCaptureView)
        motionCaptureParams.width = 200
        motionCaptureParams.height = 200
        this.mWindowManager.updateViewLayout(this.mMotionCaptureView, motionCaptureParams)
    }

    fun setContainerWidth(view : View, width : Int) {
        var layoutParams = getOrCreateLayoutParamsForContainer(view)
        layoutParams.width = width
        getWindowManager().updateViewLayout(view,layoutParams)
    }
    fun setContainerHeight(view : View, height : Int) {
        var layoutParams = getOrCreateLayoutParamsForContainer(view)
        layoutParams.height = height
        getWindowManager().updateViewLayout(view,layoutParams)
    }

    fun createContainerLayoutParams(focusable: Boolean): WindowManager.LayoutParams {
        val focusableFlag: Int
        if (focusable) {
            focusableFlag = FLAG_NOT_TOUCH_MODAL
        } else {
            focusableFlag = FLAG_NOT_TOUCHABLE or FLAG_NOT_FOCUSABLE
        }
        val layoutParams = WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                focusableFlag,
                PixelFormat.TRANSLUCENT)
        layoutParams.x = 0
        layoutParams.y = 0
        layoutParams.gravity = Gravity.TOP or Gravity.START
        return layoutParams
    }

    fun getOrCreateLayoutParamsForContainer(view : View) : WindowManager.LayoutParams{
        var layoutParams = view.layoutParams as WindowManager.LayoutParams
        if(layoutParams == null) {
            layoutParams = createContainerLayoutParams(false)
            view.layoutParams = layoutParams
        }
        return layoutParams
    }


    fun getContainerX(view : View) : Int {
        return getOrCreateLayoutParamsForContainer(view).x
    }
    fun setContainerX(view : View, xPosition: Int) {
        var layoutParams = getOrCreateLayoutParamsForContainer(view)
        layoutParams.x = xPosition
        getWindowManager().updateViewLayout(view, layoutParams)
    }
    fun getContainerY(view : View) : Int {
        return getOrCreateLayoutParamsForContainer(view).y
    }
    fun setContainerY(view : View, yPosition: Int) {
        var layoutParams = getOrCreateLayoutParamsForContainer(view)
        layoutParams.y = yPosition
        getWindowManager().updateViewLayout(view, layoutParams)
    }


    private class MotionCaptureView(context : Context) : View(context)

    inner class MotionCapturingTouchListener : View.OnTouchListener {
        override fun onTouch(view: View?, event: MotionEvent?): Boolean {
            event!!.offsetLocation(getContainerX(view!!).toFloat(), getContainerY(view!!).toFloat())
            var frameLayout = getFrameLayout()
            if(frameLayout != null) { //Gọi sự kiện touch cho view phía dưới
                return frameLayout.dispatchTouchEvent(event)
            } else {
                return false
            }
        }

    }

}