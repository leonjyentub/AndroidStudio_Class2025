package ntub.imd.myimagepicker

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat


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
        val imageView = findViewById<ImageView>(R.id.imageView)
        val btnPick = findViewById<Button>(R.id.btnPick)

        val imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == RESULT_OK){
                result.data?.data?.let { selectedImage->
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    contentResolver.query(selectedImage, filePathColumn, null, null, null)?.let{ cursor->
                        cursor.moveToFirst()
                        val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                        val filePath: String = cursor.getString(columnIndex)
                        Log.d("ImagePicker", "filePath: $filePath")
                        cursor.close()
                        val yourSelectedImage = BitmapFactory.decodeFile(filePath)
                        imageView.setImageBitmap(yourSelectedImage)
                    }
                }
            }
        }

        btnPick.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.READ_MEDIA_IMAGES)
                == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                imageLauncher.launch(intent)
            }else{
                Log.d("ImagePicker", "onCreate: 沒有權限~~~~~~~")
            }
        }
    }
}