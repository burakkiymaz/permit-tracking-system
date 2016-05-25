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
public class UserConfig {
    
    private String kadi,
            sifre,
            ad,
            soyad,
            cinsiyet,
            sehir,
            birimAdi;
    
    private int tcNo,
            birimNo,
            sicilNo;

    private Date dogumTarihi;
    
    

    public UserConfig(String kadi, 
            String sifre, 
            String ad, 
            String soyad, 
            int tcNo,
            String cinsiyet, 
            Date dogumTarihi, 
            String sehir,  
            int birimNo,
            int sicilNo,
            String birimAdi){
        
        this.kadi = kadi;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
        this.cinsiyet = cinsiyet;
        this.sehir = sehir;
        this.tcNo = tcNo;
        this.birimNo = birimNo;
        this.dogumTarihi = dogumTarihi;
        this.sicilNo = sicilNo;
        this.birimAdi = birimAdi;
    }

    public String getBirimAdi() {
        return birimAdi;
    }

    public void setBirimAdi(String birimAdi) {
        this.birimAdi = birimAdi;
    }

    public String getKadi() {
        return kadi;
    }

    public void setKadi(String kadi) {
        this.kadi = kadi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
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

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public int getTcNo() {
        return tcNo;
    }

    public void setTcNo(int tcNo) {
        this.tcNo = tcNo;
    }

    public int getBirimNo() {
        return birimNo;
    }

    public void setBirimNo(int birimNo) {
        this.birimNo = birimNo;
    }

    public Date getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(Date dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }
    
    public int getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(int sicilNo) {
        this.sicilNo = sicilNo;
    }
    
}
