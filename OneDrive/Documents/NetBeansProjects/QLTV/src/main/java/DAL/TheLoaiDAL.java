package DAL;

import DTO.TheLoaiDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAL extends connectionDB {
    
    // Thêm thể loại
    public boolean addTheLoai(TheLoaiDTO theLoai) {
        String sql = "INSERT INTO TheLoai (id, ten) VALUES (?, ?)";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting TheLoai: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách thể loại
    public List<TheLoaiDTO> getAllTheLoai() {
        List<TheLoaiDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TheLoai";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                TheLoaiDTO theLoai = new TheLoaiDTO(rs.getString("id"), rs.getString("ten"));
                list.add(theLoai);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching TheLoai: " + e.getMessage());
        }
        return list;
    }

    // Lấy thể loại theo ID
    public TheLoaiDTO getTheLoaiById(String id) {
        String sql = "SELECT * FROM TheLoai WHERE id = ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TheLoaiDTO(rs.getString("id"), rs.getString("ten"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching TheLoai by ID: " + e.getMessage());
        }
        return null;
    }
    
    // Tìm kiếm thể loại theo tên
    public List<TheLoaiDTO> searchTheLoaiByName(String name) {
        List<TheLoaiDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TheLoai WHERE ten LIKE ?";
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TheLoaiDTO theLoaiDTO = new TheLoaiDTO(rs.getString("id"), rs.getString("ten"));
                    list.add(theLoaiDTO);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching TheLoai by name: " + e.getMessage());
        }
        return list;
    }
}
