/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_toko_buku;

import java.awt.Dimension;
import java.awt.Menu;
import java.awt.Toolkit;
import java.io.File;
import java.io.InputStream;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import javax.accessibility.AccessibleAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileView;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author MM
 */
public class form_admin extends javax.swing.JFrame {

    java.sql.Connection konek = new koneksi().si();

    private DefaultTableModel model1;
    private DefaultTableModel model2;
    private DefaultTableModel model3;
    private DefaultTableModel model4;
    private DefaultTableModel model5;
    private JasperReport jasRep;
    private JasperPrint jasPri;
    private JasperDesign jasDes;
    private String sql;

    String nama = session.getNama();
    java.util.Date waktu = new java.util.Date();
    private SimpleDateFormat tglsekarang = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private String tanggal = tglsekarang.format(waktu);

    /**
     * Creates new form form_admin
     */
    public form_admin() {
        initComponents();
//        Dimension dms = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(dms.width,dms.height);
//        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        this.txtppn.hide();
        kodeoto1();
        kodeoto2();
        kodeoto3();
        kodeoto4();
        this.tgl.setText(tanggal);
        nama_admin.setText(nama);
        model1 = new DefaultTableModel();
        this.tabel1.setModel(model1);
        model1.addColumn("ID Karyawan");
        model1.addColumn("Nama Karyawan");
        model1.addColumn("Alamat");
        model1.addColumn("Telepon");
        model1.addColumn("Status");
        model1.addColumn("Username");
        model1.addColumn("Password");
        model1.addColumn("Hak Akses");
        segarkan1();

        model2 = new DefaultTableModel();
        this.tabel2.setModel(model2);
        model2.addColumn("ID Buku");
        model2.addColumn("Judul Buku");
        model2.addColumn("NOISBN");
        model2.addColumn("Penulis");
        model2.addColumn("Penerbit");
        model2.addColumn("Tahun Terbit");
        model2.addColumn("Stok");
        model2.addColumn("Harga Pokok");
        model2.addColumn("Harga Jual");
        model2.addColumn("PPN");
        model2.addColumn("Diskon");
        segarkan2();

        model3 = new DefaultTableModel();
        this.tabel3.setModel(model3);
        model3.addColumn("ID Distributor");
        model3.addColumn("Nama Distributor");
        model3.addColumn("Alamat");
        model3.addColumn("No. Telepon");
        segarkan3();

        model4 = new DefaultTableModel();
        this.tabel4.setModel(model4);
        model4.addColumn("ID Pasok");
        model4.addColumn("Nama Distributor");
        model4.addColumn("Judul Buku");
        model4.addColumn("Jumlah");
        model4.addColumn("Tanggal Beli");
        segarkan4();

        model5 = new DefaultTableModel();
        this.tabel5.setModel(model5);
        model5.addColumn("ID Penjualan");
        model5.addColumn("Judul Buku");
        model5.addColumn("Nama Karyawan");
        model5.addColumn("Jumlah");
        model5.addColumn("Total");
        model5.addColumn("Tanggal Jual");
        segarkan5();

    }

