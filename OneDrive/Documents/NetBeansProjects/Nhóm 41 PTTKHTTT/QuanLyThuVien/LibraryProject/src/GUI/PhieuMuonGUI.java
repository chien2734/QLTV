/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import DAL.NXBDAO;
import DAL.TheLoaiDAO;
import DTO.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableCellRenderer;


public class PhieuMuonGUI extends JPanel{
    private JPanel jp_ttsach, jp_ttmuon, jp_ttphieu;
    private JLabel lb_mads, lb_tensach, lb_tacgia, lb_nxb, lb_theloai;
    private JLabel lb_mathe, lb_ten, lb_email, lb_sdt;
    private JLabel lb_ngaymuon, lb_ngaytra, lb_tiencoc;
    private JTextField tf_tensach, tf_tacgia, tf_nxb, tf_theloai;
    private JTextField tf_ten, tf_email, tf_sdt;
    private JTextField tf_ngaymuon, tf_ngaytra, tf_tiencoc;
    private JComboBox cb_mads, cb_mathe;
    private JTable tb_ctpm;
    private DefaultTableModel tbModel;
    private JButton jB_themvaophieu, jb_xoa, jb_xoatatca, jb_chomuon;
    private JScrollPane scroll_pm;
    
    private PhieuMuonBLL pmBLL = new PhieuMuonBLL();
    private CT_PhieuMuonBLL ctpmBLL = new CT_PhieuMuonBLL();
    private CartUserBLL cartUserBLL = new CartUserBLL();
    private CardBUS cardBLL = new CardBUS();
    private BookCopyBLL sachBLL = new BookCopyBLL();
    private BookBLL booksBLL = new BookBLL();
    private ReaderBUS readerBLL = new ReaderBUS();
    private ArrayList<CartUserDTO> listCart = new ArrayList<>();
    
    private static int numPM ;
    
    public PhieuMuonGUI() {
        numPM = pmBLL.getBorrowAll().size();
        this.init();
        cb_mads.setBackground(Color.white);
        cb_mathe.setBackground(Color.white);
        jB_themvaophieu.setBackground(new Color(187, 222, 251));
        jb_chomuon.setBackground(new Color(187, 222, 251));
        jb_xoa.setBackground(new Color(187, 222, 251));
        jb_xoatatca.setBackground(new Color(187, 222, 251));
    }

    private void init() {
        setBackground(Color.WHITE);
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
        setLayout(null);
        //---------------Panel thông tin sách-----------
        jp_ttsach = new JPanel();
        jp_ttsach.setBackground(Color.WHITE);
        jp_ttsach.setBorder(BorderFactory.createTitledBorder(null, "Thông tin sách", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 1, 26), new Color(24, 116, 205)));
        jp_ttsach.setLayout(null);
        
        Font font = new Font("Helvetica", 0, 25);
        Font font_text = new Font("Helvetica", 0, 24);
        
        lb_mads = new JLabel("Mã đầu sách");
        lb_mads.setBounds(15, 60, 150, 35);
        lb_mads.setFont(font);
        
        lb_tensach = new JLabel("Tên sách");
        lb_tensach.setBounds(15, 120, 150, 35);
        lb_tensach.setFont(font);
        
        lb_tacgia = new JLabel("Tác giả");
        lb_tacgia.setBounds(15, 180, 150, 35);
        lb_tacgia.setFont(font);
        
        lb_nxb = new JLabel("Nhà xuất bản");
        lb_nxb.setBounds(15, 240, 160, 35);
        lb_nxb.setFont(font);
        
        lb_theloai = new JLabel("Thể loại");
        lb_theloai.setBounds(15, 300, 150, 35);
        lb_theloai.setFont(font);
        
        tf_tensach = new JTextField();
        tf_tacgia = new JTextField();
        tf_nxb = new JTextField();
        tf_theloai = new JTextField();
       
