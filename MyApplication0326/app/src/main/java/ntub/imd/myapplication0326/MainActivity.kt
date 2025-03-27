package ntub.imd.myapplication0326

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
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
        val listView = findViewById<ListView>(R.id.listView)
        val items =
            arrayOf(
                arrayOf("項目1", "項目1內容"),
                arrayOf("北商1", "北商1內容"),
                arrayOf("android程式", "android程式寫不完的程式"),
                arrayOf("台北", "天氣不錯啊"),
                arrayOf("星期四", "今天星期三啊")
            )
        //val arrayAdpter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
        val arrayAdpter2 = object: ArrayAdapter<Array<String>>(
            this, android.R.layout.simple_list_item_2, android.R.id.text2, items){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                view.findViewById<TextView>(android.R.id.text1).text = items[position][0]
                view.findViewById<TextView>(android.R.id.text2).text = items[position][1]
                return view
            }
        }
        val items3 = ArrayList<Item>()
        /*
        items3.add(Item(R.drawable.photo_1, "leon"))
        items3.add(Item(R.drawable.photo_2, "NTUB"))
        items3.add(Item(R.drawable.photo_3, "不知道是誰"))
        items3.add(Item(R.drawable.photo_4, "阿宅"))
        items3.add(Item(R.drawable.photo_5, "AI美女？"))
        items3.add(Item(R.drawable.photo_2, "酷耶"))
        items3.add(Item(R.drawable.photo_4, "宅宅裝"))
        items3.add(Item(R.drawable.photo_1, "吉它~~~"))
         */
        class MyAdapter(context: Context, val layoutId: Int, val data: ArrayList<Item>)
            : ArrayAdapter<Item>(context, layoutId, data){
            override fun getCount() = data.size
            override fun getItem(position: Int) = data[position]
            override fun getItemId(position: Int) = 0L
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = View.inflate(parent.context, layoutId, null)
                val item = getItem(position)?:return view
                view.findViewById<ImageView>(R.id.imgPhoto).setImageResource(item.photo)
                view.findViewById<TextView>(R.id.txtName).text = item.name
                return view
            } }

        val myAdapter = MyAdapter(this, R.layout.adapter_item, items3)
        listView.adapter = myAdapter

        btnDialog.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(R.layout.dialog_contact)
                .setPositiveButton("新增"){ dialog, which ->
                    val mdialog = dialog as AlertDialog
                    val name = mdialog.findViewById<TextView>(R.id.edtName)
                    val phone = mdialog.findViewById<TextView>(R.id.edtPhone)
                    Log.d("AlertDialog", "onCreate: name: ${name?.text}, phone: ${phone?.text}")
                    val img = arrayOf(
                        R.drawable.photo_1,
                        R.drawable.photo_2,
                        R.drawable.photo_3,
                        R.drawable.photo_4,
                        R.drawable.photo_5).random()
                    items3.add(Item(img, name!!.text.toString()))
                    Snackbar.make(it, "新增資料 ${name!!.text.toString()} 成功！", Snackbar.LENGTH_LONG).show()
                    myAdapter.notifyDataSetChanged()
                }
                .show()
        }
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Snackbar
                .make(view, "你點了 ${items[i][0]} ", Snackbar.LENGTH_LONG)
                .setAction("好的"){

                }.show()
        }
    }
}