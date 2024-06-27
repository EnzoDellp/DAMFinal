package com.example.myapplication.DB

import android.content.ContentValues
import android.database.Cursor
import android.util.Log

class UserRepository(private val dbHelper: UserDatabaseHelper) {

    fun addUser(username: String, apellido: String, password: String, email: String): Long {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(UserDatabaseHelper.COLUMN_USERNAME, username)
            put(UserDatabaseHelper.COLUMN_LASTNAME, apellido)
            put(UserDatabaseHelper.COLUMN_PASSWORD, password)
            put(UserDatabaseHelper.COLUMN_EMAIL, email)
        }

        Log.d("UserRepository", "Insertando usuario: $contentValues")

        return try {
            val result = db.insert(UserDatabaseHelper.TABLE_USERS, null, contentValues)
            if (result == -1L) {
                Log.d("UserRepository", "Error al insertar usuario: $username")
            }
            result
        } catch (e: Exception) {
            Log.e("UserRepository", "Excepción al insertar usuario", e)
            -1L
        }
    }


    fun getUser(username: String, password: String): Cursor {
        val db = dbHelper.readableDatabase
        val selection =
            "${UserDatabaseHelper.COLUMN_USERNAME} = ? AND ${UserDatabaseHelper.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(username, password)
        return db.query(
            UserDatabaseHelper.TABLE_USERS,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
    }
}
