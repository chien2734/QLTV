package DAL;

import DTO.SachDTO;
import java.sql.*;
import java.util.ArrayList;

public class SachDAL {

    private final Connection conn;
    public SachDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }

    // Thêm sách mới
    public boolean addSach(SachDTO sachDTO) {
        String sql = "INSERT INTO Sach (id, tenSach, theloai, tacGia, NXB, namXB, soLuong) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sachDTO.getId());
            pstmt.setString(2, sachDTO.getTenSach());
            pstmt.setString(3, sachDTO.getTheloai());
            pstmt.setString(4, sachDTO.getTacGia());
            pstmt.setString(5, sachDTO.getNXB());
            pstmt.setInt(6, sachDTO.getNamXB());
            pstmt.setInt(7, sachDTO.getSoLuong());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding Sach: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật sách
    public boolean updateSach(SachDTO sachDTO) {
        String sql = "UPDATE Sach SET tenSach = ?, theloai = ?, tacGia = ?, NXB = ?, namXB = ?, soLuong = ? WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sachDTO.getTenSach());
            pstmt.setString(2, sachDTO.getTheloai());
            pstmt.setString(3, sachDTO.getTacGia());
            pstmt.setString(4, sachDTO.getNXB());
            pstmt.setInt(5, sachDTO.getNamXB());
            pstmt.setInt(6, sachDTO.getSoLuong());
            pstmt.setString(7, sachDTO.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating Sach: " + e.getMessage());
            return false;
        }
    }

    // Xóa sách
    public boolean deleteSach(String id) {
        String sql = "DELETE FROM Sach WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting Sach: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả sách
    public ArrayList<SachDTO> getAllSach() {
        ArrayList<SachDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                SachDTO sachDTO = new SachDTO(
                        rs.getString("id"), 
                        rs.getString("tenSach"),
                        rs.getString("theloai"),
                        rs.getString("tacGia"),
                        rs.getString("NXB"),
                        rs.getInt("namXB"),
                        rs.getInt("soLuong")
                );
                list.add(sachDTO);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all Sach: " + e.getMessage());
        }
        return list;
    }

    
    public boolean hasID(String id){
        boolean checked = false;
        String sql = "select * from Sach where id = ?";
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
    // Tìm kiếm sách theo tên
//    public List<SachDTO> searchSachByName(String name) {
//        List<SachDTO> list = new ArrayList<>();
//        String sql = "SELECT * FROM Sach WHERE tenSach LIKE ?";
//        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, "%" + name + "%");
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    SachDTO sachDTO = new SachDTO(
//                            rs.getString("id"), 
//                            rs.getString("tenSach"),
//                            rs.getString("theloai"),
//                            rs.getString("tacGia"),
//                            rs.getString("NXB"),
//                            rs.getInt("namXB"),
//                            rs.getInt("soLuong")
//                    );
//                    list.add(sachDTO);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error searching Sach by name: " + e.getMessage());
//        }
//        return list;
//    }
//
//    // Lấy sách theo mã
//    public SachDTO getSachById(String id) {
//        String sql = "SELECT * FROM Sach WHERE id = ?";
//        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, id);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    return new SachDTO(
//                            rs.getString("id"), 
//                            rs.getString("tenSach"),
//                            rs.getString("theloai"),
//                            rs.getString("tacGia"),
//                            rs.getString("NXB"),
//                            rs.getInt("namXB"),
//                            rs.getInt("soLuong")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error fetching Sach by id: " + e.getMessage());
//        }
//        return null;
//    }
//
//    // Lấy sách theo thể loại
//    public List<SachDTO> getSachByCategory(String categoryId) {
//        List<SachDTO> list = new ArrayList<>();
//        String sql = "SELECT * FROM Sach WHERE theloai = ?";
//        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, categoryId);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    SachDTO sachDTO = new SachDTO(
//                            rs.getString("id"), 
//                            rs.getString("tenSach"),
//                            rs.getString("theloai"),
//                            rs.getString("tacGia"),
//                            rs.getString("NXB"),
//                            rs.getInt("namXB"),
//                            rs.getInt("soLuong")
//                    );
//                    list.add(sachDTO);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error fetching Sach by category: " + e.getMessage());
//        }
//        return list;
//    }
//
//    // Lấy sách theo tác giả
//    public List<SachDTO> getSachByAuthor(String authorId) {
//        List<SachDTO> list = new ArrayList<>();
//        String sql = "SELECT * FROM Sach WHERE tacGia = ?";
//        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, authorId);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    SachDTO sachDTO = new SachDTO(
//                            rs.getString("id"), 
//                            rs.getString("tenSach"),
//                            rs.getString("theloai"),
//                            rs.getString("tacGia"),
//                            rs.getString("NXB"),
//                            rs.getInt("namXB"),
//                            rs.getInt("soLuong")
//                    );
//                    list.add(sachDTO);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error fetching Sach by author: " + e.getMessage());
//        }
//        return list;
//    }
//
//    // Lấy sách theo nhà xuất bản
//    public List<SachDTO> getSachByPublisher(String publisherId) {
//        List<SachDTO> list = new ArrayList<>();
//        String sql = "SELECT * FROM Sach WHERE NXB = ?";
//        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, publisherId);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    SachDTO sachDTO = new SachDTO(
//                            rs.getString("id"), 
//                            rs.getString("tenSach"),
//                            rs.getString("theloai"),
//                            rs.getString("tacGia"),
//                            rs.getString("NXB"),
//                            rs.getInt("namXB"),
//                            rs.getInt("soLuong")
//                    );
//                    list.add(sachDTO);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error fetching Sach by publisher: " + e.getMessage());
//        }
//        return list;
//    }
}
