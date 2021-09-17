/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otelotomasyonu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Db extends javax.swing.JFrame {
    PreparedStatement ps=null;
    Statement st=null;
    ResultSet rs = null;
    Connection conn = null;

    public Connection connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/otel_otomasyonu?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public void  EkranaGetir() {
        

    }

    public void delete() {
        


    }
    public void Arama(){
        
    }
    public void Guncelle(){
        
    }
    public void Kaydet(){
        
        
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
  /*  public void veri() {

        String SQL = "SELECT * FROM musteri_listesi";

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            System.err.println("*****************************");
            musteri(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void musteri(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getInt("id")
                    + rs.getLong("tc") + "---------- "
                    + rs.getString("adi") + "---------- "
                    + rs.getString("soyadi") + "---------- "
                    + rs.getString("cep_telefonu") + "---------- "
                    + rs.getString("adres_bilgileri") + "---------- "
                    + rs.getString("giris_tarihi") + "---------- "
                    + rs.getString("cikis_tarihi") + "---------- "
                    + rs.getString("konaklama_suresi") + "---------- "
                    + rs.getString("oda_numarasi"));

        }

    }*/

}
