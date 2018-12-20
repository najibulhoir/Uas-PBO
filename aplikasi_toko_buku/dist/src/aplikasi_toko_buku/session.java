/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasi_toko_buku;

/**
 *
 * @author MM
 */
public class session {
    private  static String nama;
    public static String getNama(){
        return nama;
    }
    public static void setNama(String name){
        session.nama = name;
    }
    
}
