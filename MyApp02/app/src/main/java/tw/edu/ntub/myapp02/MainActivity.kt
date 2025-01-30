package tw.edu.ntub.myapp02

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
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

        val btnGuess = findViewById<Button>(R.id.btnGuess)
        val txtScissors: TextView = findViewById(R.id.txtScissors)
        val txtRock = findViewById<TextView>(R.id.txtRock)
        val txtPaper: TextView = findViewById<TextView>(R.id.txtPaper)
        val txtResult: TextView = findViewById<TextView>(R.id.txtResult)
        val rbtnScissors = findViewById<RadioButton>(R.id.rbtnScissors)
        val rbtnRock = findViewById<RadioButton>(R.id.rbtnRock)
        val rbtnPaper = findViewById<RadioButton>(R.id.rbtnPaper)
        
        btnGuess.setOnClickListener {
            txtScissors.setBackgroundResource(0)
            txtRock.setBackgroundResource(0)
            txtPaper.setBackgroundResource(0)
            val random = (1..3).random()
            when (random) {
                1 -> {
                    txtScissors.setBackgroundResource(R.drawable.bg_guess)
                    txtRock.setBackgroundResource(0)
                    txtPaper.setBackgroundResource(0)
                }
                2 -> {
                    txtScissors.setBackgroundResource(0)
                    txtRock.setBackgroundResource(R.drawable.bg_guess)
                    txtPaper.setBackgroundResource(0)
                }
                3 -> {
                    txtScissors.setBackgroundResource(0)
                    txtRock.setBackgroundResource(0)
                    txtPaper.setBackgroundResource(R.drawable.bg_guess)
                }
            }
            // 判斷結果
            // 1:剪刀 2:石頭 3:布
            if (rbtnScissors.isChecked) {
                 when (random) {
                    1 -> txtResult.text = "平手"
                    2 -> txtResult.text = "你輸了"
                    3 -> txtResult.text = "你贏了"
                }
            } else if (rbtnRock.isChecked) {
                when (random) {
                    1 -> txtResult.text = "你贏了"
                    2 -> txtResult.text = "平手"
                    3 -> txtResult.text = "你輸了"
                }
            } else if (rbtnPaper.isChecked) {
                when (random) {
                    1 -> txtResult.text = "你輸了"
                    2 -> txtResult.text = "你贏了"
                    3 -> txtResult.text = "平手"
                }
            } else {
                txtResult.text = "請選擇剪刀、石頭或布"
            }
        }
    }
}