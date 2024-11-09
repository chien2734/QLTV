/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.CT_PhieuTraDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sqlite.*;

/**
 *
 * @author PC
 */
public class CT_PhieuTraDAO implements DAOInterface<CT_PhieuTraDTO>{

    @Override
    public int insert(CT_PhieuTraDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO CT_PhieuTra(MaPhieuTra, MaSach, TenSach ,NgayTra, TinhTrang, TienPhat)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMaPT());
            ps.setString(2, t.getMasach());
            ps.setString(3, t.getTenSach());
            ps.setDate(4, (Date) t.getNgayTra());
            ps.setString(5, t.getTinhTrang());
            ps.setInt(6, t.getTienPhat());
            
            System.out.println(sql);
            ketQua = ps.executeUpdate();
            if (ketQua != 0) {
                System.out.println("insert ctpt thanh cong.");
            } else System.out.println("Insert ctpt that bai.");
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }
    
    
    public CT_PhieuTraDTO selectByMaPhieu1(String maphieu, String masach) {
        CT_PhieuTraDTO ctpt = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String query = "SELECT * FROM CT_PhieuTra WHERE MaPhieuTra = ? and MaSach= ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, maphieu);
            ps.setString(2, masach);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String tenSach = rs.getString("TenSach");
                Date ngayTra = rs.getDate("NgayTra");
                String tinhTrang = rs.getString("TinhTrang");
                int tienPhat = rs.getInt("TienPhat");
                ctpt = new CT_PhieuTraDTO(maphieu, masach, tenSach, ngayTra, tinhTrang, tienPhat);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ctpt;
    }
    
    public ArrayList<CT_PhieuTraDTO> selectByMaPhieu(CT_PhieuTraDTO t) {
        ArrayList<CT_PhieuTraDTO> ctpt = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String query = "SELECT * FROM CT_PhieuTra WHERE MaPhieuTra = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getMaPT());
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maphieu = rs.getString("MaPhieuTra");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                Date ngayTra = rs.getDate("NgayTra");
                String tinhTrang = rs.getString("TinhTrang");
                int tienPhat = rs.getInt("TienPhat");
                CT_PhieuTraDTO pt = new CT_PhieuTraDTO(maphieu, maSach, tenSach, ngayTra, tinhTrang, tienPhat);
                ctpt.add(pt);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ctpt;
    }

    @Override
    public int update(CT_PhieuTraDTO t) {
        int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE CT_PhieuTra"
                    + " SET MaSach = ?, TenSach = ?, NgayTra = ?, TinhTrang = ?, TienPhat = ?"
                    + " WHERE MaPhieuTra = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, t.getMasach());
            ps.setString(2, t.getTenSach());
            ps.setDate(3, (Date) t.getNgayTra());
            ps.setString(4, t.getTinhTrang());
            ps.setInt(5, t.getTienPhat());

            ketQua = ps.executeUpdate();
            if (ketQua != 0) {
                System.out.println("Update ctpt thanh cong.");
            } else System.out.println("Update ctpt that bai.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int delete(CT_PhieuTraDTO t) {
        int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String query = "DELETE FROM CT_PhieuTra" + " WHERE MaSach = '" + t.getMasach() + "' and MaPhieuTra = '" + t.getMaPT() + "'";

            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getMasach());
            ps.setString(2, t.getMaPT());

            ketQua = st.executeUpdate(query);
            if (ketQua != 0) {
                System.out.println("Delete ctpt thanh cong.");
            } else System.out.println("Delete ctpt that bai.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ArrayList<CT_PhieuTraDTO> selectAll() {
        ArrayList<CT_PhieuTraDTO> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM CT_PhieuTra, PhieuTra" + " WHERE PhieuTra.MaPhieuTra = CT_PhieuTra.MaPhieuTra";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maphieu = rs.getString("MaPhieuTra");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                Date ngayTra = rs.getDate("NgayTra");
                String tinhTrang = rs.getString("TinhTrang");
                int tienPhat = rs.getInt("TienPhat");

                list.add(new CT_PhieuTraDTO(maphieu, maSach, tenSach, ngayTra, tinhTrang, tienPhat));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public CT_PhieuTraDTO selectById(CT_PhieuTraDTO t) {
        CT_PhieuTraDTO ctpm = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String query = "SELECT * FROM CT_PhieuTra WHERE MaPhieuTra = ? and MaSach = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getMaPT());
            ps.setString(2, t.getMasach());
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maphieu = rs.getString("MaPhieuTra");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                Date ngayTra = rs.getDate("NgayTra");
                String tinhTrang = rs.getString("TinhTrang");
                int tienPhat = rs.getInt("TienPhat");

                ctpm = new CT_PhieuTraDTO(maphieu, maSach, tenSach, ngayTra, tinhTrang, tienPhat);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ctpm;
    }

    @Override
    public ArrayList<CT_PhieuTraDTO> selectByCondition(String condition) {
        ArrayList<CT_PhieuTraDTO> ketQua = new ArrayList<>();

        try {

            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM PhieuTra WHERE " + condition;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String maphieu = rs.getString("MaPhieuTra");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                Date ngayTra = rs.getDate("NgayTra");
                String tinhTrang = rs.getString("TinhTrang");
                int tienPhat = rs.getInt("TienPhat");

                CT_PhieuTraDTO ctpt = new CT_PhieuTraDTO(maphieu, maSach, tenSach, ngayTra, tinhTrang, tienPhat);
                System.out.println(ctpt.toString());
                ketQua.add(ctpt);
            }
            System.out.println("Bạn đã thực thi " + query);

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }

    
    
}
