/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otelotomasyonu;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author enıse
 */
public class Personel extends Db {
     @Override
    public void Guncelle() {
        int row = table_personel.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Güncelleme Yapılamaz!!!");
        } else { try {           
                PreparedStatement ps = conn.prepareStatement("update personel_listesi set tc=? ,ad=?, soyad=?, dogum_tarihi=?,telefon=? ,adres=? , cocuk_sayisi=?,"
                     + " maas=?, mesai_saati=?,mesai_ucreti=?,ise_giris_tarihi=? where id=?");
                ps.setString(1, txt_tc.getText());
                ps.setString(2, txt_adi.getText());
                ps.setString(3, txt_soyadi.getText());
                ps.setString(4, txt_dtarih.getText());
                ps.setString(5, txt_tel.getText());
                ps.setString(6, txt_adres1.getText());
                ps.setString(7, String.valueOf(cocuk.getSelectedItem()));
                ps.setString(8, txt_maas.getText());
                ps.setString(9, txt_mesai.getText());
                ps.setString(10, txt_mucret.getText());
                ps.setString(11, txt_giris.getText());
                ps.setString(12, txt_id.getText());
                ps.executeUpdate();
                EkranaGetir();
                JOptionPane.showMessageDialog(null, "Güncelleme Başarılı");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());          }     }
    }

    @Override
    public void Kaydet() {
        String tc = txt_tc.getText();
        String adi = txt_adi.getText();
        String soyadi = txt_soyadi.getText();
        String dtarih = txt_dtarih.getText();
        String cep = txt_tel.getText();
        String cocuk_sayisi = String.valueOf(cocuk.getSelectedItem());
        String adres = txt_adres1.getText();
        String giris_tarihi = txt_giris.getText();
        String maas = txt_maas.getText();
        String mesai_saati = txt_mesai.getText();
        String ucret = txt_mucret.getText();
        try {
            if (tc.equals("") || adi.equals("") || soyadi.equals("")) {

                javax.swing.JOptionPane.showMessageDialog(this, "Lütfen Boş Alanları Doldurunuz!");

            } else {
                conn = connect();
                st = conn.createStatement();

                String sql = "INSERT INTO personel_listesi (tc,ad,soyad,dogum_tarihi,telefon,adres,cocuk_sayisi,maas,mesai_saati,mesai_ucreti,ise_giris_tarihi)"
                        + " VALUES ('" + tc + "', '" + adi + "', '" + soyadi + "','" + dtarih + "', '" + cep + "', '" + adres + "','" + cocuk_sayisi + "', '" + maas + "', '" + mesai_saati + "', '" + ucret + "', '" + giris_tarihi + "')";

                st.executeUpdate(sql);
                EkranaGetir();
                javax.swing.JOptionPane.showMessageDialog(null, "Kayıt İşleminiz Gerçekleşmiştir. ");

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Veritabanına bağlantı sağlanamadı! " + ex.toString());

        }
    }
public void Temizle(){
    txt_adi.setText(null);
    txt_adres1.setText(null);
    txt_dtarih.setText(null);
    txt_giris.setText(null);
    txt_id.setText(null);
    txt_maas.setText(null);
    txt_mesai.setText(null);
    txt_mucret.setText(null);
    txt_soyadi.setText(null);
    txt_tc.setText(null);
    txt_tel.setText(null);
} String baslik[] = new String[]{"TC NO", "Adı", "Soyadı", "Doğum Tarihi", "Cep Telefonu", "Adres Bilgileri", "Çocuk Sayısı", "Maaş", "Mesai Saati", "Mesai Ücreti", "İşe Giriş Tarihi"};
    @Override
    public void EkranaGetir() {

        try {
            st = connect().createStatement();
            rs = st.executeQuery("select * from personel_listesi ORDER BY id DESC");
            rs.last();

            int kayitsayisi = rs.getRow();
            rs.beforeFirst();

            Object data[][] = new Object[kayitsayisi][];
            int i = 0;

            while (rs.next()) {

                data[i] = new Object[]{
                    rs.getString("tc"),
                    rs.getString("ad"),
                    rs.getString("soyad"),
                    rs.getString("dogum_tarihi"),
                    rs.getString("telefon"),
                    rs.getString("adres"),
                    rs.getString("cocuk_sayisi"),
                    rs.getString("maas"),
                    rs.getString("mesai_saati"),
                    rs.getString("mesai_ucreti"),
                    rs.getString("ise_giris_tarihi"),};
                i++;

            }

            table_personel.setModel(new DefaultTableModel(data, baslik));

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Veritabanına bağlantı sağlanamadı! " + ex.toString());
        }

    }

    @Override
    public void delete() {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from personel_listesi where id=?");
            ps.setString(1, txt_id.getText());
            ps.executeUpdate();
            EkranaGetir();
            JOptionPane.showMessageDialog(null, "Silme Başarılı");

        } catch (SQLException ex) {
            Logger.getLogger(Müsteri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Arama() {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from personel_listesi where id=?");
            ps.setString(1, txt_id.getText());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                txt_tc.setText(rs.getString("tc"));
                txt_adi.setText(rs.getString("ad"));
                txt_soyadi.setText(rs.getString("soyad"));
                txt_dtarih.setText(rs.getString("dogum_tarihi"));
                txt_tel.setText(rs.getString("telefon"));
                txt_adres1.setText(rs.getString("adres"));
                cocuk.setSelectedItem(rs.getString("cocuk_sayisi"));
                txt_maas.setText(rs.getString("maas"));
                txt_mesai.setText(rs.getString("mesai_saati"));
                txt_mucret.setText(rs.getString("mesai_ucreti"));
                txt_giris.setText(rs.getString("ise_giris_tarihi"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Müsteri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public Personel() {

        initComponents();
        EkranaGetir();
    }
    int selectedRow;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_tel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_giris = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_maas = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_tc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_adi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_soyadi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_mesai = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_mucret = new javax.swing.JTextField();
        txt_adres1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_dtarih = new javax.swing.JTextField();
        cocuk = new javax.swing.JComboBox<>();
        txt_id = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_personel = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telActionPerformed(evt);
            }
        });

        jLabel5.setText("Adres:");

        jLabel6.setText("Çocuk Sayısı");

        jLabel7.setText("Giriş Tarihi:");

        jLabel9.setText("Maaş:");

        jButton2.setBackground(new java.awt.Color(255, 102, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/add.png"))); // NOI18N
        jButton2.setText("Kaydet");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Personel Tc:");

        jLabel2.setText("Personel Adı:");

        jLabel3.setText("Personel Soyadı:");

        jLabel4.setText("Personel Telefon:");

        jButton1.setBackground(new java.awt.Color(153, 204, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/delete.png"))); // NOI18N
        jButton1.setText("Sil");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setText("ÇIKIŞ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Mesai Saati:");

        jLabel11.setText("Mesai Ücreti:");

        txt_adres1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_adres1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Dogum Tarihi:");

        txt_dtarih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dtarihActionPerformed(evt);
            }
        });

        cocuk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        jLabel8.setText("Id:");

        jButton4.setBackground(new java.awt.Color(153, 153, 255));
        jButton4.setText("Arama");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(153, 255, 153));
        jButton5.setText("Güncelle");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        table_personel.setModel(new javax.swing.table.DefaultTableModel(
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
        table_personel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_personelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_personel);

        jButton6.setBackground(new java.awt.Color(153, 255, 255));
        jButton6.setText("Temizle");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dtarih))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_mucret))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_mesai))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_maas))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_giris))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cocuk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_adres1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_tc, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_adi, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(txt_soyadi))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(350, 350, 350))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_tc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_adi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_soyadi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_adres1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cocuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_giris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_maas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txt_mesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_mucret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_dtarih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Kaydet();

        EkranaGetir();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_adres1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_adres1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adres1ActionPerformed

    private void txt_dtarihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dtarihActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dtarihActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        delete();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton1MousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Guncelle();


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Arama();


    }//GEN-LAST:event_jButton4ActionPerformed

    private void table_personelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_personelMouseClicked
        try {
           
                    int row = table_personel.getSelectedRow();
            String tbl = (table_personel.getModel().getValueAt(row, 0).toString());
            String sql = "select * from personel_listesi where tc=" + tbl + "";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
               String id = rs.getString("id");
                txt_id.setText(id);
                
                String tc = rs.getString("tc");
                txt_tc.setText(tc);
                
                  String ad = rs.getString("ad");
                txt_adi.setText(ad);
                
                String soyad = rs.getString("soyad");
                txt_soyadi.setText(soyad);
                String dogum = rs.getString("dogum_tarihi");
                txt_dtarih.setText(dogum);
                String tel = rs.getString("telefon");
                txt_tel.setText(tel);
                String adres = rs.getString("adres");
                txt_adres1.setText(adres);
                String cocukl = String.valueOf(rs.getString("cocuk_sayisi"));
                cocuk.setSelectedItem(cocukl);
                String maas = rs.getString("maas");
                txt_maas.setText(maas);
                String mesaati = rs.getString("mesai_saati");
                txt_mesai.setText(mesaati);
                String mucret = rs.getString("mesai_ucreti");
                txt_mucret.setText(mucret);
                String giris = rs.getString("ise_giris_tarihi");
                txt_giris.setText(giris);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }//GEN-LAST:event_table_personelMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       
        Temizle();
        
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Personel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Personel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Personel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Personel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Personel().setVisible(true);
            }
        });

    }
    private Giris atapencere;

    public Personel(Giris ata) {
        this.atapencere = ata;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cocuk;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_personel;
    private javax.swing.JTextField txt_adi;
    private javax.swing.JTextField txt_adres1;
    private javax.swing.JTextField txt_dtarih;
    private javax.swing.JTextField txt_giris;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_maas;
    private javax.swing.JTextField txt_mesai;
    private javax.swing.JTextField txt_mucret;
    private javax.swing.JTextField txt_soyadi;
    private javax.swing.JTextField txt_tc;
    private javax.swing.JTextField txt_tel;
    // End of variables declaration//GEN-END:variables
}
