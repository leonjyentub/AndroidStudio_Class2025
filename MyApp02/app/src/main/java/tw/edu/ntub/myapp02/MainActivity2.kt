package tw.edu.ntub.myapp02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tw.edu.ntub.myapp02.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGuess.setOnClickListener {
            binding.txtScissors.setBackgroundResource(0)
            binding.txtRock.setBackgroundResource(0)
            binding.txtPaper.setBackgroundResource(0)
            val random = (1..3).random()
            when (random) {
                1 -> {
                    binding.txtScissors.setBackgroundResource(R.drawable.bg_guess)
                    binding.txtRock.setBackgroundResource(0)
                    binding.txtPaper.setBackgroundResource(0)
                }
                2 -> {
                    binding.txtScissors.setBackgroundResource(0)
                    binding.txtRock.setBackgroundResource(R.drawable.bg_guess)
                    binding.txtPaper.setBackgroundResource(0)
                }
                3 -> {
                    binding.txtScissors.setBackgroundResource(0)
                    binding.txtRock.setBackgroundResource(0)
                    binding.txtPaper.setBackgroundResource(R.drawable.bg_guess)
                }
            }
            // 判斷結果
            // 1:剪刀 2:石頭 3:布
            if (binding.rbtnScissors.isChecked) {
                when (random) {
                    1 -> binding.txtResult.text = "平手"
                    2 -> binding.txtResult.text = "你輸了"
                    3 -> binding.txtResult.text = "你贏了"
                }
            } else if (binding.rbtnRock.isChecked) {
                when (random) {
                    1 -> binding.txtResult.text = "你贏了"
                    2 -> binding.txtResult.text = "平手"
                    3 -> binding.txtResult.text = "你輸了"
                }
            } else if (binding.rbtnPaper.isChecked) {
                when (random) {
                    1 -> binding.txtResult.text = "你輸了"
                    2 -> binding.txtResult.text = "你贏了"
                    3 -> binding.txtResult.text = "平手"
                }
            } else {
                binding.txtResult.text = "請選擇剪刀、石頭或布"
            }
        }
    }
}