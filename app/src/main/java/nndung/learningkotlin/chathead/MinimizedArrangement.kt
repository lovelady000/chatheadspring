package nndung.learningkotlin.chathead

import android.util.Log
import com.facebook.rebound.Spring


/**
 * Created by nndun on 8/2/2017.
 */
class MinimizedArrangement : IChatHeadArrangement {
    private var mChatHeadManager: IChatHeadManager
    private var maxWidth: Int = 1440
    private var maxHeight: Int = 2464

    constructor(manager: IChatHeadManager) : super() {
        this.mChatHeadManager = manager
    }
    override fun handleTouchUp(activeChatHead: ChatHead, xVelocity: Int, yVelocity: Int, wasDragging: Boolean) {
        settleToClosest(activeChatHead, xVelocity, yVelocity)
    }

    fun settleToClosest(activeChatHead: ChatHead, xVelocity: Int, yVelocity: Int) {
//        var horizontalSpring = activeChatHead.getHorizontalSpring()
//        var verticalSpring = activeChatHead.getVerticalSpring()
//
//        var x = xVelocity
//        var y = yVelocity
//        if (activeChatHead.getState() == ChatHead.State.FREE) {
//            if (Math.abs(xVelocity) < ChatHeadUtils.dpToPx(mChatHeadManager.getDisplayMetrics(), 50)) {
//                if (horizontalSpring.currentValue < (maxWidth - horizontalSpring.currentValue)) {
//                    x = -1
//                } else {
//                    x = 1
//                }
//                if (x < 0) {
//                    val newVelocity = (-horizontalSpring.currentValue * SpringConfigsHolder.DRAGGING.friction).toInt()
//                    if (x > newVelocity)
//                        x = newVelocity
//
//                } else if (x > 0) {
//                    val newVelocity = ((maxWidth - horizontalSpring.currentValue - 200) * SpringConfigsHolder.DRAGGING.friction).toInt()
//                    if (newVelocity > x)
//                        x = newVelocity
//                }
//            }
//        }
//        if (Math.abs(x) <= 1) {
//            // this is a hack. If both velocities are 0, onSprintUpdate is not called and the chat head remains whereever it is
//            // so we give a a negligible velocity to artificially fire onSpringUpdate
//            if (x < 0)
//                x = -1
//            else
//                x = 1
//        }
//
//        if (y == 0)
//            y = 1
//        horizontalSpring.velocity = x.toDouble()
//        verticalSpring.velocity = y.toDouble()

    }

    override fun handleTouchUp(activeChatHead: ChatHead) {
        var xSpring = activeChatHead.getHorizontalSpring()
        var ySpring = activeChatHead.getVerticalSpring()
        var x:Float = xSpring.currentValue.toFloat()
        var y :Float = ySpring.currentValue.toFloat()
        var radius = 200f
        Log.i("DUNGNN","X:" + x + " - Y:" + y + "xSpring" + xSpring.getCurrentValue() + ": ySpring:" + ySpring.getCurrentValue() );
        if( x > maxWidth) {
            xSpring.currentValue = xSpring.currentValue - (x + radius -maxWidth)
            checkVelocity(xSpring, ySpring)
        }

        if( x < 0) {
            xSpring.currentValue = xSpring.currentValue - (x)
            checkVelocity(xSpring, ySpring)
        }

        if( y + radius > maxHeight) {
            ySpring.currentValue = ySpring.currentValue - (y + radius - maxHeight)
            checkVelocity(xSpring, ySpring)
        }

        if( y < 0) {
            ySpring.currentValue = ySpring.currentValue - (y)
            checkVelocity(xSpring, ySpring)
        }
        if( Math.abs(xSpring.velocity )< 900 && Math.abs(ySpring.velocity) < 900 && !activeChatHead.getIsDragging()) {
            checkVelocity(xSpring, ySpring)
        }

    }
    fun checkVelocity(xSpring : Spring, ySpring: Spring) {
        if(xSpring.velocity != 0.0 ) {
            xSpring.velocity = 0.0
        }
        if(ySpring.velocity != 0.0 ) {
            ySpring.velocity = 0.0
        }
    }



}