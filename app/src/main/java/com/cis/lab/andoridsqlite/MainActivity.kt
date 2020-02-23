package com.cis.lab.andoridsqlite

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DB_NAME ="todo.db"
        buttonadd.setOnClickListener{
            val  dbHelper =DBHelper(this,DB_NAME,null,1)
            val newTask:Task = Task(editText.text.toString())
            dbHelper.addTask(newTask)

            Toast.makeText(this,
                editText.text.toString()+ "add to database",Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener {
            val  dbHelper =DBHelper(this,DB_NAME,null,1)
            val data:Cursor? = dbHelper.getAllTask()

            data!!.moveToFirst()
            displayText.text = ""


            do {
                val taskname = data.getString(data.getColumnIndex("taskname"))
                val id = data.getString(data.getColumnIndex("id"))
                displayText.append(id + " )" + taskname)
                displayText.append("\n")
                displayText.append(data.getString(data.getColumnIndex("taskname")))
            }while(data.moveToNext())
            data.close()
        }
        buttonDelete.setOnClickListener {
            val input = editText2.text.toString()
            val dbHelper =DBHelper(this,DB_NAME,null,1)
            val result =dbHelper.deleteTask(input.toInt())
        }
        buttonEdit.setOnClickListener {
            // 1,NewTask
            val input = editText2.text.toString()
            val datas = input.split("")
            val task =Task(datas[0].toInt(),datas[1])
            val dbHelper =DBHelper(this,DB_NAME,null,1)
            val result =dbHelper.updateTask(task)
        }
    }

}
