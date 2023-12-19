package com.example.ecil

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class table_component(private val values: MutableList<UserData.Note>?) :
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
        var col4Value = item?.col4?:0
        col4Value = col4Value % 100
        if (col4Value < 50) {
            holder.column4.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm))
//            holder.column3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm))
//            holder.column2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm))
//            holder.column1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm))
        }
        else if (col4Value <= 60){
            holder.column4.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fail))
//            holder.column3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fail))
//            holder.column2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fail))
//            holder.column1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fail))
        }
        else {
            holder.column4.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm)) // Set default background color if needed
//            holder.column3.setBackgroundColor(Color.TRANSPARENT)
//            holder.column2.setBackgroundColor(Color.TRANSPARENT)
//            holder.column1.setBackgroundColor(Color.TRANSPARENT)
        }

    }

    override fun getItemCount() = values?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val column1: TextView =view.findViewById(R.id.col1)
        val column2: TextView =view.findViewById(R.id.col2)
        val column3: TextView =view.findViewById(R.id.col3)
        val column4: TextView =view.findViewById(R.id.col4)
    }
}
