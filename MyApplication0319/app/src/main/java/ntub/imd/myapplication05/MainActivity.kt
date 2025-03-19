package ntub.imd.myapplication05

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

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
        val btnToast = findViewById<Button>(R.id.btnToast)
        btnToast.setOnClickListener {
            val toast = Toast.makeText(this, "你成功了！！", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
        val btnSnackbar = findViewById<Button>(R.id.btnSnackbar)
        btnSnackbar.setOnClickListener { it->
            Snackbar.make(it, "我是Snackbar啊~~~~", Snackbar.LENGTH_INDEFINITE)
                .setAction("瞭解！！"){ }
                .show()
        }
        val btnDialog = findViewById<Button>(R.id.btnDialog)
        btnDialog.setOnClickListener { it->
            // 這邊是 import androidx.appcompat.app.AlertDialog
            AlertDialog.Builder(this)
                .setTitle("你好我是標題")
                .setMessage("這邊呈現內容")
                .setPositiveButton("確認(放右邊)"){ dialog, which ->

                }.setNegativeButton("我也想當確認啊"){ dialog, which ->

                }
                .show()
        }
        val btnListDialog = findViewById<Button>(R.id.btnListDialog)
        var items = arrayOf("選項1", "北商", "北科", "選項n", "亂寫就對了", "最後一個")
        btnListDialog.setOnClickListener { it->
            AlertDialog.Builder(this)
                .setTitle("你好我是標題")
                .setItems(items){ dialog, i->
                    Snackbar.make(it, "你剛剛按了 ${items[i]} 這個選項", Snackbar.LENGTH_LONG)
                        .show()
                }
                .setPositiveButton("取消關掉"){ dialog, which ->

                }
                .show()
        }
        val btnSingleItem = findViewById<Button>(R.id.btnSingleItem)
        btnSingleItem.setOnClickListener { it->
            var pos = 0
            AlertDialog.Builder(this)
                .setTitle("你好我是標題")
                .setSingleChoiceItems(items, pos){ dialog, i->
                    pos = i
                    Snackbar.make(it, "你剛剛按了 ${items[i]} 這個選項", Snackbar.LENGTH_LONG)
                        .show()
                }
                .setPositiveButton("關掉"){ dialog, which ->

                }
                .show()
        }
    }
}