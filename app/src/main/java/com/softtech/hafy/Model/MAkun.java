package com.softtech.hafy.Model;

public class MAkun {

    //attribut
    private String key_akun;
    private String nama_pengguna;
    private String email;
    private String jenis_akun;
    private Boolean verified;
    private String no_telpon;
    private String tanggal_lahir;
    private String gender;
    private String pekerjaan;
    private String bio;
    private String tentang;


    //constructor
    public MAkun() {
        //
    }

    public MAkun(String key_akun, String nama_pengguna, String email, String jenis_akun, Boolean verified, String no_telpon, String tanggal_lahir, String gender, String pekerjaan) {
        this.key_akun = key_akun;
        this.nama_pengguna = nama_pengguna;
        this.email = email;
        this.jenis_akun = jenis_akun;
        this.verified = verified;
        this.no_telpon = no_telpon;
        this.tanggal_lahir = tanggal_lahir;
        this.gender = gender;
        this.pekerjaan = pekerjaan;

    }

    public String getKey_akun() {
        return key_akun;
    }

    public void setKey_akun(String key_akun) {
        this.key_akun = key_akun;
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

    public String getNo_telpon() {
        return no_telpon;
    }

    public void setNo_telpon(String no_telpon) {
        this.no_telpon = no_telpon;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }
}
