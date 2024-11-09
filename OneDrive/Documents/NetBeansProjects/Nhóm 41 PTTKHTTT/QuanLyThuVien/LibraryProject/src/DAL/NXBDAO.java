/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.NXBDTO;
import DTO.TheLoaiDTO;
import sqlite.DAOInterface;
import sqlite.JDBCUtil;

/**
 *
 * @author ADMIN
 */
public class NXBDAO implements DAOInterface<NXBDTO>{
    
     public static NXBDAO getInstance() {
        return new NXBDAO();
    }


    @Override
    public int insert(NXBDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO NXB(MaNXB, TenNXB, TrangThai)"
                    + " VALUES ('" + t.getMaNXB()+ "', N'" + t.getTenNXB()+ "', " + t.getTrangThai() + ")";

            Statement ps = con.createStatement();
            System.out.println(sql);
            ketQua = ps.executeUpdate(sql);
            System.out.println("Insert thanh cong.");
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    return ketQua ;
    }

    @Override
    public int update(NXBDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(NXBDTO t) {
         int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sqlNXB = "DELETE FROM NXB WHERE TenNXB = N'" + t.getTenNXB() + "'";

            Statement st = con.createStatement();

            ketQua = st.executeUpdate(sqlNXB);
            System.out.println("Delete thanh cong.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ArrayList<NXBDTO> selectAll() {
        ArrayList<NXBDTO> listTL = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NXB WHERE TrangThai = 1";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maNXB = rs.getString("MaNXB");
                String tenNXB = rs.getString("TenNXB");
                int trangThai = rs.getInt("TrangThai");

                listTL.add(new NXBDTO(maNXB, tenNXB, trangThai));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTL;
    }

    @Override
    public NXBDTO selectById(NXBDTO t) {
        NXBDTO nxb = null;
        try {
            System.out.println(t);
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NXB WHERE TenNXB = N'" + t.getTenNXB()+ "'";

            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                String maNXB = rs.getString(1);
                String tenNXB = rs.getString(2);
                int trangThai = rs.getInt(3);

                nxb = new NXBDTO(maNXB, tenNXB, trangThai);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nxb;
    }
    
    public NXBDTO selectByIdNXB(String maNXB) {
        NXBDTO nxb = null;
        try {
            //System.out.println(t);
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NXB WHERE MaNXB = '" + maNXB+ "'";

            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                //String maNXB = rs.getString(1);
                String tenNXB = rs.getString(2);
                int trangThai = rs.getInt(3);

                nxb = new NXBDTO(maNXB, tenNXB, trangThai);
            }
            System.out.println(nxb);
            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nxb;
    }

    @Override
    public ArrayList<NXBDTO> selectByCondition(String condition) {
        ArrayList<NXBDTO> ketQua = new ArrayList<>();

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM NXB WHERE " + condition;
            System.out.println("Bạn đã thực thi " + sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maLoai = rs.getString("MaNXB");
                String tenLoai = rs.getString("TenNXB");
                int trangThai = rs.getInt("TrangThai");

                NXBDTO ul = new NXBDTO(maLoai, tenLoai, trangThai);
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
