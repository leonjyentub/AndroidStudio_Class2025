package ntub.imd.myapp0430

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnStart = findViewById<Button>(R.id.btnStart)
        val sbarBunny = findViewById<SeekBar>(R.id.sbarBunny)
        val sbarTurtle = findViewById<SeekBar>(R.id.sbarTurtle)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val MAX = 100
        var progressBunny = 0
        var progressTurtle = 0
        btnStart.setOnClickListener {
            btnStart.isEnabled = false
            sbarTurtle.progress  = 0
            sbarBunny.progress = 0
            progressBunny = 0
            progressTurtle = 0
            Thread{
                //txtResult.text = "比賽進行！"
                while(progressBunny < MAX && progressTurtle < MAX){
                    Thread.sleep(100)
                    progressTurtle += 1
                    sbarTurtle.progress = progressTurtle
                }
                Log.d("racing", "onClickUsingThread-turtle: end")
                runOnUiThread {
                    if(progressTurtle >= MAX && progressBunny < MAX){
                        txtResult.text = "烏龜贏了！"
                        btnStart.isEnabled = true
                    }
                }
            }.start()

            Thread {
                val sleepProb = arrayOf(true, true, false)
                while (progressBunny < MAX && progressTurtle < MAX) {
                    Thread.sleep(100)
                    if (sleepProb.random())
                        Thread.sleep(300) //假設免子會多睡一點
                    progressBunny += 3
                    sbarBunny.progress = progressBunny

                    Log.d("racing", "onClickUsingThread-bunny: end")
                    runOnUiThread {
                        if (progressBunny >= MAX && progressTurtle < MAX) {
                            txtResult.text = "免子贏了！"
                            btnStart.isEnabled = true
                        }
                    }
                }
            }.start()
        }

        val handler = Handler(Looper.getMainLooper()){ msg->
            Log.d("racing", "onClickUsingHandler: msg: ${msg.what}")
            if(progressBunny >= MAX && progressTurtle < MAX){
                txtResult.text = "免子贏了！"
            }else if(progressTurtle >= MAX && progressBunny < MAX){
                txtResult.text = "烏龜贏了！"
            }
            btnStart.isEnabled = true
            return@Handler true
        }

        val btnStart2 = findViewById<Button>(R.id.btnStart2)
        btnStart2.setOnClickListener{
            Thread{
                while(progressBunny < MAX && progressTurtle < MAX){
                    Thread.sleep(100)
                    progressTurtle += 1
                    sbarTurtle.progress = progressTurtle
                }
                handler.sendEmptyMessage(0)
                Log.d("racing", "onClickUsingThread-handler-turtle: end")
            }.start()

            Thread {
                val sleepProb = arrayOf(true, true, false)
                while (progressBunny < MAX && progressTurtle < MAX) {
                    Thread.sleep(100)
                    if (sleepProb.random())
                        Thread.sleep(300) //假設免子會多睡一點
                    progressBunny += 3
                    sbarBunny.progress = progressBunny
                }
                handler.sendEmptyMessage(0)
                Log.d("racing", "onClickUsingThread-handler-bunny: end")
            }.start()
        }
    }
}