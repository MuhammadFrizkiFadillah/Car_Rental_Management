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
public class Penyewaan {

    private int idSewa;
    private String kodeSewa;

    private Penyewa penyewa;
    private Kendaraan kendaraan;

    private LocalDate tanggalSewa;
    private int lamaSewa;
    private LocalDate tanggalJatuhTempo;

    private double hargaSewaHarian;
    private double totalBayar;

    private boolean isDeleted;

    // Constructor kosong
    public Penyewaan() {

    }

    // Constructor lengkap
    public Penyewaan(int idSewa,
                     String kodeSewa,
                     Penyewa penyewa,
                     Kendaraan kendaraan,
                     LocalDate tanggalSewa,
                     int lamaSewa,
                     LocalDate tanggalJatuhTempo,
                     double hargaSewaHarian,
                     double totalBayar,
                     boolean isDeleted) {

        this.idSewa = idSewa;
        this.kodeSewa = kodeSewa;
        this.penyewa = penyewa;
        this.kendaraan = kendaraan;
        this.tanggalSewa = tanggalSewa;
        this.lamaSewa = lamaSewa;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.hargaSewaHarian = hargaSewaHarian;
        this.totalBayar = totalBayar;
        this.isDeleted = isDeleted;
    }

    public double hitungTotalBayar() {
        return hargaSewaHarian * lamaSewa;
    }

    public LocalDate hitungTanggalJatuhTempo() {
        return tanggalSewa.plusDays(lamaSewa);
    }

    public int getIdSewa() {
        return idSewa;
    }

    public void setIdSewa(int idSewa) {
        this.idSewa = idSewa;
    }

    public String getKodeSewa() {
        return kodeSewa;
    }

    public void setKodeSewa(String kodeSewa) {
        this.kodeSewa = kodeSewa;
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public void setPenyewa(Penyewa penyewa) {
        this.penyewa = penyewa;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }

    public LocalDate getTanggalSewa() {
        return tanggalSewa;
    }

    public void setTanggalSewa(LocalDate tanggalSewa) {
        this.tanggalSewa = tanggalSewa;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public void setLamaSewa(int lamaSewa) {
        this.lamaSewa = lamaSewa;
    }

    public LocalDate getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(LocalDate tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public double getHargaSewaHarian() {
        return hargaSewaHarian;
    }

    public void setHargaSewaHarian(double hargaSewaHarian) {
        this.hargaSewaHarian = hargaSewaHarian;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(double totalBayar) {
        this.totalBayar = totalBayar;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    @Override
public String toString() {

    return kodeSewa
            + " - "
            + penyewa.getNama();
}

}
