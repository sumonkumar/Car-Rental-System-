package com.example.car_rental_system;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Car> {
    private Activity context;
    private List<Car> carList;

    public CustomAdapter(Activity context,List<Car> carList) {
        super(context, R.layout.sample_view, carList);
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_view,null,true);
        Car car = carList.get(position);
        TextView t1 = view.findViewById(R.id.nametextviewid);
        TextView t2 = view.findViewById(R.id.carnumbertextviewid);
        TextView t3 = view.findViewById(R.id.carvaluetextviewid);

        t1.setText("Name: "+car.getCarname());
        t2.setText("Car Number Id: "+car.getCarnumbernumberId());
        t3.setText("Value: "+car.getCarvalue());

        return view;
    }
}
