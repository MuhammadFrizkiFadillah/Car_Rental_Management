/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FRIZKI
 */
public class MobilSUV extends Kendaraan {

    private static final double TARIF_DENDA = 75000;

    public MobilSUV() {
        super();
        setJenisKendaraan("SUV");
    }

    @Override
    public double hitungDenda(int hariTerlambat) {
        return hariTerlambat * TARIF_DENDA;
    }
}