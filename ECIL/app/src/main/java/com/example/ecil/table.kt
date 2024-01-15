package com.example.ecil

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.graphql.GraphQLRequest
import com.amplifyframework.api.graphql.GraphQLResponse
import com.amplifyframework.api.graphql.PaginatedResult
import com.amplifyframework.api.graphql.model.ModelPagination
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.core.model.query.predicate.ContainsQueryOperator
import com.amplifyframework.core.model.query.predicate.QueryPredicate
import com.amplifyframework.datastore.generated.model.Todo
import com.amplifyframework.hub.HubEventFilters.or
import com.example.ecil.Table.single.table_component
import java.util.ArrayList

class table : AppCompatActivity() {
    private lateinit var list: RecyclerView
    private lateinit var ls: MutableList<UserData.Note>
    private lateinit var title:TextView
    private lateinit var tool:Toolbar
    private lateinit var swipe:SwipeRefreshLayout
    val arr: MutableList<MutableList<Any?>> = mutableListOf()
    lateinit var showToastRunnable: Runnable
    lateinit var device:String
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table)
        supportActionBar?.hide()
//        val adapter = list.adapter as table_component
        val locval = intent.getStringExtra("location")
        Log.i("Button", locval!!)
        val filter1 = Todo.PLANT.contains(locval!!)
        device = (filter1.operator() as ContainsQueryOperator).value() as String
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
            Toast.makeText(this, "Data Loading...", Toast.LENGTH_LONG).show()
            UserData._notes.value?.clear()
            arr.clear()
            list.adapter?.notifyDataSetChanged()
            Log.i("QueryPage","Started")
            queryPage(ModelQuery.list(Todo::class.java,filter1, ModelPagination.limit(300)))
//            Amplify.API.query(
//                ModelQuery.list(Todo::class.java,filter1),
//                { response ->
//                    val data = response.data
//                    Log.d("Type","data :${data.items::class.simpleName}")
//                    Log.d("Data",data.items.toString())
//
//                    response.data.forEach{todo ->
//                        val row:MutableList<Any?> = mutableListOf()
//                        row.add(todo.id)
//                        row.add(todo.plant)
//                        row.add(todo.pname)
//                        row.add(todo.col1)
//                        row.add(todo.col2)
//                        row.add(todo.col3)
//                        row.add(todo.cnt)
//                        arr.add(row)
//                    }
//                    arr.sortWith(compareBy{ it[6] as Int? })
//                    Log.i("Sorted Data",arr.toString())
//                    for(row in arr){
//                        UserData.addNote(UserData.Note.frm(row))
//                    }
////                    response.data.forEach { todo ->
//////                        Log.i("MyAmplifyApp", todo.plant)
//////                        Log.i("MyAmplifyApp", todo.col1.toString())
////                            UserData.addNote(UserData.Note.from(todo))
////                            Log.i("From ls",todo.col2.toString())
////                    }
//                    Log.i("Inside Query","data for each")
//                },
//                { Log.e("MyAmplifyApp", "Query failure", it) }
//            )
            swipe.isRefreshing=false
        }
        Log.i("Amplify","After Query")
        UserData.notes().observe(this,
            Observer<MutableList<UserData.Note>> { notes ->
                // let's create a RecyclerViewAdapter that manages the individual cells
//                Log.i("Observe","Inside")
                list.adapter = table_component(notes)
            })
    }
//    fun query(request: GraphQLRequest<PaginatedResult<Todo>>) {
//        Amplify.API.query(request,
//            { response ->
//                if (response.hasData()) {
//                    response.data.items.forEach { todo ->
//                        Log.d("MyAmplifyApp", todo.col2)
//                        UserData.addNote(UserData.Note.from(todo))
//                    }
//                    if (response.data.hasNextResult()) {
//                        Log.i("hasNext","triggered")
//                        query(response.data.requestForNextResult)
//                    }
//                }
//            },
//            { Log.e("MyAmplifyApp", "Query failed", it) }
//        )
//    }
    fun queryPage(request: GraphQLRequest<PaginatedResult<Todo>>){
        Amplify.API.query(request,
            {response->
                if(response.hasData()){
                    response.data.items.forEach{todo->
                        val row:MutableList<Any?> = mutableListOf()
                        Log.i("plant ",todo.plant)
                        Log.i("device",device)
                        if (todo.plant == device) {
                            row.add(todo.id)
                            row.add(todo.plant)
                            row.add(todo.pname)
                            row.add(todo.col1)
                            row.add(todo.col2)
                            row.add(todo.col3)
                            row.add(todo.cnt)
                            arr.add(row)
                            Log.i("ROW", row.toString())
                        }
                    }
                }
                if(response.data.hasNextResult()){
                    Log.i("hasNext","Triggered")
                    queryPage(response.data.requestForNextResult)
                }
                else{
                    arr.sortWith(compareBy{ it[6] as Int? })
                    Log.i("Sorted Data",arr.toString())
                    for(row in arr){
                        UserData.addNote(UserData.Note.frm(row))
                    }
                }
            },
            { Log.e("MyAmplifyApp", "Query failed", it)
            }
        )
    }
}