package com.example.monday;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter{
    public List<Todomodel> list = new ArrayList<>();
    public Context context;
    ArrayList<String> animalList = new ArrayList<>();
    //private Object Context;

    public ListAdapter(List<Todomodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview, null);
        }
        ClipData.Item p = (ClipData.Item) getItem(position);
        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView);
        listItemText.setText(list.get(position).toString());

        //Handle buttons and add onClickListeners

        Button addBtn = (Button)view.findViewById(R.id.childButton);


        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(context,"Congratulation Roy Don"+v,Toast.LENGTH_SHORT).show();
                MainActivity nm = new MainActivity();

                int id2 = (int) getItemId(position);
                System.out.println(id2+"klklklklklklklklklklk");
                nm.deleteItem(String.valueOf(list.get(position).getId()));
                Log.d("hello", String.valueOf(list.get(position).getId()));
                //Button str = (Button)v.findViewById(R.id.childButton);
                //database nm = new database();
                //nm.deleteItem(v);
            }
        });

        return view;
    }

}
