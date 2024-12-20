package BUS;

import DAL.NXBDAL;
import DAL.SachDAL;
import DAL.TacGiaDAL;
import DTO.NXBDTO;
import DTO.SachDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class SachBUS {

    private final SachDAL sachDAL;
    private ArrayList<SachDTO> ds;

    // Constructor 
    public SachBUS() throws SQLException {
        sachDAL = new SachDAL();
        ds = sachDAL.getAllSach();
    }

    public ArrayList<SachDTO> getAllSach() throws SQLException {
        ArrayList<SachDTO> result = new ArrayList<>();
        for(SachDTO sach : ds){
            if(sach.getSoLuong() > 0){
                result.add(sach);
            }
        }
        return result;
    }

    public ArrayList<TheLoaiDTO> getAllTheLoai() {
        return sachDAL.getAllTheLoai();  // Gọi phương thức getAllTheLoai trong SachDAL
    }

    public ArrayList<TacGiaDTO> getAllTacGia() {
        return sachDAL.getAllTacGia();  // Gọi phương thức getAllTacGia trong SachDAL
    }

    public ArrayList<NXBDTO> getAllNhaXuatBan() {
        return sachDAL.getAllNhaXuatBan();  // Gọi phương thức getAllNhaXuatBan trong SachDAL
    }

    // Thêm sách mới
    public String addSach(SachDTO sachDTO) {
        if (sachDAL.hasID(sachDTO.getId())) {
            return "Mã này đã tồn tại, vui lòng nhập mã khác!";
        }
        boolean isAdded = sachDAL.addSach(sachDTO);
        if (isAdded) {
            ds.add(sachDTO);
            return "Thêm sách " + sachDTO.getTenSach() + " thành công!";
        }
        return "Thêm sách không thành công!";
    }

    // Cập nhật thông tin sách
    public String updateSach(SachDTO sachDTO) {
        boolean isUpdated = sachDAL.updateSach(sachDTO);
        if (ds == null) {
            ds = new ArrayList<>(); // Khởi tạo danh sách nếu chưa có
        }
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(sachDTO.getId())) {
                    ds.set(i, sachDTO);
                    break;
                }
            }
            return "Cập nhật thông tin sách " + sachDTO.getTenSach() + " thành công!";
        }
        return "Cập nhật không thành công!";

    }

    // Xóa sách
    public String deleteSach(String id) {
        boolean isDeleted = sachDAL.deleteSach(id);
        if (isDeleted) {
            ds.removeIf(sach -> sach.getId().equals(id));
            return "Xóa mã sách " + id + " thành công!";
        }
        return "Xóa không thành công!";
    }

    public boolean hasID(String id){
        return sachDAL.hasID(id);
    }
    
    public ArrayList<SachDTO> getSachByName(String name){
        ArrayList<SachDTO> result = new ArrayList<>();
        for(SachDTO sach : ds){
            if(sach.getTenSach().toLowerCase().contains(name.toLowerCase()) && sach.getSoLuong() > 0){
                result.add(sach);
            }
        }
        return result;
    }
    public ArrayList<SachDTO> getSachByTheLoai(String theloai){
        ArrayList<SachDTO> result = new ArrayList<>();
        for(SachDTO sach : ds){
            if(sach.getTheloai().toLowerCase().contains(theloai.toLowerCase()) && sach.getSoLuong() > 0){
                result.add(sach);
            }
        }
        return result;
    }
    public ArrayList<SachDTO> getSachByTacGia(String tacgia){
        ArrayList<SachDTO> result = new ArrayList<>();
        for(SachDTO sach : ds){
            if(sach.getTacGia().toLowerCase().contains(tacgia.toLowerCase()) && sach.getSoLuong() > 0){
                result.add(sach);
            }
        }
        return result;
    }
    public ArrayList<SachDTO> getSachByNXB(String nxb){
        ArrayList<SachDTO> result = new ArrayList<>();
        for(SachDTO sach : ds){
            if(sach.getNXB().toLowerCase().contains(nxb.toLowerCase()) && sach.getSoLuong() > 0){
                result.add(sach);
            }
        }
        return result;
    }
    // Lấy sách theo mã
    public SachDTO getSachById(String id) {
        for (SachDTO sach : ds) {
            if (sach.getId().equals(id) && sach.getSoLuong() > 0) {
                return sach;
            }
        }
        return null;
    }

    public String getIdByTenTheLoai(String tenTheLoai) throws SQLException {
        TheLoaiBUS theloaiBUS = new TheLoaiBUS();
        ArrayList<TheLoaiDTO> list = theloaiBUS.searchTheLoaiByName(tenTheLoai);
        if (!list.isEmpty()) {
            return list.get(0).getId(); 
        }
        return null; 
    }

    public String getIdByTenNhaXuatBan(String nhaXuatBan) throws SQLException {
        NXBBUS nxbBUS = new NXBBUS();
        ArrayList<NXBDTO> list = nxbBUS.searchNXBByName(nhaXuatBan);
        if (!list.isEmpty()) {
            return list.get(0).getId(); 
        }
        return null; 
    }

    public String getIdByTenTacGia(String tacGia) throws SQLException {
        TacGiaBUS tacGiaBUS = new TacGiaBUS();
        ArrayList<TacGiaDTO> list = tacGiaBUS.searchTacGiaByName(tacGia);
        if (!list.isEmpty()) {
            return list.get(0).getId(); 
        }
        return null;
    }

    public String generateMaSach() {
        if (ds == null || ds.isEmpty()) {
            return "S001";
        }
        ds.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
        String lastMaSach = ds.get(ds.size() - 1).getId();
        int lastNumber = Integer.parseInt(lastMaSach.substring(1));
        String newMaSach = "S" + String.format("%03d", lastNumber + 1);
        ds.add(new SachDTO(newMaSach, "", "", "", "", 0, 0));
        ds.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));

        return newMaSach;
    }
    
    public int getSum(){
        int tong = 0;
        for(SachDTO s : ds){
            tong+=s.getSoLuong();
        }
        return tong;
    }
    
