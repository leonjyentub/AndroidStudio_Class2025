package tw.edu.ntub.myapp03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
        val btnGo = findViewById<Button>(R.id.btnGo)
        btnGo.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
        val btnGo2 = findViewById<Button>(R.id.btnGo2)
        val txtinput1 = findViewById<EditText>(R.id.txtInput1)
        val txtnumber1 = findViewById<EditText>(R.id.txtNumber1)
        btnGo2.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra("name", txtinput1.text.toString()) // 轉換成字串
                putExtra("age", txtnumber1.text.toString().toInt()) // 轉換成整數
            })
        }
        val btnGo3 = findViewById<Button>(R.id.btnGo3)
        btnGo3.setOnClickListener {
            val bundle = Bundle().apply {
                putString("name", txtinput1.text.toString())
                putInt("age", txtnumber1.text.toString().toInt())
            }
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtras(bundle)
            })
        }
        val btnGo4 = findViewById<Button>(R.id.btnGo4)
        btnGo4.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL).apply {
                data = android.net.Uri.parse("tel:0912345678")
            }) //切換到撥電話
        }
        val btnGo5 = findViewById<Button>(R.id.btnGo5)
        btnGo5.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = android.net.Uri.parse("https://www.ntub.edu.tw")
            }) //切換到browser
        }
        val btnGo6 = findViewById<Button>(R.id.btnGo6)
        btnGo6.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO).apply {
                data = android.net.Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("leonjye@ntub.edu.tw", "test@ntub.edu.tw"))
                putExtra(Intent.EXTRA_SUBJECT, "哈囉你好沒有就只是想測試一下")
                putExtra(Intent.EXTRA_TEXT, "這是郵件內容垃圾垃圾垃圾垃圾垃圾垃圾")
            }) //切換到寄email
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getStringExtra("resultKey")
                //result.data就是接受的intent
                Log.d("MainActivity", "=> 收到回傳: $data")
            }
        }

        val btnGo7 = findViewById<Button>(R.id.btnGo7)
        btnGo7.setOnClickListener {
            launcher.launch(Intent(this, Main2Activity::class.java))
        }
        val imageView = findViewById<ImageView>(R.id.imageView)
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                Log.d("MainActivity", "onCreate: 圖片路徑: $uri")
                imageView.setImageURI(uri) // 顯示選取的圖片
            }
        }
        val btnImage = findViewById<Button>(R.id.btnImage)
        btnImage.setOnClickListener {
            getContent.launch("image/*") // 啟動圖片選擇器
        }

        imageView.setImageResource(R.drawable.image01)
    }
}