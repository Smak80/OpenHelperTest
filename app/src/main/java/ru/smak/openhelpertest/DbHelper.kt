package ru.smak.openhelpertest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(
    context,
    "my_db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let{ b ->
            b.beginTransaction()
            b.execSQL("CREATE TABLE stud (" +
                    "id integer primary key, " +
                    "name text, " +
                    "age integer," +
                    "`group` text)")
            b.setTransactionSuccessful()
            b.endTransaction()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addStudent(name: String, age: Int){
        val cv = ContentValues(2)
        cv.put("name", name)
        cv.put("age", age)
        with (writableDatabase){
            beginTransaction()
            insert("stud", null, cv)
            setTransactionSuccessful()
            endTransaction()
        }
    }

    fun getSudents(): List<String>{
        val lst = mutableListOf<String>()
        with(readableDatabase){
            query("stud", arrayOf("name", "age"), null, null, null, null, "name").let{
                while (it.moveToNext()){
                    val n = it.getString(0)
                    val a = it.getInt(1)
                    lst.add("$n: $a")
                }
                it.close()
            }
        }
        return lst
    }


}