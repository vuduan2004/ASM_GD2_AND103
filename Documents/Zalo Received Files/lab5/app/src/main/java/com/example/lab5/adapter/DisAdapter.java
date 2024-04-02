package com.example.lab5.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.R;
import com.example.lab5.handle.Item_Handle;
import com.example.lab5.model.Distributor;
import com.example.lab5.model.Response;
import com.example.lab5.services.HttpRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class DisAdapter extends RecyclerView.Adapter<DisAdapter.viewholder> {

    private Context context;
    private final ArrayList<Distributor> list;
    //    private Distributor dis;
    private Item_Handle handle;
    private HttpRequest httpRequest;

    public DisAdapter(Context context, ArrayList<Distributor> list, Item_Handle handle) {
        this.context = context;
        this.list = list;
        this.handle = handle;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dis, parent,
                false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Distributor dis = list.get(position);
        holder.txtid.setText(dis.getId());
        holder.txttieude.setText(dis.getName());
        //xóa
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có cắc chắn muốn xoá không");
//                        .setCancelable(false)
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handle.Delete(dis.getId());
                        Toast.makeText(context, "succ delete", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();

            }
        });
        //update
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handle.Update(dis.getId(),dis);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class viewholder extends RecyclerView.ViewHolder {
        TextView txtid, txttieude;
        ImageButton btnupdate, btndelete;
        CardView crdcv;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.txtid);
            txttieude = itemView.findViewById(R.id.txttieude);
            btnupdate = itemView.findViewById(R.id.btnupdate);
            btndelete = itemView.findViewById(R.id.btndelete);
            crdcv = itemView.findViewById(R.id.crddis);

        }
    }



}
