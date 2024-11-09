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
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

/**
 *
 * @author PC
 */
public class PhieuDKMuonGUI extends JPanel {

    private JLabel lb_timkiem;
    private JComboBox cb_timkiem;
    private JTextField tf_timkiem;
    private JPanel jp_pm, jp_ctpm, jp_timkiem;
    private JButton jb_xacnhan, jb_timkiem, jb_xemtatca;
    private DefaultTableModel tblModel_pm, tblModel_ctpm;
    private JTable tb_pm, tb_ctpm;
    private JScrollPane scroll_pm, scroll_ctpm;
    private JPopupMenu jpm_pm;
    private JMenuItem huypm;
    //private String tenDN;
    //private ActionListener ac;

    private PhieuMuonBLL pmBLL = new PhieuMuonBLL();
    private CT_PhieuMuonBLL ctpmBLL = new CT_PhieuMuonBLL();
    private CartUserBLL cartUserBLL = new CartUserBLL();
    private CardBUS cardBLL = new CardBUS();
    private BookCopyBLL sachBLL = new BookCopyBLL();
    private BookBLL booksBLL = new BookBLL();
    private ReaderBUS readerBLL = new ReaderBUS();

    public PhieuDKMuonGUI() {
        this.init();
        jb_xacnhan.setBackground(new Color(187, 222, 251));
        jb_timkiem.setBackground(new Color(187, 222, 251));
        jb_xemtatca.setBackground(new Color(187, 222, 251));
        cb_timkiem.setBackground(Color.white);
    }

    private void init() {
        setBackground(Color.WHITE);
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
        setLayout(null);

        //------------Panel tìm kiếm thông tin phiếu------------------
        Font font = new Font("Helvetica", 0, 30);
        jp_timkiem = new JPanel();
        jp_timkiem.setBackground(Color.WHITE);

        lb_timkiem = new JLabel("Tìm kiếm theo: ");
        lb_timkiem.setForeground(Color.red);
        lb_timkiem.setFont(font);
        lb_timkiem.setBounds(120, 20, 250, 35);

        String[] cb = {"Mã phiếu", "Mã thẻ mượn", "Tên độc giả"};
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

        //----------------- Panel Phiếu đk mượn------------------
        jp_pm = new JPanel();
        jp_pm.setBackground(Color.WHITE);
        tb_pm = new JTable();
        scroll_pm = new JScrollPane();
        Font font_table = new Font("Helvetica", 0, 22);

        jp_pm.setBorder(BorderFactory.createTitledBorder(null, "Phiếu mượn đã đăng ký", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 1, 26), new Color(24, 116, 205)));
        jp_pm.setLayout(null);

        tblModel_pm = new DefaultTableModel();
        String[] header_pm = new String[]{"Mã phiếu", "Mã thẻ mượn", "Tên độc giả", "Ngày đăng ký", "Tiền cọc", "Trạng thái"};
        tblModel_pm.setColumnIdentifiers(header_pm);
        tb_pm.setModel(tblModel_pm);
        tb_pm.getTableHeader().setFont(new Font("Helvetica", 0, 24));
        tb_pm.getTableHeader().setBackground(new Color(187, 222, 251));
        tb_pm.setDefaultEditor(Object.class, null);
        scroll_pm.setViewportView(tb_pm);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tb_pm.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tb_pm.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        ArrayList<PhieuMuonDTO> dspm = pmBLL.getBorrowByCondition("TrangThai = N'Quá hạn mượn' or TrangThai = N'Có thể sử dụng'");
        for (PhieuMuonDTO pm : dspm) {
            tblModel_pm.addRow(new Object[]{pm.getMaPhieu(), pm.getMaTheMuon(), pm.getTenDG(), pm.getNgayDK(), pm.getTienCoc(), pm.getTrangThai()});
        }

        tb_pm.setFont(font_table);
        tb_pm.setRowHeight(40);
        // 
        tb_pm.setShowGrid(false);
        tb_pm.setIntercellSpacing(new Dimension(0, 0));
        scroll_pm.setBounds(30, 50, 1520, 290);
        jp_pm.add(scroll_pm);

