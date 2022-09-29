package putra.carel.pendaftaranvaksin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val user = "user"
    private val pass = "1234"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            if (txtUser.text.toString() == user && txtPass.text.toString() == pass){
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }else{
                Toast.makeText(applicationContext,"Username atau Password Salah!! Mohon dicek kembali", Toast.LENGTH_SHORT).show()
            }
        }

    }
}