package DAL;

import DTO.PhieuMuonDTO;
import java.sql.*;
import java.util.ArrayList;

public class PhieuMuonDAL {
    
    private final Connection conn;
    public PhieuMuonDAL() throws SQLException {
        conn = connectionDB.openConnection();
    }

    // Thêm phiếu mượn mới
    public boolean addPhieuMuon(PhieuMuonDTO phieuMuon) {
        String sql = "INSERT INTO PhieuMuon (id, maThe, ngayMuon, hanTra, tienCoc, trangThai, soLan) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phieuMuon.getId());
            pstmt.setString(2, phieuMuon.getMaThe());
            pstmt.setDate(3, new java.sql.Date(phieuMuon.getNgayMuon().getTime()));
            pstmt.setDate(4, new java.sql.Date(phieuMuon.getHanTra().getTime()));
            pstmt.setDouble(5, phieuMuon.getTienCoc());
            pstmt.setString(6, phieuMuon.getTrangThai());
            pstmt.setInt(7, phieuMuon.getSoLanGiaHan());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật phiếu mượn
    public boolean updatePhieuMuon(PhieuMuonDTO phieuMuon) {
        String sql = "UPDATE PhieuMuon SET maThe = ?, ngayMuon = ?, hanTra = ?, tienCoc = ?, trangThai = ? WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phieuMuon.getMaThe());
            pstmt.setDate(2, new java.sql.Date(phieuMuon.getNgayMuon().getTime()));
            pstmt.setDate(3, new java.sql.Date(phieuMuon.getHanTra().getTime()));
            pstmt.setDouble(4, phieuMuon.getTienCoc());
            pstmt.setString(5, phieuMuon.getTrangThai());
            pstmt.setString(6, phieuMuon.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating PhieuMuon: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateTrangThaiPhieuMuon(PhieuMuonDTO pm) {
        String sql = "UPDATE PhieuMuon SET trangThai = ?, soLan = ? WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setNString(1, pm.getTrangThai());
            pstmt.setInt(2, pm.getSoLanGiaHan());
            pstmt.setString(3, pm.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating status PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Xóa phiếu mượn
    public boolean deletePhieuMuon(String id) {
        String sql = "DELETE FROM PhieuMuon WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    // Lấy tất cả phiếu mượn
    public ArrayList<PhieuMuonDTO> getAllPhieuMuon() {
        ArrayList<PhieuMuonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuMuon";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); 
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PhieuMuonDTO phieuMuon = new PhieuMuonDTO(
                        rs.getString("id"),
                        rs.getString("maThe"),
                        rs.getDate("ngayMuon"),
                        rs.getDate("hanTra"),
                        rs.getDouble("tienCoc"),
                        rs.getString("trangThai"),
                        rs.getInt("soLan")
                );
                list.add(phieuMuon);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all PhieuMuon: " + e.getMessage());
        }
        return list;
    }

    public boolean hasID(String id){
        boolean checked = false;
        String sql = "select * from PhieuMuon where id = ?";
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
   
}
