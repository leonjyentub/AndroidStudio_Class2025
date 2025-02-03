package tw.edu.ntub.myapp04

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tw.edu.ntub.myapp04.databinding.ActivityPizzaselectionBinding

class PizzaselectionActivity : AppCompatActivity() {
    private val TAG = PizzaselectionActivity::class.java.simpleName
    private lateinit var binding: ActivityPizzaselectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPizzaselectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnPizzaConfirm.setOnClickListener {
            val selectedPizza = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.rbtn1 -> "金帶財松葉蟹"
                R.id.rbtn2 -> "鐵板雙牛比薩"
                R.id.rbtn3 -> "泰式檸檬椒麻豬"
                R.id.rbtn4 -> "韓式泡菜燒肉"
                else -> "未選擇"
            }
            setResult(RESULT_OK, intent.putExtra("selectedPizza", selectedPizza))
            finish()
        }
    }
}