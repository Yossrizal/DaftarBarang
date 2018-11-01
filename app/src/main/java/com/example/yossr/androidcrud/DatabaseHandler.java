package com.example.yossr.androidcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_warung";

    private static final String tb_barang = "tb_barang";

    private static final String tb_barang_barcode = "barcode";
    private static final String tb_barang_nama = "nama_barang";
    private static final String tb_barang_harga = "harga_barang";

    private static final String CREATE_TABLE_BARANG = "CREATE TABLE " + tb_barang +"("
            + tb_barang_barcode + " TEXT,"
            + tb_barang_nama + " TEXT,"
            + tb_barang_harga + " TEXT " + ")";

    public DatabaseHandler (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BARANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CreateBarang (ModulBarang mdNotif){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_barang_barcode, mdNotif.get_barcode());
        values.put(tb_barang_nama, mdNotif.get_nama_barang());
        values.put(tb_barang_harga, mdNotif.get_harga_barang());
        db.insert(tb_barang,null,values);
        db.close();
    }

    public List<ModulBarang> ReadBarang(String barcode){
        List<ModulBarang> ml = new ArrayList<ModulBarang>();
        String selectquery = "SELECT * FROM "+tb_barang+" WHERE "+tb_barang_barcode+" LIKE '%"+barcode+"%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()){
            do {
                ModulBarang mb = new ModulBarang();
                mb.set_barcode(cursor.getString(0));
                mb.set_nama_barang(cursor.getString(1));
                mb.set_harga_barang(cursor.getString(2));
                ml.add(mb);
            } while (cursor.moveToNext());
        }
        db.close();
        return ml;
    }

    public List<ModulBarang> SearchBarang(){
        List<ModulBarang> ml = new ArrayList<ModulBarang>();
        String selectquery = "SELECT * FROM "+tb_barang;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()){
            do {
                ModulBarang mb = new ModulBarang();
                mb.set_barcode(cursor.getString(0));
                mb.set_nama_barang(cursor.getString(1));
                mb.set_harga_barang(cursor.getString(2));
                ml.add(mb);
            } while (cursor.moveToNext());
        }
        db.close();
        return ml;
    }

    public int UpdateBarang(ModulBarang kd){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(tb_barang_barcode, kd.get_barcode());
        values.put(tb_barang_nama, kd.get_nama_barang());
        values.put(tb_barang_harga, kd.get_harga_barang());

        return db.update(tb_barang, values, tb_barang_barcode+" = ?",
                new String[]{
                        String.valueOf(kd.get_barcode())
                });
    }

    public void DeleteBarang(ModulBarang kd){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_barang, tb_barang_barcode+" =?",
                new String[]{
                        String.valueOf(kd.get_barcode())
                });
        db.close();
    }
}
