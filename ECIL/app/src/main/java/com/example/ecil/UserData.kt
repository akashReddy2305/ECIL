package com.example.ecil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amplifyframework.datastore.generated.model.Todo

object UserData {
    private const val TAG = "UserData"
    public val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())
    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.postValue(this.value)
    }
    fun notes() : LiveData<MutableList<Note>> = _notes
    fun addNote(n : Note) {
//        Log.i("User Data","addNote")
        val notes = _notes.value
        if (notes != null) {
            notes.add(n)
            _notes.notifyObserver()
        } else {
            Log.e(TAG, "addNote : note collection is null !!")
        }
    }
    data class Note(val col1:String,val col2:String?,val col3:String?,val col4:Int?){
        companion object {
            fun from(noteData : Todo) : Note {
//                Log.i("From","Inside From")
                val res=convertToDecimal(noteData.col2)
                Log.i("result",res)
                val result = Note(noteData.pname, noteData.col1.toString(), res,noteData.col3)
//                Log.i("from",result.toString())
                return result
            }
        }
    }
    fun convertToDecimal(input: String): String {
        val isNegative = input.startsWith("-")
        val absoluteValue = if (isNegative) input.substring(1) else input

        return if (absoluteValue.length >= 2) {
            val decimalPart = absoluteValue.substring(absoluteValue.length - 2)
            val integerPart = absoluteValue.substring(0, absoluteValue.length - 2)
            val result = "$integerPart.$decimalPart"
            if (isNegative) "-$result" else result
        } else {
            val paddedValue = absoluteValue.padStart(2, '0')
            val result = "0.$paddedValue"
            if (isNegative) "-$result" else result
        }
    }
}