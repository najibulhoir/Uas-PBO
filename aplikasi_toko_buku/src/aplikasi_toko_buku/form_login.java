/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasi_toko_buku;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author MM
 */
public class form_login extends javax.swing.JFrame {
java.sql.Connection konek = new koneksi().si();
    /**
     * Creates new form form_login
     */
    public form_login() {
        initComponents();
    }
    public void log(){
        String nam = null;
        String aks = null;
        if(da.getText().equals("") && psw.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Username/Password Kosong !!!");
            da.requestFocus();
        }else if(da.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Username Kosong !!!");
            da.requestFocus();
        }else if(psw.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Password Kosong !!!");
            psw.requestFocus();
        }else{
        try {
            String sql = "SELECT * FROM karyawan WHERE username = '"+da.getText()+"' and password = '"+psw.getText()+"'";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            aks = rs.getString("akses");
            if(aks.equals("Admin")){
                nam = rs.getString("nama_karyawan");
                rs.last();
                if(rs.getRow()==1){
                    session.setNama(nam);
                    new form_admin().show();
                    dispose();
                }
            }else if(aks.equals("Karyawan")){
                nam = rs.getString("nama_karyawan");
                rs.last();
                if(rs.getRow()==1){
                    session.setNama(nam);
                    new form_karyawan().show();
                    dispose();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Username/Password Tidak Valid !!!");
            da.setText("");
            da.requestFocus();
        }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        da = new javax.swing.JTextField();
        psw = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        usr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("LOGIN");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 230, -1));

        da.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daActionPerformed(evt);
            }
        });
        getContentPane().add(da, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 220, -1));
        getContentPane().add(psw, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 220, -1));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("JavaNetbeans©2018");
        jPanel1.add(jLabel2);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 700, 20));

        usr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Form Login.jpg"))); // NOI18N
        getContentPane().add(usr, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        log();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void daActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField da;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField psw;
    private javax.swing.JLabel usr;
    // End of variables declaration//GEN-END:variables
}
