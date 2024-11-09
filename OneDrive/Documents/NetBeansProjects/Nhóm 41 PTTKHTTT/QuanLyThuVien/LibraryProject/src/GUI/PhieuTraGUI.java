/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import DAL.*;
import DTO.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

/**
 *
 * @author PC
 */
public class PhieuTraGUI extends JPanel{
    private JLabel lb_timkiem, lb_tienphat;
    private JComboBox cb_timkiem;
    private JTextField tf_timkiem, tf_tienphat;
    private JPanel jp_pm, jp_ctpm, jp_timkiem;
    private JButton jb_trasach, jb_timkiem, jb_tratoanbo, jb_xemtatca;
    private DefaultTableModel tblModel_pm, tblModel_ctpm;
    private JTable tb_pm, tb_ctpm;
    private JScrollPane scroll1, scroll2;
    private String tenDN;
    private ActionListener ac;
    
    private PhieuMuonBLL pmBLL = new PhieuMuonBLL();
    private CT_PhieuMuonBLL ctpmBLL = new CT_PhieuMuonBLL();
    private CartUserBLL cartUserBLL = new CartUserBLL();
    private CardBUS cardBLL = new CardBUS();
    private BookCopyBLL sachBLL = new BookCopyBLL();
    private BookBLL booksBLL = new BookBLL();
    private ReaderBUS readerBLL = new ReaderBUS();
    private ArrayList<CartUserDTO> listCart = new ArrayList<>();
    private PhieuTraBLL ptBLL = new PhieuTraBLL();
    private CT_PhieuTraBLL ctptBLL = new CT_PhieuTraBLL();
    private ArrayList<CT_PhieuMuonDTO> ctPhieu = new ArrayList<>();
    
    private static int numPT;
            
    public PhieuTraGUI() {
       numPT = ptBLL.selectAll().size();
       this.init();
       jb_timkiem.setBackground(new Color(187, 222, 251));
       jb_trasach.setBackground(new Color(187, 222, 251));
       jb_xemtatca.setBackground(new Color(187, 222, 251));
       cb_timkiem.setBackground(Color.white);
    }

