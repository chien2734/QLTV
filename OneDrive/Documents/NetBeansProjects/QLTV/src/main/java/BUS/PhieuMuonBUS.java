package BUS;

import DAL.PhieuMuonDAL;
import DTO.PhieuMuonDTO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhieuMuonBUS {

    private PhieuMuonDAL phieuMuonDAL;
    private ArrayList<PhieuMuonDTO> ds;
    public PhieuMuonBUS() throws SQLException {
        phieuMuonDAL = new PhieuMuonDAL();
        ds = phieuMuonDAL.getAllPhieuMuon();
    }

    // Thêm phiếu mượn mới
    public boolean addPhieuMuon(PhieuMuonDTO phieuMuonDTO) {
        boolean isAdded = phieuMuonDAL.addPhieuMuon(phieuMuonDTO);
        if(isAdded){
            ds.add(phieuMuonDTO);
        }
        return isAdded;
    }

    // Cập nhật phiếu mượn
    public boolean updatePhieuMuon(PhieuMuonDTO phieuMuonDTO) {
        boolean isUpdated = phieuMuonDAL.updatePhieuMuon(phieuMuonDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(phieuMuonDTO.getId())) {
                    ds.set(i, phieuMuonDTO);
                    break;
                }
            }
        }
        return isUpdated;
    }

    public boolean hasID(String id){
        return phieuMuonDAL.hasID(id);
    }
    
    
    public String generateNewPMCode() {
         if (ds == null || ds.isEmpty()) {
            return "PM001";
        }
        String lastPMCode = ds.get(ds.size()-1).getId();
        int currentId = Integer.parseInt(lastPMCode.substring(2));
        int nextId = currentId + 1;
        return String.format("PM%03d", nextId);
    }
    
    public boolean updateTrangThaiPhieuMuon(PhieuMuonDTO pm) {
        boolean isUpdated = phieuMuonDAL.updateTrangThaiPhieuMuon(pm);
        if (isUpdated) {
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(pm.getId())) {
                    ds.get(i).setTrangThai(pm.getTrangThai());
                    ds.get(i).setSoLanGiaHan(pm.getSoLanGiaHan());
                    break;
                }
            }
        }
        return isUpdated;
    }
    
    // Xóa phiếu mượn
    public boolean deletePhieuMuon(String id) {
        boolean isDeleted = phieuMuonDAL.deletePhieuMuon(id);
        if(isDeleted){
            ds.removeIf(pt -> pt.getId().equals(id));
        }
        return isDeleted;
    }

    // Lấy tất cả phiếu mượn
    public ArrayList<PhieuMuonDTO> getAllPhieuMuon() {
        return ds;
    }

    // Tìm kiếm phiếu mượn theo mã phiếu mượn
    public PhieuMuonDTO searchPhieuMuonByMaPhieuMuon(String maPhieuMuon) {
        for(PhieuMuonDTO pm : ds){
            if(pm.getId().equals(maPhieuMuon)){
                return pm;
            }
        }
        return null;
    }

    // Tìm kiếm phiếu mượn theo mã thẻ
    public ArrayList<PhieuMuonDTO> searchPhieuMuonByMaThe(String maThe) {
        ArrayList<PhieuMuonDTO> result = new ArrayList<>();
        for(PhieuMuonDTO pm : ds){
            if(pm.getMaThe().contains(maThe)){
                result.add(pm);
            }
        }
        return result;
    }

    // Tìm kiếm phiếu mượn theo ngày mượn
    public ArrayList<PhieuMuonDTO> searchPhieuMuonByNgayMuon(Date ngayMuon) {
        ArrayList<PhieuMuonDTO> result = new ArrayList<>();
        for(PhieuMuonDTO pm : ds){
            if(pm.getNgayMuon().equals(ngayMuon)){
                result.add(pm);
            }
        }
        return result;
    }

    // Tìm kiếm phiếu mượn theo ngày trả
    public ArrayList<PhieuMuonDTO> searchPhieuMuonByHanTra(Date hanTra) {
        ArrayList<PhieuMuonDTO> result = new ArrayList<>();
        for(PhieuMuonDTO pm : ds){
            if(pm.getHanTra().equals(hanTra)){
                result.add(pm);
            }
        }
        return result;
    }
    
    public int getSum(){
        return ds == null ? 0 : ds.size();
    }
    
    public ArrayList<Object[]> sachChuaDuocTra(){
        return phieuMuonDAL.sachChuaDuocTra();
    }
    
     public ArrayList<Object[]> sachDuocTraNhieuNhat(){
        return phieuMuonDAL.sachDuocTraNhieuNhat();
    }
     
     public ArrayList<Object[]> danhSachPhieuMuon(int month, int year){
        return phieuMuonDAL.DanhSachPhieuMuonTheoThang(month, year);
    }
     
     public ArrayList<Object[]> danhSachPhieuTra(int month, int year){
        return phieuMuonDAL.DanhSachPhieuTraTheoThang(month, year);
    }
}
