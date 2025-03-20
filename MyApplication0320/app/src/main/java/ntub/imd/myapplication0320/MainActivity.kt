package ntub.imd.myapplication0320

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

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
        val btnDialog = findViewById<Button>(R.id.btnDialog)
        btnDialog.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(R.layout.dialog_contact)
                .setPositiveButton("新增"){ dialog, which ->
                    val mdialog = dialog as AlertDialog
                    val name = mdialog.findViewById<TextView>(R.id.edtName)
                    val phone = mdialog.findViewById<TextView>(R.id.edtPhone)
                    Log.d("AlertDialog", "onCreate: name: ${name?.text}, phone: ${phone?.text}")
                }
                .show()
        }
        val listView = findViewById<ListView>(R.id.listView)
        val items = arrayListOf("項目1", "北商1", "android程式", "台北", "星期四","123","456", "567777")
        val arrayAdpter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = arrayAdpter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Snackbar
                .make(view, "你點了 ${items[i]} ", Snackbar.LENGTH_LONG)
                .setAction("好的"){

                }.show()
        }
    }
}