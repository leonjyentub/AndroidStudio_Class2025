package ntub.imd.myapplication0409_contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Contact(val name:String, val phone:String)

class MyAdapter(private val data:ArrayList<Contact>): RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val ivCall = v.findViewById<ImageView>(R.id.imageView)
        val txtName = v.findViewById<TextView>(R.id.txtName)
        val txtPhone = v.findViewById<TextView>(R.id.txtPhone)
        val btnDelete = v.findViewById<ImageButton>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text  = data[position].name
        holder.txtPhone.text = data[position].phone
        holder.btnDelete.setOnClickListener {
            data.removeAt(position)
            this.notifyDataSetChanged()
        }
        holder.ivCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:" + data[position].phone))
            it.context.startActivity(intent)
        }
    }
}

class MainActivity : AppCompatActivity() {
    private val contacts = ArrayList<Contact>()
    private lateinit var myadapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtName = findViewById<TextView>(R.id.edtName)
        val edtPhone = findViewById<TextView>(R.id.edtPhone)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        myadapter = MyAdapter(contacts)
        recyclerView.adapter = myadapter
        btnSave.setOnClickListener {
            contacts.add(Contact(edtName.text.toString(), edtPhone.text.toString()))
            myadapter.notifyDataSetChanged()
        }
    }
}