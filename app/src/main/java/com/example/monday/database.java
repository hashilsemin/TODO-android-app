package com.example.monday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper {


    public static final String TO_DO = "TO_DO";

    public database(@Nullable Context context) {
        super(context, "todo.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + getCustomer_table() + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + TO_DO + " TEXT)";
        db.execSQL(createTableStatement);
    }


    public String getCustomer_table() {
        return "CUSTOMER_TABLE";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean add(Todomodel todomodel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TO_DO,todomodel.getTodo());

        long insert = db.insert(getCustomer_table(),null,cv);
        if (insert==-1){
            return false;

        }else{
            return true;

        }
//        SQLiteDatabase db = database1.getWritableDatabase();
//
//        ContentValues cv=new ContentValues();
//        cv.put(TO_DO,);
//        return true;
    }
    public void deleteItem(String view){
        List<Todomodel> returnList = new ArrayList<>();
        System.out.println("kklkfldkflkfldkfldkdkfldk");
        System.out.println("DELETE FROM "+getCustomer_table()+" WHERE id = "+view);

        //String deleteString = "DELETE FROM "+getCustomer_table()+" WHERE int = "+view;
//        SQLiteDatabase db = this.getWritableDatabase();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
          db.delete("CUSTOMER_TABLE","id=?",new String[]{view});
        } catch (NullPointerException nullPointerException) {
            Log.d("hello", nullPointerException.toString());
        }

        //Cursor cursor = db.rawQuery(deleteString,null);


    }
    public List<Todomodel> getevery(){
        List<Todomodel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + getCustomer_table();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                int todoId = cursor.getInt(0);
                String todoname = cursor.getString(1);
                Todomodel newtodo = new Todomodel(todoname, todoId);
                returnList.add(newtodo);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

}
