/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CT_PhieuMuonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sqlite.DAOInterface;
import sqlite.JDBCUtil;

/**
 *
 * @author ADMIN
 */
public class CT_PhieuMuonDAO implements DAOInterface<CT_PhieuMuonDTO>{
    
    public static CT_PhieuMuonDAO getInstance() {
        return new CT_PhieuMuonDAO();
    }

    /*
    create table CT_PhieuMuon(
	MaPhieu	varchar(10),
	MaSach	varchar(10),
	TenSach	varchar(255),
        GiaMuon         int,
	TrangThai	int,
	primary key (MaPhieu, MaSach),
	foreign key (MaPhieu) references PhieuMuon(MaPhieu),
	foreign key (MaSach) references Sach(MaSach)
    )
    */

    @Override
    public int insert(CT_PhieuMuonDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO CT_PhieuMuon(MaPhieu, MaSach, TenSach, GiaMuon, TrangThai)"
                    + " VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(2, t.getMaSach());
            ps.setString(3, t.getTenSach());
            ps.setInt(4, t.getGiaMuon());
            ps.setString(5, t.getTrangThai());
            ps.setString(1, t.getMaPhieu());
            
            System.out.println(sql);
            ketQua = ps.executeUpdate();
            if (ketQua != 0) {
                System.out.println("Insert ctpm thanh cong.");
            } else System.out.println("Insert ctpm that bai.");
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    return ketQua ;
    }

    @Override
    public int update(CT_PhieuMuonDTO t) {
        int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE CT_PhieuMuon"
                    + " SET MaSach = ?, TenSach = ?, GiaMuon = ?, TrangThai = ?"
                    + " WHERE MaPhieu = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, t.getMaSach());
            ps.setString(2, t.getTenSach());
            ps.setInt(3, t.getGiaMuon());
            ps.setString(4, t.getTrangThai());
            ps.setString(5, t.getMaPhieu());

            ketQua = ps.executeUpdate();
            if (ketQua != 0) {
                System.out.println("Update ctpm thanh cong.");
            } else System.out.println("Update ctpm that bai.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }


    @Override
    public ArrayList<CT_PhieuMuonDTO> selectAll() {
        ArrayList<CT_PhieuMuonDTO> listCTPM = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM CT_PhieuMuon, PhieuMuon" + " WHERE PhieuMuon.MaPhieu = CT_PhieuMuon.MaPhieu";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maPhieu = rs.getString("MaPhieu");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                int giaMuon = rs.getInt("GiaMuon");
                String trangThai = rs.getString("TrangThai");

                listCTPM.add(new CT_PhieuMuonDTO(maPhieu, maSach, tenSach, giaMuon, trangThai));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCTPM;
    }

    @Override
    public CT_PhieuMuonDTO selectById(CT_PhieuMuonDTO t) {
        CT_PhieuMuonDTO ctpm = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String query = "SELECT * FROM CT_PhieuMuon WHERE MaPhieu = ? and MaSach = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getMaPhieu());
            ps.setString(2, t.getMaSach());
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maPhieu = rs.getString("MaPhieu");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                int giaMuon = rs.getInt("GiaMuon");
                String trangThai = rs.getString("TrangThai");

                ctpm = new CT_PhieuMuonDTO(maPhieu, maSach, tenSach, giaMuon, trangThai);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ctpm;
    }

    public ArrayList<CT_PhieuMuonDTO> selectByMaPhieu(String maphieu) {
        ArrayList<CT_PhieuMuonDTO> ctpm = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String query = "SELECT * FROM CT_PhieuMuon WHERE MaPhieu = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, maphieu);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //String maPhieu = rs.getString("MaPhieu");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                int giaMuon = rs.getInt("GiaMuon");
                String trangThai = rs.getString("TrangThai");
                CT_PhieuMuonDTO ct = new CT_PhieuMuonDTO(maphieu, maSach, tenSach, giaMuon, trangThai);
                ctpm.add(ct);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ctpm;
    }
    
    public CT_PhieuMuonDTO selectByMaPhieu1(String maphieu, String maSach) {
        CT_PhieuMuonDTO ctpm = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String query = "SELECT * FROM CT_PhieuMuon WHERE MaPhieu = ? and MaSach = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, maphieu);
            ps.setString(2, maSach);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //String maPhieu = rs.getString("MaPhieu");
                //String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                int giaMuon = rs.getInt("GiaMuon");
                String trangThai = rs.getString("TrangThai");
                ctpm = new CT_PhieuMuonDTO(maphieu, maSach, tenSach, giaMuon, trangThai);
            }
            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ctpm;
    }
    
    @Override
    public ArrayList<CT_PhieuMuonDTO> selectByCondition(String condition) {
        ArrayList<CT_PhieuMuonDTO> ketQua = new ArrayList<>();

        try {

            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM PhieuMuon WHERE " + condition;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String maPhieu = rs.getString("MaPhieu");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                int giaMuon = rs.getInt("GiaMuon");
                String trangThai = rs.getString("TrangThai");

                CT_PhieuMuonDTO ctpm = new CT_PhieuMuonDTO(maPhieu, maSach, tenSach, giaMuon, trangThai);
                System.out.println(ctpm.toString());
                ketQua.add(ctpm);
            }
            System.out.println("Bạn đã thực thi " + query);

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(CT_PhieuMuonDTO t) {
        int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String query = "DELETE FROM CT_PhieuMuon" + " WHERE MaSach = '" + t.getMaSach() + "' and MaPhieu = '" + t.getMaPhieu() + "'";

            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getMaSach());
            ps.setString(2, t.getMaPhieu());

            ketQua = ps.executeUpdate();
            if (ketQua != 0) {
                System.out.println("Delete ctpm thanh cong.");
            } else System.out.println("Delete ctpm that bai.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }


}
