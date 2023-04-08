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
    data class Note(val col1:String,val col2:Int,val col3:Int,val col4:Int){
        companion object {
            fun from(noteData : Todo) : Note {
//                Log.i("From","Inside From")
                val result = Note(noteData.pname, noteData.col1, noteData.col2,noteData.col3)
//                Log.i("from",result.toString())
                return result
            }
        }
    }
}