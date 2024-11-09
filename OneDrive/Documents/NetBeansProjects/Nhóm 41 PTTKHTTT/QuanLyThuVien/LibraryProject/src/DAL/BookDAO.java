/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import sqlite.DAOInterface;
import DTO.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sqlite.JDBCUtil;

/**
 *
 * @author ADMIN
 */
public class BookDAO implements DAOInterface<BookDTO> {

    public static BookDAO getInstance() {
        return new BookDAO();
    }

    /*
    create table DauSach(
	MaDS varchar(10) primary key,
	TenSach nvarchar(255),
	MaLoai	varchar(10)	foreign key references TheLoai(MaLoai),
	TacGia	nvarchar(255),
	NXB		nvarchar(255),
	NamXB	int,
	SoLuong	int,
	TrangThai	int
    )
     */
    @Override
    public int insert(BookDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO DauSach(MaDS, TenSach, MaLoai, TacGia, MaNXB, NamXB, SoLuong, TrangThai)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMaDS());
            ps.setString(2, t.getTenSach());
            ps.setString(3, t.getTenLoai());
            ps.setString(4, t.getTacGia());
            ps.setString(5, t.getnxb());
            ps.setInt(6, t.getNamXB());
            ps.setInt(7, t.getSoLuong());
            ps.setInt(8, t.getTrangThai());

            ketQua = ps.executeUpdate();
            System.out.println("Insert thanh cong.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(BookDTO t) {
        int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE DauSach"
                    + " SET TenSach = ?, MaLoai = ?, TacGia = ?, MaNXB = ?, NamXB = ?, SoLuong = ?, TrangThai = ?"
                    + " WHERE MaDS = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, t.getTenSach());
            ps.setString(2, t.getTenLoai());
            ps.setString(3, t.getTacGia());
            ps.setString(4, t.getnxb());
            ps.setInt(5, t.getNamXB());
            ps.setInt(6, t.getSoLuong());
            ps.setInt(7, t.getTrangThai());
            ps.setString(8, t.getMaDS());

            ketQua = ps.executeUpdate();
            System.out.println("Update thanh cong.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int delete(BookDTO t) {
        int ketQua2 = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sqlDauSach = "DELETE FROM DauSach" + " WHERE MaDS = ?";

            PreparedStatement ps = con.prepareStatement(sqlDauSach);
            ps.setString(1, t.getMaDS());

            ketQua2 = ps.executeUpdate();
            System.out.println("Delete thanh cong.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua2;
    }
    
    public ArrayList<BookDTO> selectAll1() {
        ArrayList<BookDTO> listDauSach = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT DS.MaDS, DS.TenSach, TL.TenLoai, DS.TacGia, NXB.TenNXB, DS.NamXB, DS.SoLuong, DS.TrangThai FROM DauSach AS DS, TheLoai AS TL, NXB" + " WHERE DS.MaLoai = TL.MaLoai AND DS.MaNXB = NXB.MaNXB";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maDS = rs.getString("MaDS");
                String tenSach = rs.getString("TenSach");
                String tenLoai = rs.getString("TenLoai");
                String tacGia = rs.getString("TacGia");
                String MaNXB = rs.getString("TenNXB");
                int NamXB = rs.getInt("NamXB");
                int soLuong = rs.getInt("SoLuong");
                int trangThai = rs.getInt("TrangThai");

                listDauSach.add(new BookDTO(maDS, tenSach, tacGia, tenLoai, MaNXB, NamXB, soLuong, trangThai));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listDauSach;
    }

    @Override
    public ArrayList<BookDTO> selectAll() {
        ArrayList<BookDTO> listDauSach = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT DS.MaDS, DS.TenSach, TL.TenLoai, DS.TacGia, NXB.TenNXB, DS.NamXB, DS.SoLuong, DS.TrangThai FROM DauSach AS DS, TheLoai AS TL, NXB" + " WHERE DS.TrangThai = 1 AND DS.MaLoai = TL.MaLoai AND DS.MaNXB = NXB.MaNXB";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maDS = rs.getString("MaDS");
                String tenSach = rs.getString("TenSach");
                String tenLoai = rs.getString("TenLoai");
                String tacGia = rs.getString("TacGia");
                String MaNXB = rs.getString("TenNXB");
                int NamXB = rs.getInt("NamXB");
                int soLuong = rs.getInt("SoLuong");
                int trangThai = rs.getInt("TrangThai");

                listDauSach.add(new BookDTO(maDS, tenSach, tacGia, tenLoai, MaNXB, NamXB, soLuong, trangThai));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listDauSach;
    }

    @Override
    public BookDTO selectById(BookDTO t) {
        BookDTO dauSach = new BookDTO();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM DauSach WHERE TrangThai = 1 AND MaDS = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMaDS());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maDS = rs.getString("MaDS");
                String tenSach = rs.getString("TenSach");
                String tenLoai = rs.getString("MaLoai");
                String tacGia = rs.getString("TacGia");
                String NXB = rs.getString("MaNXB");
                int NamXB = rs.getInt("NamXB");
                int soLuong = rs.getInt("SoLuong");
                int trangThai = rs.getInt("TrangThai");

                dauSach = new BookDTO(maDS, tenSach, tacGia, tenLoai, NXB, NamXB, soLuong, trangThai);
            }
            System.out.println("Bạn đã thực thi " + sql);
            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dauSach;
    }
    
    public BookDTO selectByIdMaDS(String mads) {
        BookDTO dauSach = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT DS.MaDS, DS.TenSach, TL.TenLoai, DS.TacGia, NXB.TenNXB, DS.NamXB, DS.SoLuong, DS.TrangThai FROM DauSach as DS, TheLoai as TL, NXB WHERE DS.TrangThai = 1 AND DS.MaDS = ? and DS.MaLoai = TL.MaLoai AND DS.MaNXB = NXB.MaNXB";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mads);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maDS = rs.getString(1);
                String tenSach = rs.getString(2);
                String tenLoai = rs.getString(3);
                String tacGia = rs.getString(4);
                String NXB = rs.getString(5);
                int NamXB = rs.getInt(6);
                int soLuong = rs.getInt(7);
                int trangThai = rs.getInt(8);

                dauSach = new BookDTO(maDS, tenSach, tacGia, tenLoai, NXB, NamXB, soLuong, trangThai);
            }
            System.out.println("Bạn đã thực thi " + sql);
            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dauSach;
    }
    
    @Override
    public ArrayList<BookDTO> selectByCondition(String condition) {
        ArrayList<BookDTO> ketQua = new ArrayList<>();

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM DauSach AS DS, TheLoai AS TL, NXB AS N WHERE DS.TrangThai = 1 AND DS.MaLoai = TL.MaLoai AND DS.MaNXB = N.MaNXB " + condition;
            System.out.println("Bạn đã thực thi " + sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maDS = rs.getString("MaDS");
                String tenSach = rs.getString("TenSach");
                String tenLoai = rs.getString("TenLoai");
                String tacGia = rs.getString("TacGia");
                String NXB = rs.getString("TenNXB");
                int NamXB = rs.getInt("NamXB");
                int soLuong = rs.getInt("SoLuong");
                int trangThai = rs.getInt("TrangThai");

                BookDTO ul = new BookDTO(maDS, tenSach, tacGia, tenLoai, NXB, NamXB, soLuong, trangThai);
                ketQua.add(ul);
            }
            System.out.println("Bạn đã thực thi " + sql);

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }

}
