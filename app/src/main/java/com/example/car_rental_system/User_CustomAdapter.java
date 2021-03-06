package com.example.car_rental_system;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class User_CustomAdapter extends RecyclerView.Adapter<User_CustomAdapter.MyViewHolder> {


    private static ClickListener clickListener;
    Context context;
    DatabaseReference databaseReference;
    private List<User_Car> carList;
    public String carUID;

    public User_CustomAdapter(Context context, List<User_Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.user_sampleview, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull User_CustomAdapter.MyViewHolder holder, int position) {

        User_Car car = carList.get(position);
        holder.carName.setText(car.getCarname());
        holder.carNumber.setText(car.getCarnumbernumberId());
        holder.carValue.setText(car.getCarvalue());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //int position = holder.getBindingAdapterPosition();
            //carUID = carList.get(position).getuID();
            //Log.d("TAG"," "+carUID);

            Intent intent = new Intent(context, Rent.class);
            //intent.putExtra("carUid", carUID);
            intent.putExtra("car_Name",car.getCarname());
            intent.putExtra("car_Number",car.getCarnumbernumberId());
            intent.putExtra("car_Value",car.getCarvalue());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView carName, carNumber, carValue;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.nametextviewid);
            carNumber = itemView.findViewById(R.id.carnumbertextviewid);
            carValue = itemView.findViewById(R.id.carvaluetextviewid);

            //itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);



        }

        /*
        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAbsoluteAdapterPosition(),view);

            int position = getBindingAdapterPosition();
            carUID = carList.get(position).getuID();
            Log.d("TAG"," "+carUID);

            Intent intent = new Intent(context, Rent.class);
            intent.putExtra("carUid", carUID);
            context.startActivity(intent);
        }*/

        @Override
        public boolean onLongClick(View view) {

            clickListener.onItemLongClick(getAbsoluteAdapterPosition(),view);
            return false;
        }
    }
    public interface ClickListener{

        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener){

        User_CustomAdapter.clickListener = clickListener;
    }
    }