package com.example.ecil

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Todo
import java.util.ArrayList

class table : AppCompatActivity() {
    private lateinit var list: RecyclerView
    private lateinit var ls: MutableList<UserData.Note>
    private lateinit var title:TextView
    private lateinit var swipe:SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table)
//        val adapter = list.adapter as table_component
        val btn = intent.getStringExtra("btn")
        Log.i("Button", btn!!)
        list=findViewById(R.id.recylerList)
        ls = ArrayList<UserData.Note>()
        swipe=findViewById(R.id.swipe)
//        title=findViewById(R.id.title)
//        title.text=btn
        Log.i("Amplify","Before Query")
        UserData._notes.value?.clear()
        list.adapter?.notifyDataSetChanged()
        swipe.setOnRefreshListener {
            UserData._notes.value?.clear()
            list.adapter?.notifyDataSetChanged()
            Amplify.API.query(
                ModelQuery.list(Todo::class.java, Todo.PLANT.contains(btn!!)),
                { response ->
                    response.data.forEach { todo ->
//                        Log.i("MyAmplifyApp", todo.plant)
//                        Log.i("MyAmplifyApp", todo.col1.toString())
                        UserData.addNote(UserData.Note.from(todo))
//                        Log.i("From ls",todo.pname)
                    }
                    Log.i("Inside Query","data for each")
                },
                { Log.e("MyAmplifyApp", "Query failure", it) }
            )
            swipe.isRefreshing=false
        }
        Log.i("Amplify","After Query")
        UserData.notes().observe(this,
            androidx.lifecycle.Observer<MutableList<UserData.Note>> { notes ->
                // let's create a RecyclerViewAdapter that manages the individual cells
//                Log.i("Observe","Inside")
                list.adapter = table_component(notes)
            })
    }
}