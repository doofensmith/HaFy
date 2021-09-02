package com.softtech.hafy.Model;

public class MAkun {

    //attribut
    private String nama_pengguna;
    private String email;
    private String jenis_akun;
    private Boolean verified;

    //constructor
    public MAkun() {
        //
    }

    public MAkun(String nama_pengguna, String email, String jenis_akun, Boolean verified) {
        this.nama_pengguna = nama_pengguna;
        this.email = email;
        this.jenis_akun = jenis_akun;
        this.verified = verified;
    }

    public String getNama_pengguna() {
        return nama_pengguna;
    }

    public void setNama_pengguna(String nama_pengguna) {
        this.nama_pengguna = nama_pengguna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenis_akun() {
        return jenis_akun;
    }

    public void setJenis_akun(String jenis_akun) {
        this.jenis_akun = jenis_akun;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
