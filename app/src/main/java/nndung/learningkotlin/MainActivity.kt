package nndung.learningkotlin

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nndung.learningkotlin.chathead.ChatHeadManager
import nndung.learningkotlin.chathead.interfaces.IChatHeadManager


class MainActivity : AppCompatActivity() {
    lateinit var chatHeadManager : IChatHeadManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requirePermisstion()
        setContentView(R.layout.activity_main)
//        val p = WindowManager.LayoutParams(
//                // Shrink the window to wrap the content rather than filling the screen
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                // Display it on top of other application windows, but only for the current user
//                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//                // Don't let it grab the input focus
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                // Make the underlying application window visible through any transparent parts
//                PixelFormat.TRANSLUCENT)
//        p.gravity = Gravity.TOP or Gravity.LEFT
//        p.x = 100
//        p.y = 100
//        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        var imgView : ChatHead = ChatHead(this)
//        imgView.setImageResource(R.drawable.image_chat_head)
//        val parms = LinearLayout.LayoutParams(100, 100)
//        imgView.setLayoutParams(parms)
//        //imgView.layoutParams.height = ChatHeadConfig.headHeight
//        //imgView.layoutParams.width = ChatHeadConfig.headWidth
//
//        //val myView : View = findViewById(R.layout.head_layout)
//        windowManager.addView(imgView, p)
        chatHeadManager = ChatHeadManager(this)
        chatHeadManager.addChatHead(true, true)
        button.setOnClickListener {
            chatHeadManager.onClick()
        }

    }

    fun requirePermisstion() {
        val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permission, 11)
            if (!Settings.canDrawOverlays(this@MainActivity)) {

                val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName))
                startActivityForResult(myIntent, 101)
            }
        }
    }



}
