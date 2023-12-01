package com.example.ecil

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var loc:TextView
    lateinit var search:Button
    lateinit var logout:Button
    lateinit var tool:Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        logo
        supportActionBar?.hide()

//        tool=findViewById(R.id.toolbar)
//        setActionBar(tool)
        var locval: String? =null
        logout=findViewById(R.id.logout)
//        loc=findViewById(R.id.location)
        search=findViewById(R.id.search)

        auth= FirebaseAuth.getInstance()
        var currentUser=auth.currentUser
        Log.i("USER HOME",auth.currentUser.toString())
//        Reference
        if(currentUser==null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        // Provide a list of suggestions (you can also load from an array resource)
        val suggestions = listOf("DEVICE1", "DEVICE2", "DEVICE3", "DEVICE4", "DEVICE5",
            "DEVICE6","DEVICE6","DEVICE7","DEVICE8","DEVICE9","DEVICE10")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
        var selectedSuggestion:String = ""
        Log.i("selected",adapter.toString())
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            selectedSuggestion = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Selected: $selectedSuggestion", Toast.LENGTH_SHORT).show()
        }
        search.setOnClickListener {
//            locval=loc.text.toString().trim()
            var intent = Intent(applicationContext,table::class.java)
            intent.putExtra("location",selectedSuggestion)
            startActivity(intent)
        }
//        plantA=findViewById(R.id.PLANTA)
//        plantB=findViewById(R.id.PLANTB)
//        plantA.setOnClickListener {
//            btn="PLANTA"
//            var intent = Intent(applicationContext,table::class.java)
//            intent.putExtra("btn",btn)
//            startActivity(intent)
//        }
//        plantB.setOnClickListener {
//            btn="PLANTB"
//            var intent = Intent(applicationContext,table::class.java)
//            intent.putExtra("btn",btn)
//            startActivity(intent)
//        }
        logout.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}