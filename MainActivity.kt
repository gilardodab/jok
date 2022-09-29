package putra.carel.pendaftaranvaksin

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var db : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBOpenHelper(this).writableDatabase

        btnHome.setOnClickListener(this)
        btnDaftar.setOnClickListener(this)
        btnMaps.setOnClickListener(this)
        btnKeluar.setOnClickListener(this)
    }

    fun getDbObject() : SQLiteDatabase{
        return db
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnHome -> {
                startActivity(Intent(this, HomeActivity::class.java))
                Toast.makeText(baseContext, "MENU HOME", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.btnDaftar -> {
                startActivity(Intent(this, DaftarActivity::class.java))
                Toast.makeText(baseContext, "MENU PENDAFTARAN", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.btnMaps -> {
                startActivity(Intent(this, MapsActivity::class.java))
                Toast.makeText(baseContext, "MENU LOKASI VAKSINASI", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.btnKeluar -> {
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(baseContext, "BERHASIL LOGOUT!!", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }
}