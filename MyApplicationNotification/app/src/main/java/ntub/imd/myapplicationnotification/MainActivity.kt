package ntub.imd.myapplicationnotification

import android.Manifest
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
        val CHANNEL_ID:String = "MyNotifyID"
        val channel_name:String = "我的頻道"
        var notificationId: Int = 1
        private fun createNotificationChannel() {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID, channel_name, NotificationManager.IMPORTANCE_DEFAULT)
                // Register the channel with the system
                val notificationManager: NotificationManager
                    = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted. Continue the action or workflow in your
            // app.
            with(NotificationManagerCompat.from(this)) {
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
                    //notify(notificationId, builder.build())
                }
            }
        } else {
            // Explain to the user that the feature is unavailable because the
            // feature requires a permission that the user has denied. At the
            // same time, respect the user's decision. Don't link to system
            // settings in an effort to convince the user to change their
            // decision.
        }
    }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            val btnGo = findViewById<Button>(R.id.btnGo)
            createNotificationChannel()
            btnGo.setOnClickListener {
                notificationId += 1
                var builder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.btn_radio)
                    .setContentTitle("簡單訊息")
                    .setContentText("不知道寫什麼所以在亂寫")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                with(NotificationManagerCompat.from(this)) {
                    // notificationId是一組你自己定的唯一整數就可以了，例如說：1
                    //數字一樣就被當成同一個訊息蓋過去，不一樣就會再列出一個新的
                    when{
                        ActivityCompat.checkSelfPermission(
                            this@MainActivity, Manifest.permission.POST_NOTIFICATIONS)
                                == PackageManager.PERMISSION_GRANTED ->{
                                    notify(notificationId, builder.build())
                                }
                        else -> {
                            // You can directly ask for the permission.
                            // The registered ActivityResultCallback gets the result of this request.
                            //requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    }
                }
            }
    }
}