package com.example.monday;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<n> extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.ListView,StringArray);

        text =findViewById(R.id.editTextTextPersonName);



        Button button= findViewById(R.id.button);
        button.setOnClickListener(
                click -> createTask()
        );



        updateList();
        //Toast.makeText(MainActivity.this,every.toString(),Toast.LENGTH_LONG).show();
        text.setOnKeyListener(
                (v, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                        Log.d("hello", "clicked");
                        list.add(text.getText().toString());
                        //
                        Todomodel todomodel;
                        todomodel = new Todomodel(text.getText().toString(),-1);
                        database database = new database(MainActivity.this);
                        boolean success =  database.add(todomodel);
                        text.getText().clear();
                        updateList();
                        //return true;
                    }
                    return false;
                }
        );
    }

    //EditText max=findViewById(R.id.editTextTextPersonName);

    public void createTask(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        EditText text=findViewById(R.id.editTextTextPersonName);

        list.add(text.getText().toString());




Todomodel todomodel;
todomodel = new Todomodel(text.getText().toString(),-1);
        database database = new database(MainActivity.this);
        boolean success =  database.add(todomodel);

        Toast.makeText(MainActivity.this,"succes:"+success,Toast.LENGTH_LONG).show();
        text.getText().clear();
        updateList();
    }
    public void  deleteItem(String n){
        database database = new database(MainActivity.this);
        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmm");
        database.deleteItem(n);
    }
    void updateList() {
        database database = new database(MainActivity.this);
        List<Todomodel> every = database.getevery();
        System.out.println(every);
        //Toast.makeText(this, "hh"+every, Toast.LENGTH_SHORT).show();
        ListAdapter adapter = new ListAdapter(every, this);
        ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);
    }


}