        cb_mads = new JComboBox<>(getMaDS());
        cb_mads.setBounds(220, 60, 280, 35);
        cb_mads.setFont(font_text);
        cb_mads.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jComboxSachMouseClicked();
            }
        });
        
        
        tf_tensach.setBounds(220, 120, 280, 35);
        tf_tensach.setFont(font_text);
        tf_tensach.setEditable(false);
        tf_tensach.setBackground(Color.WHITE);
        tf_tensach.setOpaque(true);
        tf_tensach.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        
        tf_tacgia.setBounds(220, 180, 280, 35);
        tf_tacgia.setFont(font_text);
        tf_tacgia.setEditable(false);
        tf_tacgia.setBackground(Color.WHITE);
        tf_tacgia.setOpaque(true);
        tf_tacgia.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        
        tf_nxb.setBounds(220, 240, 280, 35);
        tf_nxb.setFont(font_text);
        tf_nxb.setEditable(false);
        tf_nxb.setBackground(Color.WHITE);
        tf_nxb.setOpaque(true);
        tf_nxb.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        
        tf_theloai.setBounds(220, 300, 280, 35);
        tf_theloai.setFont(font_text);
        tf_theloai.setEditable(false);
        tf_theloai.setBackground(Color.WHITE);
        tf_theloai.setOpaque(true);
        tf_theloai.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        jB_themvaophieu = new JButton("Thêm vào phiếu");
        jB_themvaophieu.setFont(font);
        jB_themvaophieu.setBounds(170, 370, 240, 40);
        jB_themvaophieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    jButtonThemVaoPhieu(ae);
            }
        });
        
        jp_ttsach.add(lb_mads); jp_ttsach.add(lb_tensach);
        jp_ttsach.add(lb_tacgia); jp_ttsach.add(lb_nxb); jp_ttsach.add(lb_theloai);
        jp_ttsach.add(cb_mads); jp_ttsach.add(tf_tensach);
        jp_ttsach.add(tf_tacgia); jp_ttsach.add(tf_nxb); jp_ttsach.add(tf_theloai);
        jp_ttsach.add(jB_themvaophieu);
        this.add(jp_ttsach);
        jp_ttsach.setBounds(20, 10, 550, 420);
        
        //-----------------------Panel thông tin mượn-----------------------
        jp_ttmuon = new JPanel(new GridLayout(4, 2));
        jp_ttmuon.setBackground(Color.WHITE);
        jp_ttmuon.setBorder(BorderFactory.createTitledBorder(null, "Thông tin mượn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 1, 26), new Color(24, 116, 205)));
        jp_ttmuon.setLayout(null);
        
        lb_mathe = new JLabel("Mã thẻ mượn");
        lb_mathe.setBounds(15, 70, 180, 35);
        lb_mathe.setFont(font);
        
        lb_ten = new JLabel("Họ tên");
        lb_ten.setBounds(15, 150, 150, 35);
        lb_ten.setFont(font);
        
        lb_email = new JLabel("Email");
        lb_email.setBounds(15, 230, 150, 35);
        lb_email.setFont(font);
        
        lb_sdt = new JLabel("Số điện thoại");
        lb_sdt.setBounds(15, 310, 150, 35);
        lb_sdt.setFont(font);
        
        cb_mathe = new JComboBox<>(getMaThe());
        cb_mathe.setBounds(210, 70, 280, 35);
        cb_mathe.setFont(font_text);
        cb_mathe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jComboxTheMouseClicked();
            }
        });
        
        tf_ten = new JTextField();
        tf_ten.setBounds(210, 150, 280, 35);
        tf_ten.setFont(font_text);
        tf_ten.setEditable(false);
        tf_ten.setBackground(Color.WHITE);
        tf_ten.setOpaque(true);
        tf_ten.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        tf_email = new JTextField();
        tf_email.setBounds(210, 230, 280, 35);
        tf_email.setFont(font_text);
        tf_email.setEditable(false);
        tf_email.setBackground(Color.WHITE);
        tf_email.setOpaque(true);
        tf_email.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        tf_sdt = new JTextField();
        tf_sdt.setBounds(210, 310, 280, 35);
        tf_sdt.setFont(font_text);
        tf_sdt.setEditable(false);
        tf_sdt.setBackground(Color.WHITE);
        tf_sdt.setOpaque(true);
        tf_sdt.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        jp_ttmuon.add(lb_mathe); jp_ttmuon.add(lb_ten); jp_ttmuon.add(lb_email);
        jp_ttmuon.add(lb_sdt); jp_ttmuon.add(cb_mathe); jp_ttmuon.add(tf_ten); 
        jp_ttmuon.add(tf_email); jp_ttmuon.add(tf_sdt); 
        
        this.add(jp_ttmuon);
        jp_ttmuon.setBounds(600, 10, 550, 420);
        
        //----------------------Panel thông tin phiếu----------------
        jp_ttphieu = new JPanel();
        jp_ttphieu.setBackground(Color.WHITE);
        jp_ttphieu.setBorder(BorderFactory.createTitledBorder(null, "Thông tin phiếu", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 1, 26), new Color(24, 116, 205)));
        jp_ttphieu.setLayout(null);
            
        lb_ngaymuon = new JLabel("Ngày mượn");
        lb_ngaymuon.setBounds(15, 70, 150, 35);
        lb_ngaymuon.setFont(font);
        
        lb_ngaytra = new JLabel("Ngày trả");
        lb_ngaytra.setBounds(15, 150, 150, 35);
        lb_ngaytra.setFont(font);
        
        lb_tiencoc = new JLabel("Tiền cọc");
        lb_tiencoc.setBounds(15, 230, 150, 35);
        lb_tiencoc.setFont(font);
        
        tf_ngaymuon = new JTextField();
        tf_ngaymuon.setBounds(190, 70, 220, 35);
        tf_ngaymuon.setFont(font_text);
        tf_ngaymuon.setEditable(false);
        tf_ngaymuon.setBackground(Color.WHITE);
        tf_ngaymuon.setOpaque(true);
        tf_ngaymuon.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        tf_ngaytra = new JTextField();
        tf_ngaytra.setBounds(190, 150, 220, 35);
        tf_ngaytra.setFont(font_text);
        tf_ngaytra.setEditable(false);
        tf_ngaytra.setBackground(Color.WHITE);
        tf_ngaytra.setOpaque(true);
        tf_ngaytra.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        tf_tiencoc = new JTextField();
        tf_tiencoc.setBounds(190, 230, 220, 35);
        tf_tiencoc.setFont(font_text);
        tf_tiencoc.setEditable(false);
        tf_tiencoc.setBackground(Color.WHITE);
        tf_tiencoc.setOpaque(true);
        tf_tiencoc.setBorder(BorderFactory.createLineBorder(new Color(24, 116, 205), 1));
        
        jp_ttphieu.add(lb_ngaymuon); jp_ttphieu.add(lb_ngaytra); jp_ttphieu.add(lb_tiencoc);
        jp_ttphieu.add(tf_ngaymuon); jp_ttphieu.add(tf_ngaytra); jp_ttphieu.add(tf_tiencoc);
        
        this.add(jp_ttphieu);
        jp_ttphieu.setBounds(1180, 30, 450, 360);
        
        //-------------------Bảng chi tiết phiếu mượn----------------
        tb_ctpm = new JTable();
        scroll_pm  = new JScrollPane();
        tbModel = new DefaultTableModel();
        String[] header_ctpm = new String[]{"Mã thẻ mượn", "Mã sách", "Tên sách", "Giá mượn"};
        tbModel.setColumnIdentifiers(header_ctpm);
        tb_ctpm.setModel(tbModel);
        tb_ctpm.setFont(font);
        tb_ctpm.setRowHeight(45);
        tb_ctpm.getTableHeader().setFont(font);
        tb_ctpm.getTableHeader().setBackground(new Color(187, 222, 251));
        tb_ctpm.getColumnModel().getColumn(0).setPreferredWidth(100);
        tb_ctpm.setShowGrid(false);
        tb_ctpm.setIntercellSpacing(new Dimension(0, 0));
        tb_ctpm.setDefaultEditor(Object.class, null);
        scroll_pm.setViewportView(tb_ctpm);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tb_ctpm.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tb_ctpm.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tb_ctpm.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        for (CartUserDTO cart : listCart) {
            tbModel.addRow(new Object[]{cart.getMaTheMuon(), cart.getMaSach(), cart.getTenSach(), cart.getGiaMuon()});
        }
        
        scroll_pm.setBounds(30, 500, 1600, 320);
        this.add(scroll_pm);
        
        //------------------------------
        jb_xoa = new JButton("Xoá");
        jb_xoa.setFont(new Font("Helvetica", 1, 25));
        jb_xoa.setBounds(580, 870, 100, 40);
        jb_xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    jButtonXoa(ae);
            }
        });
        
        jb_xoatatca = new JButton("Xoá tất cả");
        jb_xoatatca.setBounds(730, 870, 200, 40);
        jb_xoatatca.setFont(new Font("Helvetica", 1, 25));
        jb_xoatatca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    jButtonXoaTatCa(ae);
            }
        });
        
        jb_chomuon = new JButton("Cho mượn");
        jb_chomuon.setBounds(980, 870, 200, 40);
        jb_chomuon.setFont(new Font("Helvetica", 1, 25));
        jb_chomuon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jButtonChoMuon(ae);
            }
        });
        
        this.add(jb_xoa); this.add(jb_xoatatca); this.add(jb_chomuon);
    }
    
    public DefaultComboBoxModel<String> getMaDS() {
        ArrayList<BookDTO> dauSach = booksBLL.getBookAll();
        ArrayList<String> listDS = new ArrayList<>();
        //listDS.add("");
        for (BookDTO sach : dauSach) {
            listDS.add(sach.getMaDS());
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listDS.toArray(new String[0]));
        return model;
        
    }
    
    public DefaultComboBoxModel<String> getMaThe() {
        ArrayList<CardDTO> Card = cardBLL.getAllActivatedCards();
        ArrayList<String> listCard = new ArrayList<>();
        for (CardDTO card : Card) {
            listCard.add(card.getMaTheMuon());
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listCard.toArray(new String[0]));
        return model; 
    }
    
    private void jComboxSachMouseClicked() {
        String mads = (String) cb_mads.getSelectedItem();
        if (mads != null) {
            BookDTO dausach = booksBLL.getBookByIdMaDS(mads);
            NXBDTO nxb = NXBDAO.getInstance().selectByIdNXB(dausach.getnxb());
            TheLoaiDTO tl = TheLoaiDAO.getInstance().selectByIdTL(dausach.getTenLoai());
            if (nxb!=null && tl!= null) {
                tf_tensach.setText(dausach.getTenSach());
                tf_tacgia.setText(dausach.getTacGia());
                tf_nxb.setText(nxb.getTenNXB());
                tf_theloai.setText(tl.getTenLoai());
            }
        }
    }       
    ////// Error/////////
    private void jComboxTheMouseClicked() {
        String mathe = (String) cb_mathe.getSelectedItem();
        if (mathe != null) {
            CardDTO themuon = cardBLL.getCardsByIdTM(mathe);
            ReaderDTO docgia = readerBLL.selectById(themuon.getTenDN());
            tf_ten.setText(docgia.getTenDG());
            tf_sdt.setText(docgia.getSDT());
            tf_email.setText(docgia.getEmail());
        }
    }
    
    public void docDuLieuBang(ArrayList<CartUserDTO> listcart) {  
        try {
            tbModel.setRowCount(0);
           for (CartUserDTO data : listcart) {
                tbModel.addRow(new Object[]{data.getMaTheMuon(), data.getMaSach(), data.getTenSach(), data.getGiaMuon()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    ////////Error///////
    public void jButtonXoa(ActionEvent ae) {
        int selecRow = tb_ctpm.getSelectedRow();
        if (selecRow != -1) {
            String maSach = (String) tb_ctpm.getValueAt(selecRow, 1);
            
            boolean stop = true;
            for (CartUserDTO c: listCart) {
                if (c.getMaSach().equalsIgnoreCase(maSach)) {
                    listCart.remove(c);
                    stop = false;
                    break;
                }
            }
            // Cập nhật trạng thái sách
            if (stop == false) {
                BookCopyDTO book = sachBLL.getBookCopyById(new BookCopyDTO(maSach));
                book.setTinhTrang("Hiện có");
                sachBLL.updateBook(book);
            }
            
            int tiencoc = 0;
            for (CartUserDTO c : listCart) {
                tiencoc += c.getGiaMuon();
            }
            tf_tiencoc.setText(String.valueOf(tiencoc));
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần xoá!!", "", JOptionPane.INFORMATION_MESSAGE);
        }
        docDuLieuBang(listCart);
        if (tb_ctpm.getRowCount()==0) {
            cb_mathe.setEnabled(true);
        }
    }
    
    
    public void jButtonXoaTatCa(ActionEvent ae) {
        int check = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hết?", "", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            String mathe = (String) cb_mathe.getSelectedItem();
            listCart.clear();
            tbModel.setRowCount(0);
            tf_tiencoc.setText("");
            
            // Cập nhật trạng thái sách
            for (CartUserDTO c : listCart) {
                BookCopyDTO book = sachBLL.getBookCopyById(new BookCopyDTO(c.getMaSach()));
                book.setTinhTrang("Hiện có");
                sachBLL.updateBook(book);
            }
            
            JOptionPane.showMessageDialog(null, "Xoá thành công!", "", JOptionPane.INFORMATION_MESSAGE);
        }
        cb_mathe.setEnabled(true);
    }
    
    private ArrayList<CartUserDTO> jButtonThemVaoPhieu(ActionEvent ae) {
        // Kiểm tra mã thẻ, nếu trong giỏ hàng có sp thì mã thẻ ko được đổi
        if (!listCart.isEmpty()) {
            cb_mathe.setEnabled(false);
        }
        int count =0;
        String mathe = (String) cb_mathe.getSelectedItem();
        String mads = (String) cb_mads.getSelectedItem();
        String tensach = (String) tf_tensach.getText();
        for (CartUserDTO cart : listCart) {
            BookCopyDTO book = sachBLL.getBookCopyById(new BookCopyDTO(cart.getMaSach()));
            String maDS = String.valueOf(book.getMaDS());
            if (maDS.equalsIgnoreCase(mads)) {
                count ++;
            }
        }
        
        BookCopyDTO sach = sachBLL.getBookCopyByCondition1(mads, (int) (count +1));
        if (sach != null) {
            //BookDTO dausach =  booksBLL.getBookById(new BookDTO(mads));
            CartUserDTO cart = new CartUserDTO(mathe, sach.getMaSach(), tensach, (int) sach.getGiaMuon());
            listCart.add(cart);
            ////
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar calendar = Calendar.getInstance();
            Date ngayMuon = new Date(calendar.getTimeInMillis());
            tf_ngaymuon.setText(dateFormat.format(ngayMuon));

            calendar.setTime(ngayMuon);
            calendar.add(Calendar.DAY_OF_MONTH, 10);        // Hạn trả là 10 ngày kể từ ngày mượn
            Date hanTra = new Date(calendar.getTimeInMillis());
            tf_ngaytra.setText(dateFormat.format(hanTra));

            int tiencoc = 0;
            for (CartUserDTO c : listCart) {
                tiencoc += c.getGiaMuon();
            }
            tf_tiencoc.setText(String.valueOf(tiencoc));
            
            // Cập nhật trạng thái sách
            for (CartUserDTO c : listCart) {
                BookCopyDTO book = sachBLL.getBookCopyById(new BookCopyDTO(c.getMaSach()));
                book.setTinhTrang("Đã mượn");
                sachBLL.updateBook(book);
            }
            
            JOptionPane.showMessageDialog(null, "Thêm sách thành công!", "", JOptionPane.INFORMATION_MESSAGE);
            cb_mathe.setEnabled(false); 
        } else {
            JOptionPane.showMessageDialog(null, "Sách hiện không phục vụ mượn! Vui lòng chọn sách khác!", "", JOptionPane.INFORMATION_MESSAGE);
        }
        
        docDuLieuBang(listCart);
        return listCart;
    }
    
    public void jButtonChoMuon(ActionEvent ae){
        String mads = (String) cb_mads.getSelectedItem();
        String mathe = (String) cb_mathe.getSelectedItem();
        String tendg = (String) tf_ten.getText();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String ngaymuon = dateFormat.format(calendar.getTimeInMillis());
        Date ngayMuon = java.sql.Date.valueOf(ngaymuon);
        
        calendar.setTime(ngayMuon);
        calendar.add(Calendar.DAY_OF_MONTH, 10);        // Hạn trả là 10 ngày kể từ ngày mượn
        String hantra = dateFormat.format(calendar.getTimeInMillis());
        Date hanTra = java.sql.Date.valueOf(hantra);
        
        int tiencoc = Integer.parseInt(tf_tiencoc.getText());
        String trangthai = "Đã mượn";
        
        // Kiểm tra số lượng sách trong giỏ
        if (tb_ctpm.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần mượn!", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (mathe.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn độc giả mượn!", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Kiểm tra số lượng sách độc giả đang mượn
                ArrayList<PhieuMuonDTO> listpm = pmBLL.getBorrowByCondition("MaTheMuon = '" + mathe + "' and (TrangThai = N'Đã mượn' or TrangThai = N'Có thể sử dụng')");
                int soSachDangMuon = 0;
                for (PhieuMuonDTO pm : listpm) {
                    ArrayList<CT_PhieuMuonDTO> listctpm = ctpmBLL.getDetailBorrowByMaPhieu(pm.getMaPhieu());
                    soSachDangMuon += listctpm.size();
                }
                if (soSachDangMuon + tb_ctpm.getRowCount() > 5 ) {
                        JOptionPane.showMessageDialog(null, "Số sách mượn đã vượt quá quy định!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String maphieu = null;
                    ++numPM;
                    
                    if (numPM < 10) {
                        maphieu = "PM000" + numPM;
                    } else if (numPM > 9) {
                        maphieu = "PM00" + numPM;
                    } else if (numPM > 99) {
                        maphieu = "PM0" + numPM;
                    }
                    System.out.println("Mã phiếu mượn: " + numPM);
                    PhieuMuonDTO pm = new PhieuMuonDTO(maphieu, mathe, tendg, ngayMuon, hanTra, tiencoc, trangthai);
                    System.out.println(pm.toString());
                    
                    int dong = pmBLL.insertBorrow1(pm);
                    int count = 0;
                    // Cập nhật trạng thái sách
                    for (CartUserDTO cart : listCart) {
                        BookCopyDTO book = sachBLL.getBookCopyById(new BookCopyDTO(cart.getMaSach()));
                        CT_PhieuMuonDTO ctpm = new CT_PhieuMuonDTO(maphieu, cart.getMaSach(), cart.getTenSach(), cart.getGiaMuon(), book.getTrangThai());
                        book.setTinhTrang("Đã mượn");
                        sachBLL.updateBook(book);
                        count += ctpmBLL.insertDetailBorrow(ctpm);
                        System.out.println(ctpm.toString());
                    }
                    if (count == listCart.size()) {
                        listCart.clear();
                        JOptionPane.showMessageDialog(null, "Mượn thành công!", "", JOptionPane.INFORMATION_MESSAGE);
                        cb_mathe.setEnabled(true);
                        tbModel.setRowCount(0);
                        tf_tiencoc.setText("0");
                    } else JOptionPane.showMessageDialog(null, "Mượn thất bại!", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
   


}