    public void segarkan1() {
        model1.getDataVector().removeAllElements();
        this.model1.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM karyawan";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[9];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                as[4] = rs.getString(5);
                as[5] = rs.getString(6);
                as[6] = rs.getString(7);
                as[7] = rs.getString(8);
                model1.addRow(as);
            }
        } catch (Exception e) {
        }
    }

    public void segarkan2() {
        model2.getDataVector().removeAllElements();
        this.model2.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM buku";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[12];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                as[4] = rs.getString(5);
                as[5] = rs.getString(6);
                as[6] = rs.getString(7);
                as[7] = rs.getString(8);
                as[8] = rs.getString(9);
                as[9] = rs.getString(10);
                as[10] = rs.getString(11);
                model2.addRow(as);
            }
        } catch (Exception e) {
        }
    }

    public void segarkan3() {
        model3.getDataVector().removeAllElements();
        this.model3.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM distributor";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[9];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                model3.addRow(as);
            }
        } catch (Exception e) {
        }
    }

    public void segarkan4() {
        model4.getDataVector().removeAllElements();
        this.model4.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM pasok";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[6];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                as[4] = rs.getString(5);
                model4.addRow(as);
            }
        } catch (Exception e) {
        }
    }

    public void segarkan5() {
        model5.getDataVector().removeAllElements();
        this.model5.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM penjualan";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[7];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                as[4] = rs.getString(5);
                as[5] = rs.getString(6);
                model5.addRow(as);
            }
        } catch (Exception e) {
        }
    }

    public void click1() {
        int i = this.tabel1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String A = (String) model1.getValueAt(i, 0);
        this.kry1.setText(A);
        String B = (String) model1.getValueAt(i, 1);
        this.kry2.setText(B);
        String C = (String) model1.getValueAt(i, 2);
        this.kry3.setText(C);
        String D = (String) model1.getValueAt(i, 3);
        this.kry4.setText(D);
        String E = (String) model1.getValueAt(i, 4);
        this.kry5.setText(E);
        String F = (String) model1.getValueAt(i, 5);
        this.kry6.setText(F);
        String G = (String) model1.getValueAt(i, 6);
        this.kry7.setText(G);
        String H = (String) model1.getValueAt(i, 7);
        this.cbakses.setSelectedItem(H);

    }

    public void click2() {
        int i = this.tabel2.getSelectedRow();
        if (i == -1) {
            return;
        }
        String A = (String) model2.getValueAt(i, 0);
        this.bk1.setText(A);
        String B = (String) model2.getValueAt(i, 1);
        this.bk2.setText(B);
        String C = (String) model2.getValueAt(i, 2);
        this.bk3.setText(C);
        String D = (String) model2.getValueAt(i, 3);
        this.bk4.setText(D);
        String E = (String) model2.getValueAt(i, 4);
        this.bk5.setText(E);
        String F = (String) model2.getValueAt(i, 5);
        this.bk5.setText(F);
        String G = (String) model2.getValueAt(i, 6);
        this.bk7.setText(G);
        String H = (String) model2.getValueAt(i, 7);
        this.bk8.setText(H);
        String I = (String) model2.getValueAt(i, 8);
        this.bk9.setText(I);
        String J = (String) model2.getValueAt(i, 9);
        this.bk10.setText(J);
        String K = (String) model2.getValueAt(i, 10);
        this.bk11.setText(K);
    }

    public void click3() {
        int i = this.tabel3.getSelectedRow();
        if (i == -1) {
            return;
        }
        String A = (String) model3.getValueAt(i, 0);
        this.dst1.setText(A);
        String B = (String) model3.getValueAt(i, 1);
        this.dst2.setText(B);
        String C = (String) model3.getValueAt(i, 2);
        this.dst3.setText(C);
        String D = (String) model3.getValueAt(i, 3);
        this.dst4.setText(D);
    }

    public void click4() {
        int i = this.tabel4.getSelectedRow();
        if (i == -1) {
            return;
        }
        String A = (String) model4.getValueAt(i, 0);
        this.pb1.setText(A);
        String B = (String) model4.getValueAt(i, 1);
        this.pb2.setText(B);
        String C = (String) model4.getValueAt(i, 2);
        this.pb3.setText(C);
        String D = (String) model4.getValueAt(i, 3);
        this.pb4.setText(D);
        String E = (String) model4.getValueAt(i, 4);
        this.pb5.setText(E);

    }

    public void click5() {
        int i = this.tabel5.getSelectedRow();
        if (i == -1) {
            return;
        }
        String A = (String) model5.getValueAt(i, 0);
        this.pj1.setText(A);
        String B = (String) model5.getValueAt(i, 1);
        this.pj2.setText(B);
        String C = (String) model5.getValueAt(i, 2);
        this.pj3.setText(C);
        String D = (String) model5.getValueAt(i, 3);
        this.pj4.setText(D);
        String E = (String) model5.getValueAt(i, 4);
        this.pj5.setText(E);
        String F = (String) model5.getValueAt(i, 5);
        this.pj6.setText(F);
    }

    public void kodeoto1() {
        try {
            String sql = "SELECT sum(kode) as iniv FROM kodeoto";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_kry = rs.getInt("iniv") + 1;

                kry1.setText("KRY" + String.valueOf(id_kry));

            }
        } catch (Exception e) {
        }
    }

    public void kodeoto2() {
        try {
            String sql = "SELECT sum(kode) as iniv FROM kodeoto";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_bk = rs.getInt("iniv") + 1;

                bk1.setText("BK" + String.valueOf(id_bk));

            }
        } catch (Exception e) {
        }
    }

    public void kodeoto3() {
        try {
            String sql = "SELECT sum(kode) as iniv FROM kodeoto";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_dst = rs.getInt("iniv") + 1;

                dst1.setText("DST" + String.valueOf(id_dst));

            }
        } catch (Exception e) {
        }
    }

    public void kodeoto4() {
        try {
            String sql = "SELECT sum(kode) as iniv FROM kodeoto";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_pb = rs.getInt("iniv") + 1;

                pb1.setText("PB" + String.valueOf(id_pb));

            }
        } catch (Exception e) {
        }
    }

    public void ppn() {
        float harga, ppn, hasil;

        harga = Float.valueOf(this.bk8.getText());
        ppn = Float.valueOf(this.bk10.getText());

        hasil = harga + (harga * ppn / 100);
        this.txtppn.setText("" + hasil);
    }

    public void diskon() {
        float diskon, hasilp, hasil;

        diskon = Float.valueOf(this.bk11.getText());
        hasilp = Float.valueOf(this.txtppn.getText());

        hasil = hasilp - (hasilp * diskon / 100);

        this.bk9.setText("" + hasil);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        nama_admin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel1 = new javax.swing.JTable();
        bntcari1 = new javax.swing.JButton();
        btnprint1 = new javax.swing.JButton();
        btnrefresh1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtcari1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        kry1 = new javax.swing.JTextField();
        kry2 = new javax.swing.JTextField();
        kry3 = new javax.swing.JTextField();
        kry4 = new javax.swing.JTextField();
        kry5 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbakses = new javax.swing.JComboBox();
        kry6 = new javax.swing.JTextField();
        kry7 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btnsimpan1 = new javax.swing.JButton();
        btnedit1 = new javax.swing.JButton();
        btnhapus1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel2 = new javax.swing.JTable();
        bntcari2 = new javax.swing.JButton();
        btnprint2 = new javax.swing.JButton();
        btnrefresh2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtcari2 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bk1 = new javax.swing.JTextField();
        bk2 = new javax.swing.JTextField();
        bk3 = new javax.swing.JTextField();
        bk4 = new javax.swing.JTextField();
        bk5 = new javax.swing.JTextField();
        bk6 = new javax.swing.JTextField();
        bk7 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtppn = new javax.swing.JLabel();
        bk8 = new javax.swing.JTextField();
        bk9 = new javax.swing.JTextField();
        bk10 = new javax.swing.JTextField();
        bk11 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        btnsimpan2 = new javax.swing.JButton();
        btnedit2 = new javax.swing.JButton();
        btnhapus2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel3 = new javax.swing.JTable();
        bntcari3 = new javax.swing.JButton();
        btnprint3 = new javax.swing.JButton();
        btnrefresh3 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        dst1 = new javax.swing.JTextField();
        dst2 = new javax.swing.JTextField();
        dst3 = new javax.swing.JTextField();
        dst4 = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        btnsimpan3 = new javax.swing.JButton();
        btnedit3 = new javax.swing.JButton();
        btnhapus3 = new javax.swing.JButton();
        txtcari3 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel4 = new javax.swing.JTable();
        bntcari4 = new javax.swing.JButton();
        btnprint4 = new javax.swing.JButton();
        btnrefresh4 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pb1 = new javax.swing.JTextField();
        pb2 = new javax.swing.JTextField();
        pb3 = new javax.swing.JTextField();
        pb4 = new javax.swing.JTextField();
        pb5 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        btnsimpan4 = new javax.swing.JButton();
        btnedit4 = new javax.swing.JButton();
        btnhapus4 = new javax.swing.JButton();
        txtcari4 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabel5 = new javax.swing.JTable();
        bntcari5 = new javax.swing.JButton();
        btnprint5 = new javax.swing.JButton();
        btnrefresh5 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        pj1 = new javax.swing.JTextField();
        pj2 = new javax.swing.JTextField();
        pj3 = new javax.swing.JTextField();
        pj4 = new javax.swing.JTextField();
        pj5 = new javax.swing.JTextField();
        pj6 = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        btnsimpan5 = new javax.swing.JButton();
        btnedit5 = new javax.swing.JButton();
        btnhapus5 = new javax.swing.JButton();
        txtcari5 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APLIKASI TOKO BUKU");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/header.png"))); // NOI18N

        tgl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tgl.setForeground(new java.awt.Color(255, 255, 255));
        tgl.setText("Tanggal");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText(" Selamat Datang !!!");

        nama_admin.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        nama_admin.setForeground(new java.awt.Color(255, 255, 255));
        nama_admin.setText("Nama");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nama_admin)
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(tgl)
                        .addGap(27, 27, 27))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tgl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(nama_admin)))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("JavaNetbeans©2018");

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        tabel1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel1);

        bntcari1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        bntcari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        bntcari1.setText("Cari");
        bntcari1.setToolTipText("Search");
        bntcari1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntcari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntcari1ActionPerformed(evt);
            }
        });

        btnprint1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnprint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        btnprint1.setText("Print");
        btnprint1.setToolTipText("Print");
        btnprint1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnprint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprint1ActionPerformed(evt);
            }
        });

        btnrefresh1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnrefresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        btnrefresh1.setText("Segarkan");
        btnrefresh1.setToolTipText("Refresh");
        btnrefresh1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnrefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefresh1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Data Karyawan");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnrefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnprint1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntcari1)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcari1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnprint1)
                    .addComponent(btnrefresh1)))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Id Karyawan");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Nama Karyawan");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Alamat");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Telepon");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Status");

        kry1.setEnabled(false);

        kry2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kry2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kry3)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kry1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(kry2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(kry4)
                    .addComponent(kry5))
                .addGap(106, 106, 106))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kry1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(kry2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(kry3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(kry4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(kry5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Username");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Password");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Hak Akses");

        cbakses.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbakses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Pilih Type User--", "Admin", "Karyawan" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbakses, 0, 213, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kry7))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kry6)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(kry6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(kry7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(cbakses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnsimpan1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnsimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        btnsimpan1.setText("Simpan");
        btnsimpan1.setToolTipText("Save");
        btnsimpan1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpan1ActionPerformed(evt);
            }
        });

        btnedit1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnedit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit_16x16.png"))); // NOI18N
        btnedit1.setText("Edit");
        btnedit1.setToolTipText("Edit");
        btnedit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnedit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedit1ActionPerformed(evt);
            }
        });

        btnhapus1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnhapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete_16x16.png"))); // NOI18N
        btnhapus1.setText("Hapus");
        btnhapus1.setToolTipText("Delete");
        btnhapus1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapus1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(btnsimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnedit1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnsimpan1)
                .addComponent(btnedit1)
                .addComponent(btnhapus1))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("          Karyawan          ", jPanel3);

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        tabel2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel2);

        bntcari2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        bntcari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        bntcari2.setText("Cari");
        bntcari2.setToolTipText("Search");
        bntcari2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntcari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntcari2ActionPerformed(evt);
            }
        });

        btnprint2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnprint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        btnprint2.setText("Print");
        btnprint2.setToolTipText("Print");
        btnprint2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnprint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprint2ActionPerformed(evt);
            }
        });

        btnrefresh2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnrefresh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        btnrefresh2.setText("Segarkan");
        btnrefresh2.setToolTipText("Refresh");
        btnrefresh2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnrefresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefresh2ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Data Buku");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnrefresh2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnprint2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcari2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntcari2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntcari2)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcari2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnprint2)
                    .addComponent(btnrefresh2)))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("ID Buku");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Judul Buku");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("NOISBN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Penulis");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Penerbit");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tahun Terbit");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Stok");

        bk1.setEnabled(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bk1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bk4))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bk5))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bk6))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bk7))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bk2)
                            .addComponent(bk3))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(bk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(bk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(bk3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(bk4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(bk5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(bk6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(bk7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Harga Pokok");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Harga Jual");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("PPN");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Diskon");

        txtppn.setText("hasilppn");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtppn)
                        .addGap(166, 166, 166))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bk11))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bk8, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bk9))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bk10)))
                        .addContainerGap(38, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(bk8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(bk9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(bk10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(bk11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(txtppn)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnsimpan2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnsimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        btnsimpan2.setText("Simpan");
        btnsimpan2.setToolTipText("Save");
        btnsimpan2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpan2ActionPerformed(evt);
            }
        });

        btnedit2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnedit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit_16x16.png"))); // NOI18N
        btnedit2.setText("Edit");
        btnedit2.setToolTipText("Edit");
        btnedit2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnedit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedit2ActionPerformed(evt);
            }
        });

        btnhapus2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnhapus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete_16x16.png"))); // NOI18N
        btnhapus2.setText("Hapus");
        btnhapus2.setToolTipText("Delete");
        btnhapus2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhapus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapus2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(btnsimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnedit2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnhapus2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnsimpan2)
                .addComponent(btnedit2)
                .addComponent(btnhapus2))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 61, Short.MAX_VALUE))
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("              Buku              ", jPanel4);

        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        tabel3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel3);

        bntcari3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        bntcari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        bntcari3.setText("Cari");
        bntcari3.setToolTipText("Search");
        bntcari3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntcari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntcari3ActionPerformed(evt);
            }
        });

        btnprint3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnprint3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        btnprint3.setText("Print");
        btnprint3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnprint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprint3ActionPerformed(evt);
            }
        });

        btnrefresh3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnrefresh3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        btnrefresh3.setText("Segarkan");
        btnrefresh3.setToolTipText("Refresh");
        btnrefresh3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnrefresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefresh3ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Data Distributor");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnrefresh3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnprint3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntcari3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntcari3)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnprint3)
                    .addComponent(btnrefresh3)))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("ID Distributor");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Nama Distributor");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Alamat");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("No. Telepon");

        dst1.setEnabled(false);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dst1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dst2))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dst3))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dst4)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(dst1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(dst2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(dst3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(dst4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnsimpan3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnsimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        btnsimpan3.setText("Simpan");
        btnsimpan3.setToolTipText("Save");
        btnsimpan3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpan3ActionPerformed(evt);
            }
        });

        btnedit3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnedit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit_16x16.png"))); // NOI18N
        btnedit3.setText("Edit");
        btnedit3.setToolTipText("Edit");
        btnedit3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnedit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedit3ActionPerformed(evt);
            }
        });

        btnhapus3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnhapus3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete_16x16.png"))); // NOI18N
        btnhapus3.setText("Hapus");
        btnhapus3.setToolTipText("Delete");
        btnhapus3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhapus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapus3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(btnsimpan3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnedit3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnhapus3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnsimpan3)
                .addComponent(btnedit3)
                .addComponent(btnhapus3))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(txtcari3, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtcari3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("           Distributor           ", jPanel5);

        jPanel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        tabel4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabel4);

        bntcari4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        bntcari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        bntcari4.setText("Cari");
        bntcari4.setToolTipText("Search");
        bntcari4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntcari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntcari4ActionPerformed(evt);
            }
        });

        btnprint4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnprint4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        btnprint4.setText("Print");
        btnprint4.setToolTipText("Print");
        btnprint4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnprint4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprint4ActionPerformed(evt);
            }
        });

        btnrefresh4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnrefresh4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        btnrefresh4.setText("Segarkan");
        btnrefresh4.setToolTipText("Refresh");
        btnrefresh4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnrefresh4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefresh4ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Data Pembelian Buku");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnrefresh4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnprint4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntcari4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntcari4)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnprint4)
                    .addComponent(btnrefresh4)))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("ID Pasok");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Nama Distributor");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Judul Buku");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Jumlah");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Tanggal Beli");

        pb1.setEnabled(false);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pb1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pb2))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pb3))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pb4))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pb5)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(pb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(pb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(pb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(pb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(pb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnsimpan4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnsimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        btnsimpan4.setText("Simpan");
        btnsimpan4.setToolTipText("Save");
        btnsimpan4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpan4ActionPerformed(evt);
            }
        });

        btnedit4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnedit4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit_16x16.png"))); // NOI18N
        btnedit4.setText("Edit");
        btnedit4.setToolTipText("Edit");
        btnedit4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnedit4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedit4ActionPerformed(evt);
            }
        });

        btnhapus4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnhapus4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete_16x16.png"))); // NOI18N
        btnhapus4.setText("Hapus");
        btnhapus4.setToolTipText("Delete");
        btnhapus4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhapus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapus4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(btnsimpan4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnedit4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnhapus4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnsimpan4)
                .addComponent(btnedit4)
                .addComponent(btnhapus4))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(txtcari4, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtcari4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(418, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("       Pembelian Buku       ", jPanel6);

        jPanel28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        tabel5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabel5);

        bntcari5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        bntcari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search_16x16.png"))); // NOI18N
        bntcari5.setText("Cari");
        bntcari5.setToolTipText("Search");
        bntcari5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntcari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntcari5ActionPerformed(evt);
            }
        });

        btnprint5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnprint5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print_16x16.png"))); // NOI18N
        btnprint5.setText("Print");
        btnprint5.setToolTipText("Print");
        btnprint5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnprint5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprint5ActionPerformed(evt);
            }
        });

        btnrefresh5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnrefresh5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh_16x16.png"))); // NOI18N
        btnrefresh5.setText("Segarkan");
        btnrefresh5.setToolTipText("Refresh");
        btnrefresh5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnrefresh5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefresh5ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Data Penjualan Buku");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnrefresh5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnprint5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntcari5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntcari5)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnprint5)
                    .addComponent(btnrefresh5)))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("ID Penjualan");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Judul Buku");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Nama Karyawan");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Jumlah");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("Total");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Tanggal Jual");

        pj1.setEnabled(false);

        pj3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pj3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pj1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pj2))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pj3))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pj4))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pj5))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pj6)))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(pj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(pj2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(pj3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(pj4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(pj5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(pj6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnsimpan5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnsimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Save_16x16.png"))); // NOI18N
        btnsimpan5.setText("Simpan");
        btnsimpan5.setToolTipText("Save");
        btnsimpan5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpan5ActionPerformed(evt);
            }
        });

        btnedit5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnedit5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit_16x16.png"))); // NOI18N
        btnedit5.setText("Edit");
        btnedit5.setToolTipText("Edit");
        btnedit5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnedit5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedit5ActionPerformed(evt);
            }
        });

        btnhapus5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnhapus5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete_16x16.png"))); // NOI18N
        btnhapus5.setText("Hapus");
        btnhapus5.setToolTipText("Delete");
        btnhapus5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhapus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapus5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(btnsimpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnedit5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnhapus5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnsimpan5)
                .addComponent(btnedit5)
                .addComponent(btnhapus5))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addComponent(txtcari5, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtcari5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(636, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("       Penjualan Buku       ", jPanel7);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jMenu1.setText("About");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Logout");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Log Out", "Informasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            new form_login().show();
            dispose();
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    private void btnhapus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus5ActionPerformed
        // TODO add your handling code here:
        if (pj1.getText().equals("") || pj2.getText().equals("") || pj3.getText().equals("") || pj4.getText().equals("") || pj5.getText().equals("") || pj6.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Menghapus !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "DELETE FROM `penjualan` WHERE id_penjualan = '" + pj1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                    segarkan5();
                    pj1.setText("");
                    pj2.setText("");
                    pj3.setText("");
                    pj4.setText("");
                    pj5.setText("");
                    pj6.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnhapus5ActionPerformed

    private void btnedit5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedit5ActionPerformed
        // TODO add your handling code here:
        if (pj1.getText().equals("") || pj2.getText().equals("") || pj3.getText().equals("") || pj4.getText().equals("") || pj5.getText().equals("") || pj6.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Mengedit !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "UPDATE `penjualan` SET `id_penjualan`='" + pj1.getText() + "',`judul_buku`='" + pj2.getText() + "',`nama_karyawan`='" + pj3.getText() + "',`jumlah`='" + pj4.getText() + "',`total`='" + pj5.getText() + "',`tanggal`='" + pj6.getText() + "' WHERE id_penjualan = '" + pj1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Edit");
                    segarkan5();
                    pj1.setText("");
                    pj2.setText("");
                    pj3.setText("");
                    pj4.setText("");
                    pj5.setText("");
                    pj6.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Edit");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnedit5ActionPerformed

    private void btnsimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan5ActionPerformed
        // TODO add your handling code here:
        if (pj1.getText().equals("") || pj2.getText().equals("") || pj3.getText().equals("") || pj4.getText().equals("") || pj5.getText().equals("") || pj6.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            try {
                String sql = "INSERT INTO `penjualan`(`id_penjualan`, `judul_buku`, `nama_karyawan`, `jumlah`, `total`, `tanggal`) VALUES ('" + pj1.getText() + "','" + pj2.getText() + "','" + pj3.getText() + "','" + pj4.getText() + "','" + pj5.getText() + "','" + pj6.getText() + "')";
                PreparedStatement pst = konek.prepareStatement(sql);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
                segarkan5();
                pj1.setText("");
                pj2.setText("");
                pj3.setText("");
                pj4.setText("");
                pj5.setText("");
                pj6.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnsimpan5ActionPerformed

    private void btnrefresh5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefresh5ActionPerformed
        // TODO add your handling code here:
        segarkan5();
        pj1.setText("");
        pj2.setText("");
        pj3.setText("");
        pj4.setText("");
        pj5.setText("");
        pj6.setText("");
        txtcari5.setText("");

    }//GEN-LAST:event_btnrefresh5ActionPerformed

    private void bntcari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntcari5ActionPerformed
        // TODO add your handling code here:
        this.model5.getDataVector().removeAllElements();
        this.model5.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM penjualan WHERE id_penjualan = '" + txtcari5.getText() + "' or judul_buku = '" + txtcari5.getText() + "'";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[7];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                as[4] = rs.getString(5);
                as[5] = rs.getString(6);
                model5.addRow(as);

                pj1.setText("" + as[0]);
                pj2.setText("" + as[1]);
                pj3.setText("" + as[2]);
                pj4.setText("" + as[3]);
                pj5.setText("" + as[4]);
                pj6.setText("" + as[5]);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bntcari5ActionPerformed

    private void tabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel5MouseClicked
        // TODO add your handling code here:
        click5();
    }//GEN-LAST:event_tabel5MouseClicked

    private void btnhapus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus4ActionPerformed
        // TODO add your handling code here:
        if (pb1.getText().equals("") || pb2.getText().equals("") || pb3.getText().equals("") || pb4.getText().equals("") || pb5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Menghapus !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "DELETE FROM `pasok` WHERE id_pasok = '" + pb1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                    segarkan4();
                    kodeoto4();
                    pb2.setText("");
                    pb3.setText("");
                    pb4.setText("");
                    pb5.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnhapus4ActionPerformed

    private void btnedit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedit4ActionPerformed
        // TODO add your handling code here:
        if (pb1.getText().equals("") || pb2.getText().equals("") || pb3.getText().equals("") || pb4.getText().equals("") || pb5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Mengedit !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "UPDATE `pasok` SET `id_pasok`='" + pb1.getText() + "',`nama_distributor`='" + pb2.getText() + "',`judul_buku`='" + pb3.getText() + "',`jumlah`='" + pb4.getText() + "',`tanggal_beli`='" + pb5.getText() + "' WHERE id_pasok ='" + pb1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Edit");
                    segarkan4();
                    kodeoto4();
                    pb2.setText("");
                    pb3.setText("");
                    pb4.setText("");
                    pb5.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Edit");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnedit4ActionPerformed

    private void btnsimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan4ActionPerformed
        // TODO add your handling code here:
        if (pb1.getText().equals("") || pb2.getText().equals("") || pb3.getText().equals("") || pb4.getText().equals("") || pb5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            try {
                String sql = "INSERT INTO `pasok`(`id_pasok`, `nama_distributor`, `judul_buku`, `jumlah`, `tanggal_beli`) VALUES ('" + pb1.getText() + "','" + pb2.getText() + "','" + pb3.getText() + "','" + pb4.getText() + "','" + pb5.getText() + "')";
                PreparedStatement pst = konek.prepareStatement(sql);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
                segarkan4();
                kodeoto4();
                pb1.setText("");
                pb2.setText("");
                pb3.setText("");
                pb4.setText("");
                pb5.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnsimpan4ActionPerformed

    private void btnrefresh4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefresh4ActionPerformed
        // TODO add your handling code here:
        segarkan4();
        kodeoto4();

        pb2.setText("");
        pb3.setText("");
        pb4.setText("");
        pb5.setText("");
        txtcari4.setText("");
    }//GEN-LAST:event_btnrefresh4ActionPerformed

    private void bntcari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntcari4ActionPerformed
        // TODO add your handling code here:
        this.model4.getDataVector().removeAllElements();
        this.model4.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM pasok WHERE id_pasok = '" + txtcari4.getText() + "' or nama_distributor= '" + txtcari4.getText() + "'";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[5];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                as[4] = rs.getString(5);
                model4.addRow(as);

                pb1.setText("" + as[0]);
                pb2.setText("" + as[1]);
                pb3.setText("" + as[2]);
                pb4.setText("" + as[3]);
                pb5.setText("" + as[4]);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bntcari4ActionPerformed

    private void tabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel4MouseClicked
        // TODO add your handling code here:
        click4();
    }//GEN-LAST:event_tabel4MouseClicked

    private void btnhapus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus3ActionPerformed
        // TODO add your handling code here:
        if (dst1.getText().equals("") || dst2.getText().equals("") || dst3.getText().equals("") || dst4.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Menghapus !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "DELETE FROM `distributor` WHERE id_distributor = '" + dst1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                    segarkan3();
                    kodeoto3();
                    dst2.setText("");
                    dst3.setText("");
                    dst4.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnhapus3ActionPerformed

    private void btnedit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedit3ActionPerformed
        // TODO add your handling code here:
        if (dst1.getText().equals("") || dst2.getText().equals("") || dst3.getText().equals("") || dst4.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Mengedit !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "UPDATE `distributor` SET `id_distributor`='" + dst1.getText() + "',`nama_distributor`='" + dst2.getText() + "',`alamat`='" + dst3.getText() + "',`telepon`='" + dst4.getText() + "' WHERE id_distributor ='" + dst1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Edit");
                    segarkan3();
                    kodeoto3();
                    dst2.setText("");
                    dst3.setText("");
                    dst4.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Edit");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnedit3ActionPerformed

    private void btnsimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan3ActionPerformed
        // TODO add your handling code here:
        if (dst1.getText().equals("") || dst2.getText().equals("") || dst3.getText().equals("") || dst4.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            try {
                String sql = "INSERT INTO `distributor`(`id_distributor`, `nama_distributor`, `alamat`, `telepon`) VALUES ('" + dst1.getText() + "','" + dst2.getText() + "','" + dst3.getText() + "','" + dst4.getText() + "')";
                PreparedStatement pst = konek.prepareStatement(sql);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
                segarkan3();
                kodeoto3();
                dst1.setText("");
                dst2.setText("");
                dst3.setText("");
                dst4.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnsimpan3ActionPerformed

    private void btnrefresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefresh3ActionPerformed
        // TODO add your handling code here:
        segarkan3();
        kodeoto3();

        dst2.setText("");
        dst3.setText("");
        dst4.setText("");
        txtcari3.setText("");
    }//GEN-LAST:event_btnrefresh3ActionPerformed

    private void bntcari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntcari3ActionPerformed
        // TODO add your handling code here:
        this.model3.getDataVector().removeAllElements();
        this.model3.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM distributor WHERE id_distributor = '" + txtcari3.getText() + "' or nama_distributor = '" + txtcari3.getText() + "'";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[5];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                model3.addRow(as);

                dst1.setText("" + as[0]);
                dst2.setText("" + as[1]);
                dst3.setText("" + as[2]);
                dst4.setText("" + as[3]);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bntcari3ActionPerformed

    private void tabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel3MouseClicked
        // TODO add your handling code here:
        click3();
    }//GEN-LAST:event_tabel3MouseClicked

    private void btnhapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus2ActionPerformed
        // TODO add your handling code here:
        if (bk1.getText().equals("") || bk2.getText().equals("") || bk3.getText().equals("") || bk4.getText().equals("") || bk5.getText().equals("") || bk5.getText().equals("") || bk7.getText().equals("") || bk8.getText().equals("") || bk9.getText().equals("") || bk10.getText().equals("") || bk11.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Menghapus !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "DELETE FROM `buku` WHERE id_buku = '" + bk1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                    segarkan2();
                    kodeoto2();

                    bk2.setText("");
                    bk3.setText("");
                    bk4.setText("");
                    bk5.setText("");
                    bk5.setText("");
                    bk7.setText("");
                    bk8.setText("");
                    bk9.setText("");
                    bk10.setText("");
                    bk11.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnhapus2ActionPerformed

    private void btnedit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedit2ActionPerformed
        // TODO add your handling code here:
        if (bk1.getText().equals("") || bk2.getText().equals("") || bk3.getText().equals("") || bk4.getText().equals("") || bk5.getText().equals("") || bk5.getText().equals("") || bk7.getText().equals("") || bk8.getText().equals("") || bk9.getText().equals("") || bk10.getText().equals("") || bk11.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Mengedit !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "UPDATE `buku` SET `id_buku`='" + bk1.getText() + "',`judul_buku`='" + bk2.getText() + "',`noisbn`='" + bk3.getText() + "',`penulis`='" + bk4.getText() + "',`penerbit`='" + bk5.getText() + "',`tahun_terbit`='" + bk5.getText() + "',`stok`='" + bk7.getText() + "',`harga_pokok`='" + bk8.getText() + "',`harga_jual`='" + bk9.getText() + "',`ppn`='" + bk10.getText() + "',`diskon`='" + bk11.getText() + "' WHERE id_buku = '" + bk1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Edit");
                    segarkan2();
                    kodeoto2();
                    bk2.setText("");
                    bk3.setText("");
                    bk4.setText("");
                    bk5.setText("");
                    bk5.setText("");
                    bk7.setText("");
                    bk8.setText("");
                    bk9.setText("");
                    bk10.setText("");
                    bk11.setText("");
                    bk1.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Edit");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnedit2ActionPerformed

    private void btnsimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan2ActionPerformed
        // TODO add your handling code here:
        if (bk1.getText().equals("") || bk2.getText().equals("") || bk3.getText().equals("") || bk4.getText().equals("") || bk5.getText().equals("") || bk5.getText().equals("") || bk7.getText().equals("") || bk8.getText().equals("") || bk9.getText().equals("") || bk10.getText().equals("") || bk11.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else {
            try {
                String sql = "INSERT INTO `buku`(`id_buku`, `judul_buku`, `noisbn`, `penulis`, `penerbit`, `tahun_terbit`, `stok`, `harga_pokok`, `harga_jual`, `ppn`, `diskon`) VALUES ('" + bk1.getText() + "','" + bk2.getText() + "','" + bk3.getText() + "','" + bk4.getText() + "','" + bk5.getText() + "','" + bk5.getText() + "','" + bk7.getText() + "','" + bk8.getText() + "','" + bk9.getText() + "','" + bk10.getText() + "','" + bk11.getText() + "')";
                PreparedStatement pst = konek.prepareStatement(sql);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
                segarkan2();
                kodeoto2();
                bk1.setText("");
                bk2.setText("");
                bk3.setText("");
                bk4.setText("");
                bk5.setText("");
                bk5.setText("");
                bk7.setText("");
                bk8.setText("");
                bk9.setText("");
                bk10.setText("");
                bk11.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnsimpan2ActionPerformed

    private void btnrefresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefresh2ActionPerformed
        // TODO add your handling code here:
        segarkan2();
        kodeoto2();

        bk2.setText("");
        bk3.setText("");
        bk4.setText("");
        bk5.setText("");
        bk5.setText("");
        bk7.setText("");
        bk8.setText("");
        bk9.setText("");
        bk10.setText("");
        bk11.setText("");
        txtcari2.setText("");
    }//GEN-LAST:event_btnrefresh2ActionPerformed

    private void bntcari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntcari2ActionPerformed
        // TODO add your handling code here:
        this.model2.getDataVector().removeAllElements();
        this.model2.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM buku WHERE id_buku = '" + txtcari2.getText() + "' or judul_buku = '" + txtcari2.getText() + "'";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[12];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                as[4] = rs.getString(5);
                as[5] = rs.getString(6);
                as[6] = rs.getString(7);
                as[7] = rs.getString(8);
                as[8] = rs.getString(9);
                as[9] = rs.getString(10);
                as[10] = rs.getString(11);
                model2.addRow(as);

                bk1.setText("" + as[0]);
                bk2.setText("" + as[1]);
                bk3.setText("" + as[2]);
                bk4.setText("" + as[3]);
                bk5.setText("" + as[4]);
                bk5.setText("" + as[5]);
                bk7.setText("" + as[6]);
                bk8.setText("" + as[7]);
                bk9.setText("" + as[8]);
                bk10.setText("" + as[9]);
                bk11.setText("" + as[10]);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bntcari2ActionPerformed

    private void tabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel2MouseClicked
        // TODO add your handling code here:
        click2();
    }//GEN-LAST:event_tabel2MouseClicked

    private void btnhapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus1ActionPerformed
        // TODO add your handling code here:
        if (kry1.getText().equals("") || kry2.getText().equals("") || kry3.getText().equals("") || kry4.getText().equals("") || kry5.getText().equals("") || kry6.getText().equals("") || kry7.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else if (cbakses.getSelectedItem().equals("--Pilih Type User--")) {
            JOptionPane.showMessageDialog(null, "Hak Akses Belum Terpilih !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Menghapus !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "DELETE FROM `karyawan` WHERE id_karyawan = '" + kry1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                    segarkan1();
                    kodeoto1();

                    kry2.setText("");
                    kry3.setText("");
                    kry4.setText("");
                    kry5.setText("");
                    kry6.setText("");
                    kry7.setText("");
                    cbakses.setSelectedItem("--Pilih Type User--");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnhapus1ActionPerformed

    private void btnedit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedit1ActionPerformed
        // TODO add your handling code here:
        if (kry1.getText().equals("") || kry2.getText().equals("") || kry3.getText().equals("") || kry4.getText().equals("") || kry5.getText().equals("") || kry6.getText().equals("") || kry7.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else if (cbakses.getSelectedItem().equals("--Pilih Type User--")) {
            JOptionPane.showMessageDialog(null, "Hak Akses Belum Terpilih !!!");
        } else {
            int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin Ingin Mengedit !!!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String sql = "UPDATE `karyawan` SET `id_karyawan`='" + kry1.getText() + "',`nama_karyawan`='" + kry2.getText() + "',`alamat`='" + kry3.getText() + "',`telepon`='" + kry4.getText() + "',`status`='" + kry5.getText() + "',`username`='" + kry6.getText() + "',`password`=md5('" + kry7.getText() + "'),`akses`='" + cbakses.getSelectedItem() + "' WHERE id_karyawan = '" + kry1.getText() + "'";
                    PreparedStatement pst = konek.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Edit");
                    segarkan1();
                    kodeoto1();

                    kry2.setText("");
                    kry3.setText("");
                    kry4.setText("");
                    kry5.setText("");
                    kry6.setText("");
                    kry7.setText("");
                    cbakses.setSelectedItem("--Pilih Type User--");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Edit");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnedit1ActionPerformed

    private void btnsimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan1ActionPerformed
        // TODO add your handling code here:
        if (kry1.getText().equals("") || kry2.getText().equals("") || kry3.getText().equals("") || kry4.getText().equals("") || kry5.getText().equals("") || kry6.getText().equals("") || kry7.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Inputan Kosong !!!");
        } else if (cbakses.getSelectedItem().equals("--Pilih Type User--")) {
            JOptionPane.showMessageDialog(null, "Hak Akses Belum Terpilih !!!");
        } else {
            try {
                String sql = "INSERT INTO `karyawan`(`id_karyawan`, `nama_karyawan`, `alamat`, `telepon`, `status`, `username`, `password`, `akses`) VALUES ('" + kry1.getText() + "','" + kry2.getText() + "','" + kry3.getText() + "','" + kry4.getText() + "','" + kry5.getText() + "','" + kry6.getText() + "',md5('" + kry7.getText() + "'),'" + cbakses.getSelectedItem() + "')";
                PreparedStatement pst = konek.prepareStatement(sql);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
                segarkan1();
                kodeoto1();
                kry2.setText("");
                kry3.setText("");
                kry4.setText("");
                kry5.setText("");
                kry6.setText("");
                kry7.setText("");
                cbakses.setSelectedItem("--Pilih Type User--");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan");
                System.out.println(e);
            }
            try {
                String sql = "INSERT INTO `kodeoto`(`id`) VALUES ('" + kry1.getText() + "')";
                PreparedStatement pst = konek.prepareStatement(sql);

                pst.executeUpdate();

            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_btnsimpan1ActionPerformed

    private void btnrefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefresh1ActionPerformed
        // TODO add your handling code here:
        segarkan1();
        kodeoto1();
        kry2.setText("");
        kry3.setText("");
        kry4.setText("");
        kry5.setText("");
        kry6.setText("");
        kry7.setText("");
        txtcari1.setText("");
        cbakses.setSelectedItem("--Pilih Type User--");
    }//GEN-LAST:event_btnrefresh1ActionPerformed

    private void bntcari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntcari1ActionPerformed
        // TODO add your handling code here:
        this.model1.getDataVector().removeAllElements();
        this.model1.fireTableDataChanged();
        try {
            String sql = "SELECT * FROM karyawan WHERE id_karyawan ='" + txtcari1.getText() + "' or nama_karyawan = '" + txtcari1.getText() + "'";
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object as[] = new Object[9];
                as[0] = rs.getString(1);
                as[1] = rs.getString(2);
                as[2] = rs.getString(3);
                as[3] = rs.getString(4);
                as[4] = rs.getString(5);
                as[5] = rs.getString(6);
                as[6] = rs.getString(7);
                as[7] = rs.getString(8);
                model1.addRow(as);

                kry1.setText("" + as[0]);
                kry2.setText("" + as[1]);
                kry3.setText("" + as[2]);
                kry4.setText("" + as[3]);
                kry5.setText("" + as[4]);
                kry6.setText("" + as[5]);
                kry7.setText("" + as[6]);
                cbakses.setSelectedItem("" + as[7]);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bntcari1ActionPerformed

    private void tabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel1MouseClicked
        // TODO add your handling code here:
        click1();
    }//GEN-LAST:event_tabel1MouseClicked

    private void btnprint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprint1ActionPerformed
           HashMap param = new HashMap();
        try {
            InputStream report = getClass().getResourceAsStream("/laporan/report1.jrxml");
            jasDes = JRXmlLoader.load(report);
            JRDesignQuery newQuery= new JRDesignQuery();
            newQuery.setText(sql);
            jasDes.setQuery(newQuery);
            param.clear();
            jasRep= JasperCompileManager.compileReport(jasDes);
            jasPri= JasperFillManager.fillReport(jasRep, null, konek);
            JasperViewer.viewReport(jasPri, false);

        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnprint1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        new About1(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void bk10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bk10KeyReleased
        // TODO add your handling code here:
        ppn();
    }//GEN-LAST:event_bk10KeyReleased

    private void bk11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bk11KeyReleased
        // TODO add your handling code here:
        diskon();
    }//GEN-LAST:event_bk11KeyReleased

    private void btnprint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprint2ActionPerformed
        // TODO add your handling code here:
        try {
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnprint2ActionPerformed

    private void btnprint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprint3ActionPerformed
        // TODO add your handling code here:
        try {
           

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnprint3ActionPerformed

    private void btnprint4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprint4ActionPerformed
        // TODO add your handling code here:
        try {
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnprint4ActionPerformed

    private void btnprint5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprint5ActionPerformed
        // TODO add your handling code here:
        try {
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnprint5ActionPerformed

    private void kry2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kry2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kry2ActionPerformed

    private void pj3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pj3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pj3ActionPerformed

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
            java.util.logging.Logger.getLogger(form_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bk1;
    private javax.swing.JTextField bk10;
    private javax.swing.JTextField bk11;
    private javax.swing.JTextField bk2;
    private javax.swing.JTextField bk3;
    private javax.swing.JTextField bk4;
    private javax.swing.JTextField bk5;
    private javax.swing.JTextField bk6;
    private javax.swing.JTextField bk7;
    private javax.swing.JTextField bk8;
    private javax.swing.JTextField bk9;
    private javax.swing.JButton bntcari1;
    private javax.swing.JButton bntcari2;
    private javax.swing.JButton bntcari3;
    private javax.swing.JButton bntcari4;
    private javax.swing.JButton bntcari5;
    private javax.swing.JButton btnedit1;
    private javax.swing.JButton btnedit2;
    private javax.swing.JButton btnedit3;
    private javax.swing.JButton btnedit4;
    private javax.swing.JButton btnedit5;
    private javax.swing.JButton btnhapus1;
    private javax.swing.JButton btnhapus2;
    private javax.swing.JButton btnhapus3;
    private javax.swing.JButton btnhapus4;
    private javax.swing.JButton btnhapus5;
    private javax.swing.JButton btnprint1;
    private javax.swing.JButton btnprint2;
    private javax.swing.JButton btnprint3;
    private javax.swing.JButton btnprint4;
    private javax.swing.JButton btnprint5;
    private javax.swing.JButton btnrefresh1;
    private javax.swing.JButton btnrefresh2;
    private javax.swing.JButton btnrefresh3;
    private javax.swing.JButton btnrefresh4;
    private javax.swing.JButton btnrefresh5;
    private javax.swing.JButton btnsimpan1;
    private javax.swing.JButton btnsimpan2;
    private javax.swing.JButton btnsimpan3;
    private javax.swing.JButton btnsimpan4;
    private javax.swing.JButton btnsimpan5;
    private javax.swing.JComboBox cbakses;
    private javax.swing.JTextField dst1;
    private javax.swing.JTextField dst2;
    private javax.swing.JTextField dst3;
    private javax.swing.JTextField dst4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField kry1;
    private javax.swing.JTextField kry2;
    private javax.swing.JTextField kry3;
    private javax.swing.JTextField kry4;
    private javax.swing.JTextField kry5;
    private javax.swing.JTextField kry6;
    private javax.swing.JTextField kry7;
    private javax.swing.JLabel nama_admin;
    private javax.swing.JTextField pb1;
    private javax.swing.JTextField pb2;
    private javax.swing.JTextField pb3;
    private javax.swing.JTextField pb4;
    private javax.swing.JTextField pb5;
    private javax.swing.JTextField pj1;
    private javax.swing.JTextField pj2;
    private javax.swing.JTextField pj3;
    private javax.swing.JTextField pj4;
    private javax.swing.JTextField pj5;
    private javax.swing.JTextField pj6;
    private javax.swing.JTable tabel1;
    private javax.swing.JTable tabel2;
    private javax.swing.JTable tabel3;
    private javax.swing.JTable tabel4;
    private javax.swing.JTable tabel5;
    private javax.swing.JLabel tgl;
    private javax.swing.JTextField txtcari1;
    private javax.swing.JTextField txtcari2;
    private javax.swing.JTextField txtcari3;
    private javax.swing.JTextField txtcari4;
    private javax.swing.JTextField txtcari5;
    private javax.swing.JLabel txtppn;
    // End of variables declaration//GEN-END:variables
}
