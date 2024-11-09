/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.CardDTO;
import BLL.CardBUS;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import BLL.ReaderBUS;
import DTO.ReaderDTO;

/**
 *
 * @author ADMIN
 */
public class InformationGUI extends JPanel {

    public JLabel jLabel_dkThe;
    public JLabel jLabel_dkThe2;
    public JLabel jLabel_email;
    public JLabel jLabel_email2;
    public JLabel jLabel_fullName;
    public JLabel jLabel_fullName2;
    public JLabel jLabel_maTheMuon;
    public JLabel jLabel_maTheMuon2;
    public JLabel jLabel_phone;
    public JLabel jLabel_phone2;
    public JLabel jLabel_diaChi;
    public JLabel jLabel_diaChi2;
    public JLabel jLabel_tenTK;
    public JLabel jLabel_tenTK2;
    public JLabel jLabel_tinhTrang;
    public JLabel jLabel_tinhTrang2;
    public JLabel jLabel_hanThe;
    public JLabel jLabel_hanThe2;
    public JPanel jPanel_card;
    public JPanel jPanel_info;
    public JPanel jPanel_pass;
    public JLabel jLabel_matKhau;
    public JLabel jLabel_matKhauCu;
    public JLabel jLabel_xacNhanMK;
    public JPasswordField jpassField_matKhau;
    public JPasswordField jpassField_matKhauCu;
    public JPasswordField jpassField_xanNhanMK;
    public JButton jButton_capNhatMK;
    public String maDG;

    public ReaderBUS dgBLL = new ReaderBUS();
    public CardBUS tmBLL = new CardBUS();
    
    public InformationGUI(String maDG) {
        this.maDG = maDG;
        this.init();
    }

    public void init() {
        jPanel_info = new JPanel();
        jLabel_tenTK = new JLabel();
        jLabel_fullName = new JLabel();
        jLabel_diaChi = new JLabel();
        jLabel_email = new JLabel();
        jLabel_phone = new JLabel();
        jLabel_phone2 = new JLabel();
        jLabel_tenTK2 = new JLabel();
        jLabel_fullName2 = new JLabel();
        jLabel_diaChi2 = new JLabel();
        jLabel_email2 = new JLabel();
        jPanel_card = new JPanel();
        jLabel_maTheMuon = new JLabel();
        jLabel_tinhTrang = new JLabel();
        jLabel_dkThe = new JLabel();
        jLabel_dkThe2 = new JLabel();
        jLabel_maTheMuon2 = new JLabel();
        jLabel_tinhTrang2 = new JLabel();
        jLabel_hanThe = new JLabel();
        jLabel_hanThe2 = new JLabel();
        jPanel_pass = new JPanel();
        jLabel_matKhauCu = new JLabel();
        jLabel_matKhau = new JLabel();
        jLabel_xacNhanMK = new JLabel();
        jpassField_matKhauCu = new JPasswordField();
        jpassField_matKhau = new JPasswordField();
        jpassField_xanNhanMK = new JPasswordField();
        jButton_capNhatMK = new JButton("Cập nhật mật khẩu");

        ReaderDTO dg = new ReaderDTO(maDG);
        ReaderDTO docGia = dgBLL.selectById(dg.getTenDN());

        setBackground(Color.WHITE);
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
//        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        setLayout(null);

        jPanel_info.setBackground(new Color(236, 242, 243));
        jPanel_info.setBorder(BorderFactory.createTitledBorder(null, "Thông tin tài khoản", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18)));
        jPanel_info.setLayout(null);

        jLabel_tenTK.setFont(new Font("Roboto", 0, 18));
        jLabel_tenTK.setText("Tên tài khoản");
        jPanel_info.add(jLabel_tenTK);
        jLabel_tenTK.setBounds(100, 70, 130, 40);

        jLabel_fullName.setFont(new Font("Roboto", 0, 18));
        jLabel_fullName.setText("Họ tên");
        jPanel_info.add(jLabel_fullName);
        jLabel_fullName.setBounds(130, 160, 54, 25);

        jLabel_diaChi.setFont(new Font("Roboto", 0, 18));
        jLabel_diaChi.setText("Địa chỉ");
        jPanel_info.add(jLabel_diaChi);
        jLabel_diaChi.setBounds(130, 250, 66, 25);

        jLabel_email.setFont(new Font("Roboto", 0, 18));
        jLabel_email.setText("Email");
        jPanel_info.add(jLabel_email);
        jLabel_email.setBounds(130, 330, 80, 25);

        jLabel_phone.setFont(new Font("Roboto", 0, 18));
        jLabel_phone.setText("Phone");
        jPanel_info.add(jLabel_phone);
        jLabel_phone.setBounds(130, 430, 80, 25);

