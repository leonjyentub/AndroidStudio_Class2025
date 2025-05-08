package ntub.imd.myapplicationnotification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val msg = arrayOf(
            "每一步都算數，重要的是持之以恆。",
            "不要害怕失敗，因為每一次的挫折都是成功的一部分。",
            "成功不是終點，而是每一次努力的積累。",
            "當你不再畏懼挑戰，成就就會隨之而來。"
        )
        Thread{
            for (i in (1..10)){ // (0..10)
                Thread.sleep(2000)
                Log.i("MyService", "onStartCommand: " + msg.random())
            }
        }.start()
        return START_STICKY
    }
    override fun onBind(intent: Intent): IBinder? = null
}