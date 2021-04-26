package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY ,name VARCHAR,age INT)");

            //database.execSQL("INSERT INTO musicians (name,age)VALUES('James',50)");//oluşturma
            //database.execSQL("INSERT INTO musicians (name,age)VALUES('Lars',60)");
            //database.execSQL("INSERT INTO musicians (name,age)VALUES('Kirk',55)");

            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");//guncelleme
            //database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");

            database.execSQL("DELETE FROM musicians WHERE id =2");//silme

            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name = 'james'",null);//ismi james olnı getir
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%' ",null);//k ile başlayanı getir
            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);

            int NameIx = cursor.getColumnIndex("name");
            int AgeIx = cursor.getColumnIndex("age");
            int idIx  = cursor.getColumnIndex("id");

            while(cursor.moveToNext()){
                System.out.println("Name: "+cursor.getString(NameIx));
                System.out.println("Age: "+cursor.getInt(AgeIx));
                System.out.println("Id: "+cursor.getInt(idIx));
            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}