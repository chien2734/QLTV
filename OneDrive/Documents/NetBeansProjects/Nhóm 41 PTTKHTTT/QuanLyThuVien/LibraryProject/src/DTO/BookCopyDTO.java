/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import BLL.CT_PhieuMuonBLL;
import BLL.PhieuMuonBLL;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BookCopyDTO {

    /*
    create table Sach(
	MaSach	varchar(10) primary key,
	MaDS	varchar(10) foreign key references DauSach(MaDS),
	Loai	nvarchar(50),	-- "Có thể mượn", "Đọc tại chỗ"
	GiaSach	int,
	GiaMuon int,				-- Tính tự động: Sách mới: Giá sách < 500: GiaSach*10% + GiaSach; Sách > 500; GiaSach*15% + GiaSach
							--	 Sách hư hỏng nhẹ: Giá sách < 500: GiaSach*8% + GiaSach; Sách > 500; GiaSach*12% + GiaSach
	TrangThai nvarchar,			-- "Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất" ------ Trạng thái này chỉ hiển thị trên giao diện admin để tính GiaMuon tự động ko hiện trên giao diện user
	TinhTrang	nvarchar(50)	--"Hiện có", "Đã mượn", "Không phục vụ" (cho trường hợp bị mất); sách bị "Hư hỏng nặng" set Loai = "Đọc tại chỗ"
    )
     */
    private String maSach;
    private String maDS;
    private String loaiSach;
    private int gia;
    private int giaMuon = 0;
    private String trangThai;
    private String tinhTrang;
    
    private PhieuMuonBLL pmBLL = new PhieuMuonBLL();
    private CT_PhieuMuonBLL ctpmBLL = new CT_PhieuMuonBLL();
    
    public BookCopyDTO(String maSach) {
        this.maSach = maSach;
    }

    public BookCopyDTO(String maSach, String maDS, String loaiSach, int gia, int giaMuon, String trangThai, String tinhTrang) {
        this.maSach = maSach;
        this.maDS = maDS;
        this.loaiSach = loaiSach;
        this.gia = gia;
        this.giaMuon = giaMuon;
        this.trangThai = trangThai;
        this.tinhTrang = tinhTrang;
    }

    public BookCopyDTO(String maSach, String maDS, String loaiSach, int gia, String trangThai, String tinhTrang) {
        this.maSach = maSach;
        this.maDS = maDS;
        this.loaiSach = loaiSach;
        this.gia = gia;
        this.trangThai = trangThai;
        this.tinhTrang = tinhTrang;
    }
    
    public BookCopyDTO() {
    }

    public BookCopyDTO(String maSach, String maDS, String loai, int giaMuon, String tinhTrang) {
        this.maSach = maSach;
        this.maDS = maDS;
        this.giaMuon = giaMuon;
        this.tinhTrang = tinhTrang;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String tenSach) {
        this.maSach = tenSach;
    }

    public String getMaDS() {
        return maDS;
    }

    public void setMaDS(String maDS) {
        this.maDS = maDS;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }
    ////////////
    public void setTinhTrang(String tinhTrang) {
        ArrayList<PhieuMuonDTO> listpm = pmBLL.getBorrowByCondition(" TrangThai = N'Quá hạn mượn'");
        if (listpm != null) {
            for (PhieuMuonDTO pm : listpm) {
                CT_PhieuMuonDTO ctpm = ctpmBLL.getDetailBorrowByMaPhieu1(pm.getMaPhieu(), maSach);
                if (ctpm != null && !this.tinhTrang.equalsIgnoreCase("Đã mượn")) {
                    this.tinhTrang = "Hiện có";
                    break;
                } else this.tinhTrang = tinhTrang;
            }
        }
        if (this.trangThai.equalsIgnoreCase("Mất")) {
            this.tinhTrang = "Không phục vụ";
        }
        
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        if (this.trangThai.equalsIgnoreCase("Hư hỏng nặng")) {
            this.loaiSach = "Đọc tại chỗ";
        } else this.loaiSach = loaiSach;
        
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getGiaMuon() {
        return giaMuon;
    }

    public void setGiaMuon(int gia) {
        this.giaMuon = tinhGiaMuon(gia);
    }

    public int tinhGiaMuon(int gia) {
        int giaMuonSach = 0;
        if (gia < 200000 && trangThai.equals("Nguyên vẹn")) {
            giaMuonSach = (int) Math.round(gia * 1.1);          // Làm tròn đến số nguyên
            System.out.println("giaMuon = " + giaMuonSach);
        } else if (gia > 200000 && trangThai.equals("Nguyên vẹn")) {
            giaMuonSach = (int) Math.round(gia * 1.15);
            System.out.println("giaMuon = " + giaMuonSach);
        } else if (gia > 200000 && trangThai.equals("Hư hỏng nhẹ")) {
            giaMuonSach = (int) Math.round(gia * 1.1);
            System.out.println("giaMuon = " + giaMuonSach);
        } else if (gia < 200000 && trangThai.equals("Hư hỏng nhẹ")) {
            giaMuonSach = (int) Math.round(gia * 0.9);
            System.out.println("giaMuon = " + giaMuonSach);
        } else if (trangThai.equals("Hư hỏng nặng") || trangThai.equals("Mất")) {
            giaMuonSach = 0;
        }
        System.out.println("giaMuon = " + giaMuonSach);
        return giaMuonSach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "BookCopyDTO{" + "maSach=" + maSach + ", maDS=" + maDS + ", loaiSach=" + loaiSach + ", gia=" + gia + ", giaMuon=" + giaMuon + ", trangThai=" + trangThai + ", tinhTrang=" + tinhTrang + '}';
    }

}
