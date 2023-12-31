package com.example.fundametalsubmission.berkas.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase?{
            if (INSTANCE == null){
                synchronized(UserDatabase){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"User database").build()
                }
            }
            return INSTANCE
        }
    }
    abstract  fun favoriteUserDao(): FavoriteUserDao
}