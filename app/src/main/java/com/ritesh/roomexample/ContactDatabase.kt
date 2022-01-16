package com.ritesh.roomexample

import android.content.Context
import androidx.room.*

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object{
        @Volatile
        private var INSTANCE: ContactDatabase? =null

        fun getDatabase(context: Context): ContactDatabase{
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, ContactDatabase::class.java,
                        "contactDB"
                    ).build()
                }
            }

            return  INSTANCE!!
        }
    }

}