package com.cis.lab.andoridsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int

) : SQLiteOpenHelper(context, name, factory, version) {

    val DATABASE_NAME = "todo.db"
    val DATABASE_VERSION = 1
    val TABLE_NAME = "task"
    val COLUMN_ID = "id"
    val COLUMN_TASKNAME = "taskname"

    fun addTask(newTask:Task){
        val values = ContentValues();
        values.put(COLUMN_TASKNAME,newTask.taskname)

        val db = this.writableDatabase
        db.insert(TABLE_NAME ,null,values)
        db.close()
    }
    fun getAllTask(): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null)
        0    }
    fun updateTask(data:Task):Int{
        val db = this.writableDatabase
        val contenValue = ContentValues()
        val result = db.update(TABLE_NAME,contenValue,
            COLUMN_ID+"="+data.id,null)
        db.close()
        return result
    }
    fun deleteTask(id:Int):Int{
        val db = this.writableDatabase
        val result=db.delete(TABLE_NAME,COLUMN_ID+"="+id,null)
        db.close()
        return result
    }


    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMALY KEY," + COLUMN_TASKNAME + " TEXT)"
        db.execSQL(CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val UPGRADE_TABLE ="DROP TABLE IF EXITS" + TABLE_NAME
        db.execSQL(UPGRADE_TABLE)
        onCreate(db)
    }
}


