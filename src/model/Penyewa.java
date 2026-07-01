/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FRIZKI
 */
public class Penyewa {

    private int idPenyewa;
    private String kodePenyewa;
    private String nama;
    private String alamat;
    private String noHp;
    private boolean isDeleted;

    public Penyewa() {

    }

    public Penyewa(int idPenyewa, String kodePenyewa,
            String nama, String alamat,
            String noHp, boolean isDeleted) {

        this.idPenyewa = idPenyewa;
        this.kodePenyewa = kodePenyewa;
        this.nama = nama;
        this.alamat = alamat;
        this.noHp = noHp;
        this.isDeleted = isDeleted;
    }

    public int getIdPenyewa() {
        return idPenyewa;
    }

    public void setIdPenyewa(int idPenyewa) {
        this.idPenyewa = idPenyewa;
    }

    public String getKodePenyewa() {
        return kodePenyewa;
    }

    public void setKodePenyewa(String kodePenyewa) {
        this.kodePenyewa = kodePenyewa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    @Override
public String toString() {
    return kodePenyewa + " - " + nama;
}
}