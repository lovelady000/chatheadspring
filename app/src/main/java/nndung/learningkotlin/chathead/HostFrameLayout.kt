package nndung.learningkotlin.chathead

import android.content.Context
import android.util.Log
import android.widget.FrameLayout

/**
 * Created by nndun on 7/26/2017.
 */
class HostFrameLayout : FrameLayout {
    constructor(context : Context) : super(context)

//    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
//        var handled =  super.dispatchKeyEvent(event)
//        if(!handled) {
//            if(event!!.action == KeyEvent.ACTION_UP && event.keyCode == KeyEvent.KEYCODE_BACK) {
//                // Sử lý even
//                return true
//            }
//        }
//        return handled
//    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.i("DUNGNN", "TOUCH DUNGNN")
    }
}