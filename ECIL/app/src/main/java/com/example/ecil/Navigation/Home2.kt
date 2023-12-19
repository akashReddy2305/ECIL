package com.example.ecil.Navigation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Todo
import com.example.ecil.R
import com.example.ecil.Table.single.table

class Home2 : AppCompatActivity() {
    lateinit var loc: TextView
    lateinit var search: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
//        logo
        supportActionBar?.hide()

//        tool=findViewById(R.id.toolbar)
//        setActionBar(tool)

//        loc=findViewById(R.id.location)
        search=findViewById(R.id.search)

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

//        val adapter = list.adapter as table_component
        val locval = intent.getStringExtra("location")
        Log.i("Button", locval!!)
        val filter1 = Todo.PLANT.contains(locval!!)

        // Provide a list of suggestions (you can also load from an array resource)
        var selectedSuggestion:String = ""
        val suggestions = mutableSetOf<String>()
        Amplify.API.query(
            ModelQuery.list(Todo::class.java,filter1),
            { response ->
                val sortedData = response.data.sortedBy { it.plant }
                sortedData.forEach { todo ->
                    suggestions.add(todo.pname)
                    Log.i("Test ADI here",todo.pname)
                }
                Log.i("Test ADI 2",suggestions.toList().toString())
                runOnUiThread {
                    val adapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_dropdown_item_1line,
                        suggestions.toList()
                    )
                    Log.i("selected", adapter.toString())
                    autoCompleteTextView.setAdapter(adapter)
                }
            },
            { Log.e("MyAmplifyApp", "Query failure", it) }
        )

        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            selectedSuggestion = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Selected: $selectedSuggestion", Toast.LENGTH_SHORT).show()
        }
        search.setOnClickListener {
//            locval=loc.text.toString().trim()
            var intent = Intent(applicationContext, table::class.java)
            intent.putExtra("location",locval)
            intent.putExtra("point",selectedSuggestion)
            startActivity(intent)
        }
    }
}