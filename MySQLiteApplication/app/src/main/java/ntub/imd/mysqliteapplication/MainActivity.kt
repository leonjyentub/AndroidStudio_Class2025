package ntub.imd.mysqliteapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtScore: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtScore = findViewById(R.id.edtScore)
        btnSave = findViewById(R.id.btnSave)

        database = AppDatabase.getInstance(this)

        btnSave.setOnClickListener {
            val name = edtName.text.toString()
            val score = edtScore.text.toString().toIntOrNull()

            if (name.isBlank() || score == null) {
                Toast.makeText(this, "請輸入有效的名字和分數", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 在背景執行緒中執行資料庫操作
            CoroutineScope(Dispatchers.IO).launch {
                val user = User(name = name, score = score, mTime = LocalDateTime.now())
                database.userDao().insert(user)

                // 回到主執行緒顯示訊息
                CoroutineScope(Dispatchers.Main).launch {
                    Snackbar.make(it, "資料已儲存", Snackbar.LENGTH_SHORT)
                        .setAction("OK") { }
                        .show()
                    //Toast.makeText(this@MainActivity, "資料已儲存", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
