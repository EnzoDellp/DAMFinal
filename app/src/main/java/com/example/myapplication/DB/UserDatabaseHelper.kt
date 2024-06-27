package com.example.myapplication.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "users.db"
        const val DATABASE_VERSION = 3
        const val TABLE_USERS = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_LASTNAME = "lastname"
        const val COLUMN_EMAIL = "email"


    }

    //Crear Tablas
    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_PASSWORD TEXT, " +
                "$COLUMN_LASTNAME TEXT, " +
                "$COLUMN_EMAIL TEXT)"
        db.execSQL(createUsersTable)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
            onCreate(db)
        }


    }
}
