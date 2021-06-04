package com.example.monday;

import android.app.NotificationManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter{
    SqliteManager db;
    public List<Todomodel> list = new ArrayList<>();
    public Context context;
    ArrayList<String> animalList = new ArrayList<>();
    //private Object Context;

    public ListAdapter(List<Todomodel> list, Context context,SqliteManager db) {
        this.list = list;
        this.context = context;
        this.db=db;
        this.notifyDataSetChanged();

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


        View finalView = view;
        TextView etext = (TextView)view.findViewById(R.id.textView);
        etext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("jkfjdkjfdkjfk", "onClick: HELLo");
                Log.d("msmd,smd+", "onClick:     kjjkj     "+position+"dsdsdsd"+ list.get(position).getTodo());
                SqliteManager db1 = new SqliteManager(context);
                List<Todomodel> every = db1.getData(list.get(position).getId());
                Log.d("here", "onClick: here"+every);


            }
        });





        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(context,"Congratulation Roy Don"+v,Toast.LENGTH_SHORT).show();
                //MainActivity nm = new MainActivity();
                Log.d("msmd,smd+", "onClick:     kjjkj     "+position+"dsdsdsd"+ list.get(position).getId());
                int id2 = (int) getItemId(position);
                System.out.println(id2+"klklklklklklklklklklk");
                SqliteManager db1 = new SqliteManager(context);
                db1.deleteItem1(String.valueOf(list.get(position).getId()));
                int s = 5;
                int idx=0;
               Todomodel todomodel ;

               //int y = todomodel.getId();

                while (idx < list.get(position).getId())
                {
                    Log.d("msmd,smd+", "onClick: "+idx +    "kjjkj     "+position+"dsdsdsd"+ list.get(position).getId());
                    if(idx == position)
                    {
                        // Remove item
                        list.remove(position);
                        notifyDataSetChanged();


                        //ArrayList2.remove(idx);
                        return;
                    }
                    else
                    {
                        ++idx;
                    }
                }
                notifyDataSetChanged();
//                Log.d("mmmmmmmmmmmmm", "onClick: "+pos);
                //list.remove(pos);
                //list.remove(pos);

//                Intent myIntent = new Intent(context,MainActivity.class);
//                context.startActivity(myIntent);
                //ArrayList<Todomodel> every = db1.readAllItems();

//MainActivity nn = new MainActivity();
//nn.here(pos,context);
//                List<Todomodel> every = db1.readAllItems();
                //ListAdapter adapter = new ListAdapter(every, context,db);
//               ListView listview = finalView.findViewById(R.id.listView);
//               listview.setAdapter(adapter);

                notifyDataSetChanged();

                //mn.updateList();
                //nm.deleteItem(String.valueOf(list.get(position).getId()));
                //Log.d("hello", String.valueOf(list.get(position).getId()));
                //Button str = (Button)v.findViewById(R.id.childButton);
                //database nm = new database();
                //nm.deleteItem(v);
            }
        });

        return view;
    }

}
