package com.example.sqlite

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.w3c.dom.Text

//SQLiteOpenHelper
/* onCreate()- only once whenever class is loaded   Create table,database, insert record
* onUpgrade()- only when you change your version     alter*/
//SQLiteDatabase- instance of this class is used to perform INSERT, UPDATE & DELETE
/* var db: SQLiteDatabase
* db.insert
* db.update
* db.delete
* db.execSQL("CREATE TABLE ...")
* db.rawQuery("SELECT * FROM TABLE WHERE COL=? AND COL2=?",arrayOf("Rajkot","Gujarat"))*/

//Cursor-Current set of records
/*var rs:Cursor
or var rs=db.rawQuery("Select empno, ename,sal from emp where sal>5000",null)
* rs.geString(0)
* rs.moveToNext()*/
class MainActivity : AppCompatActivity() {
    lateinit var edName:EditText
    lateinit var edMeaning:EditText
    lateinit var first:Button
    lateinit var next:Button
    lateinit var previous:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edName=findViewById(R.id.edName)
        edMeaning=findViewById(R.id.edMeaning)
        first=findViewById(R.id.btFirst)
        next=findViewById(R.id.btNext)
        previous=findViewById(R.id.btPrevious)
        var helper=MyHelper(applicationContext)
        var db:SQLiteDatabase=helper.readableDatabase
        var rs: Cursor =db.rawQuery("select * from ACTABLE ",null)
        first.setOnClickListener{
            if(rs.moveToFirst()) {
                edName.setText(rs.getString(1))
                edMeaning.setText(rs.getString(2))
            }else{
                Toast.makeText(this,"You have no record",Toast.LENGTH_LONG).show()
            }
        }
        next.setOnClickListener{
            if(rs.moveToNext()){
                edName.setText(rs.getString(1))
                edMeaning.setText(rs.getString(2))
            }else if(rs.moveToFirst()) {
                edName.setText(rs.getString(1))
                edMeaning.setText(rs.getString(2))
            }else{
                Toast.makeText(this,"You have no record",Toast.LENGTH_LONG).show()
            }
        }
        previous.setOnClickListener{
            if(rs.moveToPrevious()){
                edName.setText(rs.getString(1))
                edMeaning.setText(rs.getString(2))
            }else if(rs.moveToLast()) {
                edName.setText(rs.getString(1))
                edMeaning.setText(rs.getString(2))
            }else{
                Toast.makeText(this,"You have no record",Toast.LENGTH_LONG).show()
            }
        }
    }
}