        jLabel_phone2.setBackground(new Color(255, 255, 255));
        jLabel_phone2.setFont(new Font("Roboto", 0, 18));
        jLabel_phone2.setOpaque(true);
        jPanel_info.add(jLabel_phone2);
        jLabel_phone2.setText(docGia.getSDT());
        jLabel_phone2.setBounds(330, 420, 320, 40);

        jLabel_tenTK2.setBackground(new Color(255, 255, 255));
        jLabel_tenTK2.setFont(new Font("Roboto", 0, 18));
        jLabel_tenTK2.setOpaque(true);
        jPanel_info.add(jLabel_tenTK2);
        jLabel_tenTK2.setText(docGia.getTenDN());
        jLabel_tenTK2.setBounds(330, 70, 320, 40);

        jLabel_fullName2.setBackground(new Color(255, 255, 255));
        jLabel_fullName2.setFont(new Font("Roboto", 0, 18));
        jLabel_fullName2.setOpaque(true);
        jLabel_fullName2.setText(docGia.getTenDG());
        jPanel_info.add(jLabel_fullName2);
        jLabel_fullName2.setBounds(330, 150, 320, 40);

        jLabel_diaChi2.setBackground(new Color(255, 255, 255));
        jLabel_diaChi2.setFont(new Font("Roboto", 0, 18));
        jLabel_diaChi2.setOpaque(true);
        jPanel_info.add(jLabel_diaChi2);
        jLabel_diaChi2.setText(docGia.getDiaChi());
        jLabel_diaChi2.setBounds(330, 240, 320, 40);

        jLabel_email2.setBackground(new Color(255, 255, 255));
        jLabel_email2.setFont(new Font("Roboto", 0, 18));
        jLabel_email2.setOpaque(true);
        jLabel_email2.setText(docGia.getEmail());
        jPanel_info.add(jLabel_email2);
        jLabel_email2.setBounds(330, 320, 320, 40);

        jPanel_info.setBounds(50, 150, 900, 550);

