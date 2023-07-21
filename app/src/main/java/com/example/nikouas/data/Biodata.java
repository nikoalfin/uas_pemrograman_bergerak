package com.example.nikouas.data;

public class Biodata {
    private  String id,nama,jk;

    public Biodata(String id,String nama,String jk){
        this.id=id;
        this.nama=nama;
        this.jk=jk;
    }

    public String getId() {
        return id;
    }

    public String getJk() {
        return jk;
    }
    public String getNama() {
        return nama;
    }
}
