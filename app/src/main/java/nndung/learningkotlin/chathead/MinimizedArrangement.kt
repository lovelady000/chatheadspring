package nndung.learningkotlin.chathead


/**
 * Created by nndun on 8/2/2017.
 */
class MinimizedArrangement : IChatHeadArrangement {
    private var mChatHeadManager: IChatHeadManager
    private var maxWidth: Int = 1440
    private var maxHeight: Int = 2000

    constructor(manager: IChatHeadManager) : super() {
        this.mChatHeadManager = manager
    }

    override fun handleTouchUp(activeChatHead: ChatHead, xVelocity: Int, yVelocity: Int, wasDragging: Boolean) {
        settleToClosest(activeChatHead, xVelocity, yVelocity)
    }

    fun settleToClosest(activeChatHead: ChatHead, xVelocity: Int, yVelocity: Int) {
        var horizontalSpring = activeChatHead.getHorizontalSpring()
        var verticalSpring = activeChatHead.getVerticalSpring()
        var x = xVelocity
        var y = yVelocity
        if (Math.abs(xVelocity) < ChatHeadUtils.dpToPx(mChatHeadManager.getDisplayMetrics(), 50)) {
            if (horizontalSpring.currentValue < (maxWidth - verticalSpring.currentValue)) {
                x = -1
            } else {
                y = 1
            }
            if (x < 0) {
                val newVelocity = (-horizontalSpring.currentValue * SpringConfigsHolder.DRAGGING.friction).toInt()
                if (x > newVelocity)
                    x = newVelocity

            } else if (y > 0) {
                val newVelocity = ((maxWidth - verticalSpring.currentValue - 200) * SpringConfigsHolder.DRAGGING.friction).toInt()
                if (newVelocity > y)
                    y = newVelocity
            }
        }
        if (Math.abs(x) <= 1) {
            // this is a hack. If both velocities are 0, onSprintUpdate is not called and the chat head remains whereever it is
            // so we give a a negligible velocity to artificially fire onSpringUpdate
            if (x < 0)
                x = -1
            else
                x = 1
        }

        if (y == 0)
            y = 1
        horizontalSpring.velocity = x.toDouble()
        verticalSpring.velocity = y.toDouble()

    }

}