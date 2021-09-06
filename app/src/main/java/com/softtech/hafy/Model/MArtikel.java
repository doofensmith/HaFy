package com.softtech.hafy.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MArtikel {
    //atribut
    private String key_artikel;
    private String judul_artikel;
    private String kategori_artikel;
    private Object isi_artikel;
    private String penulis_artikel;
    private  String tanggal_terbit;

    public MArtikel() {
        //
    }

    public MArtikel(String key_artikel, String judul_artikel, String kategori_artikel, Object isi_artikel, String penulis_artikel, String tanggal_terbit) {
        this.key_artikel = key_artikel;
        this.judul_artikel = judul_artikel;
        this.kategori_artikel = kategori_artikel;
        this.isi_artikel = isi_artikel;
        this.penulis_artikel = penulis_artikel;
        this.tanggal_terbit = tanggal_terbit;
    }

    public String getKey_artikel() {
        return key_artikel;
    }

    public void setKey_artikel(String key_artikel) {
        this.key_artikel = key_artikel;
    }

    public String getJudul_artikel() {
        return judul_artikel;
    }

    public void setJudul_artikel(String judul_artikel) {
        this.judul_artikel = judul_artikel;
    }

    public String getKategori_artikel() {
        return kategori_artikel;
    }

    public void setKategori_artikel(String kategori_artikel) {
        this.kategori_artikel = kategori_artikel;
    }

    public Object getIsi_artikel() {
        return isi_artikel;
    }

    public void setIsi_artikel(Object isi_artikel) {
        this.isi_artikel = isi_artikel;
    }

    public String getPenulis_artikel() {
        return penulis_artikel;
    }

    public void setPenulis_artikel(String penulis_artikel) {
        this.penulis_artikel = penulis_artikel;
    }

    public String getTanggal_terbit() {
        return tanggal_terbit;
    }

    public void setTanggal_terbit(String tanggal_terbit) {
        this.tanggal_terbit = tanggal_terbit;
    }
}
