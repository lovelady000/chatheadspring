package nndung.learningkotlin.chathead

import android.content.Context
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.widget.ImageView
import com.facebook.rebound.*




/**
 * Created by nndun on 7/26/2017.
 */
class ChatHead : ImageView, SpringListener {

    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop

    private var isDragging: Boolean = false
    private var mContext: Context
    private var mChatHeadManager: IChatHeadManager
    private var downX: Float = -1f
    private var downY: Float = -1f

    private var lastX: Float = 0f
    private var lastY: Float = 0f
    private var mSpringSystem: SpringSystem
    private var mX: Float = 0f
    private var mY: Float = 0f

    private lateinit var mState: State


    private var velocityTracker: VelocityTracker? = null
    private lateinit var xPositionSpring: Spring
    private lateinit var xPositisionListener: SpringListener
    private lateinit var yPositionSpring: Spring
    private lateinit var yPositisionListener: SpringListener
    private var radius: Float = 400f

    constructor(context: Context, chatHeadManager: IChatHeadManager, springSystem: SpringSystem) : super(context) {
        this.mContext = context
        this.mChatHeadManager = chatHeadManager
        springSystem.addListener(object : SpringSystemListener {
            override fun onBeforeIntegrate(springSystem: BaseSpringSystem?) {
            }

            override fun onAfterIntegrate(springSystem: BaseSpringSystem?) {
                mChatHeadManager.getArrangement().handleTouchUp(this@ChatHead)
            }

        })
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
        xPositionSpring.currentValue = 200.0

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
        yPositionSpring.currentValue = 200.0
        yPositionSpring.addListener(yPositisionListener)



    }

    fun getState(): State {
        return this.mState
    }

    fun setState(state: State) {
        this.mState = state
    }

    fun getIsDragging() : Boolean {
        return this.isDragging
    }

    fun getHorizontalSpring(): Spring {
        return xPositionSpring
    }

    fun getVerticalSpring(): Spring {
        return yPositionSpring
    }


    override fun onSpringUpdate(spring: Spring?) {
        mX = xPositionSpring.currentValue.toFloat()
        mY = yPositionSpring.currentValue.toFloat()
        invalidate()
    }

    override fun onSpringEndStateChange(spring: Spring?) {
    }

    override fun onSpringAtRest(spring: Spring?) {
    }

    override fun onSpringActivate(spring: Spring?) {
    }

    private val CONVERGING = SpringConfig.fromOrigamiTensionAndFriction(20.0, 3.0)
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        var touchX = event!!.rawX
        var touchY = event!!.rawY

        when (event!!.action) {
        //Bắt đầu tính toán
            MotionEvent.ACTION_DOWN -> {

                velocityTracker = VelocityTracker.obtain()
                velocityTracker!!.addMovement(event)

                setState(State.FREE)

                downX = touchX
                downY = touchY

                xPositionSpring.springConfig = CONVERGING
                yPositionSpring.springConfig = CONVERGING

                lastX = downX
                lastY = downY

//                if (downX > xPositionSpring.currentValue - radius && downX < xPositionSpring.currentValue + radius && yPositionSpring.currentValue > lastY - radius && yPositionSpring.currentValue < lastY + radius) {
//                    isDragging = true
//                }
                isDragging = true
            }
        //Sử lý even khi di chuyển
            MotionEvent.ACTION_MOVE -> run {
                if(!isDragging) {
                    return@run
                }
                setState(State.FREE)
                velocityTracker!!.addMovement(event)
                val offsetX = lastX - touchX
                val offsetY = lastY - touchY
                xPositionSpring.setCurrentValue(xPositionSpring.currentValue - offsetX).setAtRest()
                yPositionSpring.setCurrentValue(yPositionSpring.currentValue - offsetY).setAtRest()

                mChatHeadManager.getArrangement().handleTouchUp(this@ChatHead)
            }
        //Sử lý even khi bỏ tay khỏi màn hình hoặc bị hủy
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> run {
                if(!isDragging) {
                    return@run
                }

                isDragging = false
                velocityTracker!!.addMovement(event)
                velocityTracker!!.computeCurrentVelocity(1000)

                xPositionSpring.springConfig = SpringConfigsHolder.COASTING
                yPositionSpring.springConfig = SpringConfigsHolder.COASTING
                downX = 0f
                downY = 0f

                xPositionSpring.velocity = velocityTracker!!.xVelocity.toDouble()
                yPositionSpring.velocity = velocityTracker!!.yVelocity.toDouble()

            }
        }
        lastX = touchX
        lastY = touchY
        return true
    }

    enum class State {
        FREE, CAPTURED
    }
}