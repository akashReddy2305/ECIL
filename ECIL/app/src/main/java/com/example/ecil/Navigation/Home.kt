package com.example.ecil.Navigation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.ecil.MainActivity
import com.example.ecil.R
import com.example.ecil.Table.BigTable
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

        var locval: String? =null
        logout=findViewById(R.id.logout)
//        loc=findViewById(R.id.location)
        search=findViewById(R.id.search)

        auth= FirebaseAuth.getInstance()
        var currentUser=auth.currentUser
        Log.i("USER HOME",auth.currentUser.toString())
//        Reference
        if(currentUser==null){
            startActivity(Intent(this, MainActivity::class.java))
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
            val radioButton1 = findViewById<RadioButton>(R.id.Single)
            val radioButton2 = findViewById<RadioButton>(R.id.Full)
            if (radioButton1.isChecked) {
                var intent = Intent(applicationContext, Home2::class.java)
                intent.putExtra("location",selectedSuggestion)
                startActivity(intent)
            }
            if (radioButton2.isChecked){
                var intent = Intent(applicationContext, BigTable::class.java)
                intent.putExtra("location",selectedSuggestion)
                startActivity(intent)
            }
        }
        logout.setOnClickListener{
            logginout()
        }
    }

    fun logginout(){
        auth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}