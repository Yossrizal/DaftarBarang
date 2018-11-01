package com.example.yossr.androidcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainCreate extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler db;
    private EditText Ebarcode, Enama, Eharga;
    private String Sbarcode, Snama, Sharga;

    private Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db = new DatabaseHandler(this);
        Ebarcode = (EditText)findViewById(R.id.create_barcode);
        Enama = (EditText)findViewById(R.id.create_nama);
        Eharga = (EditText)findViewById(R.id.create_harga);

        scanBtn = (Button)findViewById(R.id.scan_button);

        scanBtn.setOnClickListener(this);

        Button btnCreate = (Button) findViewById(R.id.create_btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sbarcode = String.valueOf(Ebarcode.getText());
                Snama = String.valueOf(Enama.getText());
                Sharga = String.valueOf(Eharga.getText());

                if (Sbarcode.equals("")) {
                    Ebarcode.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan Isi Barcode", Toast.LENGTH_SHORT).show();
                } else if (Snama.equals("")) {
                    Enama.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan Isi Nama", Toast.LENGTH_SHORT).show();
                } else if (Sharga.equals("")) {
                    Eharga.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan Isi Harga", Toast.LENGTH_SHORT).show();
                } else {
                    Ebarcode.setText("");
                    Enama.setText("");
                    Eharga.setText("");

                    db.CreateBarang(new ModulBarang(Sbarcode, Snama, Sharga));
                    Toast.makeText(MainCreate.this, "Data telah ditambahkan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v){
//respond to clicks
        if(v.getId()==R.id.scan_button){
//scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
//we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
//            formatTxt.setText("FORMAT: " + scanFormat);
//            contentTxt.setText("CONTENT: " + scanContent);
            Ebarcode.setText(scanContent);
        } else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
