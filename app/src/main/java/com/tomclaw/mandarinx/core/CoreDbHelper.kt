package com.tomclaw.mandarinx.core

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CoreDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
//        val SQL_CREATE_GUESTS_TABLE =
//            ("CREATE TABLE " + GuestEntry.TABLE_NAME.toString() + " ("
//                    + HotelContract.GuestEntry._ID.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + GuestEntry.COLUMN_NAME.toString() + " TEXT NOT NULL, "
//                    + GuestEntry.COLUMN_CITY.toString() + " TEXT NOT NULL, "
//                    + GuestEntry.COLUMN_GENDER.toString() + " INTEGER NOT NULL DEFAULT 3, "
//                    + GuestEntry.COLUMN_AGE.toString() + " INTEGER NOT NULL DEFAULT 0);")

//        db.execSQL(SQL_CREATE_GUESTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

}

private const val DB_NAME = "msg_db"
private const val DB_VERSION = 1