    private void init() {
        setBackground(Color.WHITE);
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
        setLayout(null);
        
        //------------Panel tìm kiếm thông tin phiếu------------------
        Font font = new Font("Helvetica", 0, 30);
        Font font_table = new Font("Helvetica", 0, 22);
        jp_timkiem = new JPanel();
        jp_timkiem.setBackground(Color.WHITE);
        
        lb_timkiem = new JLabel("Tìm kiếm theo: ");
        lb_timkiem.setForeground(Color.red);
        lb_timkiem.setFont(font);
        lb_timkiem.setBounds(120, 20, 250, 35);
        
        
        String[] cb = {"Mã phiếu", "Mã thẻ mượn", "Tên độc giả", "Ngày mượn", "Hạn trả"};
        cb_timkiem = new JComboBox(cb);
        cb_timkiem.setBounds(350, 20, 250, 35);
        cb_timkiem.setFont(new Font("Helvetica", 0, 25));
        
        
        tf_timkiem = new JTextField();
        tf_timkiem.setBounds(650, 20, 250, 35);
        tf_timkiem.setFont(new Font("Helvetica", 0, 25));
        
        jb_timkiem = new JButton("Tìm kiếm");
        jb_timkiem.setBounds(930, 20, 230, 35);
        jb_timkiem.setFont(font);
        jb_timkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jButtonTimKiemActionPerformed();
            }

        });
        
        jb_xemtatca = new JButton("Xem tất cả");
        jb_xemtatca.setBounds(1200, 20, 230, 35);
        jb_xemtatca.setFont(font);
        jb_xemtatca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jButtonXemTatCaActionPerformed();
            }
        });
        
        jp_timkiem.setLayout(null);
        jp_timkiem.add(lb_timkiem); 
        jp_timkiem.add(cb_timkiem);
        jp_timkiem.add(tf_timkiem);
        jp_timkiem.add(jb_timkiem);
        jp_timkiem.add(jb_xemtatca);
        this.add(jp_timkiem);
        jp_timkiem.setBounds(20, 10, 1700, 70);
        
        //----------------- Panel Phiếu mượn đã đăng ký------------------
        jp_pm = new JPanel();
        jp_pm.setBackground(Color.WHITE);
        tb_pm = new JTable();
        scroll1 = new JScrollPane();
        
        jp_pm.setBorder(BorderFactory.createTitledBorder(null, "Phiếu mượn đã đăng ký", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 1, 26), new Color(24, 116, 205)));
        jp_pm.setLayout(null);
        
        tblModel_pm = new DefaultTableModel();
        String[] header_pm = new String[]{"Mã phiếu", "Mã thẻ mượn", "Tên độc giả", "Ngày mượn", "Hạn trả", "Tiền cọc", "Trạng Thái"};
        tblModel_pm.setColumnIdentifiers(header_pm);
        tb_pm.setModel(tblModel_pm);
        tb_pm.getTableHeader().setFont(new Font("Helvetica", 0, 26));
        tb_pm.getTableHeader().setBackground(new Color(187, 222, 251));
        tb_pm.setDefaultEditor(Object.class, null);
        // Xoá đường biên và khoảng cách giữa các ô
        tb_pm.setShowGrid(false);
        tb_pm.setIntercellSpacing(new Dimension(0, 0));
        scroll1.setViewportView(tb_pm);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tb_pm.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        tb_pm.addMouseListener(new MouseAdapter() {
            public  void mouseClicked(MouseEvent e) {
                jTablePMMouseClicked(e);
            }
        });
        
        ArrayList<PhieuMuonDTO> dspm = pmBLL.getBorrowByCondition("TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả'");
        for (PhieuMuonDTO pm : dspm) {
            tblModel_pm.addRow(new Object[]{pm.getMaPhieu(), pm.getMaTheMuon(), pm.getTenDG(), pm.getNgayMuon(), pm.getHanTra(), pm.getTienCoc(), pm.getTrangThai()});
        }
        
        
        tb_pm.setFont(font_table);
        tb_pm.setRowHeight(40);
        scroll1.setBounds(30, 50, 1520, 270);
        jp_pm.add(scroll1);
       
        
        this.add(jp_pm);
        //jpm_pm..setBounds(0, 700, this.getMaximumSize().width, this.getMaximumSize().height);
        jp_pm.setBounds(30, 100, 1600, 350);
        
        //----------------- Panel Chi tiết phiếu đk------------------
        jp_ctpm = new JPanel();
        jp_ctpm.setBackground(Color.WHITE);
        tb_ctpm = new JTable();
        scroll2 = new JScrollPane();
        
        jp_ctpm.setBorder(BorderFactory.createTitledBorder(null, "Chi tiết phiếu đăng ký", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 1, 26), new Color(24, 116, 205)));
        jp_ctpm.setLayout(null);
        
        tblModel_ctpm = new DefaultTableModel();
        String[] header_ctpm = new String[] {"Mã sách", "Tên sách", "Tình trạng nhận", "Tiền phạt", "Chọn"};
        tblModel_ctpm.setColumnIdentifiers(header_ctpm);
        tb_ctpm.setModel(tblModel_ctpm);
        tb_ctpm.setShowGrid(false);
        tb_ctpm.setIntercellSpacing(new Dimension(0, 0));
        
        tb_ctpm.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tb_ctpm.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tb_ctpm.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tb_ctpm.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        TableColumn column_tinhTrang = tb_ctpm.getColumnModel().getColumn(2);
        JComboBox<String> cb_tinhTrang = new JComboBox<>(new String[]{"Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất"});
        int selected = tb_pm.getSelectedRow();
        if (selected != -1) {
            String maPhieu = (String) tb_pm.getValueAt(selected, 0);
            ArrayList<CT_PhieuMuonDTO> listctpm = ctpmBLL.getDetailBorrowByMaPhieu(maPhieu);
            for (CT_PhieuMuonDTO ctpm : listctpm) {
                if (ctpm.getTrangThai().equalsIgnoreCase("Nguyên vẹn")) {
                    cb_tinhTrang.addItem("Nguyên vẹn"); cb_tinhTrang.addItem("Hư hỏng nhẹ"); cb_tinhTrang.addItem("Hư hỏng nặng"); cb_tinhTrang.addItem("Mất"); 
                } else if (ctpm.getTrangThai().equalsIgnoreCase("Hư hỏng nhẹ")) {
                    cb_tinhTrang.addItem("Hư hỏng nhẹ"); cb_tinhTrang.addItem("Hư hỏng nặng"); cb_tinhTrang.addItem("Mất");
                }
            }
        }
        cb_tinhTrang.setFont(new Font("Helvetica", 0, 23));
        column_tinhTrang.setCellEditor(new DefaultCellEditor(cb_tinhTrang));
        
        TableColumn selectColumn = tb_ctpm.getColumnModel().getColumn(4);
        JCheckBox chon = new JCheckBox();
        // Căn chỉnh dữ liệu ra giữa ô
        selectColumn.setCellEditor(new DefaultCellEditor(chon));
        selectColumn.setCellRenderer(tb_ctpm.getDefaultRenderer(Boolean.class));
        
        tb_ctpm.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 4 ) {
                    tinhTongTienPhat();
                }
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 2) {
                    tinhTienPhat();
                    tinhTongTienPhat();
                }
            }
        });
        
        tb_ctpm.getTableHeader().setFont(new Font("Helvetica", 0, 26));
        tb_ctpm.getTableHeader().setBackground(new Color(187, 222, 251));
        tb_ctpm.setDefaultEditor(Object.class, null);
        scroll2.setViewportView(tb_ctpm);
        
        tb_ctpm.setFont(font_table);
        //setRowHeight: chỉnh độ rộng của từng dòng trong bảng
        tb_ctpm.setRowHeight(40);
        scroll2.setBounds(30, 50, 1520, 230);
        jp_ctpm.add(scroll2);
        
        this.add(jp_ctpm);
        jp_ctpm.setBounds(30, 540, 1600, 310);
        
        //----------------------
        lb_tienphat = new JLabel("Thanh toán (VNĐ)");
        lb_tienphat.setFont(new Font("Helvetica", 0, 26));
        lb_tienphat.setBounds(1050, 480, 250, 40);
        lb_tienphat.setForeground(Color.red);
        this.add(lb_tienphat);
        
        tf_tienphat = new JTextField("0");
        tf_tienphat.setFont(new Font("Helvetica", 0, 26));
        tf_tienphat.setBounds(1300, 480, 250, 40);
        tf_tienphat.setEditable(false);
        tf_tienphat.setBackground(Color.WHITE);
        tf_tienphat.setOpaque(true);
        tf_tienphat.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        this.add(tf_tienphat);
        
        jb_trasach = new JButton("Trả sách");
        jb_trasach.setBounds(650, 880, 250, 40);;
        jb_trasach.setFont(font);
        this.add(jb_trasach);
        jb_trasach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               jButtonTraSach();
            }
        });
        
