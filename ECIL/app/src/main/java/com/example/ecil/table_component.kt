package com.example.ecil

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class table_component(
    private val values: MutableList<UserData.Note>?) :
    RecyclerView.Adapter<table_component.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_component, parent, false)
        Log.i("onCreateViewHolder","Entered")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("onBindViewHolder","Entered")
        val item = values?.get(position)
        holder.column1.text = item?.col1.toString()
        holder.column2.text = item?.col2.toString()
        holder.column3.text = item?.col3.toString()
        holder.column4.text = item?.col4.toString()
        Log.i("onBindViewHolder", holder.column1.text as String)
        Log.i("onBindViewHolder",item?.col1.toString())
    }

    override fun getItemCount() = values?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val column1: TextView =view.findViewById(R.id.col1)
        val column2: TextView =view.findViewById(R.id.col2)
        val column3: TextView =view.findViewById(R.id.col3)
        val column4: TextView =view.findViewById(R.id.col4)
    }
}