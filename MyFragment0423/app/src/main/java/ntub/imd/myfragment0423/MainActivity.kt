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
import com.google.android.material.bottomnavigation.BottomNavigationView

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
    private lateinit var viewpager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewpager = findViewById<ViewPager2>(R.id.viewpager)
        viewpager.adapter = ViewPagerAdapter(this)
        viewpager.setPageTransformer(Pager2_CubeInDepthTransformer())
        //findViewById<Button>(R.id.btnFirst).setOnClickListener { viewpager.setCurrentItem(0, true) }
        //findViewById<Button>(R.id.btnSecond).setOnClickListener { viewpager.setCurrentItem(1, true) }
        //findViewById<Button>(R.id.btnThird).setOnClickListener { viewpager.setCurrentItem(2, true) }
        val btmNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btmNav.selectedItemId = when(position){
                    0 -> R.id.btnLeft
                    1 -> R.id.btnMiddle
                    2 -> R.id.btnRight
                    else -> R.id.btnLeft
                }
            }
        })
        btmNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btnLeft -> {
                    viewpager.currentItem = 0
                    true
                }
                R.id.btnMiddle -> {
                    viewpager.currentItem = 1
                    true
                }
                R.id.btnRight -> {
                    viewpager.currentItem = 2
                    true
                }
                else-> {
                    viewpager.currentItem = 0
                    true
                }
            }
        }
    }

    override fun onBackPressed() {
        if(viewpager.currentItem > 0){
            viewpager.currentItem = viewpager.currentItem -1
        }else{
            super.onBackPressed()
        }
    }
}