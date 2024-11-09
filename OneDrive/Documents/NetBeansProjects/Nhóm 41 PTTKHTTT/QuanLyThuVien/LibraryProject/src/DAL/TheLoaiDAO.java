/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.BookDTO;
import DTO.TheLoaiDTO;
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
public class TheLoaiDAO implements DAOInterface<TheLoaiDTO> {

    public static TheLoaiDAO getInstance() {
        return new TheLoaiDAO();
    }

    @Override
    public int insert(TheLoaiDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO TheLoai(MaLoai, TenLoai, TrangThai)"
                    + " VALUES ('" + t.getMaLoai() + "', N'" + t.getTenLoai() + "', " + t.getTrangThai() + ")";

            Statement ps = con.createStatement();
            System.out.println(sql);
            ketQua = ps.executeUpdate(sql);
            System.out.println("Insert thanh cong.");
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int update(TheLoaiDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(TheLoaiDTO t) {
        int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sqlTL = "DELETE FROM TheLoai WHERE TenLoai = N'" + t.getTenLoai()+ "'";;

            Statement st = con.createStatement();

            ketQua = st.executeUpdate(sqlTL);
            System.out.println("Delete thanh cong.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ArrayList<TheLoaiDTO> selectAll() {
        ArrayList<TheLoaiDTO> listTL = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM TheLoai WHERE TrangThai = 1";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maLoai = rs.getString("MaLoai");
                String tenLoai = rs.getString("TenLoai");
                int trangThai = rs.getInt("TrangThai");

                listTL.add(new TheLoaiDTO(maLoai, tenLoai, trangThai));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTL;
    }

    @Override
    public TheLoaiDTO selectById(TheLoaiDTO t) {
        TheLoaiDTO theLoai = null;
        try {
            System.out.println(t);
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM TheLoai WHERE TenLoai = N'" + t.getTenLoai() + "'";

            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery(sql);
            System.out.println(t.getTenLoai());

            while (rs.next()) {
                String maLoai = rs.getString(1);
                String tenLoai = rs.getString(2);
                int trangThai = rs.getInt(3);

                theLoai = new TheLoaiDTO(maLoai, tenLoai, trangThai);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return theLoai;
    }

    public TheLoaiDTO selectByIdTL(String maLoai) {
        TheLoaiDTO theLoai = null;
        try {
            //System.out.println(t);
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM TheLoai WHERE MaLoai = '" + maLoai + "'";

            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery(sql);
            //System.out.println(t.getTenLoai());

            while (rs.next()) {
                //String maLoai = rs.getString(1);
                String tenLoai = rs.getString(2);
                int trangThai = rs.getInt(3);

                theLoai = new TheLoaiDTO(maLoai, tenLoai, trangThai);
            }
            System.out.println(theLoai);
            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return theLoai;
    }
    
    @Override
    public ArrayList<TheLoaiDTO> selectByCondition(String condition) {
        ArrayList<TheLoaiDTO> ketQua = new ArrayList<>();

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM TheLoai WHERE " + condition;
            System.out.println("Bạn đã thực thi " + sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maLoai = rs.getString("MaLoai");
                String tenLoai = rs.getString("TenLoai");
                int trangThai = rs.getInt("TrangThai");

                TheLoaiDTO ul = new TheLoaiDTO(maLoai, tenLoai, trangThai);
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