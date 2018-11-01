package com.example.yossr.androidcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainUpdel extends AppCompatActivity {

    private DatabaseHandler db;
    private String Sbarcode, Snama, Sharga;
    private EditText Enama, Eharga;
    private TextView Ebarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updel);

        db = new DatabaseHandler(this);

        Intent i = this.getIntent();
        Sbarcode = i.getStringExtra("Ibarcode");
        Snama = i.getStringExtra("Inama");
        Sharga = i.getStringExtra("Iharga");

        Ebarcode = (TextView)findViewById(R.id.update_barcode);
        Enama = (EditText)findViewById(R.id.update_nama);
        Eharga = (EditText)findViewById(R.id.update_harga);

        Ebarcode.setText(Sbarcode);
        Enama.setText(Snama);
        Eharga.setText(Sharga);

        Button btnUpdate = (Button)findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Sbarcode = String.valueOf(Ebarcode.getText());
                Snama = String.valueOf(Enama.getText());
                Sharga = String.valueOf(Eharga.getText());
                if (Sbarcode.equals("")) {
                    Ebarcode.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan Isi Barcode", Toast.LENGTH_SHORT).show();
                } else if (Snama.equals("")) {
                    Enama.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan Isi Nama", Toast.LENGTH_SHORT).show();
                } else if (Sharga.equals("")) {
                    Eharga.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan Isi Harga", Toast.LENGTH_SHORT).show();
                } else {
                    db.UpdateBarang(new ModulBarang(Sbarcode, Snama, Sharga));
                    Toast.makeText(MainUpdel.this, "Data telah diperbarui", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        Button btnDelete = (Button)findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                db.DeleteBarang(new ModulBarang(Sbarcode, Snama, Sharga));
                Toast.makeText(MainUpdel.this, "Data telah dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
