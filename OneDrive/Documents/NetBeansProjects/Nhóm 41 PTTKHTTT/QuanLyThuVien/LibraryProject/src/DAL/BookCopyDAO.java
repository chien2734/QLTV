/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.BookCopyDTO;
import DTO.BookDTO;
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
public class BookCopyDAO implements DAOInterface<BookCopyDTO> {

    /*
    create table Sach(
	MaSach	varchar(10) primary key,
	MaDS	varchar(10) foreign key references DauSach(MaDS),
	Loai	nvarchar(50),	-- "Có thể mượn", "Đọc tại chỗ"
	GiaSach	int,
	GiaMuon int,				-- Tính tự động: Sách mới: Giá sách < 500: GiaSach*10% + GiaSach; Sách > 500; GiaSach*15% + GiaSach
							--	 Sách hư hỏng nhẹ: Giá sách < 500: GiaSach*8% + GiaSach; Sách > 500; GiaSach*12% + GiaSach
	TrangThai int,				-- "Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất" ------ Trạng thái này chỉ hiển thị trên giao diện admin để tính GiaMuon tự động ko hiện trên giao diện user
	TinhTrang	nvarchar(50)	--"Hiện có", "Đã mượn", "Đã đăng ký", "Không phục vụ" (cho trường hợp bị hư hỏng nặng hc mất)
    )
    */

    public static BookCopyDAO getInstance() {
        return new BookCopyDAO();
    }

    @Override
    public int insert(BookCopyDTO t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO Sach(MaSach, MADS, Loai, GiaSach, GiaMuon, TrangThai, TinhTrang)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMaSach());
            ps.setString(2, t.getMaDS());
            ps.setString(3, t.getLoaiSach());
            ps.setInt(4, t.getGia());
            ps.setDouble(5, t.getGiaMuon());
            ps.setString(7, t.getTinhTrang());
            ps.setString(6, t.getTrangThai());

            ketQua = ps.executeUpdate();
            System.out.println("Insert thanh cong.");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(BookCopyDTO t) {
        int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE Sach"
                    + " SET Loai = ?, GiaSach = ?, TinhTrang = ?, GiaMuon = ?, TrangThai = ?"
                    + " WHERE MaSach = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(6, t.getMaSach());
            ps.setString(1, t.getLoaiSach());
            ps.setDouble(2, t.getGia());
            ps.setString(3, t.getTinhTrang());
            ps.setDouble(4, t.getGiaMuon());
            ps.setString(5, t.getTrangThai());

            ketQua = ps.executeUpdate();
            if (ketQua!=0) {
                System.out.println("Update sách thành công!");
            } else System.out.println("Update sách thất bại!");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int delete(BookCopyDTO t) {
        int ketQua = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sqlSach = "DELETE FROM Sach" + " WHERE MaSach = '" + t.getMaSach() + "'";

            Statement st = con.createStatement();

            ketQua = st.executeUpdate(sqlSach);
            if (ketQua!=0) {
                System.out.println("Delete sách thành công!");
            } else System.out.println("Delete sách thất bại!");

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ArrayList<BookCopyDTO> selectAll() {
        ArrayList<BookCopyDTO> listSach = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Sach, DauSach AS DS" + " WHERE Sach.MaDS = DS.MaDS";
            System.out.println(sql);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maSach = rs.getString("MaSach");
                String maDS = rs.getString("MaDS");
                String tinhTrang = rs.getString("TinhTrang");
                String loai = rs.getString("Loai");
                int gia = rs.getInt("GiaSach");
                String trangThai = rs.getString("TrangThai");
                int giaMuon = rs.getInt("GiaMuon");

                listSach.add(new BookCopyDTO(maSach, maDS, loai, gia, giaMuon, trangThai, tinhTrang));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listSach;
    }

    @Override
    public BookCopyDTO selectById(BookCopyDTO t) {
        BookCopyDTO sach = new BookCopyDTO();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Sach WHERE maSach = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMaSach());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               String maSach = rs.getString("MaSach");
                String maDS = rs.getString("MaDS");
                String tinhTrang = rs.getString("TinhTrang");
                String loai = rs.getString("Loai");
                int gia = rs.getInt("GiaSach");
                int giaMuon = rs.getInt("GiaMuon");
                String trangThai = rs.getString("TrangThai");

                sach = new BookCopyDTO(maSach, maDS, loai, gia, giaMuon, trangThai, tinhTrang);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sach;
    }

    @Override
    public ArrayList<BookCopyDTO> selectByCondition(String condition) {
        ArrayList<BookCopyDTO> ketQua = new ArrayList<>();

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT DS.MaDS, S.MaSach, S.TinhTrang, S.Loai, S.GiaSach, S.GiaMuon, S.TrangThai FROM DauSach AS DS, Sach AS S WHERE DS.MaDS = S.MaDS " + condition;
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maSach = rs.getString("MaSach");
                String maDS = rs.getString("MaDS");
                String tinhTrang = rs.getString("TinhTrang");
                String loai = rs.getString("Loai");
                int gia = rs.getInt("GiaSach");
                int giaMuon = rs.getInt("GiaMuon");
                String trangThai = rs.getString("TrangThai");

                BookCopyDTO ul = new BookCopyDTO(maSach, maDS, loai, gia, giaMuon, trangThai, tinhTrang);
                System.out.println(ul.toString());
                ketQua.add(ul);
            }
            System.out.println("Bạn đã thực thi " + sql);

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    
    public BookCopyDTO selectByCondition1(String mads, int top) {
        BookCopyDTO sach = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY (SELECT 0)) AS RowNum FROM Sach WHERE TinhTrang = N'Hiện có' and (TrangThai = N'Nguyên vẹn' or TrangThai = N'Hư hỏng nhẹ') and MaDS = '" + mads + "') AS SubQuery WHERE RowNum =" + top;

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String maSach = rs.getString("MaSach");
                String maDS = rs.getString("MaDS");
                String tinhTrang = rs.getString("TinhTrang");
                String loai = rs.getString("Loai");
                int gia = rs.getInt("GiaSach");
                int giaMuon = rs.getInt("GiaMuon");
                String trangThai = rs.getString("TrangThai");

                sach = new BookCopyDTO(maSach, maDS, loai, gia, giaMuon, trangThai, tinhTrang);
            }
            System.out.println("Bạn đã thực thi " + query);

            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sach;
    } 
}
