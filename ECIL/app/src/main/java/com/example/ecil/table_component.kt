package com.example.ecil.Table.single

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ecil.R
import com.example.ecil.UserData

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
        holder.column4.text = item?.col3.toString()
        Log.i("onBindViewHolder col1",item?.col1.toString())
        Log.i("onBindViewHolder col2",item?.col2.toString())
        Log.i("onBindViewHolder col3",item?.col3.toString())
        Log.i("onBindViewHolder col4",item?.col4.toString())
        var col1Value = item?.col1.toString()
        var col3Value = item?.col3.toString().toDouble()
        if (col1Value[1] == 'N'){
            holder.column2.text = "ANALOG"
            holder.column3.text = item?.col3.toString() + ".00"
            if (col3Value < 50.0) {
                holder.column4.text = "GOOD"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.alarm))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context,
//                    R.color.alarm
//                ))
//            holder.column3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm))
//            holder.column2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm))
//            holder.column1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm))
            }
            else if (col3Value <= 60){
                holder.column4.text = "BAD"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.fail))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.fail))
//            holder.column3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fail))
//            holder.column2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fail))
//            holder.column1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fail))
            }
            else {
                holder.column4.text = "GOOD"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.alarm))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.alarm))
                // Set default background color if needed
//            holder.column3.setBackgroundColor(Color.TRANSPARENT)
//            holder.column2.setBackgroundColor(Color.TRANSPARENT)
//            holder.column1.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        else if(col1Value[1] == 'I'){
            holder.column2.text = "DIGITAL"
            holder.column4.text = "GOOD"
            if(col3Value == 0.00){
                holder.column3.text = "RUN"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.alarm))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.alarm))
            }
            else{
                holder.column3.text = "STOP"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.fail))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.fail))
            }
        }
        else{
            holder.column2.text = "D.DIGITAL"
            holder.column4.text = "GOOD"
            if(col3Value == 0.00){
                holder.column3.text = "MOVING"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.yell))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.yell))
            }
            else if(col3Value == 1.0){
                holder.column3.text = "OPEN"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.alarm))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.alarm))
            }
            else if(col3Value == 2.0){
                holder.column3.text = "CLOSE"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.fail))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.fail))
            }
            else{
                holder.column3.text = "FAILED"
                holder.column3.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.teal_200))
//                holder.column4.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.teal_200))
            }
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