        jPanel_card.setBorder(BorderFactory.createTitledBorder(null, "Thông tin thẻ ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18)));
        jPanel_card.setLayout(null);

        CardDTO tm = tmBLL.getCardsByIdMaDG(new CardDTO(maDG));

        jLabel_maTheMuon.setFont(new Font("Roboto", 0, 18));
        jLabel_maTheMuon.setText("Mã thẻ mượn");
        jPanel_card.add(jLabel_maTheMuon);
        jLabel_maTheMuon.setBounds(70, 100, 130, 25);

        jLabel_tinhTrang.setFont(new Font("Roboto", 0, 18));
        jLabel_tinhTrang.setText("Tình trạng");
        jPanel_card.add(jLabel_tinhTrang);
        jLabel_tinhTrang.setBounds(70, 200, 100, 25);

        jLabel_dkThe.setFont(new Font("Roboto", 0, 18));
        jLabel_dkThe.setText("Ngày đk thẻ");
        jPanel_card.add(jLabel_dkThe);
        jLabel_dkThe.setBounds(70, 300, 97, 25);

        jLabel_dkThe2.setBackground(new Color(255, 255, 255));
        jLabel_dkThe2.setFont(new Font("Roboto", 0, 18));
        jLabel_dkThe2.setOpaque(true);
        if(docGia.getTrangThai() == 1) {
            jLabel_dkThe2.setText(String.valueOf(tm.getNgayDK()));
        } else {
            jLabel_dkThe2.setText(String.valueOf(tm.getNgayDK()));
        }
        
        jPanel_card.add(jLabel_dkThe2);
        jLabel_dkThe2.setBounds(250, 300, 220, 40);

        jLabel_maTheMuon2.setBackground(new Color(255, 255, 255));
        jLabel_maTheMuon2.setFont(new Font("Roboto", 0, 18));
        jLabel_maTheMuon2.setOpaque(true);
        jPanel_card.add(jLabel_maTheMuon2);
        if (docGia.getTrangThai() == 1) {
            jLabel_maTheMuon2.setText(tm.getMaTheMuon());
        } else {
            jLabel_maTheMuon2.setText("Chưa có");
        }

        jLabel_maTheMuon2.setBounds(250, 100, 220, 40);

        jLabel_tinhTrang2.setBackground(new Color(255, 255, 255));
        jLabel_tinhTrang2.setFont(new Font("Roboto", 0, 18));
        jLabel_tinhTrang2.setOpaque(true);
        jPanel_card.add(jLabel_tinhTrang2);
        jLabel_tinhTrang2.setBounds(250, 200, 220, 40);
        if (docGia.getTrangThai() == 1) {
            jLabel_tinhTrang2.setText("Đã kích hoạt");
        } else {
            jLabel_tinhTrang2.setText("Chưa kích hoạt");
        }

        jPanel_card.setBounds(1000, 60, 600, 400);

        jPanel_pass.setBorder(BorderFactory.createTitledBorder(null, "Thông tin mật khẩu", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18)));
        jPanel_pass.setLayout(null);

        jLabel_matKhauCu.setFont(new Font("Roboto", 0, 18));
        jLabel_matKhauCu.setText("Mật khẩu cũ");
        jPanel_pass.add(jLabel_matKhauCu);
        jLabel_matKhauCu.setBounds(50, 50, 130, 25);

        jpassField_matKhauCu.setFont(new Font("Roboto", 0, 18));
        jPanel_pass.add(jpassField_matKhauCu);
        jpassField_matKhauCu.setBounds(250, 50, 300, 40);

        jLabel_matKhau.setFont(new Font("Roboto", 0, 18));
        jLabel_matKhau.setText("Mật khẩu mới");
        jPanel_pass.add(jLabel_matKhau);
        jLabel_matKhau.setBounds(50, 120, 130, 25);

        jLabel_xacNhanMK.setFont(new Font("Roboto", 0, 18));
        jLabel_xacNhanMK.setText("Xác nhận mật khẩu");
        jPanel_pass.add(jLabel_xacNhanMK);
        jLabel_xacNhanMK.setBounds(50, 190, 170, 25);

        jpassField_matKhau.setFont(new Font("Roboto", 0, 18));
        jPanel_pass.add(jpassField_matKhau);
        jpassField_matKhau.setBounds(250, 120, 300, 40);

        jpassField_xanNhanMK.setBackground(new Color(255, 255, 255));
        jpassField_xanNhanMK.setFont(new Font("Roboto", 0, 18));
        jpassField_xanNhanMK.setOpaque(true);
        jPanel_pass.add(jpassField_xanNhanMK);
        jpassField_xanNhanMK.setBounds(250, 190, 300, 40);

        jButton_capNhatMK.setFont(new Font("Roboto", 1, 20));
        jButton_capNhatMK.setBackground(new Color(187, 222, 251));
        jPanel_pass.add(jButton_capNhatMK);
        jButton_capNhatMK.setBounds(190, 270, 220, 40);
        jButton_capNhatMK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonDoiMKActionPerformed(evt);
            }
        });

        jPanel_pass.setBounds(1000, 570, 600, 350);

        this.add(jPanel_info);
        this.add(jPanel_card);
        this.add(jPanel_pass);

        this.setVisible(true);
    }

    public void resetFrm() {
        jpassField_matKhau.setText("");
        jpassField_xanNhanMK.setText("");
        jpassField_matKhauCu.setText("");
    }

    public void jButtonDoiMKActionPerformed(ActionEvent evt) {
        String checkPass = "^(.{8,16})$";
        Pattern patternPass = Pattern.compile(checkPass);
        Matcher matcherPass = patternPass.matcher(String.valueOf(jpassField_matKhau.getPassword()));
        boolean check = true;
        if (!matcherPass.matches()) {
            JOptionPane.showMessageDialog(null, "Yêu cầu nhập mật khẩu từ 8 - 16 kí tự", "error", JOptionPane.INFORMATION_MESSAGE);
            resetFrm();
            check = false;
        } else {
            ReaderDTO dg = dgBLL.selectById(maDG);
            if (!String.valueOf(jpassField_matKhauCu.getPassword()).equals(dg.getMatKhau())) {
                JOptionPane.showMessageDialog(null, "Mật khẩu không đúng.", "error", JOptionPane.INFORMATION_MESSAGE);
                check = false;
            }
            if (String.valueOf(jpassField_matKhauCu.getPassword()).equals(String.valueOf(jpassField_matKhau.getPassword()))) {
                JOptionPane.showMessageDialog(null, "2 mật khẩu này giống nhau. Vui lòng nhập mật khẩu khác", "error", JOptionPane.INFORMATION_MESSAGE);
                check = false;
            }
            if (!String.valueOf(jpassField_matKhau.getPassword()).equals(String.valueOf(jpassField_xanNhanMK.getPassword()))) {
                JOptionPane.showMessageDialog(null, "Mật khẩu không trùng khớp.", "error", JOptionPane.INFORMATION_MESSAGE);
                check = false;
                resetFrm();
            }
            if (check) {
                dg.setMatKhau(String.valueOf(jpassField_matKhau.getPassword()));
                String dong = dgBLL.changePassword(dg);             
                    JOptionPane.showMessageDialog(null, dong, "error", JOptionPane.INFORMATION_MESSAGE);
                resetFrm();
            }
        }
    }
}
