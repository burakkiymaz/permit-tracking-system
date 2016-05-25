/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author burak
 */
public class DbWork {
    
    /**
     * ADMIN AND USER PANEL DATABASE CONTROLS
     */
    
    ////////////////////////////////////////////////////////////////////////////
    // getting user info for admin panel and user panel
    public UserConfig getUserInfo(int sicilNo){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        try {
            PreparedStatement prs = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select k.kadi, k.sifre, p.ad, p.soyad, p.tcNo, p.cinsiyet, p.dogumTarihi, p.sehir, p.birimNo "
                            + "from personel p, kullanici k "
                            + "where p.sicilNo = ? and p.sicilNo = k.sicilNo order by p.sicilNo");
            prs.setInt(1, sicilNo);
            ResultSet rs = prs.executeQuery();
            
            String kadi = null;
            String sifre = null; 
            String ad = null; 
            String soyad = null;
            int tcNo = 0;
            String cinsiyet = null;
            Date dogumTarihi = null;
            String sehir = null;
            int birimNo = 0;
            String birimAdi = null;
            
            
            while (rs.next()) {
                 kadi=rs.getString(1);
                 sifre=rs.getString(2); 
                 ad=rs.getString(3); 
                 soyad=rs.getString(4);
                 tcNo=rs.getInt(5);
                 cinsiyet=rs.getString(6);
                 dogumTarihi=rs.getDate(7);
                 sehir=rs.getString(8);
                 birimNo=rs.getInt(9);
            }
            
            UserConfig user = new UserConfig(kadi, sifre, ad, soyad, 
                    tcNo, cinsiyet, dogumTarihi, sehir, birimNo, 
                    sicilNo,birimAdi);
            
            return user;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        } finally {
            dbcon.disConnect();
        }
    }
    
    /**
     * MAIN MENU DATABASE CONTROLS
     */
    
    ////////////////////////////////////////////////////////////////////////////
    //LOGIN CONTROL
    public int login(String kadi, String sifre) {
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();

        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select p.sicilNo "
                            + "from kullanici k, personel p "
                            + "where k.kadi=? and k.sifre=? and p.sicilNo = k.sicilNo");
            ps.setString(1, kadi);
            ps.setString(2, sifre);
            ResultSet rs = ps.executeQuery();
            
            String sicilNo = null;
            
            while (rs.next()) {
                sicilNo=rs.getString(1);
            }
            
            int durum = getStatus(Integer.parseInt(sicilNo)); //check info(admin-user)
            
            return durum;
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Kullanıcı Adı veya Şifre Hatalı");
            return 0;
        } finally {
            dbcon.disConnect();
            
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    // admin or user control function
    /**
     * if status= 0 -> default(error)
     *          = 1 -> opens Administrator Panel
     *          = 2 -> opens User Panel
     */
    private int getStatus(int sicilNo){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();

        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select tipi from kullanici where sicilNo=?");
            ps.setInt(1, sicilNo);
            ResultSet rs = ps.executeQuery();
            
            int status = 0;
            
            while (rs.next()) {
                status=rs.getInt(1);
            }
            return status;
           

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return 0;
        } finally {
            dbcon.disConnect();
            
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    
    public int findSicilNo (String kadi){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        try {
            PreparedStatement prs = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select sicilNo from kullanici where kadi = ?");
            prs.setString(1, kadi);
            ResultSet rs = prs.executeQuery();
            
            String temp_sicilNo = null;
            
            while (rs.next()) {
                temp_sicilNo=rs.getString(1);
            }
            
            return Integer.parseInt(temp_sicilNo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return 0;
        } finally {
            dbcon.disConnect();
        }
    }
    
    /**
     * NEW USER DATABASE CONTROLS
     */
    
    ////////////////////////////////////////////////////////////////////////////
    // Sign in a new user information to personel table 
    
    public void signIn(UserConfig user){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();

        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("insert into personel(ad,soyad,tcNo,cinsiyet,dogumTarihi,sehir,birimNo) "
                            + "values (?,?,?,?,?,?,?)");
            
            ps.setString(1, user.getAd());
            ps.setString(2, user.getSoyad());
            ps.setInt(3, user.getTcNo());
            ps.setString(4, user.getCinsiyet());
            ps.setDate(5, (Date) user.getDogumTarihi());
            ps.setString(6, user.getSehir());
            ps.setInt(7, user.getBirimNo()+1);
            ps.executeUpdate();
            
            System.out.println("kayıt eklendi");
            
            
            //find sicilNo which already signed in user
            int temp_sicilNo = findSicilNo(user.getTcNo()); 
            
            System.out.println("sicilNo = " + temp_sicilNo);
            
            
            signInUser(temp_sicilNo, user.getKadi(), user.getSifre(), 2);
            
            JOptionPane.showMessageDialog(null, "Kullanıcı Kaydı Başarılı\n\nSicil No: "+temp_sicilNo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            dbcon.disConnect();
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //Sign in a new user information to kullanici table
    
    private void signInUser(int sicilNo, String kadi, String sifre, int tipi){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("insert into kullanici values (?,?,?,?)");
            
            ps.setInt(1, sicilNo);
            ps.setString(2, kadi);
            ps.setString(3, sifre);
            ps.setInt(4, tipi);
            ps.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            dbcon.disConnect();
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /**
    * this user added database but we can't know sicilNo
    * this function is says to us using tcNo
    */ 
    public int findSicilNo (int tcNo){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        try {
            PreparedStatement prs = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select sicilNo from personel where tcNo = ?");
            prs.setInt(1, tcNo);
            ResultSet rs = prs.executeQuery();
            
            String temp_sicilNo = null;
            
            while (rs.next()) {
                temp_sicilNo=rs.getString(1);
            }
            
            return Integer.parseInt(temp_sicilNo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return 0;
        } finally {
            dbcon.disConnect();
        }
    }
    
    /**
     * UPDATE USER INFORMATION(INSIDE USER PANEL) DATABASE CONTROLS
     */
    ////////////////////////////////////////////////////////////////////////////
    //update user information
    
    public void UpdateUser(UserConfig user){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();

        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("update personel "
                            + "set ad=?, soyad=?, tcNo=?, cinsiyet=?, dogumTarihi=date_add(?,interval 41 day), sehir=?, birimNo=? "
                            + "where sicilNo=?");
            
            ps.setString(1, user.getAd());
            ps.setString(2, user.getSoyad());
            ps.setInt(3, user.getTcNo());
            ps.setString(4, user.getCinsiyet());
            ps.setDate(5,user.getDogumTarihi());
            ps.setString(6, user.getSehir());
            ps.setInt(7, user.getBirimNo()+1);
            ps.setInt(8, user.getSicilNo());
            ps.executeUpdate();
            
            PreparedStatement prs = (PreparedStatement) dbcon.getCon().
                    prepareStatement("update kullanici set kadi=?,sifre=? where sicilNo=?");
            
            prs.setString(1, user.getKadi());
            prs.setString(2, user.getSifre());
            prs.setInt(3,user.getSicilNo());
            prs.executeUpdate();
            
            System.out.println("kayıt güncellendi");
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            dbcon.disConnect();
        }
    }
    
    /**
     * UPDATE USER INFORMATION (ADMIN PANEL) DATABASE CONTROLS
     */
    ////////////////////////////////////////////////////////////////////////////
    /**
     * User info for UpdateUserAdminPanel. this function finds all of 
     * user informations using sicilNo
     */

    
    public String[] UserInfoForAdmin(int sicilNo){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        String[] InfoContent = new String[12];
        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select p.ad, p.soyad, p.tcNo, p.cinsiyet, p.dogumTarihi, p.sehir, p.birimNo, p.sicilNo, k.kadi, k.sifre, k.tipi,count(*) "
                                    + "from personel p, izinKontrol i, kullanici k "
                                    + "where p.sicilNo = i.sicilNo and p.sicilNo = k.sicilNo and p.sicilNo = ?;");
            ps.setInt(1, sicilNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                InfoContent[0] = rs.getString(1);
                InfoContent[1] = rs.getString(2);
                InfoContent[2] = Integer.toString(rs.getInt(3));
                InfoContent[3] = rs.getString(4);
                InfoContent[4] = rs.getDate(5).toString();
                InfoContent[5] = rs.getString(6);
                InfoContent[6] = rs.getString(7);
                InfoContent[7] = Integer.toString(rs.getInt(8));
                InfoContent[8] = rs.getString(9);
                InfoContent[9] = rs.getString(10);
                InfoContent[10] = Integer.toString(rs.getInt(11));
                InfoContent[11] = Integer.toString(rs.getInt(12));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "dbError: "+e.getMessage());
        }
        finally{
            dbcon.disConnect();
        }
        return InfoContent;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Updates user info. this function updates user's info by admins
    
    public void UpdateUserInfoForAdmin(String[] arr){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        
        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("update kullanici k, personel p "
                            + "set p.ad = ?, p.soyad = ?, p.tcNo =?, p.cinsiyet=?, p.dogumTarihi =?,p.sehir=?,p.birimNo=?,k.kadi=?,k.sifre=? ,k.tipi = ? "
                            + "where p.sicilNo = k.sicilNo and p.sicilNo = ?");
            ps.setString(1, arr[0]);
            ps.setString(2, arr[1]);
            ps.setInt(3, Integer.parseInt(arr[2]));
            ps.setString(4, arr[3]);
            ps.setDate(5, java.sql.Date.valueOf(arr[4]));
            ps.setString(6, arr[5]);
            ps.setInt(7, Integer.parseInt(arr[6]));
            ps.setString(8, arr[7]);
            ps.setString(9, arr[8]);
            ps.setInt(10, Integer.parseInt(arr[9]));
            ps.setInt(11, Integer.parseInt(arr[10]));
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Kullanıcı Başarıyla Güncellendi");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Message: "+e.getMessage());
        }
    }
    
    /**
     * SEARCH USER INFORMATION
     */
    ////////////////////////////////////////////////////////////////////////////
    // this function searchs user's information (integer input)
    
    public List<UserConfig> getUserInfoTable(String query,int sicilNo){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        List<UserConfig> tableContentArray = new ArrayList<>();
        int i=0;
        try { // for listing some users with sicilNo
            
            PreparedStatement prs = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select p.sicilNo, k.kadi, k.sifre, p.ad, p.soyad, p.tcNo, p.cinsiyet, p.dogumTarihi, p.sehir,p.birimNo , b.birimAdi from personel p, kullanici k,birimler b where p."+ query +"=? and p.sicilNo = k.sicilNo and p.birimNo = b.birimNo order by p.sicilNo");
            prs.setInt(1, sicilNo);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                 tableContentArray.add(new UserConfig(
                         rs.getString(2), 
                         rs.getString(3), 
                         rs.getString(4), 
                         rs.getString(5), 
                         rs.getInt(6), 
                         rs.getString(7), 
                         rs.getDate(8), 
                         rs.getString(9), 
                         rs.getInt(10), 
                         rs.getInt(1),
                         rs.getString(11)
                 ));
                 i++;
            }
            return tableContentArray;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error Message: "+e.getMessage());
            return null;
        } finally {
            dbcon.disConnect();
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    //this function searchs user's information (String input)
    
    public List<UserConfig> getUserInfoTable(String query,String string) throws SQLException{
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        List<UserConfig> tableContentArray = new ArrayList<>();

        int i=0;
        try {   //for listing some users
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select p.sicilNo, k.kadi, k.sifre, p.ad, p.soyad, p.tcNo, p.cinsiyet, p.dogumTarihi, p.sehir, b.birimAdi, p.birimNo from personel p, kullanici k,birimler b where p."+ query +" like ? and p.sicilNo = k.sicilNo and p.birimNo = b.birimNo order by p.sicilNo");
            ps.setString(1, "%"+string+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 tableContentArray.add(new UserConfig(
                         rs.getString(2), 
                         rs.getString(3), 
                         rs.getString(4), 
                         rs.getString(5), 
                         rs.getInt(6), 
                         rs.getString(7), 
                         rs.getDate(8), 
                         rs.getString(9), 
                         rs.getInt(11),
                         rs.getInt(1),
                         rs.getString(10)
                 ));
                 i++;
            }
            return tableContentArray;


        } catch (Exception e) {
            try {       //for list all of users
                PreparedStatement prs = (PreparedStatement) dbcon.getCon().
                        prepareStatement("select p.sicilNo, k.kadi, k.sifre, p.ad, p.soyad, p.tcNo, p.cinsiyet, p.dogumTarihi, p.sehir, b.birimAdi, p.birimNo from personel p, kullanici k, birimler b where p.sicilNo = k.sicilNo and p.birimNo = b.birimNo order by p.sicilNo");
                ResultSet rss = prs.executeQuery();

                while (rss.next()) {
                     tableContentArray.add(new UserConfig(
                             rss.getString(2), 
                             rss.getString(3), 
                             rss.getString(4), 
                             rss.getString(5), 
                             rss.getInt(6), 
                             rss.getString(7), 
                             rss.getDate(8), 
                             rss.getString(9),
                             rss.getInt(11),
                             rss.getInt(1),
                             rss.getString(10)
                             
                     ));
                     i++;
                }
                return tableContentArray;

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
                return null;
            }
        }finally {
            dbcon.disConnect();
        }
    }
    /**
     * TAKE PERMIT DATABASE CONTROLS
     */
    ////////////////////////////////////////////////////////////////////////////
    //this function requests permit between certain dates from admin
    
    public void requestPermit(int sicilNo, int izinTuru, Date start, Date end){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("insert into izinKontrol(izinTarihi, izinTuru, izinBitisi, sicilNo, durum) values (?,?,?,?,2)");
            
            ps.setDate(1, start);
            ps.setInt(2, izinTuru);
            ps.setDate(3, end);
            ps.setInt(4, sicilNo);
            ps.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            System.out.println("Error: "+e.getMessage());
        } finally {
            dbcon.disConnect();
        }
    }
    /**
     * SEE PERMITS DATABASE CONTROLS
     */
    ////////////////////////////////////////////////////////////////////////////
    // this function show own permits every user.
    
    public List<PermitConfig> getPermitInfoForTable(int sicilNo){
        
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        List<PermitConfig> tableContent = new ArrayList<>();
        int i=0;
        
        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().prepareStatement("select i.kayitNo, i.izinTarihi, i.izinBitisi, datediff(i.izinBitisi, curdate()), d.durum, t.izinAdi from izinKontrol i,izinDurumu d, izinTuru t where i.sicilNo =? and i.durum = d.durumNo and i.izinTuru = t.izinNo order by i.sicilNo");
            ps.setInt(1, sicilNo);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               
                tableContent.add(new PermitConfig(
                        rs.getInt(1), 
                        rs.getInt(4), 
                        rs.getDate(2), 
                        rs.getDate(3),
                        rs.getString(5),
                        rs.getString(6))
                );
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata: "+e.getMessage());
        }
        finally{
            dbcon.disConnect();
        }
        return tableContent;
     
    }
    
    /**
     * LIST ALL OF PERMITS FOR ADMIN PANEL
     */
    ////////////////////////////////////////////////////////////////////////////
    // created an array for ListLeaves Class

    public String[] selectPermitInfo (int kayitNo){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        
        String[] array = new String[6];
        
        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("select concat(p.ad,' ',p.soyad), i.izinTarihi, i.izinBitisi, t.izinAdi, i.durum, i.sicilNo "
                            + "from personel p, izinKontrol i, izinTuru t "
                            + "where i.sicilNo = p.sicilNo and i.izinTuru = t.izinNo and kayitNo =  ?");
            ps.setInt(1, kayitNo);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               array[0] = rs.getString(1);
               array[1] = rs.getDate(2).toString();
               array[2] = rs.getDate(3).toString();
               array[3] = rs.getString(4);
               array[4] = Integer.toString(rs.getInt(5));
               array[5] = Integer.toString(rs.getInt(6));
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata: "+e.getMessage());
        }
        finally{
            dbcon.disConnect();
        }
        return array;
        
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // permit accept or deny control function
    public void setChangesFromPermits(int kayitNo, int status){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        
        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().
                    prepareStatement("update izinKontrol set durum = ? where kayitNo = ?");
            ps.setInt(1, status);
            ps.setInt(2, kayitNo);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Güncelleme Başarılı");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
            dbcon.disConnect();
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    // this function lists all of permit informations
    public List<PermitListConfig> getPermitList(int status){
        DbConnection dbcon = new DbConnection();
        dbcon.Connect();
        List<PermitListConfig> tableContent = new ArrayList<>();
        int i=0;
        
        try {
            PreparedStatement ps = (PreparedStatement) dbcon.getCon().prepareStatement(""
                    + "select "
                    + "i.kayitNo, "
                    + "p.ad, "
                    + "p.soyad, "
                    + "i.izinTarihi, "
                    + "i.izinBitisi, "
                    + "t.izinAdi, "
                    + "d.durum "
                    + "from personel p,izinKontrol i, izinTuru t, izinDurumu d "
                    + "where "
                    + "p.sicilNo = i.sicilNo and "
                    + "i.izinTuru = t.izinNo and "
                    + "i.durum = d.durumNo and  "
                    + "i.durum = ? order by i.kayitNo;");
            ps.setInt(1, status);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               
                tableContent.add(new PermitListConfig(
                        rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(4), 
                        rs.getDate(5))
                );
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata: "+e.getMessage());
        }
        finally{
            dbcon.disConnect();
        }
        return tableContent;
    }
}
