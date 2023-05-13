package com.example.latihanpertemuan7

import android.provider.BaseColumns

object UserContract {
    object UserEntry : BaseColumns{
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
    }
}