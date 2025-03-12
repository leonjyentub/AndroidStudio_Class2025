package imd.ntub.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtAge = findViewById<TextView>(R.id.txtAge)
        val btnGo = findViewById<Button>(R.id.btnGo)
        btnGo.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", txtName.text.toString())
            intent.putExtra("age", txtAge.text.toString().toInt())
            startActivity(intent)

            /*
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra("name", txtName.text.toString())
                putExtra("age", txtAge.text.toString().toInt())
            })
            */
        }
        val btn4 = findViewById<Button>(R.id.btnGo4)
        btn4.setOnClickListener {
            /*
            startActivity(Intent(Intent.ACTION_DIAL).apply {
                data = android.net.Uri.parse("tel:0987654321")
            })
             */
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = android.net.Uri.parse("https://www.ntub.edu.tw")
            })
        }
    }
}