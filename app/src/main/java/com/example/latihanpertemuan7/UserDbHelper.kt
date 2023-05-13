package com.example.latihanpertemuan7

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Email

class UserDbHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "user.db"
        private val DATABASE_VERSION = 1

        private val SQL_CREATE_ENTRIES =
            " CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (" +
                    "${UserContract.UserEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${UserContract.UserEntry.COLUMN_EMAIL} VARCHAR(255)," +
                    "${UserContract.UserEntry.COLUMN_PASSWORD} VARCHAR(255))"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${UserContract.UserEntry.TABLE_NAME} "
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
    }

    fun insertData (user: User){
        val db = writableDatabase
        val sql = "INSERT INTO ${UserContract.UserEntry.TABLE_NAME} " +
                "( ${UserContract.UserEntry.COLUMN_ID}, " +
                "${UserContract.UserEntry.COLUMN_EMAIL}, " +
                "${UserContract.UserEntry.COLUMN_PASSWORD} ) " +
                "VALUES (null, '${user.email}', '${user.password}')"
        db.execSQL(sql)
        db.close()
    }

    fun getUser (email: String, password : String) : User? {
        val db = readableDatabase
        val sql = "SELECT * FROM ${UserContract.UserEntry.TABLE_NAME} WHERE ${UserContract.UserEntry.COLUMN_EMAIL} = '${email}' " +
                " AND ${UserContract.UserEntry.COLUMN_PASSWORD} = '${password}'"

        val cursor = db.rawQuery(sql,null)

        var user : User? =  null

        if (cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_ID))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_EMAIL))
            val password = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_PASSWORD))

            user = User(id, email, password)
        }
        cursor.close()
        db.close()
        return user
    }

}