        jpm_pm = new JPopupMenu();
        huypm = new JMenuItem("Huỷ phiếu đăng ký");
        huypm.setBackground(new Color(235, 235, 235));
        huypm.setFont(new Font("Helvetica", 0, 22));
        jpm_pm.add(huypm);
        huypm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                huyPhieuDK();
            }
        });

        tb_pm.setComponentPopupMenu(jpm_pm);
        //tb_pm.setBackground(Color.yellow);
        //xoaPM.addActionListener(ac);
        tb_pm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jTablePMMouseClicked(e);
            }
        });

        this.add(jp_pm);
        //jpm_pm..setBounds(0, 700, this.getMaximumSize().width, this.getMaximumSize().height);
        jp_pm.setBounds(30, 100, 1600, 360);

        //----------------- Panel Chi tiết phiếu đk------------------
        jp_ctpm = new JPanel();
        jp_ctpm.setBackground(Color.WHITE);
        tb_ctpm = new JTable();
        scroll_ctpm = new JScrollPane();

        jp_ctpm.setBorder(BorderFactory.createTitledBorder(null, "Chi tiết phiếu đăng ký", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 1, 26), new Color(24, 116, 205)));
        jp_ctpm.setLayout(null);
        this.add(jp_ctpm);
        jp_ctpm.setBounds(30, 520, 1600, 330);

        tblModel_ctpm = new DefaultTableModel();
        String[] header_ctpm = new String[]{"Mã phiếu", "Mã sách", "Tên sách", "Giá mượn", "Tình trạng giao"};
        tblModel_ctpm.setColumnIdentifiers(header_ctpm);
        tb_ctpm.setModel(tblModel_ctpm);
        tb_ctpm.getTableHeader().setFont(new Font("Helvetica", 0, 24));
        tb_ctpm.getTableHeader().setBackground(new Color(187, 222, 251));
        tb_ctpm.setDefaultEditor(Object.class, null);
        scroll_ctpm.setViewportView(tb_ctpm);

        tb_ctpm.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tb_ctpm.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tb_ctpm.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tb_ctpm.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        // Xoá đường biên
        tb_pm.setShowGrid(false);
        tb_pm.setIntercellSpacing(new Dimension(0, 0));
        tb_ctpm.setFont(font_table);
        tb_ctpm.setRowHeight(40);
        scroll_ctpm.setBounds(30, 50, 1520, 250);

        jp_ctpm.add(scroll_ctpm);

        //--------------------------------------
        jb_xacnhan = new JButton("Xác nhận mượn");
        jb_xacnhan.setBounds(650, 880, 250, 40);
        jb_xacnhan.setFont(font);
        this.add(jb_xacnhan);
        jb_xacnhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    jButtonXacNhanActionPerformed(ae);
                } catch (ParseException ex) {
                    Logger.getLogger(PhieuDKMuonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    public void docDuLieuPhieuMuon(ArrayList<PhieuMuonDTO> list) {
        try {
            tblModel_pm.setRowCount(0);
            for (PhieuMuonDTO pm : list) {
                tblModel_pm.addRow(new Object[]{pm.getMaPhieu(), pm.getMaTheMuon(), pm.getTenDG(), pm.getNgayDK(), pm.getTienCoc(), pm.getTrangThai()});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void docDuLieuCTPhieuMuon(String maPhieu) {
        try {
            ArrayList<CT_PhieuMuonDTO> list = ctpmBLL.getDetailBorrowByMaPhieu(maPhieu);
            tblModel_ctpm.setRowCount(0);
            for (CT_PhieuMuonDTO ctpm : list) {
                tblModel_ctpm.addRow(new Object[]{ctpm.getMaPhieu(), ctpm.getMaSach(), ctpm.getTenSach(), ctpm.getGiaMuon(), ctpm.getTrangThai()});
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
        }
    }

    private void jButtonXemTatCaActionPerformed() {
        ArrayList<PhieuMuonDTO> dspm = pmBLL.getBorrowByCondition("TrangThai = N'Quá hạn mượn' or TrangThai = N'Có thể sử dụng'");
        docDuLieuPhieuMuon(dspm);
        docDuLieuCTPhieuMuon(null);
    }

    private void jButtonTimKiemActionPerformed() {
        String duLieu = tf_timkiem.getText();
        String loaitk = cb_timkiem.getSelectedItem().toString();
        if (loaitk.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại tìm kiếm!", "info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (duLieu.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khoá để tìm kiếm!", "info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String sql = "";
                if (loaitk.equals("Mã phiếu")) {
                    sql += "(TrangThai = N'Quá hạn mượn' or TrangThai = N'Có thể sử dụng') AND MaPhieu LIKE N'%" + duLieu + "%'";
                }
                if (loaitk.equals("Mã thẻ mượn")) {
                    sql += "(TrangThai = N'Quá hạn mượn' or TrangThai = N'Có thể sử dụng') AND MaTheMuon LIKE N'%" + duLieu + "%'";
                }
                if (loaitk.equals("Tên độc giả")) {
                    sql += "(TrangThai = N'Quá hạn mượn' or TrangThai = N'Có thể sử dụng') AND TenDG LIKE N'%" + duLieu + "%'";
                }
                ArrayList<PhieuMuonDTO> dspm = PhieuMuonDAO.getInstance().selectByCondition(sql);
                docDuLieuPhieuMuon(dspm);
            }
        }
    }

    /////////Error/////////
    public void huyPhieuDK() {
        int selected = tb_pm.getSelectedRow();
        if (selected < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn cần xoá!!", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //int countRow = tb_ctpm.getRowCount();
            int checkCount = 0;
            String maphieu = (String) tb_pm.getValueAt(selected, 0);
            ArrayList<CT_PhieuMuonDTO> listctpm = ctpmBLL.getDetailBorrowByMaPhieu(maphieu);
            for (CT_PhieuMuonDTO ct : listctpm) {
                BookCopyDTO sach = sachBLL.getBookCopyById(new BookCopyDTO(ct.getMaSach()));
                sach.setTinhTrang("Hiện có");
                sachBLL.updateBook(sach);           // Cập nhật lại tình trạng sách
                //ctpmBLL.deleteDetailBorrow(ct);     // Xoá chi tiết phiếu mượn
                checkCount++;
            }
            System.out.println("Mã phiếu huỷ: " + maphieu);
            int count = pmBLL.deleteBorrow(new PhieuMuonDTO(maphieu));
            if (count != 0) {
                JOptionPane.showMessageDialog(null, "Xoá phiếu thành công!!", "", JOptionPane.INFORMATION_MESSAGE);
                jButtonXemTatCaActionPerformed();      // Cập nhật lại bảng pm
                docDuLieuCTPhieuMuon(null);                // Cập nhật lại bảng ctpm
            } else {
                JOptionPane.showMessageDialog(null, "Xoá phiếu thât bại!!", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void jButtonXacNhanActionPerformed(ActionEvent ae) throws ParseException {
        int selected = tb_pm.getSelectedRow();
        if (selected == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu cần xác nhận!!", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (tb_pm.getValueAt(selected, 5).equals("Quá hạn mượn")) {
                JOptionPane.showMessageDialog(null, "Phiếu đã quá hạn mượn! Vui lòng chọn lại!", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String maphieu = (String) tb_pm.getValueAt(selected, 0);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                String ngaymuon = dateFormat.format(calendar.getTimeInMillis());
                Date ngayMuon = java.sql.Date.valueOf(ngaymuon);

                calendar.setTime(ngayMuon);
                calendar.add(Calendar.DAY_OF_MONTH, 10);        // Hạn trả là 10 ngày kể từ ngày mượn
                String hantra = dateFormat.format(calendar.getTimeInMillis());
                Date hanTra = java.sql.Date.valueOf(hantra);

                // Cập nhật trạng thái, ngày mượn,hạn trả của phiếu mượn
                PhieuMuonDTO pm = pmBLL.getBorrowById(new PhieuMuonDTO(maphieu));
                pm.setTrangThai("Đã mượn");
                pm.setNgayMuon((java.sql.Date) ngayMuon);
                pm.setHanTra((java.sql.Date) hanTra);

//                ArrayList<CT_PhieuMuonDTO> listctpm = ctpmBLL.getDetailBorrowByMaPhieu(maphieu);
//                for (CT_PhieuMuonDTO ct : listctpm) {
//                    BookCopyDTO sach = sachBLL.getBookCopyById(new BookCopyDTO(ct.getMaSach()));
//                    sach.setTrangThai("Đã mượn");
//                    sachBLL.updateBook(sach);           // Cập nhật trạng thái sách
//                }
                // Cập nhật trạng thái phiếu mượn
                int dong = pmBLL.updateBorrow(pm);
                if (dong != 0) {
                    //JOptionPane.showMessageDialog(null, "Mượn sách thành công!", "", JOptionPane.INFORMATION_MESSAGE);
                    docDuLieuPhieuMuon(pmBLL.getBorrowByCondition("TrangThai = N'Quá hạn mượn' or TrangThai = N'Có thể sử dụng'"));
                    tblModel_ctpm.setRowCount(0);
                    new XuatPhieuMuonGUI(maphieu);
                }

            }
        }

    }

}