//        jb_tratoanbo = new JButton("Trả toàn bộ phiếu");
//        jb_tratoanbo.setBounds(900, 880, 300, 40);
//        jb_tratoanbo.setFont(font);
//        this.add(jb_tratoanbo);
//        cb_tinhTrang.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                jButtonTraToanBo();
//            }
//        });
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////
    
    public void docDuLieuPhieuMuon(ArrayList<PhieuMuonDTO> list) {
        try {
            tblModel_pm.setRowCount(0);
            for (PhieuMuonDTO pm : list) {
                tblModel_pm.addRow(new Object[]{pm.getMaPhieu(), pm.getMaTheMuon(), pm.getTenDG(), pm.getNgayMuon(), pm.getHanTra(), pm.getTienCoc(), pm.getTrangThai()});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
        
    public void docDuLieuCTPhieuMuon(String maPhieu) {
        try {
            ArrayList<CT_PhieuMuonDTO> listctpm = ctpmBLL.getDetailBorrowByMaPhieu(maPhieu);
            ArrayList<CT_PhieuMuonDTO> dspm = new ArrayList<>();
            tblModel_ctpm.setRowCount(0);
            boolean isSelected = false;
            for (CT_PhieuMuonDTO ctpm : listctpm) {
                // Hiển thị sách chưa trả
                //System.out.println(ctpm.toString());
                //ArrayList<PhieuTraDTO> listpt = ptBLL.selectByCondition(" MaPhieuMuon = '"+ ctpm.getMaPhieu() + "'");
                BookCopyDTO book = sachBLL.getBookCopyById(new BookCopyDTO(ctpm.getMaSach()));
                if (book.getTinhTrang().equalsIgnoreCase("Đã mượn")) {
                    tblModel_ctpm.addRow(new Object[]{ctpm.getMaSach(), ctpm.getTenSach(), ctpm.getTrangThai(), 0, isSelected});
                    dspm.add(ctpm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jTablePMMouseClicked(MouseEvent e) {
        int selected = tb_pm.getSelectedRow();       
        if (selected != -1) {
            String maphieu = (String) tb_pm.getValueAt(selected, 0);
            docDuLieuCTPhieuMuon(maphieu);
            //System.out.println("Chọn phiếu: " + maphieu + "; Chọn dòng: " + tb_ctpm.getSelectedRow() + "; Chọn cột: " + tb_ctpm.getSelectedColumn());
        }
    }
    
    private void jButtonXemTatCaActionPerformed() {
        ArrayList<PhieuMuonDTO> dspm = pmBLL.getBorrowByCondition("TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả'");
        docDuLieuPhieuMuon(dspm);
    }
    
    private void tinhTienPhat() { 
        for (int row=0; row < tb_ctpm.getRowCount(); row++) {
            String trangThai = (String) tb_ctpm.getValueAt(row, 2);
            String maSach = (String) tb_ctpm.getValueAt(row, 0);
            BookCopyDTO sach = sachBLL.getBookCopyById(new BookCopyDTO(maSach));
           
            int tienPhat = 0;       // Làm tròn đến số nguyên
            if (trangThai.equals("Nguyên vẹn") || trangThai.equalsIgnoreCase(sach.getTrangThai())) {
                tienPhat = 0;          
            } else if (sach.getGia() > 200000 && trangThai.equals("Hư hỏng nhẹ") && sach.getTrangThai().equalsIgnoreCase("Nguyên vẹn")) {
                tienPhat = (int) Math.round(sach.getGia() * 0.07);
            } else if (sach.getGia() < 200000 && trangThai.equals("Hư hỏng nhẹ") && sach.getTrangThai().equalsIgnoreCase("Nguyên vẹn")) {
                tienPhat = (int) Math.round(sach.getGia() * 0.05);
            } else if (sach.getGia() > 200000 && trangThai.equals("Hư hỏng nặng") && sach.getTrangThai().equalsIgnoreCase("Nguyên vẹn")) {
                tienPhat = (int) Math.round(sach.getGia() * 0.6);
            } else if (sach.getGia() < 200000 && trangThai.equals("Hư hỏng nặng") && sach.getTrangThai().equalsIgnoreCase("Nguyên vẹn")) {
                tienPhat = (int) Math.round(sach.getGia() * 0.5);
            } else if (trangThai.equals("Mất") && sach.getTrangThai().equalsIgnoreCase("Nguyên vẹn")) {
                tienPhat = (int) Math.round(sach.getGia() * 1.2);
            } else if (trangThai.equals("Hư hỏng nặng") && sach.getTrangThai().equalsIgnoreCase("Hư hỏng nhẹ")) {
                tienPhat = (int) Math.round(sach.getGia() * 0.3);
            } else if (trangThai.equals("Mất") && sach.getTrangThai().equalsIgnoreCase("Hư hỏng nhẹ")) {
                tienPhat = (int) Math.round(sach.getGia() * 0.8);
            } else if (trangThai.equals("Mất") && sach.getTrangThai().equalsIgnoreCase("Hư hỏng nặng")) {
                tienPhat = (int) Math.round(sach.getGia() * 0.5);
            }
            
            tb_ctpm.setValueAt(tienPhat, row, 3);            
        }
    }
    
    private int tinhTienPhatTreNgay() {
        int selected = tb_pm.getSelectedRow();
        int temp = 0;
        if (selected != -1) {
            String maphieu = (String) tb_pm.getValueAt(selected, 0);
            PhieuMuonDTO pm = pmBLL.getBorrowById(new PhieuMuonDTO(maphieu));

            // Tính tiền số ngày trễ hạn trả
            Calendar currentDate = Calendar.getInstance();
            Date hantra = pm.getHanTra();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayTra = dateFormat.format(currentDate.getTimeInMillis());
            Date ngaytra = java.sql.Date.valueOf(ngayTra);

            if (hantra != null) {
                if (ngaytra.after(hantra)) {            
                    long millisecondsLate = ngaytra.getTime() - hantra.getTime();
                    long ngayTre = millisecondsLate / (24 * 60 * 60 * 1000);
                    temp = (int) (ngayTre*3000); 
                } 
            }
        }
        return temp;
    }
    
    private int tinhTongTienPhat() {
        int tongTien = 0;
        //String maphieu = (String) tb_ctpm.getColumnName(0);
        for (int row=0; row < tb_ctpm.getRowCount(); row++) {
            boolean isSelected = (boolean) tb_ctpm.getValueAt(row, 4);
            if (isSelected) {
                String maSach = (String) tb_ctpm.getValueAt(row, 0);
                BookCopyDTO book = sachBLL.getBookCopyById(new BookCopyDTO(maSach));
                int tienPhat = (int) tb_ctpm.getValueAt(row, 3);
                tongTien = tongTien + tienPhat + tinhTienPhatTreNgay() - book.getGiaMuon();
            }
        } 
//        int temp = tinhTienPhatTreNgay();
//        tongTien = tongTien + temp;
        tf_tienphat.setText(String.valueOf(tongTien));
        return tongTien;
    }
    
    private void jButtonTraSach() {
        int count =0;
        for (int row=0; row < tb_ctpm.getRowCount(); row++) {
            boolean isSelected = (boolean) tb_ctpm.getValueAt(row, 4);
            if (isSelected) {
                count++;
            }
        }
        int selected_pm = tb_pm.getSelectedRow();
        if (count == 0 || selected_pm == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần trả!!", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Tạo phiếu trả 
            numPT = numPT + 2;
            String maphieu = (String) tb_pm.getValueAt(selected_pm, 0);
            PhieuMuonDTO pm = pmBLL.getBorrowById(new PhieuMuonDTO(maphieu));
            int tongTienPhat = tinhTongTienPhat();
            String mapt = null;
            if (numPT < 10) {
                mapt = "PT000" + numPT;
            } else if (numPT > 9) {
                mapt = "PT00" + numPT;
            } else if (numPT > 99) {
                mapt = "PT0" + numPT;
            }
            System.out.println("Mã phiếu trả: " + numPT);
            PhieuTraDTO pt = new PhieuTraDTO(mapt, pm.getMaPhieu(), pm.getMaTheMuon(), pm.getNgayMuon(), tongTienPhat, 1);
            int check = ptBLL.insert(pt);
            if (check != 0) {
                System.out.println(pt.toString());

                for (int row=0; row < tb_ctpm.getRowCount(); row++) {
                    boolean isSelected = (boolean) tb_ctpm.getValueAt(row, 4);
                    if (isSelected) {
                        String maSach = (String) tb_ctpm.getValueAt(row, 0);
                        String tinhTrang = (String) tb_ctpm.getValueAt(row, 2);
                        int tienPhat = (int) tb_ctpm.getValueAt(row, 3);

                        // Cập nhật trạng thái, tình trạng sách
                        BookCopyDTO sach = sachBLL.getBookCopyById(new BookCopyDTO(maSach));
                        sach.setTinhTrang("Hiện có");
                        sach.setTrangThai(tinhTrang);
                        sachBLL.updateBook(sach);                
                        BookDTO books = booksBLL.getBookByIdMaDS(sach.getMaDS());

                        // Tạo chi tiết phiếu trả
    //                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //                    Calendar calendar = Calendar.getInstance();
    //                    String ngayTra = dateFormat.format(calendar.getTimeInMillis());
    //                    Date ngaytra = java.sql.Date.valueOf(ngayTra);
                        if (books!= null) {
                            // Tạo chi tiết phiếu trả
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar calendar = Calendar.getInstance();
                            String ngayTra = dateFormat.format(calendar.getTimeInMillis());
                            Date ngaytra = java.sql.Date.valueOf(ngayTra);

                            CT_PhieuTraDTO ctpt = new CT_PhieuTraDTO(mapt, maSach, books.getTenSach(), ngaytra, tinhTrang, tienPhat);
                            ctptBLL.insert(ctpt);

                        }
                    }
                }
                // kiểm tra phiếu mượn trả hết chưa, đã trả hết thì thay đối trạng thái phiếu mượn
                ArrayList<CT_PhieuMuonDTO> listctpm = ctpmBLL.getDetailBorrowByMaPhieu(maphieu);
                ArrayList<CT_PhieuTraDTO> listctpt = ctptBLL.getSelectedByMaPhieu(new CT_PhieuTraDTO(mapt));
                if (listctpm.size() == listctpt.size() || tb_ctpm.getRowCount()==0) {
                    pm.setTrangThai("Đã trả hết");
                    pmBLL.updateBorrow(pm);
                    System.out.println(pm.toString());
                }
                        //
                JOptionPane.showMessageDialog(null, "Trả thành công!!!", "", JOptionPane.INFORMATION_MESSAGE);
                ArrayList<PhieuMuonDTO> dspm = pmBLL.getBorrowByCondition("TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả'");
                docDuLieuPhieuMuon(dspm);
                docDuLieuCTPhieuMuon(null);
            }
        }
    }
    
    private void jButtonTraToanBo() {
        int selected_pm = tb_pm.getSelectedRow();
        if (selected_pm == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần trả!", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            numPT = numPT + 1;
            String maphieu = (String) tb_pm.getValueAt(selected_pm, 0);
            PhieuMuonDTO pm = pmBLL.getBorrowById(new PhieuMuonDTO(maphieu));
            int tongTienPhat = Integer.parseInt(tf_tienphat.getText());
            String checkNumber = "^[0-9]*$";
            Pattern pattern = Pattern.compile(checkNumber);
            Matcher matcher = pattern.matcher(tongTienPhat + "");
            if (matcher.matches() == true) {
                    //int tienPhat = Integer.parseInt(tf_tienphat.getText());
                    String mapt = null;
                    if (numPT < 10) {
                        mapt = "PT000" + numPT;
                    } else if (numPT > 9) {
                        mapt = "PT00" + numPT;
                    } else if (numPT > 99) {
                        mapt = "PT0" + numPT;
                    }
                    System.out.println("Mã phiếu trả: " + numPT);
                    
                    // Tạo phiếu trả
                    PhieuTraDTO pt = new PhieuTraDTO(mapt, pm.getMaPhieu(), pm.getMaTheMuon(), pm.getNgayMuon(), tongTienPhat, 1);
                    ptBLL.insert(pt);
                    System.out.println(pt.toString());
                    
                    // Chuyển sang ArrayList và lặp arraylist
                    
                    for (int row=0; row < tb_ctpm.getRowCount(); row++) {
                        String maSach = (String) tb_ctpm.getValueAt(row, 0);
                        String tinhTrang = (String) tb_ctpm.getValueAt(row, 2);
                        int tienPhat = (int) tb_ctpm.getValueAt(row, 3);

                        // cập nhật trạng thái, tình trạng sách
                        BookCopyDTO sach = sachBLL.getBookCopyById(new BookCopyDTO(maSach));
                        sach.setTinhTrang("Hiện có");
                        sach.setTrangThai(tinhTrang);
                        sachBLL.updateBook(sach);

                        BookDTO books = booksBLL.getBookByIdMaDS(sach.getMaDS());
                        if (books!= null) {
                            // Tạo chi tiết phiếu trả
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar calendar = Calendar.getInstance();
                            String ngayTra = dateFormat.format(calendar.getTimeInMillis());
                            Date ngaytra = java.sql.Date.valueOf(ngayTra);

                            CT_PhieuTraDTO ctpt = new CT_PhieuTraDTO(mapt, maSach, books.getTenSach(), ngaytra, tinhTrang, tienPhat);
                            ctptBLL.insert(ctpt);
                        }
                    }
                    // kiểm tra phiếu mượn trả hết chưa, đã trả hết thì thay đối trạng thái phiếu mượn
                    ArrayList<CT_PhieuMuonDTO> listctpm = ctpmBLL.getDetailBorrowByMaPhieu(maphieu);
                    ArrayList<CT_PhieuTraDTO> listctpt = ctptBLL.getSelectedByMaPhieu(new CT_PhieuTraDTO(mapt));
                    if (listctpm.size() == listctpt.size()) {
                        pm.setTrangThai("Đã trả hết");
                        pmBLL.updateBorrow(pm);
                    }
                    //
                    JOptionPane.showMessageDialog(null, "Trả thành công!", "", JOptionPane.INFORMATION_MESSAGE);
                    ArrayList<PhieuMuonDTO> dspm = pmBLL.getBorrowByCondition("TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả'");
                    docDuLieuPhieuMuon(dspm);
                    docDuLieuCTPhieuMuon(null);
            }
        }
    }
    
    private void jButtonTimKiemActionPerformed() {
        String duLieu = tf_timkiem.getText();
        String loaitk = cb_timkiem.getSelectedItem().toString();
        if (loaitk.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại tìm kiếm!", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (duLieu.equals("") ) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khoá để tìm kiếm!", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String sql = "";
                if (loaitk.equals("Mã phiếu")) {
                    sql += "(TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả') AND MaPhieu LIKE N'%" + duLieu + "%'";
                }
                if (loaitk.equals("Mã thẻ mượn")) {
                    sql += "(TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả') AND MaTheMuon LIKE N'%" + duLieu + "%'";
                }
                if (loaitk.equals("Tên độc giả")) {
                    sql += "(TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả') AND TenDG LIKE N'%" + duLieu + "%'";        
                }    
                if (loaitk.equals("Ngày mượn")) {
                    sql += "(TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả') AND NgayMuon = '%" + duLieu + "%'";        
                } 
                if (loaitk.equals("Hạn trả")) {
                    sql += "(TrangThai = N'Đã mượn' or TrangThai = N'Quá hạn trả') AND HanTra = '%" + duLieu + "%'";        
                } 
                ArrayList<PhieuMuonDTO> dspm = PhieuMuonDAO.getInstance().selectByCondition(sql);
                docDuLieuPhieuMuon(dspm);
            }
        }
    }
    public static void main(String[] args) {
       new PhieuTraGUI();
    }
}