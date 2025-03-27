package ntub.imd.myapplication0326

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val gridView = findViewById<GridView>(R.id.gridView)
        val items3 = ArrayList<Item>()
        items3.add(Item(R.drawable.photo_1, "leon"))
        items3.add(Item(R.drawable.photo_2, "NTUB"))
        items3.add(Item(R.drawable.photo_3, "不知道是誰"))
        items3.add(Item(R.drawable.photo_4, "阿宅"))
        items3.add(Item(R.drawable.photo_5, "AI美女？"))
        items3.add(Item(R.drawable.photo_2, "酷耶"))
        items3.add(Item(R.drawable.photo_4, "宅宅裝"))
        items3.add(Item(R.drawable.photo_1, "吉它~~~"))

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
        gridView.adapter = myAdapter
    }
}