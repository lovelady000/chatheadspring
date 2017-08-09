package nndung.learningkotlin.chathead

import com.facebook.rebound.SpringConfig



/**
 * Created by nndun on 8/2/2017.
 */
object SpringConfigsHolder {
    var NOT_DRAGGING = SpringConfig.fromOrigamiTensionAndFriction(190.0, 20.0)
    var CAPTURING = SpringConfig.fromOrigamiTensionAndFriction(100.0, 10.0)
    var DRAGGING = SpringConfig.fromOrigamiTensionAndFriction(0.0, 1.5)

    var COASTING = SpringConfig.fromOrigamiTensionAndFriction(0.0, 0.5)
}