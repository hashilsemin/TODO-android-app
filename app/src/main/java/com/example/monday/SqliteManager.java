package com.example.monday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqliteManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "newTodo";
    public static final int version = 1;
    public SqliteManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbQuery = "CREATE TABLE newTodo (id INTEGER PRIMARY KEY AUTOINCREMENT,task TEXT)";
        db.execSQL(dbQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList<Todomodel> getData(int n){
        ArrayList<Todomodel> items = new ArrayList<>();
        String queryString = "SELECT * FROM " + DATABASE_NAME+" WHERE id= "+n;
        SQLiteDatabase db = getReadableDatabase();

        //Cursor cursor = db.rawQuery(queryString,null);
//see above point 2 function
        Cursor cursor = db.rawQuery(queryString,null);
//    Cursor cursor = db.query("Items"
//            , null// columns - null will give all
//            , null// selection
//            , null// selection arguments
//            , null// groupBy
//            , null// having
//            , null);// no need or order by for now;
        if(cursor.moveToFirst()){
            do {
                int todoId = cursor.getInt(0);
                String todoname = cursor.getString(1);
                Todomodel newtodo = new Todomodel(todoname, todoId);
                items.add(newtodo);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items;



    }
    public void addItem(Todomodel todomodel) {
        System.out.println(todomodel + "rerereklklklrer");
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put("task",todomodel.getTodo());
        // name - column

        // description is column in items table, item.description has value for description
        db.insert(DATABASE_NAME, null, contentValues);//Items is table name
        db.close();
    }
    public void  deleteItem1(String n) {
        //System.out.println(todomodel + "rerereklklklrer");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        //
        String whereClause = "id=?";
        String whereArgs[] = {String.valueOf(n)};
        db.delete("newtodo", whereClause, whereArgs);
        db.close();


    }
public ArrayList<Todomodel> readAllItems() {
    ArrayList<Todomodel> items = new ArrayList<>();
    String queryString = "SELECT * FROM " + DATABASE_NAME;
    SQLiteDatabase db = getReadableDatabase();

    //Cursor cursor = db.rawQuery(queryString,null);
//see above point 2 function
    Cursor cursor = db.rawQuery(queryString,null);
//    Cursor cursor = db.query("Items"
//            , null// columns - null will give all
//            , null// selection
//            , null// selection arguments
//            , null// groupBy
//            , null// having
//            , null);// no need or order by for now;
    if(cursor.moveToFirst()){
        do {
            int todoId = cursor.getInt(0);
            String todoname = cursor.getString(1);
            Todomodel newtodo = new Todomodel(todoname, todoId);
            items.add(newtodo);
        }while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return items;
}
}
