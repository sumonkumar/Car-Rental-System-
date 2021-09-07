package com.example.car_rental_system;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    public static ClickListener clickListener;
    Context context;
    DatabaseReference databaseReference;
    private List<Car> carList;

    public TestAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TestAdapter.MyViewHolder holder, int position) {


        Car car = carList.get(position);
        holder.carName.setText(car.getCarname());
        holder.carNumber.setText(car.getCarnumbernumberId());
        holder.carValue.setText(car.getCarvalue());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,Rent.class);
                intent.putExtra("Position", position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView carName, carNumber, carValue;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.nametextviewid);
            carNumber = itemView.findViewById(R.id.carnumbertextviewid);
            carValue = itemView.findViewById(R.id.carvaluetextviewid);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAbsoluteAdapterPosition(),view);
            Intent intent = new Intent(context,Rent.class);
            context.startActivity(intent);

        }

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

        TestAdapter.clickListener = clickListener;
    }
}
