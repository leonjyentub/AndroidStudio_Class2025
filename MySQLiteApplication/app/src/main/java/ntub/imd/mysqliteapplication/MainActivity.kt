package ntub.imd.mysqliteapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Update
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtScore: EditText
    private lateinit var btnSave: Button
    private lateinit var btnSelct: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtScore = findViewById(R.id.edtScore)
        btnSave = findViewById(R.id.btnSave)
        btnSelct = findViewById(R.id.btnSelect)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDel)
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
                    Snackbar.make(it, "資料已儲存", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK") { }
                        .show()
                }
            }
        }
        var user: User? = null
        btnSelct.setOnClickListener {
            val name = edtName.text.toString()
            if (name.isBlank()) {
                Toast.makeText(this, "請輸入有效的名字和分數", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 在背景執行緒中執行資料庫操作
            CoroutineScope(Dispatchers.IO).launch {
                user = database.userDao().getUserByName(name)
                if(user != null){
                    CoroutineScope(Dispatchers.Main).launch {
                        edtScore.setText(user?.score.toString())
                        Snackbar.make(it, "資料已取得！！", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK") { }
                            .show()
                    }
                }else{
                    CoroutineScope(Dispatchers.Main).launch {
                        Snackbar.make(it, "沒有找到資料", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK") { }
                            .show()
                    }
                }
            }
        }

        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val rows = user?.let { _user ->
                    val name = edtName.text.toString()
                    val score = edtScore.text.toString().toIntOrNull()
                    _user.name = name
                    _user.score = score
                    _user.mTime = LocalDateTime.now()
                    database.userDao().update(_user)
                }
                var msg = if(rows == 1) "更新成功！" else "更新失敗！"
                CoroutineScope(Dispatchers.Main).launch {
                    Snackbar.make(it, msg , Snackbar.LENGTH_LONG)
                            .setAction("OK") { }
                            .show()
                }
            }
        }
        btnDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                user?.let { _user -> database.userDao().delete(_user) }
                CoroutineScope(Dispatchers.Main).launch {
                    Snackbar.make(it, "刪除成功！", Snackbar.LENGTH_LONG)
                        .setAction("OK") { }
                        .show()
                }
            }
        }
    }
}
