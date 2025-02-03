package tw.edu.ntub.myapp04

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tw.edu.ntub.myapp04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtNumber.setOnFocusChangeListener { v, hasFocus ->
            // 獲得焦點時：檢查 EditText 的內容，如果正好是預設的「0」，則將內容清空，方便使用者直接輸入新數字。
            // 失去焦點時：檢查若使用者沒有輸入任何內容，則恢復預設值「0」，這樣可以避免欄位出現空白
            if (hasFocus) {
                if (binding.txtNumber.text.toString() == "0") {
                    binding.txtNumber.text = Editable.Factory.getInstance().newEditable("")
                }
            }else {
                if (binding.txtNumber.text.toString().trim().isEmpty()) {
                    binding.txtNumber.text = Editable.Factory.getInstance().newEditable("0")
                }
            }
        }
        val pizzaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                binding.txtPizza.text = it.data?.getStringExtra("selectedPizza")
            }
        }
        binding.btnChangePizza.setOnClickListener {
            pizzaLauncher.launch(Intent(this, PizzaselectionActivity::class.java))
        }
        val sideResultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                binding.txtSide.text = it.data?.getStringExtra("sideSelection")
            }
        }
        binding.btnChangeSide.setOnClickListener {
            sideResultLauncher.launch(Intent(this, SideBeverageSelectionActivity::class.java))

        }
    }
}