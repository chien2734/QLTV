/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.BookCopyBLL;
import DTO.BookCopyDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class UpdateBookCopy extends JFrame {

    private javax.swing.JButton jButton_capNhat;
    private javax.swing.JComboBox<String> jComboBox_loai;
    private javax.swing.JLabel jLabel_fieldSach;
    private javax.swing.JLabel jLabel_gia;
    private javax.swing.JLabel jLabel_loai;
    private javax.swing.JLabel jLabel_maSach;
    private javax.swing.JLabel jLabel_tinhTrang;
    private JLabel jLabel_trangThai;
    private JComboBox<String> jComboBox_TrangThai;
    private javax.swing.JSpinner jSpinner_gia;
    private JComboBox<String> jComboBox_tinhTrang;
    public BookCopyDTO book;
    public String maDS;
    public BookCopyBLL bcBLL = new BookCopyBLL();

    public UpdateBookCopy(BookCopyDTO book, String maDS) {
        this.book = book;
        this.maDS = maDS;
        this.init();
    }

    public void init() {

        jLabel_maSach = new javax.swing.JLabel();
        jLabel_fieldSach = new javax.swing.JLabel();
        jLabel_tinhTrang = new javax.swing.JLabel();
        jLabel_loai = new javax.swing.JLabel();
        jLabel_gia = new javax.swing.JLabel();
        jComboBox_loai = new javax.swing.JComboBox<>();
        jSpinner_gia = new javax.swing.JSpinner();
        jButton_capNhat = new javax.swing.JButton();
        jLabel_trangThai = new JLabel();
        jComboBox_TrangThai = new JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setTitle("Thông tin chi tiết bản sách");
        getContentPane().setLayout(null);
        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(213, 226, 234));

        jLabel_maSach.setFont(new java.awt.Font("Roboto", 0, 18));
        jLabel_maSach.setText("Mã sách");
        getContentPane().add(jLabel_maSach);
        jLabel_maSach.setFont(new Font("Roboto", 0, 18));
        jLabel_maSach.setBounds(30, 50, 170, 25);

        jLabel_fieldSach.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_fieldSach.setOpaque(true);
        jLabel_fieldSach.setBackground(Color.white);
        jLabel_fieldSach.setText(this.book.getMaSach());
        jLabel_fieldSach.setFont(new Font("Roboto", 0, 18));
        getContentPane().add(jLabel_fieldSach);
        jLabel_fieldSach.setBounds(200, 40, 250, 35);

        jLabel_tinhTrang.setFont(new java.awt.Font("Roboto", 0, 18));
        jLabel_tinhTrang.setText("Tình trạng");
        getContentPane().add(jLabel_tinhTrang);
        jLabel_tinhTrang.setFont(new Font("Roboto", 0, 18));
        jLabel_tinhTrang.setBounds(30, 110, 170, 25);

        String[] listTinhTrang = {"Hiện có", "Đã mượn", "Không phục vụ"};
        jComboBox_tinhTrang = new JComboBox<>(listTinhTrang);
        jComboBox_tinhTrang.setFont(new java.awt.Font("Roboto", 0, 18));
        getContentPane().add(jComboBox_tinhTrang);
        jComboBox_tinhTrang.setBackground(Color.white);
        jComboBox_tinhTrang.setSelectedItem(this.book.getTinhTrang());
        jComboBox_tinhTrang.setFont(new Font("Roboto", 0, 18));
        jComboBox_tinhTrang.setBounds(200, 110, 250, 35);

        jLabel_loai.setFont(new java.awt.Font("Roboto", 0, 18));
        jLabel_loai.setText("Hình thức");
        getContentPane().add(jLabel_loai);
        jLabel_loai.setBounds(40, 170, 170, 25);
        jLabel_loai.setFont(new Font("Roboto", 0, 18));

        jLabel_gia.setFont(new java.awt.Font("Roboto", 0, 18));
        jLabel_gia.setText("Giá");
        getContentPane().add(jLabel_gia);
        jLabel_gia.setFont(new Font("Roboto", 0, 18));
        jLabel_gia.setBounds(40, 230, 170, 25);

        jComboBox_loai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Có thể mượn", "Đọc tại chỗ"}));
        getContentPane().add(jComboBox_loai);
        jComboBox_loai.setBounds(200, 170, 250, 35);
        jComboBox_loai.setBackground(Color.white);
        jComboBox_loai.setFont(new Font("Roboto", 0, 18));
        jComboBox_loai.setSelectedItem(this.book.getLoaiSach());

        getContentPane().add(jSpinner_gia);
        jSpinner_gia.setBounds(200, 230, 250, 35);
        jSpinner_gia.setBackground(Color.white);
        jSpinner_gia.setValue(this.book.getGia());
        jSpinner_gia.setFont(new Font("Roboto", 0, 18));

        jLabel_trangThai.setFont(new java.awt.Font("Roboto", 0, 18));
        jLabel_trangThai.setText("Trạng thái");
        getContentPane().add(jLabel_trangThai);
        jLabel_trangThai.setFont(new Font("Roboto", 0, 18));
        jLabel_trangThai.setBounds(40, 300, 170, 25);

        jComboBox_TrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất"}));
        getContentPane().add(jComboBox_TrangThai);
        jComboBox_TrangThai.setBounds(200, 300, 250, 35);
        jComboBox_TrangThai.setBackground(Color.white);
        jComboBox_TrangThai.setFont(new Font("Roboto", 0, 18));
        jComboBox_TrangThai.setSelectedItem(book.getTrangThai());

        jButton_capNhat.setText("Cập nhật");
        jButton_capNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_capNhatActionPerformed(evt);
            }
        });
        jButton_capNhat.setFont(new Font("Roboto", 0, 24));
        getContentPane().add(jButton_capNhat);
        jButton_capNhat.setBounds(170, 380, 150, 40);
        jButton_capNhat.setBackground(new Color(187, 222, 251));

        this.setVisible(true);
    }

    private void jButton_capNhatActionPerformed(java.awt.event.ActionEvent evt) {
        String maSach = jLabel_fieldSach.getText();
        String tinhTrang = (jComboBox_tinhTrang.getSelectedItem().toString());
        String loai = jComboBox_loai.getSelectedItem().toString();
        String trangThai = jComboBox_TrangThai.getSelectedItem().toString();
        int gia = (int) jSpinner_gia.getValue();

        boolean check = true;
        if (gia < 0) {
            JOptionPane.showMessageDialog(null, "Yêu cầu nhập lại giá", "error", JOptionPane.INFORMATION_MESSAGE);
            check = false;
        }
        if (tinhTrang.equals("Không phục vụ") && (trangThai.equals("Nguyên vẹn") || trangThai.equals("Hư hỏng nhẹ"))) {
            JOptionPane.showMessageDialog(null, "Đối với tình trạng không phục vụ phải thay đổi trạng thái khác!", "error", JOptionPane.INFORMATION_MESSAGE);
            check = false;
        } else if (tinhTrang.equals("Không phục vụ") && (trangThai.equals("Hư hỏng nặng") || trangThai.equals("Mất"))) {
            jComboBox_loai.setSelectedItem("Đọc tại chỗ");
            loai = jComboBox_loai.getSelectedItem().toString();
        } else if(!tinhTrang.equals("Không phục vụ") && (trangThai.equals("Hư hỏng nặng") || trangThai.equals("Mất"))) {
           JOptionPane.showMessageDialog(null, "Đối với trạng thái này phải để không phục vụ!", "error", JOptionPane.INFORMATION_MESSAGE);
            check = false; 
        }
        if (check) {
            BookCopyDTO bookCopyNew = new BookCopyDTO(maSach, maDS, loai, gia, trangThai, tinhTrang);
            System.out.println("gtri sap update: " + bookCopyNew.toString());
            int dong = bcBLL.updateBook(bookCopyNew);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công", "update", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new DetailBookCopy(maDS);
        }
    }
}
