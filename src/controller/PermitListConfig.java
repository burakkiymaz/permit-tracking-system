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
public class PermitListConfig {
    private int kayitNo;
    
    private String
            ad,
            soyad,
            izinTipi,
            durum;

    private Date 
            izinBaslangic,
            izinBitis;
    
     public PermitListConfig(
             int kayitNo, 
             String ad, 
             String soyad, 
             String izinTipi, 
             String durum, 
             Date izinBaslangic, 
             Date izinBitis) {
         
        this.kayitNo = kayitNo;
        this.ad = ad;
        this.soyad = soyad;
        this.izinTipi = izinTipi;
        this.durum = durum;
        this.izinBaslangic = izinBaslangic;
        this.izinBitis = izinBitis;
    }

    public int getSicilNo() {
        return kayitNo;
    }

    public void setSicilNo(int kayitNo) {
        this.kayitNo = kayitNo;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getIzinTipi() {
        return izinTipi;
    }

    public void setIzinTipi(String izinTipi) {
        this.izinTipi = izinTipi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public Date getIzinBaslangic() {
        return izinBaslangic;
    }

    public void setIzinBaslangic(Date izinBaslangic) {
        this.izinBaslangic = izinBaslangic;
    }

    public Date getIzinBitis() {
        return izinBitis;
    }

    public void setIzinBitis(Date izinBitis) {
        this.izinBitis = izinBitis;
    }
    
    
   
    
    
}
