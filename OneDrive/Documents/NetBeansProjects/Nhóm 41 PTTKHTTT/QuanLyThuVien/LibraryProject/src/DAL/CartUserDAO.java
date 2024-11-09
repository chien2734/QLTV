/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CartUserDTO;
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
public class CartUserDAO implements DAOInterface<CartUserDTO>{
    
    /*
    MaTheMuon	varchar(10),
	MaSach		varchar(10),
	TenSach		nvarchar(255)
    */
    
    public static CartUserDAO getInstance() {
        return new CartUserDAO();
    }

    @Override
    public int insert(CartUserDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO GioDK(MaTheMuon, MaSach, TenSach, GiaMuon)"
                    + " VALUES ('" + t.getMaTheMuon()+ "', '" + t.getMaSach()+ "', N'" + t.getTenSach() + "', "+ t.getGiaMuon()+")";

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
    public int update(CartUserDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(CartUserDTO t) {
         int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sqlGioDK = "DELETE FROM GioDK" + " WHERE MaTheMuon = ?";

            PreparedStatement ps = con.prepareStatement(sqlGioDK);
            ps.setString(1, t.getMaTheMuon());

            ketQua = ps.executeUpdate();
            System.out.println("Delete thanh cong.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }
    
    public int deleteSach(CartUserDTO t) {
         int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sqlGioDK = "DELETE FROM GioDK" + " WHERE MaSach = ? AND MaTheMuon = ?";

            PreparedStatement ps = con.prepareStatement(sqlGioDK);
            ps.setString(1, t.getMaSach());
            ps.setString(2, t.getMaTheMuon());

            ketQua = ps.executeUpdate();
            System.out.println("Delete thanh cong.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ArrayList<CartUserDTO> selectAll() {
        ArrayList<CartUserDTO> listCard = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM GioDK";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maTheMuon = rs.getString("MaTheMuon");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                int giaMuon = rs.getInt("GiaMuon");
                

                listCard.add(new CartUserDTO(maTheMuon, maSach, tenSach, giaMuon));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCard;
    }

    @Override
    public CartUserDTO selectById(CartUserDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CartUserDTO> selectByCondition(String condition) {
        ArrayList<CartUserDTO> ketQua = new ArrayList<>();

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM GioDK WHERE " + condition;
            System.out.println("Bạn đã thực thi " + sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maTheMuon = rs.getString("MaTheMuon");
                String maSach = rs.getString("MaSach");
                String tenSach = rs.getString("TenSach");
                int giaMuon = rs.getInt("GiaMuon");

                CartUserDTO ul = new CartUserDTO(maTheMuon, maSach, tenSach, giaMuon);
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
