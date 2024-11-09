/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.PhieuTraDTO;
import java.sql.*;
import java.util.ArrayList;
import sqlite.*;

/**
 *
 * @author PC
 */
public class PhieuTraDAO implements DAOInterface<PhieuTraDTO>{
    
    public static PhieuTraDAO getInstance() {
        return new PhieuTraDAO();
    }
        
    @Override
    public int insert(PhieuTraDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO PhieuTra(MaPhieuTra, MaPhieuMuon, MaTheMuon, NgayMuon, TongTienPhat, TrangThai)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(2, t.getMaPM());
            ps.setString(3, t.getMathe());
            ps.setDate(4, (Date) t.getNgayMuon());
            ps.setInt(5, t.getTienPhat());
            ps.setInt(6, t.getTrangThai());
            ps.setString(1, t.getMaPT());
            
            //System.out.println(sql);
            ketQua = ps.executeUpdate();
            System.out.println("Insert thanh cong.");
            if (ketQua != 0) {
                System.out.println("Insert pt thanh cong.");
            } else System.out.println("Inser pt that bai.");
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int update(PhieuTraDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE PhieuTra"
                    + " SET MaPhieuMuon = ?, MaTheMuon = ?, NgayMuon = ?, TongTienPhat = ?, TrangThai = ?"
                    + " WHERE MaPhieuTra = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, t.getMaPM());
            ps.setString(2, t.getMathe());
            ps.setDate(3, (Date) t.getNgayMuon());
            ps.setInt(4, t.getTienPhat());
            ps.setInt(5, t.getTrangThai());
            ps.setString(6, t.getMaPT());

            ketQua = ps.executeUpdate();
            if (ketQua != 0) {
                System.out.println("Update pt thanh cong.");
            } else System.out.println("Update pt that bai.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int delete(PhieuTraDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String query = "UPDATE Phieutra"
                    + " TrangThai = 0"
                    + " WHERE MaPhieuTra = ?";

            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getMaPT());

            ketQua = ps.executeUpdate();
            if (ketQua != 0) {
                System.out.println("Delete thanh cong.");
            } else System.out.println("Delete that bai.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ArrayList<PhieuTraDTO> selectAll() {
        ArrayList<PhieuTraDTO> listPhieu = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuTra";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maPT = rs.getString("MaPhieuTra");
                String maPM = rs.getString("MaPhieuMuon");
                String mathe = rs.getString("MaTheMuon");
                Date ngayMuon = rs.getDate("NgayMuon");
                int tongtien = rs.getInt("TongTienPhat");
                int trangThai = rs.getInt("TrangThai");

                listPhieu.add(new PhieuTraDTO(maPT, maPM, mathe, ngayMuon, tongtien, trangThai));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPhieu;
    }

    @Override
    public PhieuTraDTO selectById(PhieuTraDTO t) {
        PhieuTraDTO pt = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuTra WHERE TrangThai <> 0 AND MaPhieuTra = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMaPT());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maPT = rs.getString("MaPhieuTra");
                String maPM = rs.getString("MaPhieuMuon");
                String mathe = rs.getString("MaThe");
                Date ngayMuon = rs.getDate("NgayMuon");
                int tongtien = rs.getInt("TongTienPhat");
                int trangThai = rs.getInt("TrangThai");

                pt = new PhieuTraDTO(maPT, maPM, mathe, ngayMuon, tongtien, trangThai);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pt;
    }

    @Override
    public ArrayList<PhieuTraDTO> selectByCondition(String condition) {
        ArrayList<PhieuTraDTO> ketQua = new ArrayList<>();

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuTra WHERE " + condition;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maPT = rs.getString("MaPhieuTra");
                String maPM = rs.getString("MaPhieuMuon");
                String mathe = rs.getString("MaTheMuon");
                Date ngayMuon = rs.getDate("NgayMuon");
                int tongtien = rs.getInt("TongTienPhat");
                int trangThai = rs.getInt("TrangThai");

                PhieuTraDTO pt = new PhieuTraDTO(maPT, maPM, mathe, ngayMuon, tongtien, trangThai);
                ketQua.add(pt);
            }
            System.out.println("Bạn đã thực thi " + sql);

            JDBCUtil.closeConnection(con);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    
}
