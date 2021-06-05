package com.example.monday;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                Log.d("msmd,smd+", "onClick:     kjjkj     "+position+"dsdsdsd"+ list.get(position).getTodo()+"l;l;l");
                SqliteManager db1 = new SqliteManager(context);
                //List<Todomodel> every = db1.editData(list.get(position).getId());
                //Log.d("here", "onClick: here"+every);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                final EditText edittext = new EditText(context);
                dialogBuilder.setMessage("Edit the text");
                dialogBuilder.setTitle("Hey ROY DON");
                edittext.setText(list.get(position).getTodo());
                dialogBuilder.setView(edittext);

                dialogBuilder.setPositiveButton("Yes smudge", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        //OR
                        String YouEditTextValue = edittext.getText().toString();
                        SqliteManager db = new SqliteManager(context);
                        int n=db.editData(YouEditTextValue,list.get(position).getId());
                        int idx=0;
                        Todomodel todomodel ;

                        //int y = todomodel.getId();
                        list.get(position).setTodo(YouEditTextValue);
                        notifyDataSetChanged();
//                        while (idx < list.get(position).getId())
//                        {
//                            Log.d("msmd,smd+", "onClick: "+idx +    "kjjkj     "+position+"dsdsdsd"+ list.get(position).getId());
//                            if(idx == position)
//                            {
//                                // Remove item
//
//                                list.set(list.get(position).getId(),todomodel.setTodo(YouEditTextValue));
//                                notifyDataSetChanged();
//
//
//                                //ArrayList2.remove(idx);
//                                return;
//                            }
//                            else
//                            {
//                                ++idx;
//                            }
//                        }
//
//                        notifyDataSetChanged();
                    }
                });
                dialogBuilder.setNegativeButton("No Smudge", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                dialogBuilder.show();
                // ...Irrelevant code for customizing the buttons and title

//                LayoutInflater inflater = this.getLayoutInflater();
//
//                View dialogView= inflater.inflate(R.layout.dialog, null);
//                dialogBuilder.setView(dialogView);
//
//                Button button = (Button)dialogView.findViewById(R.id.button2);
//
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.d("lkjkjkjkj", "onClick: ");
//                        //Commond here......
//
//                    }
//                });
//
////                EditText editText = (EditText)
////                        dialogView.findViewById(R.id.label_field);
////
////                editText.setText("test label");
//
//                dialogBuilder.create().show();

            }

            private LayoutInflater getLayoutInflater() {
                return null;
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
