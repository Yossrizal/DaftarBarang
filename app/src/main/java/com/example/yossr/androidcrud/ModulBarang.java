package com.example.yossr.androidcrud;

public class ModulBarang {

    private String _barcode, _nama_barang, _harga_barang;

    public ModulBarang(String barcode, String nama_barang, String harga_barang){
        this._barcode = barcode;
        this._nama_barang = nama_barang;
        this._harga_barang = harga_barang;
    }

    public ModulBarang(){

    }

    public void set_barcode(String barcode){
        this._barcode = barcode;
    }

    public String get_barcode(){
        return this._barcode;
    }

    public void set_nama_barang(String nama_barang){
        this._nama_barang = nama_barang;
    }

    public String get_nama_barang(){
        return this._nama_barang;
    }

    public void set_harga_barang(String harga_barang){
        this._harga_barang = harga_barang;
    }

    public String get_harga_barang(){
        return this._harga_barang;
    }
}
