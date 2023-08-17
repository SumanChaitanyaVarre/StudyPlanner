package com.example.outlab9;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.outlab9.R;

import java.util.ArrayList;

public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MyOwnHolder> {
    ArrayList<String> data1;
    ArrayList<String> data2;
    ArrayList<String> data3;
    ArrayList<String> data4;

    Context ctx;
    MyCoreDatabase1 db1;
    MyCoreDatabase2 db2;
    MyCoreDatabase3 db3;
    MyCoreDatabase4 db4;
    int databasetype;

    public MyOwnAdapter(Context ct, ArrayList<String> al1, ArrayList<String> al2, ArrayList<String> al3, ArrayList<String> al4, MyCoreDatabase1 d1){
        ctx = ct;
        data1 = al1;
        data2 = al2;
        data3 = al3;
        data4 = al4;
        db1 = d1;
        databasetype=1;
    }
    public MyOwnAdapter(Context ct, ArrayList<String> al1, ArrayList<String> al2, ArrayList<String> al3, ArrayList<String> al4, MyCoreDatabase2 d2){
        ctx = ct;
        data1 = al1;
        data2 = al2;
        data3 = al3;
        data4 = al4;
        db2 = d2;
        databasetype=2;
    }
    public MyOwnAdapter(Context ct, ArrayList<String> al1, ArrayList<String> al2, ArrayList<String> al3, ArrayList<String> al4, MyCoreDatabase3 d3){
        ctx = ct;
        data1 = al1;
        data2 = al2;
        data3 = al3;
        data4 = al4;
        db3 = d3;
        databasetype = 3;
    }
    public MyOwnAdapter(Context ct, ArrayList<String> al1, ArrayList<String> al2, ArrayList<String> al3, ArrayList<String> al4, MyCoreDatabase4 d4){
        ctx = ct;
        data1 = al1;
        data2 = al2;
        data3 = al3;
        data4 = al4;
        db4 = d4;
        databasetype = 4;
    }

    @NonNull
    @Override
    public MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myOwnView = myInflater.inflate(R.layout.my_row, parent, false);
        return new MyOwnHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnHolder holder, int position) {
        String s1, s2;
        s1 = data1.get(position);
        s2 = data2.get(position);
        holder.text1.setText(s1);
        holder.text2.setText(s2);
        String s3 = data3.get(position) + "---" + data4.get(position);
        holder.text3.setText(s3);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Delete Task");
                builder.setMessage("Are You Sure ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (databasetype == 1) {
                            db1.deleteTask(s1, s2);
                        } else if (databasetype == 2) {
                            db2.deleteTask(s1, s2);
                        } else if (databasetype == 3) {
                            db3.deleteTask(s1, s2);
                        } else if (databasetype == 4) {
                            db4.deleteTask(s1, s2);
                        }

                        Toast.makeText(ctx, "Deleted the task. Please Refresh", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder{
        TextView text1, text2, text3;
        ImageButton delete;
        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.t1);
            text2 = (TextView) itemView.findViewById(R.id.t2);
            text3 = (TextView) itemView.findViewById(R.id.t3);
            delete = (ImageButton) itemView.findViewById(R.id.delete);
        }
    }
}
