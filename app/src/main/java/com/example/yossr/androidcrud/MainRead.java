package com.example.yossr.androidcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener {

//    public static String EXTRA_BARCODE = "extra_name";
//    String barcode = getIntent().getStringExtra(EXTRA_BARCODE);
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private DatabaseHandler db;
    private List<ModulBarang> ListBarang = new ArrayList<ModulBarang>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Intent intent = getIntent();
        String barcode = intent.getExtras().getString("barcode");

        db = new DatabaseHandler(this);

        adapter_off = new CustomListAdapter(this, ListBarang);
        mListView = (ListView) findViewById(R.id.list_barang);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListBarang.clear();

        List<ModulBarang> contacts = db.ReadBarang(barcode);
        for (ModulBarang cn : contacts) {
            ModulBarang kd = new ModulBarang();
            kd.set_barcode(cn.get_barcode());
            kd.set_nama_barang(cn.get_nama_barang());
            kd.set_harga_barang(cn.get_harga_barang());
            ListBarang.add(kd);

            if ((ListBarang.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(MainRead.this,"Ada data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Object o = mListView.getItemAtPosition(position);
        ModulBarang obj_itemDetails = (ModulBarang) o;

        String Sbarcode = obj_itemDetails.get_barcode();
        String Snama = obj_itemDetails.get_nama_barang();
        String Sharga = obj_itemDetails.get_harga_barang();

        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Ibarcode", Sbarcode);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iharga", Sharga);
        startActivity(goUpdel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListBarang.clear();
        mListView.setAdapter(adapter_off);
        Intent intent = getIntent();
        String barcode = intent.getExtras().getString("barcode");
        List<ModulBarang> contacts = db.ReadBarang(barcode);
        for (ModulBarang cn : contacts) {
            ModulBarang kd = new ModulBarang();
            kd.set_barcode(cn.get_barcode());
            kd.set_nama_barang(cn.get_nama_barang());
            kd.set_harga_barang(cn.get_harga_barang());
            ListBarang.add(kd);

            if ((ListBarang.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(MainRead.this,"Ada data", Toast.LENGTH_SHORT).show();
        }
    }
}
