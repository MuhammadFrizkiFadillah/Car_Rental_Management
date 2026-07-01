/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FRIZKI
 */
public abstract class Kendaraan {

    private int idKendaraan;
    private String kodeKendaraan;
    private String merk;
    private String model;
    private String jenisKendaraan;
    private String platNomor;
    private int tahun;
    private String warna;
    private double hargaSewa;
    private String status;
    private boolean isDeleted;

    public Kendaraan() {

    }

    public Kendaraan(int idKendaraan,
            String kodeKendaraan,
            String merk,
            String model,
            String jenisKendaraan,
            String platNomor,
            int tahun,
            String warna,
            double hargaSewa,
            String status,
            boolean isDeleted) {

        this.idKendaraan = idKendaraan;
        this.kodeKendaraan = kodeKendaraan;
        this.merk = merk;
        this.model = model;
        this.jenisKendaraan = jenisKendaraan;
        this.platNomor = platNomor;
        this.tahun = tahun;
        this.warna = warna;
        this.hargaSewa = hargaSewa;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    public abstract double hitungDenda(int hariTerlambat);

    public int getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public String getKodeKendaraan() {
        return kodeKendaraan;
    }

    public void setKodeKendaraan(String kodeKendaraan) {
        this.kodeKendaraan = kodeKendaraan;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(double hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public double hitungBiayaSewa(int lamaSewa) {
    return hargaSewa * lamaSewa;
    }
    @Override
public String toString() {

    return kodeKendaraan
            + " - "
            + merk
            + " "
            + model;
}
}
