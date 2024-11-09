/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CardDTO;
import java.sql.*;
import java.util.ArrayList;
import sqlite.JDBCUtil;

/**
 *
 * @author HUY LONG
 */
public class CardDAO {

    private Connection con = null;

    public boolean openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://LAPTOP-40C6I9LE\\THUONG:1433;databaseName=QLTV2;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String password = "12345";
            con = DriverManager.getConnection(url, user, password);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static CardDAO getInstance() {
        return new CardDAO();
    }
    
    public ArrayList<CardDTO> getAllCards() {
        ArrayList<CardDTO> list = new ArrayList<>();
        if (openConnection()) {
            try {
                String sql = "SELECT * FROM TheMuon";
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String maThe = rs.getString(1);
                    String maDG = rs.getString(2);
                    Date ngayDK = rs.getDate(3);
                    int trangThai = rs.getInt(4);
                    CardDTO card = new CardDTO(maThe, maDG, ngayDK, trangThai);
                    list.add(card);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return list;
    }

    public ArrayList<CardDTO> getAllActivatedCards() {
        ArrayList<CardDTO> list = new ArrayList<>();
        if (openConnection()) {
            try {
                String sql = "SELECT * FROM TheMuon WHERE MaTheMuon LIKE 'TM000%' "
                        + "AND TrangThai = 1";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String maThe = rs.getString(1);
                    String maDG = rs.getString(2);
                    Date ngayDK = rs.getDate(3);
                    int trangThai = rs.getInt(4);
                    CardDTO card = new CardDTO(maThe, maDG, ngayDK, trangThai);
                    list.add(card);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return list;
    }

    public boolean addCard(CardDTO card) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "INSERT INTO TheMuon values"
                        + " (?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, card.hashCode() + "");
                stmt.setString(2, card.getTenDN());
                stmt.setDate(3, card.getNgayDK());
                stmt.setInt(4, card.getTrangThai());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }

        return result;
    }

    public boolean activatedCard(CardDTO card) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "UPDATE TheMuon SET"
                        + " MaTheMuon = ?,NgayDK = ?, TrangThai = ?"
                        + " WHERE MaDG = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, card.getMaTheMuon());
                stmt.setDate(2, card.getNgayDK());
                stmt.setInt(3, card.getTrangThai());
                stmt.setString(4, card.getTenDN());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }

        return result;
    }

    public CardDTO selectByIdMaDG(CardDTO t) {
        CardDTO tm = new CardDTO();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM TheMuon WHERE MaDG = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTenDN());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maThe = rs.getString(1);
                String maDG = rs.getString(2);
                java.sql.Date ngDK = rs.getDate(3);

                int trangThai = rs.getInt(4);

                tm = new CardDTO(maThe, maDG, ngDK, trangThai);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tm;
    }

    public CardDTO selectByIdTM(String mathe) {
        CardDTO tm = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM TheMuon WHERE MaTheMuon = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mathe);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maThe = rs.getString(1);
                String maDG = rs.getString(2);
                Date ngDK = rs.getDate(3);
                int trangThai = rs.getInt(4);

                tm = new CardDTO(maThe, maDG, ngDK, trangThai);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tm;
    }
    
    public ArrayList<CardDTO> getCardsByCondition(String condition) {
        ArrayList<CardDTO> list = new ArrayList<>();
        if (openConnection()) {
            try {
                String sql = "SELECT * FROM TheMuon WHERE " + condition;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String maThe = rs.getString(1);
                    String maDG = rs.getString(2);
                    Date ngayDK = rs.getDate(3);
                    int trangThai = rs.getInt(4);
                    CardDTO card = new CardDTO(maThe, maDG, ngayDK, trangThai);
                    list.add(card);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return list;
    }
}
