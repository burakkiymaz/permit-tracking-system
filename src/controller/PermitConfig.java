/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;

/**
 *
 * @author burak
 */
public class PermitConfig {

    public PermitConfig(
            int kayitNo, 
            int kalan, 
            Date izinTarihi, 
            Date izinBitisi,
            String durum,
            String izinTuru) {
        
        this.kayitNo = kayitNo;
        this.kalan = kalan;
        this.izinTarihi = izinTarihi;
        this.izinBitisi = izinBitisi;
        this.durum = durum;
        this.izinTuru = izinTuru;
        
    }
    
    private String  durum,
                    izinTuru;
    
    private int     kayitNo,
                    kalan;
    
    private Date    izinTarihi,
                    izinBitisi;

    public int getKayitNo() {
        return kayitNo;
    }

    public String getIzinTuru() {
        return izinTuru;
    }

    public void setIzinTuru(String izinTuru) {
        this.izinTuru = izinTuru;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public void setKayitNo(int kayitNo) {
        this.kayitNo = kayitNo;
    }

    public int getKalan() {
        return kalan;
    }

    public void setKalan(int kalan) {
        this.kalan = kalan;
    }

    public Date getIzinTarihi() {
        return izinTarihi;
    }

    public void setIzinTarihi(Date izinTarihi) {
        this.izinTarihi = izinTarihi;
    }

    public Date getIzinBitisi() {
        return izinBitisi;
    }

    public void setIzinBitisi(Date izinBitisi) {
        this.izinBitisi = izinBitisi;
    }
            
    
}
