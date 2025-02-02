package tw.edu.ntub.myapp03

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnOK = findViewById<Button>(R.id.btnOK)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        //透過在MainActivity的launcher呼叫，在finish()後會把資料透過Intent回傳
        btnOK.setOnClickListener {
            setResult(RESULT_OK, intent.apply {
                putExtra("resultKey", edtPassword.text.toString())
            })
            finish()
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }
}