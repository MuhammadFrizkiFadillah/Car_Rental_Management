/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FRIZKI
 */
public class MobilSedan extends Kendaraan {

    private static final double TARIF_DENDA = 60000;

    public MobilSedan() {
        super();
        setJenisKendaraan("SEDAN");
    }

    @Override
    public double hitungDenda(int hariTerlambat) {
        return hariTerlambat * TARIF_DENDA;
    }
}
