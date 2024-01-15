package com.example.ecil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amplifyframework.datastore.generated.model.Todo

object UserData {
    private const val TAG = "UserData"
    public val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())
    public val _notesTemp = MutableLiveData<MutableList<Note>>(mutableListOf())
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
    fun addNoteTemp(n : Note) {
//        Log.i("User Data","addNote")
        val notes = _notesTemp.value
        if (notes != null) {
            notes.add(n)
            _notesTemp.notifyObserver()
        } else {
            Log.e(TAG, "addNote : note collection is null !!")
        }
    }
    data class Note(val col1:Any?, val col2:Any?,val col3:Any?,val col4:Any?){
        companion object{
            fun from(noteData: Todo) : Note{
                val res = noteData.col2 + ".00"
                val result = Note(noteData.pname,noteData.col1,res,noteData.col3)
                return result
            }
            fun frm(sdata: MutableList<Any?>) : Note{
                val res1 = sdata[4].toString()
                val result1 = Note(sdata.get(2),sdata.get(3),res1,sdata.get(5))
                return  result1
            }

        }
    }
    fun convertToDecimal(input: String): String {
        val isNegative = input.startsWith("-")
        val absoluteValue = if (isNegative) input.substring(1) else input

        return if (absoluteValue.isNotEmpty()) {
            val result = if (absoluteValue.length >= 2) {
                val decimalPart = absoluteValue.substring(absoluteValue.length - 2)
                val integerPart = absoluteValue.substring(0, absoluteValue.length - 2)
                "$integerPart.$decimalPart"
            } else {
                val paddedValue = absoluteValue.padStart(2, '0')
                "0.$paddedValue"
            }

            if (isNegative) "-$result" else result
        } else {
            "0.00"  // Default value when the input is empty
        }
    }

}