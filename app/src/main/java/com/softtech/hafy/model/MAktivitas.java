package com.softtech.hafy.model;

public class MAktivitas {
    private String key_aktivitas;
    private String nama_aktivitas;
    private String jenis_aktivitas;
    private String tanggal_aktivitas;

    public MAktivitas() {
    }

    public MAktivitas(String key_aktivitas, String nama_aktivitas, String jenis_aktivitas, String tanggal_aktivitas) {
        this.key_aktivitas = key_aktivitas;
        this.nama_aktivitas = nama_aktivitas;
        this.jenis_aktivitas = jenis_aktivitas;
        this.tanggal_aktivitas = tanggal_aktivitas;
    }

    public String getKey_aktivitas() {
        return key_aktivitas;
    }

    public void setKey_aktivitas(String key_aktivitas) {
        this.key_aktivitas = key_aktivitas;
    }

    public String getNama_aktivitas() {
        return nama_aktivitas;
    }

    public void setNama_aktivitas(String nama_aktivitas) {
        this.nama_aktivitas = nama_aktivitas;
    }

    public String getJenis_aktivitas() {
        return jenis_aktivitas;
    }

    public void setJenis_aktivitas(String jenis_aktivitas) {
        this.jenis_aktivitas = jenis_aktivitas;
    }

    public String getTanggal_aktivitas() {
        return tanggal_aktivitas;
    }

    public void setTanggal_aktivitas(String tanggal_aktivitas) {
        this.tanggal_aktivitas = tanggal_aktivitas;
    }
}
