package DAL;

import DTO.TheLoaiDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TheLoaiDAL extends connectionDB {

    private final Connection conn;
    
    public TheLoaiDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }
    
    // Thêm thể loại
    public boolean addTheLoai(TheLoaiDTO theLoai) {
        String sql = "INSERT INTO TheLoai (id, ten) VALUES (?, ?)";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, theLoai.getId());
            pstmt.setString(2, theLoai.getTen());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding TheLoai: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật thể loại
    public boolean updateTheLoai(TheLoaiDTO theLoai) {
        String sql = "UPDATE TheLoai SET ten = ? WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, theLoai.getTen());
            pstmt.setString(2, theLoai.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating TheLoai: " + e.getMessage());
            return false;
        }
    }

    // Xóa thể loại
    public boolean deleteTheLoai(String id) {
        String sql = "DELETE FROM TheLoai WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting TheLoai: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách thể loại
    public ArrayList<TheLoaiDTO> getAllTheLoai() {
        ArrayList<TheLoaiDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TheLoai";
        try (
                PreparedStatement pstmt = conn.prepareStatement(sql); 
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                TheLoaiDTO theLoai = new TheLoaiDTO(rs.getString("id"), rs.getString("ten"));
                list.add(theLoai);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching TheLoai: " + e.getMessage());
        }
        return list;
    }

    
    public boolean hasID(String id){
        boolean checked = false;
        String sql = "select * from TheLoai where id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return checked;
    }

    public int getSoLuongSachofTheLoai(String id){
        int soLuong = 0;
        String sql = "SELECT tl.id, tl.ten, SUM(s.SoLuong) AS SoLuongSach "
                + "FROM TheLoai tl "
                + "INNER JOIN Sach s ON s.theloai = tl.id "
                + "WHERE tl.id = ? "
                + "GROUP BY tl.id, tl.Ten";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString( 1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt(3);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return soLuong;
    }
    
    public int getSoLuongSachConlai(String id) {
        int soLuong = 0;
        String sql = """
                     SELECT tl.id, tl.ten, sach.soLuong - ISNULL(SUM(ctpm.soLuong), 0) AS SoLuongConLai 
                     FROM TheLoai tl 
                     JOIN Sach sach ON sach.theloai = tl.id 
                     LEFT JOIN  CT_PhieuMuon ctpm ON sach.id = ctpm.maSach 
                     WHERE tl.id = ? and ctpm.trangThai = 'Đang mượn'
                     GROUP BY tl.id, tl.ten 
                     """;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt(3);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return soLuong;
    }
}
