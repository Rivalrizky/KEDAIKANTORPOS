package com.polinema.android.kotlin.kedaikantorpos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_pelanggan.*
import kotlinx.android.synthetic.main.activity_main.*

class HomePelanggan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_pelanggan)
        back_home_pelanggan.setOnClickListener{
            //            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btListPesan.setOnClickListener{
            //            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,TransaksiActivity::class.java)
            startActivity(intent)
        }
        btLogoutPel.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
