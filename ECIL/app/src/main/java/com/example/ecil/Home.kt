package com.example.ecil

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var plantA:Button
    lateinit var plantB:Button
    lateinit var logout:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var btn: String? =null
        logout=findViewById(R.id.logout)
        auth= FirebaseAuth.getInstance()
        var currentUser=auth.currentUser
        Log.i("USER HOME",auth.currentUser.toString())
//        Reference
        if(currentUser==null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        plantA=findViewById(R.id.PLANTA)
        plantB=findViewById(R.id.PLANTB)
        plantA.setOnClickListener {
            btn="PLANTA"
            var intent = Intent(applicationContext,table::class.java)
            intent.putExtra("btn",btn)
            startActivity(intent)
        }
        plantB.setOnClickListener {
            btn="PLANTB"
            var intent = Intent(applicationContext,table::class.java)
            intent.putExtra("btn",btn)
            startActivity(intent)
        }
        logout.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}