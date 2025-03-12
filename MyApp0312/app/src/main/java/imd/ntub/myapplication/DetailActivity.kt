package imd.ntub.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", -1)
        Log.d("mytest", "onCreate: name=$name, age=$age")
    }
}