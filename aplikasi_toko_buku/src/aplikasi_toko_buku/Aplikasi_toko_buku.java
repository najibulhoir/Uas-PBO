/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasi_toko_buku;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author MM
 */
public class Aplikasi_toko_buku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            SwingUtilities.updateComponentTreeUI( new form_admin());
        } catch (Exception e) {
            System.out.println(e);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new form_admin().setVisible(true);
                //new About().setVisible(true);
                //new form_karyawan().setVisible(true);
                new form_login().setVisible(true);
            }
        });
    }
    
}
