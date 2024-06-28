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

        //talabSocios
        const val TABLE_SOCIOS="socios"
        const val COLUMN_ID_SOCIOS="socioId"
        const val COLUMN_USUERNAME_SOCIO="socioName"
        const val COLUMN_LASTNAME_SOCIO="socioLastname"
        const val COLUMN_EMAIL_SOCIO="socioEmail"
        const val COLUMN_DNI_SOCIO="socioDNI"
        const val COLUMN_FECHA_SOCIO="socioFecha"
        const val COLUMN_ES_SOCIO="esSocio"

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

        //TablaSocios
        val createSociosTable = "CREATE TABLE $TABLE_SOCIOS (" +
                "$COLUMN_ID_SOCIOS INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USUERNAME_SOCIO TEXT, " +
                "$COLUMN_LASTNAME_SOCIO TEXT, " +
                "$COLUMN_EMAIL_SOCIO TEXT, " +
                "$COLUMN_DNI_SOCIO TEXT, " +
                "$COLUMN_FECHA_SOCIO TEXT," +
                "$COLUMN_ES_SOCIO INTEGER)"
        db.execSQL(createSociosTable)
    }





    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_SOCIOS")
            onCreate(db)
        }


    }
}
