package com.example.ecil.Table.single

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Todo
import com.example.ecil.R
import com.example.ecil.UserData
import java.util.ArrayList


class table : AppCompatActivity() {
    private lateinit var list: RecyclerView
    private lateinit var ls: MutableList<UserData.Note>
    private lateinit var title:TextView
    private lateinit var tool:Toolbar
    private lateinit var swipe:SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table)
        supportActionBar?.hide()
//        val adapter = list.adapter as table_component
        val locval = intent.getStringExtra("location")
        Log.i("Button", locval!!)
        val locval2 = intent.getStringExtra("point")
        Log.i("Button", locval2!!)
        val filter1 = Todo.PLANT.contains(locval!!)
        val filter2 = Todo.PNAME.contains(locval2!!)
        val combinedFilter = filter1.and(filter2)

        list=findViewById(R.id.recylerList)
        ls = ArrayList<UserData.Note>()
        swipe=findViewById(R.id.swipe)
        title=findViewById(R.id.head)
        tool=findViewById(R.id.toolbar)

//        title=findViewById(R.id.title)
        if (locval == ""){
            title.text="ECIL"
        }
        else {
            title.text = locval
        }
        Log.i("Amplify","Before Query")
        UserData._notes.value?.clear()
        list.adapter?.notifyDataSetChanged()
        swipe.setOnRefreshListener {
            UserData._notes.value?.clear()
            list.adapter?.notifyDataSetChanged()
            Amplify.API.query(
                ModelQuery.list(Todo::class.java,combinedFilter),
                { response ->
                    val sortedData = response.data.sortedBy { it.plant }
                    sortedData.forEach { todo ->
//                        Log.i("MyAmplifyApp", todo.plant)
//                        Log.i("MyAmplifyApp", todo.col1.toString())
                        UserData.addNote(UserData.Note.from(todo))
                            Log.i("From ls",todo.col2.toString())
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
//                 let's create a RecyclerViewAdapter that manages the individual cells
//                Log.i("Observe","Inside")
                list.adapter = table_component(notes)
            })
    }
}