package nndung.learningkotlin.chathead

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.ImageView
import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringListener
import com.facebook.rebound.SpringSystem



/**
 * Created by nndun on 7/26/2017.
 */
class ChatHead : ImageView, SpringListener {

    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop

    private var isDragging :Boolean = false
    private var mContext : Context
    private var mChatHeadManager : IChatHeadManager
    private var downX: Float = -1f
    private var downY: Float = -1f
    private var downTranslationX : Float = -1f
    private var downTranslationY : Float = -1f
    private var mSpringSystem : SpringSystem
    private lateinit var xPositionSpring : Spring
    private lateinit var xPositisionListener : SpringListener
    private lateinit var yPositionSpring : Spring
    private lateinit var yPositisionListener : SpringListener

    constructor(context: Context, chatHeadManager: IChatHeadManager, springSystem: SpringSystem) : super(context) {
        this.mContext = context
        this.mChatHeadManager = chatHeadManager
        this.mSpringSystem = springSystem
        initialize()
    }

    fun initialize() {
        xPositisionListener = object : SimpleSpringListener() {
            override fun onSpringUpdate(spring: Spring?) {
                super.onSpringUpdate(spring)
                mChatHeadManager.getChatHeadContainer().setViewX(this@ChatHead, spring!!.currentValue.toInt())
            }

            override fun onSpringAtRest(spring: Spring?) {
                super.onSpringAtRest(spring)
            }
        }
        xPositionSpring = mSpringSystem.createSpring()
        xPositionSpring.addListener(xPositisionListener)

        yPositisionListener = object : SimpleSpringListener() {
            override fun onSpringUpdate(spring: Spring?) {
                super.onSpringUpdate(spring)
                mChatHeadManager.getChatHeadContainer().setViewY(this@ChatHead, spring!!.currentValue.toInt())
            }

            override fun onSpringAtRest(spring: Spring?) {
                super.onSpringAtRest(spring)
            }
        }
        yPositionSpring = mSpringSystem.createSpring()
        yPositionSpring.addListener(yPositisionListener)


    }



    override fun onSpringUpdate(spring: Spring?) {

    }

    override fun onSpringEndStateChange(spring: Spring?) {
    }

    override fun onSpringAtRest(spring: Spring?) {
    }

    override fun onSpringActivate(spring: Spring?) {
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(xPositionSpring == null || yPositionSpring == null)
            return false
        var horizontalSpring = xPositionSpring
        var verticalSpring = yPositionSpring

        var action: Int = event!!.action
        var rawX: Float = event.rawX
        var rawY: Float = event.rawY
        var offsetX = rawX - downX
        var offsetY = rawY - downY

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                downX = rawX
                downY = rawY
                horizontalSpring.springConfig = SpringConfigsHolder.NOT_DRAGGING
                verticalSpring.springConfig = SpringConfigsHolder.NOT_DRAGGING
                downTranslationX = horizontalSpring.currentValue.toFloat()
                downTranslationY = horizontalSpring.currentValue.toFloat()

            }
            MotionEvent.ACTION_UP -> {
                isDragging = false
            }
            MotionEvent.ACTION_MOVE -> {

                if(Math.hypot(offsetX.toDouble(), offsetY.toDouble()) > touchSlop) {
                    isDragging = true
                }
                if(isDragging) {
                    horizontalSpring.currentValue = (downTranslationX  + offsetX).toDouble()
                    verticalSpring.currentValue = (downTranslationY + offsetY).toDouble()
                }
            }
        }
        Log.i("DUNGNN", "TOUCH")
        return true
    }
}