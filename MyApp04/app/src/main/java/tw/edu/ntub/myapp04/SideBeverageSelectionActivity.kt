package tw.edu.ntub.myapp04

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tw.edu.ntub.myapp04.databinding.ActivitySideBeverageBinding

class SideBeverageSelectionActivity : AppCompatActivity() {
    private val TAG = SideBeverageSelectionActivity::class.java.simpleName
    private lateinit var binding: ActivitySideBeverageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySideBeverageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnConfirmSide.setOnClickListener {
            var sideResult = ""
            if (binding.cbCheeseSticks.isChecked) {
                sideResult += "起司棒 "
            }
            if (binding.cbFries.isChecked) {
                sideResult += "薯條 "
            }
            val selectedBeverageId = binding.rgBeverageSize.checkedRadioButtonId
            if (selectedBeverageId != -1) {
                val selectedBeverage = findViewById<RadioButton>(selectedBeverageId)
                sideResult += "飲料尺寸: ${selectedBeverage.text}"
            }
            setResult(RESULT_OK, intent.putExtra("sideSelection", sideResult))
            finish()
        }
    }
}