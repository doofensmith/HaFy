package com.softtech.hafy.model;

public class MChat {
    //atribut
    private String key_chat;
    private Object pesan;
    private String waktu_kirim;
    private String pengirim_pesan;
    private String penerima_pesan;
    private Boolean terbaca;


    public MChat() {
    }

    //

    public MChat(String key_chat, Object pesan, String waktu_kirim, String pengirim_pesan, String penerima_pesan, Boolean terbaca) {
        this.key_chat = key_chat;
        this.pesan = pesan;
        this.waktu_kirim = waktu_kirim;
        this.pengirim_pesan = pengirim_pesan;
        this.penerima_pesan = penerima_pesan;
        this.terbaca = terbaca;
    }

    public String getKey_chat() {
        return key_chat;
    }

    public void setKey_chat(String key_chat) {
        this.key_chat = key_chat;
    }

    public Object getPesan() {
        return pesan;
    }

    public void setPesan(Object pesan) {
        this.pesan = pesan;
    }

    public String getWaktu_kirim() {
        return waktu_kirim;
    }

    public void setWaktu_kirim(String waktu_kirim) {
        this.waktu_kirim = waktu_kirim;
    }

    public String getPengirim_pesan() {
        return pengirim_pesan;
    }

    public void setPengirim_pesan(String pengirim_pesan) {
        this.pengirim_pesan = pengirim_pesan;
    }

    public String getPenerima_pesan() {
        return penerima_pesan;
    }

    public void setPenerima_pesan(String penerima_pesan) {
        this.penerima_pesan = penerima_pesan;
    }

    public Boolean getTerbaca() {
        return terbaca;
    }

    public void setTerbaca(Boolean terbaca) {
        this.terbaca = terbaca;
    }
}
