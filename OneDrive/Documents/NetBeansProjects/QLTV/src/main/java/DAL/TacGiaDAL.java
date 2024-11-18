package DAL;

import DTO.TacGiaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TacGiaDAL extends connectionDB {

    private final Connection conn;
    public TacGiaDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }

    // Thêm tác giả mới
    public boolean addTacGia(TacGiaDTO tacGia) {
        String sql = "INSERT INTO TacGia (id, ten) VALUES (?, ?)";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tacGia.getId());
            pstmt.setString(2, tacGia.getTen());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding TacGia: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật thông tin tác giả
    public boolean updateTacGia(TacGiaDTO tacGia) {
        String sql = "UPDATE TacGia SET ten = ? WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tacGia.getTen());
            pstmt.setString(2, tacGia.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating TacGia: " + e.getMessage());
            return false;
        }
    }

    // Xóa tác giả
    public boolean deleteTacGia(String id) {
        String sql = "DELETE FROM TacGia WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting TacGia: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách tất cả tác giả
    public ArrayList<TacGiaDTO> getAllTacGia() {
        ArrayList<TacGiaDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TacGia";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                TacGiaDTO tacGia = new TacGiaDTO(
                        rs.getString("id"),
                        rs.getString("ten")
                );
                list.add(tacGia);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all TacGia: " + e.getMessage());
        }
        return list;
    }

    public boolean hasID(String id){
        boolean checked = false;
        String sql = "select * from TacGia where id = ?";
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
    // Lấy thông tin tác giả theo ID
//    public TacGiaDTO getTacGiaById(String id) {
//        String sql = "SELECT * FROM TacGia WHERE id = ?";
//        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, id);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    return new TacGiaDTO(
//                            rs.getString("id"),
//                            rs.getString("ten")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error fetching TacGia by ID: " + e.getMessage());
//        }
//        return null;
//    }
//
//    // Tìm kiếm tác giả theo tên
//    public List<TacGiaDTO> searchTacGiaByName(String name) {
//        List<TacGiaDTO> list = new ArrayList<>();
//        String sql = "SELECT * FROM TacGia WHERE ten LIKE ?";
//        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, "%" + name + "%");
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    TacGiaDTO tacGiaDTO = new TacGiaDTO(
//                        rs.getString("id"), 
//                        rs.getString("ten")
//                    );
//                    list.add(tacGiaDTO);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error searching TacGia by name: " + e.getMessage());
//        }
//        return list;
//    }
}