//    public ArrayList<Object[]> getSoLuongSachBYTacGia(){
//        return sachDAL.getSoLuongSachBYTacGia();
//    }
//    
//    public ArrayList<Object[]> getSoLuongSachBYTheLoai(){
//        return sachDAL.getSoLuongSachBYTheLoai();
//    }
//    public ArrayList<Object[]> getSoLuongSachBYNXB(){
//        return sachDAL.getSoLuongSachBYNXB();
//    }
    
    public ArrayList<Object[]> getSachKhongNguyenVen(){
        return sachDAL.getSachKhongNguyenVen();
    }
    
    public ArrayList<Object[]> getSachTheoLuotMuon(){
        return sachDAL.getSachTheoLuotMuon();
    }
    
    public ArrayList<Object[]> getTop10SachMuonNhieuNhat(){
        return sachDAL.getTop10SachMuonNhieuNhat();
    }
    
    public ArrayList<Object[]> getTop10SachMuonItNhat(){
        return sachDAL.getTop10SachMuonItNhat();
    }
    public int getSoSachConLai(String id){
        if(sachDAL.getSoSachConLai(id) == 0){
            SachDTO s = getSachById(id);
            return s.getSoLuong();
        } else{
            return sachDAL.getSoSachConLai(id);
        }      
    }
    
    public int getSachCLcuaTL(String idTheLoai){
        int slcl = 0;
        for(SachDTO s : ds){
            if(s.getTheloai().equals(idTheLoai)){
               slcl +=  getSoSachConLai(s.getId());
            }
        }
        return slcl;
    }
    
    public int getSachCLcuaNXB(String idnxb){
        int slcl = 0;
        for(SachDTO s : ds){
            if(s.getNXB().equals(idnxb)){
               slcl +=  sachDAL.getSoSachConLai(s.getId());
            }
        }
        return slcl;
    }
    
    public int getSachCLcuaTG(String idTacGia){
        int slcl = 0;
        for(SachDTO s : ds){
            if(s.getTacGia().equals(idTacGia)){
               slcl +=  getSoSachConLai(s.getId());
            }
        }
        return slcl;
    }
}
