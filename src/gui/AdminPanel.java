/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.DbWork;
import controller.UserConfig;

/**
 *
 * @author burak
 */
public class AdminPanel extends javax.swing.JFrame {

    /**
     * Creates new form AdminPanel
     */
    private int sicilNo;
    
    public AdminPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_welcome = new javax.swing.JLabel();
        btn_ListPermit = new javax.swing.JButton();
        btn_searchUser = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        btn_userInfo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Panel");

        lbl_welcome.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbl_welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_welcome.setText("Welcome!");

        btn_ListPermit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/labor-day.png"))); // NOI18N
        btn_ListPermit.setText("        List Permits         ");
        btn_ListPermit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ListPermit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_ListPermit.setInheritsPopupMenu(true);
        btn_ListPermit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ListPermitActionPerformed(evt);
            }
        });

        btn_searchUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/human-resources.png"))); // NOI18N
        btn_searchUser.setText("         Search User         ");
        btn_searchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchUserActionPerformed(evt);
            }
        });

        btn_exit.setBackground(new java.awt.Color(192, 57, 43));
        btn_exit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(189, 195, 199));
        btn_exit.setText("Exit");
        btn_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_exit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_exit.setInheritsPopupMenu(true);
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        btn_userInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/admin-with-cogwheels.png"))); // NOI18N
        btn_userInfo.setText("      User Information       ");
        btn_userInfo.setPreferredSize(new java.awt.Dimension(150, 30));
        btn_userInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_userInfoActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administrator Panel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_welcome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_ListPermit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_userInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_searchUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_welcome)
                .addGap(42, 42, 42)
                .addComponent(btn_userInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_searchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ListPermit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_exit)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ListPermitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ListPermitActionPerformed
        ListPermits ll = new ListPermits();
        ll.setVisible(true);
    }//GEN-LAST:event_btn_ListPermitActionPerformed
        //for changing header
    void setHeader(String header){
        lbl_welcome.setText("Welcome "+header+"!");
    }
    void setSicilNo(int sicilNo){
        this.sicilNo = sicilNo;
    }
    
        //this button opens main menu (login menu)
    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        MainMenu main = new MainMenu();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_userInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_userInfoActionPerformed
        DbWork db = new DbWork();
        UserConfig user = db.getUserInfo(sicilNo);
        UpdateUser updateUser = new UpdateUser();
        updateUser.setVisible(true);
        updateUser.setUser(user);
        
        updateUser.setTitle("User Information");
    }//GEN-LAST:event_btn_userInfoActionPerformed

    private void btn_searchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchUserActionPerformed
        SearchUser searchUser = new SearchUser();
        searchUser.setVisible(true);
    }//GEN-LAST:event_btn_searchUserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Darcula".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ListPermit;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_searchUser;
    private javax.swing.JButton btn_userInfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_welcome;
    // End of variables declaration//GEN-END:variables
}
