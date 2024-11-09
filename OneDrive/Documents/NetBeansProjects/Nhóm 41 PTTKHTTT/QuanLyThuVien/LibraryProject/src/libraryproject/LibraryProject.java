/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package libraryproject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import GUI.LoginGUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sqlite.JDBCUtil;

/**
 *
 * @author ADMIN
 */
public class LibraryProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LoginGUI();
    
        try {
            Connection con = JDBCUtil.getConnection();
            // Lấy ngày hiện tại
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            String ngay = dateFormat.format(calendar.getTimeInMillis());
            Date ngayMuon = java.sql.Date.valueOf(ngay);
            
            String sql1 = "SELECT * FROM PhieuMuon AS PM Where TrangThai = N'Đang mượn' and HanTra < '" + ngayMuon + "'";
            String sql2 = "SELECT * FROM PhieuMuon AS PM Where TrangThai = N'Có thể sử dụng' and HanNhan < '" + ngayMuon + "'";
            String sql3 = "SELECT * FROM Sach Where TrangThai = N'Hiện có'";
            
            
            
            // Cập nhận trạng thái các phiếu mượn quá hạn trả
            PreparedStatement ps = con.prepareStatement(sql1);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String maphieu = resultSet.getString("MaPhieu");
                
                String updatesql = "UPDATE PhieuMuon"
                    + " SET TrangThai = N'Quá hạn trả'"
                    + " WHERE MaPhieu = ?";
                
                PreparedStatement updateStatement = con.prepareStatement(updatesql);
                updateStatement.setString(1, maphieu);
                updateStatement.executeUpdate();
                updateStatement.close();     
            }
            
            // Cập nhận trạng thái các phiếu mượn quá hạn nhận
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ResultSet resultSet2 = ps2.executeQuery();
            while (resultSet2.next()) {
                String maphieu = resultSet2.getString("MaPhieu");
                
                String updatesql = "UPDATE PhieuMuon"
                    + " SET TrangThai = N'Quá hạn mượn'"
                    + " WHERE MaPhieu = ?";
                
                PreparedStatement updateStatement = con.prepareStatement(updatesql);
                updateStatement.setString(1, maphieu);
                updateStatement.executeUpdate();
                updateStatement.close();     
            }
            
            // Cập nhật giá mượn các sách
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ResultSet resultSet3 = ps3.executeQuery();
            while (resultSet3.next()) {
                String maSach = resultSet3.getString("MaSach");
                String trangThai = resultSet3.getString("TrangThai");
                int giaSach = resultSet3.getInt("GiaSach");
                
                int giaMuonSach = 0;
                if (giaSach < 200000 && trangThai.equals("Nguyên vẹn")) {
                    giaMuonSach = (int) Math.round(giaSach * 1.1);          // Làm tròn đến số nguyên
                } else if (giaSach > 200000 && trangThai.equals("Nguyên vẹn")) {
                    giaMuonSach = (int) Math.round(giaSach * 1.15);
                } else if (giaSach > 200000 && trangThai.equals("Hư hỏng nhẹ")) {
                    giaMuonSach = (int) Math.round(giaSach * 1.1);
                } else if (giaSach < 200000 && trangThai.equals("Hư hỏng nhẹ")) {
                    giaMuonSach = (int) Math.round(giaSach * 0.9);
                } else if (trangThai.equals("Hư hỏng nặng") || trangThai.equals("Mất")) {
                    giaMuonSach = 0;
                }
                
                String updatesql = "UPDATE PhieuMuon"
                    + " SET GiaMuon = ?"
                    + " WHERE MaSach = ?";
                
                PreparedStatement updateStatement = con.prepareStatement(updatesql);
                updateStatement.setInt(1, giaMuonSach);
                updateStatement.setString(2, maSach);
                updateStatement.executeUpdate();
                updateStatement.close();     
            }
            
            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
