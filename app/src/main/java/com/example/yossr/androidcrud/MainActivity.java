package com.example.yossr.androidcrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button scanBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button)findViewById(R.id.scan_harga);

        scanBtn.setOnClickListener(this);
    }

    public void btnCreate (View v){
        Intent goCreate = new Intent(MainActivity.this, MainCreate.class);
        startActivity(goCreate);
    }

    public void btnRead (View v){
        Intent goRead = new Intent(MainActivity.this, MainRead.class);
        goRead.putExtra("barcode","");
        startActivity(goRead);
    }

    @Override
    public void onClick(View v){
//respond to clicks
        if(v.getId()==R.id.scan_harga){
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
            Intent goScanHarga = new Intent(MainActivity.this, MainRead.class);
            goScanHarga.putExtra("barcode",scanContent);
            startActivity(goScanHarga);

        } else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
