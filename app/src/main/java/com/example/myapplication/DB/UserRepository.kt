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

    //añadirSocio
    fun addSocio(
        socioName: String,
        socioApellido: String,
        socioEmail: String,
        socioDni: String,
        socioFecha: String,
        esSocio: Boolean
    ): Long {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(UserDatabaseHelper.COLUMN_USUERNAME_SOCIO, socioName)
            put(UserDatabaseHelper.COLUMN_LASTNAME_SOCIO, socioApellido)
            put(UserDatabaseHelper.COLUMN_EMAIL_SOCIO, socioEmail)
            put(UserDatabaseHelper.COLUMN_DNI_SOCIO, socioDni)
            put(UserDatabaseHelper.COLUMN_FECHA_SOCIO, socioFecha)
            put(UserDatabaseHelper.COLUMN_ES_SOCIO, if (esSocio) 1 else 0)
        }
        Log.d("UserRepository", "Valores a insertar:$contentValues")
        return try {
            val result = db.insert(UserDatabaseHelper.TABLE_SOCIOS, null, contentValues)
            if (result == -1L) {
                Log.d("UserRepository", "Error al insertar socio: $socioName")
            }
            result
        } catch (e: Exception) {
            Log.e("UserRepository", "Excepción al insertar socio", e)
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

    //obtener Socios
    fun getAllSocios(): List<Socio> {
        val db = dbHelper.readableDatabase
        val sociosList = mutableListOf<Socio>()
        val cursor: Cursor = db.query(
            UserDatabaseHelper.TABLE_SOCIOS,
            null,
            null,
            null,
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            do {
                val socioId =
                    cursor.getLong(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ID_SOCIOS))
                val socioName =
                    cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_USUERNAME_SOCIO))
                val socioLastname =
                    cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_LASTNAME_SOCIO))
                val socioEmail =
                    cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_EMAIL_SOCIO))
                val socioFecha =
                    cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_FECHA_SOCIO))
                val socioDni =
                    cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_DNI_SOCIO))
                val esSocio =
                    cursor.getInt(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ES_SOCIO)) == 1

                val socio = Socio(
                    socioId,
                    socioName,
                    socioLastname,
                    socioEmail,
                    socioFecha,
                    socioDni,
                    esSocio
                )
                sociosList.add(socio)

            } while (cursor.moveToNext())
        }
        cursor.close()
        return sociosList
    }

    fun deleteSocio(socioId: Long): Boolean {
        val db = dbHelper.writableDatabase
        return try {
            val affectedRows = db.delete(
                UserDatabaseHelper.TABLE_SOCIOS,
                "${UserDatabaseHelper.COLUMN_ID_SOCIOS} = ?",
                arrayOf(socioId.toString())
            )
            affectedRows > 0
        } catch (e: Exception) {
            Log.e("UserRepository", "Excepción al eliminar socio", e)
            false
        }
    }

    fun updateSocio(socio: Socio): Boolean {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(UserDatabaseHelper.COLUMN_USUERNAME_SOCIO, socio.socioName)
            put(UserDatabaseHelper.COLUMN_LASTNAME_SOCIO, socio.socioLastname)
            put(UserDatabaseHelper.COLUMN_EMAIL_SOCIO, socio.socioEmail)
            put(UserDatabaseHelper.COLUMN_DNI_SOCIO, socio.socioDni)
            put(UserDatabaseHelper.COLUMN_ES_SOCIO, if (socio.esSocio) 1 else 0)
            put(UserDatabaseHelper.COLUMN_FECHA_SOCIO, socio.socioFecha)
        }
        return try {
            val affectedRows = db.update(
                UserDatabaseHelper.TABLE_SOCIOS,
                contentValues,
                "${UserDatabaseHelper.COLUMN_ID_SOCIOS} = ?",
                arrayOf(socio.socioId.toString())
            )
            affectedRows > 0
        } catch (e: Exception) {
            Log.e("UserRepository", "Excepción al actualizar socio", e)
            false
        }
    }

    data class Socio(
        val socioId: Long,
        val socioName: String,
        val socioLastname: String,
        val socioEmail: String,
        val socioDni: String,
        val socioFecha: String,
        val esSocio: Boolean
    )
}
