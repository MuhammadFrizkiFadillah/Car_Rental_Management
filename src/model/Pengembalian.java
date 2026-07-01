/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FRIZKI
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Pengembalian {

    private int idPengembalian;
    private String kodePengembalian;

    private Penyewaan penyewaan;

    private LocalDate tanggalKembali;

    private int hariTerlambat;
    private double totalDenda;

    private boolean isDeleted;

    // Constructor kosong
    public Pengembalian() {

    }

    // Constructor lengkap
    public Pengembalian(int idPengembalian,
            String kodePengembalian,
            Penyewaan penyewaan,
            LocalDate tanggalKembali,
            int hariTerlambat,
            double totalDenda,
            boolean isDeleted) {

        this.idPengembalian = idPengembalian;
        this.kodePengembalian = kodePengembalian;
        this.penyewaan = penyewaan;
        this.tanggalKembali = tanggalKembali;
        this.hariTerlambat = hariTerlambat;
        this.totalDenda = totalDenda;
        this.isDeleted = isDeleted;
    }

    public int hitungHariTerlambat() {

        LocalDate jatuhTempo =
                penyewaan.getTanggalJatuhTempo();

        long selisih =
                ChronoUnit.DAYS.between(
                        jatuhTempo,
                        tanggalKembali
                );

        return (int) Math.max(selisih, 0);
    }

    public double hitungTotalDenda() {

        Kendaraan kendaraan =
                penyewaan.getKendaraan();

        int terlambat =
                hitungHariTerlambat();

        return kendaraan.hitungDenda(
                terlambat
        );
    }

    public int getIdPengembalian() {
        return idPengembalian;
    }

    public void setIdPengembalian(int idPengembalian) {
        this.idPengembalian = idPengembalian;
    }

    public String getKodePengembalian() {
        return kodePengembalian;
    }

    public void setKodePengembalian(String kodePengembalian) {
        this.kodePengembalian = kodePengembalian;
    }

    public Penyewaan getPenyewaan() {
        return penyewaan;
    }

    public void setPenyewaan(Penyewaan penyewaan) {
        this.penyewaan = penyewaan;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public int getHariTerlambat() {
        return hariTerlambat;
    }

    public void setHariTerlambat(int hariTerlambat) {
        this.hariTerlambat = hariTerlambat;
    }

    public double getTotalDenda() {
        return totalDenda;
    }

    public void setTotalDenda(double totalDenda) {
        this.totalDenda = totalDenda;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    
}
