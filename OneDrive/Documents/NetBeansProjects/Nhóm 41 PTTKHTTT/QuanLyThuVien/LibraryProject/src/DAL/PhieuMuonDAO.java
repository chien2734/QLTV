/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.PhieuMuonDTO;
import java.sql.*;
import java.util.ArrayList;
import sqlite.*;

/**
 *
 * @author ADMIN
 */
public class PhieuMuonDAO implements DAOInterface<PhieuMuonDTO> {

    /*
    create table PhieuMuon(
            MaPhieu		varchar(10) primary key,
            MaTheMuon	varchar(10) foreign key references DocGia(TENDN),
            TenDG		nvarchar(50),
            NgayDK		datetime,
            HanNhan		datetime,
            NgayMuon	datetime,
            HanTra		datetime,
            TienCoc		int,		-- Tự động tính tổng GiaMuon
            TrangThai	int			-- "Quá hạn mượn" (có thể xoá), "Có thể mượn" (cho phép xác nhận mượn), "Đã nhận sách"
    )
    
    java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Define a date format
        String formattedDate = formatter.format(currentDate);
        System.out.println(formattedDate);
        java.sql.Date a = java.sql.Date.valueOf(formattedDate);
     */
    public static PhieuMuonDAO getInstance() {
        return new PhieuMuonDAO();
    }

    @Override
    public int insert(PhieuMuonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO PhieuMuon(MaPhieu, MaTheMuon, TenDG, NgayDK, HanNhan, NgayMuon, HanTra, TienCoc, TrangThai)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, t.getMaPhieu());
            ps.setString(2, t.getMaTheMuon());
            ps.setString(3, t.getTenDG());
            ps.setDate(4, t.getNgayDK());
            ps.setDate(5, t.getHanNhan());
            ps.setDate(6, t.getNgayMuon());
            ps.setDate(7, t.getHanTra());
            ps.setInt(8, t.getTienCoc());
            ps.setString(9, t.getTrangThai());
            
            System.out.println(sql);
            ketQua = ps.executeUpdate();
            System.out.println("Insert thanh cong.");
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }
    
    public int insert1(PhieuMuonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO PhieuMuon(MaPhieu, MaTheMuon, TenDG, NgayDK, HanNhan, NgayMuon, HanTra, TienCoc, TrangThai)"
                    + " VALUES (?, ?, ?, null, null, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, t.getMaPhieu());
            ps.setString(2, t.getMaTheMuon());
            ps.setString(3, t.getTenDG());
            ps.setDate(4, t.getNgayMuon());
            ps.setDate(5, t.getHanTra());
            ps.setInt(6, t.getTienCoc());
            ps.setString(7, t.getTrangThai());
            System.out.println(sql);
            ketQua = ps.executeUpdate();
            System.out.println("Insert thanh cong.");
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int update(PhieuMuonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE PhieuMuon"
                    + " SET MaTheMuon = ?, NgayDK = ?, HanNhan = ?, NgayMuon = ?, HanTra=?, TrangThai = ?"
                    + " WHERE MaPhieu = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, t.getMaTheMuon());
            ps.setDate(2, (Date) t.getNgayDK());
            ps.setDate(3, (Date) t.getHanNhan());
            ps.setDate(4, (Date) t.getNgayMuon());
            ps.setDate(5, (Date) t.getHanTra());
            ps.setString(6, t.getTrangThai());
            ps.setString(7, t.getMaPhieu());

            ketQua = ps.executeUpdate();
            
            if (ketQua != 0) {
                System.out.println("Update phieu muon thanh cong.");
            } else System.out.println("Update phieu muon that bai.");
            
            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int delete(PhieuMuonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String query = "UPDATE PhieuMuon"
                    + " SET TrangThai = N'Xoá'"
                    + " WHERE MaPhieu = ?";

            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getMaPhieu());

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
    public ArrayList<PhieuMuonDTO> selectAll() {
        ArrayList<PhieuMuonDTO> listPhieu = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuMuon AS PM";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maPhieu = rs.getString("MaPhieu");
                String maTheMuon = rs.getString("MaTheMuon");
                String tenDG = rs.getString("TenDG");
                Date ngayDK = rs.getDate("NgayDK");
                Date ngayMuon = rs.getDate("NgayMuon");
                Date hanNhan = rs.getDate("HanNhan");
                Date hanTra = rs.getDate("HanTra");
                int tienCoc = rs.getInt("TienCoc");
                String trangThai = rs.getString("TrangThai");

                listPhieu.add(new PhieuMuonDTO(maPhieu, maTheMuon, tenDG, ngayDK, hanNhan, ngayMuon, hanTra, tienCoc, trangThai));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPhieu;
    }
    
    
    
    @Override
    public PhieuMuonDTO selectById(PhieuMuonDTO t) {
        PhieuMuonDTO pm = new PhieuMuonDTO();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuMuon WHERE TrangThai <> N'Xoa' AND MaPhieu = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMaPhieu());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maphieu = rs.getString(1);
                String mathe = rs.getString(2);
                String tendg = rs.getString(3);
                Date ngaydk = rs.getDate(4);
                Date hannhan = rs.getDate(5);
                Date ngaymuon = rs.getDate(6);
                Date hantra = rs.getDate(7);
                int tiencoc = rs.getInt(8);
                String trangthai = rs.getString(9);

                pm = new PhieuMuonDTO(maphieu, mathe, tendg, ngaydk, hannhan, ngaymuon, hantra, tiencoc, trangthai);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pm;
    }

    @Override
    public ArrayList<PhieuMuonDTO> selectByCondition(String condition) {
        ArrayList<PhieuMuonDTO> ketQua = new ArrayList<>();

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuMuon WHERE " + condition;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maPhieu = rs.getString("MaPhieu");
                String maTheMuon = rs.getString("MaTheMuon");
                String tenDG = rs.getString("TenDG");
                Date ngayDK = rs.getDate("NgayDK");
                Date ngayMuon = rs.getDate("NgayMuon");
                Date hanNhan = rs.getDate("HanNhan");
                Date hanTra = rs.getDate("HanTra");
                int tienCoc = rs.getInt("TienCoc");
                String trangThai = rs.getString("TrangThai");

                PhieuMuonDTO pm = new PhieuMuonDTO(maPhieu, maTheMuon, tenDG, ngayDK, hanNhan, ngayMuon, hanTra, tienCoc, trangThai);
                ketQua.add(pm);
            }
            System.out.println("Bạn đã thực thi " + sql);

            JDBCUtil.closeConnection(con);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }

}
