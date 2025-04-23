package ntub.imd.myfragment0423

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class ViewPagerAdapter(activity: MainActivity): FragmentStateAdapter(activity){
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment = when (position){
        0 -> FirstFragment.newInstance()
        1 -> SecondFragment.newInstance()
        2 -> ThirdFragment.newInstance()
        else -> FirstFragment.newInstance()
    }
}
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
        val viewpapger = findViewById<ViewPager2>(R.id.viewpager)
        viewpapger.adapter = ViewPagerAdapter(this)
        findViewById<Button>(R.id.btnFirst).setOnClickListener { viewpapger.setCurrentItem(0, true) }
        findViewById<Button>(R.id.btnSecond).setOnClickListener { viewpapger.setCurrentItem(1, true) }
        findViewById<Button>(R.id.btnThird).setOnClickListener { viewpapger.setCurrentItem(2, true) }
    }
}