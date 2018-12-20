/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasi_toko_buku;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
/**
 *
 * @author MM
 */
public class koneksi {
    
    Connection konek;
    public Connection si(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil LOAD ke Driver");
        } catch (Exception e) {
            System.out.println("gagal load ke Driver"+e);
        }
        try {
            String url = "jdbc:mysql://localhost:3306/aplikasi_toko_buku";
            konek = DriverManager.getConnection(url, "root", "");
            System.out.println("Berhasil Koneksi Ke DB");
        } catch (Exception e) {
            System.out.println("gagal koneksi ke DB"+e);
        }
        return konek;
    }
    
}
