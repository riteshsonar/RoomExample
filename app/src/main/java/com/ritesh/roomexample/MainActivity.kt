package com.ritesh.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*database= Room.databaseBuilder(applicationContext,
                        ContactDatabase::class.java,"contactDB").build()*/

        database = ContactDatabase.getDatabase(this)

        /**** create a coroutine ***/
        GlobalScope.launch {

        database.contactDao().insertContact(Contact(0,"john","90989890", Date()))
        }
    }
    fun getData(view : View){
        database.contactDao().getContact().observe(this, Observer {
            Log.d("GetDataFromDatabase",it.toString())
        })
    }
}