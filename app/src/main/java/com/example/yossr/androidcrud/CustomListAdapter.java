package com.example.yossr.androidcrud;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModulBarang> movieItems;

    public CustomListAdapter(Activity activity, List<ModulBarang> movieItems){
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_list, null);

        TextView barcode = (TextView)convertView.findViewById(R.id.txt_barcode);
        TextView nama = (TextView)convertView.findViewById(R.id.txt_nama);
        TextView harga = (TextView)convertView.findViewById(R.id.txt_harga);

        ModulBarang kd = movieItems.get(position);

        barcode.setText(kd.get_barcode());
        nama.setText(kd.get_nama_barang());
        harga.setText(kd.get_harga_barang());

        return convertView;

    }
}
