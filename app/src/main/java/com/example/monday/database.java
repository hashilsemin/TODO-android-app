package com.example.monday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteOpenHelper;
public class database extends SQLiteOpenHelper {


    public static final String TO_DO = "TO_DO";
    public Context context;
    private boolean databaseHelper ;

    public database(@Nullable Context context) {
        super(context, "todo.db", null, 1);
        String databasePath = context.getDatabasePath("TO_DO").getPath().toString();
        Log.d("path","hin"+databasePath);
        //this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + getCustomer_table() + " (Id INTEGER PRIMARY KEY AUTOINCREMENT, " + TO_DO + " TEXT)";
        db.execSQL(createTableStatement);

    }


    public String getCustomer_table() {
        return "CUSTOMER_TABLE";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//    public boolean deleteIt(String view) {
//        SQLiteDatabase db = getWritableDatabase();
//        String whereClause = "id=?";
//        String whereArgs[] = {item.id.toString()};
//        db.delete("Items", whereClause, whereArgs);
//        return true;
//    }
    public boolean add(Todomodel todomodel){
        System.out.println(todomodel + "rerereklklklrer");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TO_DO,todomodel.getTodo());
        long insert = db.insert(getCustomer_table(),null,cv);
        db.close();

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
    public boolean deleteItem(Todomodel todomodel, Context context){
        int numb1 = todomodel.getId();

        System.out.println(todomodel + "rerererer" +numb1);
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            int numb = todomodel.getId();
            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(numb)};
            db.delete("CUSTOMER_TABLE", whereClause, whereArgs);
            db.close();


        }catch (Exception error){
            Log.d("error",""+ error);
        }
        return false;

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
