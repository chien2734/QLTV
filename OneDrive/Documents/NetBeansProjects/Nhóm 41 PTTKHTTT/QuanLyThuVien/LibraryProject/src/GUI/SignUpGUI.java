/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.CardBUS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.MainLib;
import BLL.ReaderBUS;
import DTO.CardDTO;
import DTO.ReaderDTO;

/**
 *
 * @author ADMIN
 */
public class SignUpGUI extends JFrame {

    public ButtonGroup buttonGroup_sex;
    public JButton jButton_signup;
    public JCheckBox jCheckBox_agree;
    public JLabel jLabel_email;
    public JLabel jLabel_fullName;
    public JLabel jLabel_passwd;
    public JLabel jLabel_phone;
    public JLabel jLabel_sex;
    public JLabel jLabel_title;
    public JLabel jLabel_userName;
    public JLabel jLabel_address;
    public JTextField jTextField_email;
    public JTextField jTextField_fullName;
    public JPasswordField jPassField;
    public JTextField jTextField_phone;
    public JTextField jTextField_userName;
    public JComboBox<String> jComboBox_address;

    public JLabel errorUserName;
    public JLabel errorpass;
    public JLabel errorfulName;
    public JLabel errorphone;
    public JLabel erroremail;

    public ReaderBUS dgBLL = new ReaderBUS();
    public CardBUS cBLL = new CardBUS();

    public static int soLuongDocGia;

    public SignUpGUI() {
        soLuongDocGia = dgBLL.getAllReaders().size();
        this.init();
    }

    public void init() {
        buttonGroup_sex = new ButtonGroup();
        jLabel_title = new JLabel();
        jLabel_userName = new JLabel();
        jLabel_passwd = new JLabel();
        jLabel_fullName = new JLabel();
        jLabel_phone = new JLabel();
        jLabel_email = new JLabel();
        jLabel_address = new JLabel();
        jLabel_sex = new JLabel();
        jTextField_userName = new JTextField();
        jPassField = new JPasswordField();
        jTextField_fullName = new JTextField();
        jTextField_phone = new JTextField();
        jTextField_email = new JTextField();
        jCheckBox_agree = new JCheckBox();
        jButton_signup = new JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign_up");
        getContentPane().setBackground(new Color(236, 242, 243));
        setBounds(0, 0, 440, 825);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel_title.setFont(new Font("Roboto", 1, 22));
        jLabel_title.setForeground(new Color(102, 153, 255));
        jLabel_title.setText("ĐĂNG KÝ TÀI KHOẢN");
        getContentPane().add(jLabel_title);
        jLabel_title.setBounds(100, 0, 250, 50);

        jLabel_userName.setFont(new Font("Roboto", 0, 18));
        jLabel_userName.setText("Tên đăng nhập");
        getContentPane().add(jLabel_userName);
        jLabel_userName.setBounds(50, 50, 170, 40);

        jLabel_passwd.setFont(new Font("Roboto", 0, 18));
        jLabel_passwd.setText("Mật khẩu");
        getContentPane().add(jLabel_passwd);
        jLabel_passwd.setBounds(50, 150, 76, 25);

        jLabel_fullName.setFont(new Font("Roboto", 0, 18));
        jLabel_fullName.setText("Họ và tên");
        getContentPane().add(jLabel_fullName);
        jLabel_fullName.setBounds(50, 240, 120, 25);

        jLabel_phone.setFont(new Font("Roboto", 0, 18));
        jLabel_phone.setText("Số điện thoại");
        getContentPane().add(jLabel_phone);
        jLabel_phone.setBounds(50, 330, 120, 25);

        jLabel_email.setFont(new Font("Roboto", 0, 18));
        jLabel_email.setText("Email");
        getContentPane().add(jLabel_email);
        jLabel_email.setBounds(50, 420, 60, 25);

        jLabel_address.setText("Địa chỉ");
        getContentPane().add(jLabel_address);
        jLabel_address.setBounds(50, 512, 100, 25);
        jLabel_address.setFont(new Font("Roboto", 0, 18));

        jLabel_sex.setFont(new Font("Roboto", 0, 18));
        jLabel_sex.setText("Sex");
//        getContentPane().add(jLabel_sex);
        jLabel_sex.setBounds(50, 608, 40, 30);
        getContentPane().add(jTextField_userName);

        jTextField_userName.setBounds(50, 90, 330, 40);
        jTextField_userName.setFont(new Font("Roboto", 0, 17));

        errorUserName = new JLabel("Tên đăng nhập không được quá 7 kí tự");
        errorUserName.setForeground(Color.red);
        errorUserName.setBounds(100, 120, 330, 40);
        getContentPane().add(errorUserName);

        jTextField_fullName.setBounds(50, 270, 330, 40);
        jTextField_fullName.setFont(new Font("Roboto", 0, 17));
        getContentPane().add(jTextField_fullName);

        errorfulName = new JLabel("Ví dụ: Nguyễn Văn A");
        errorfulName.setForeground(Color.red);
        errorfulName.setBounds(140, 300, 330, 40);
        getContentPane().add(errorfulName);

        jPassField.setBounds(50, 180, 330, 40);
        jPassField.setFont(new Font("Roboto", 0, 17));
        getContentPane().add(jPassField);

        errorpass = new JLabel("Mật khẩu có độ dài 8-16");
        errorpass.setForeground(Color.red);
        errorpass.setBounds(130, 210, 330, 40);
        getContentPane().add(errorpass);

        jTextField_phone.setBounds(50, 360, 330, 40);
        jTextField_phone.setFont(new Font("Roboto", 0, 17));
        getContentPane().add(jTextField_phone);

        errorphone = new JLabel("Số điện thoại có 10 chữ số");
        errorphone.setForeground(Color.red);
        errorphone.setBounds(120, 390, 330, 40);
        getContentPane().add(errorphone);

        jTextField_email.setBounds(50, 450, 330, 40);
        jTextField_email.setFont(new Font("Roboto", 0, 17));
        getContentPane().add(jTextField_email);

        erroremail = new JLabel("Ví dụ: thuong@gmail.com");
        erroremail.setForeground(Color.red);
        erroremail.setBounds(150, 480, 330, 40);
        getContentPane().add(erroremail);

        String[] s = {"Hà Giang", "Cao Bằng", "Lào Cai", "Sơn La", "Lai Châu", "Bắc Kạn", "Lạng Sơn",
            "Tuyên Quang", "Yên Bái", "Thái Nguyên", "Điện Biên", "Phú Thọ", "Vĩnh Phúc", "Bắc Giang",
            "Bắc Ninh", "Hà Nội", "Quảng Ninh", "Hải Dương", "Hải Phòng", "Hòa Bình", "Hưng Yên",
            "Hà Nam", "Thái Bình", "Nam Định", "Ninh Bình", "Thanh Hóa", "Nghệ An", "Hà Tĩnh",
            "Quảng Bình", "Quảng Trị", "Thừa Thiên Huế", "Đà Nẵng", "Quảng Nam", "Quảng Ngãi", "KonTum",
            "Gia Lai", "Bình Định ", "Phú Yên", "Đắk Lắk", "Khánh Hòa", "Đắk Nông", "Lâm Đồng", "Ninh Thuận",
            "Bình Phước", "Tây Nguyên", "Bình Dương", "Đồng Nai", "Bình Thuận", "TP.Hồ Chí Minh", "Long An",
            "Bà Rịa - Vũng Tàu", "Đồng Tháp", "An Giang", "Tiền Giang", "Vĩnh Long", "Bến Tre", "Cần Thơ",
            "Kiên Giang", "Trà Vinh", "Hậu Giang", "Sóc Trăng", "Bạc Liêu", "Cà Mau"};
        jComboBox_address = new JComboBox<>(s);
        jComboBox_address.setBackground(Color.white);
        jComboBox_address.setBounds(50, 545, 330, 40);
        jComboBox_address.setFont(new Font("Roboto", 0, 17));
        getContentPane().add(jComboBox_address);

        jCheckBox_agree.setFont(new Font("Roboto", 0, 18));
        jCheckBox_agree.setText("Tôi đồng ý với các điều khoản của thư viện");
        jCheckBox_agree.setBackground(new Color(236, 242, 243));
        getContentPane().add(jCheckBox_agree);
        jCheckBox_agree.setBounds(35, 615, 370, 31);

        jButton_signup.setBackground(new Color(102, 153, 255));
        jButton_signup.setFont(new Font("Roboto", 1, 20));
        jButton_signup.setForeground(Color.white);
        jButton_signup.setText("Đăng kí");
        getContentPane().add(jButton_signup);
        jButton_signup.setBounds(130, 710, 170, 40);
        jButton_signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerTaiKhoanUser();
            }
        });

        this.setVisible(true);
    }

    public boolean checkRegex() {
        String checkTenDN = "^[a-zA-Z0-9-_]{1,7}$";
        Pattern pattern = Pattern.compile(checkTenDN);
        Matcher matcherTacGia = pattern.matcher(jTextField_userName.getText());
        if (!matcherTacGia.matches()) {
            return false;
        }

        String checkPass = "^(.{8,16})$";
        Pattern patternPass = Pattern.compile(checkPass);
        Matcher matcherPass = patternPass.matcher(String.valueOf(jPassField.getPassword()));
        if (!matcherPass.matches()) {
            return false;
        }

        String checkName = "([A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]+[\\s]+)([A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]+[\\s]*)+";
//        String checkName = "^[p{L}s]{5,}$";
        Pattern patternName = Pattern.compile(checkName);
        Matcher matcherName = patternName.matcher(jTextField_fullName.getText());
        if (!matcherName.matches()) {
            return false;
        }

        String checkPhone = "^0[0-9]{9}$";
        Pattern patternPhone = Pattern.compile(checkPhone);
        Matcher matcherPhone = patternPhone.matcher(jTextField_phone.getText());
        if (!matcherPhone.matches()) {
            return false;
        }

        String checkEmail = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        Pattern patternEmail = Pattern.compile(checkEmail);
        Matcher matcherEmail = patternEmail.matcher(jTextField_email.getText());
        if (!matcherEmail.matches()) {
            return false;
        }

        if (jCheckBox_agree.isSelected() == false) {
            return false;
        }
        return true;
    }

    public boolean ktraTonTai(String s) {
        ArrayList<ReaderDTO> docGiaTonTai = dgBLL.getReadersByCondition(s);
        if (!docGiaTonTai.isEmpty()) {
            JOptionPane.showMessageDialog(null, s + "đã tồn tại", "warning", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

//    public void registerTaiKhoanUser() {
//        if (checkRegex() == false) {
//            JOptionPane.showMessageDialog(null, "Yêu cầu nhập đúng dữ liệu", "warning", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            String userName = jTextField_userName.getText();
//            String pass = String.valueOf(jPassField.getPassword());
//            String fullName = jTextField_fullName.getText();
//            String phone = jTextField_phone.getText();
//            String email = jTextField_email.getText();
//            String address = jComboBox_address.getSelectedItem().toString();
//            ReaderDTO docGia = new ReaderDTO(userName, pass, fullName, phone, email, address, 0);
//            ReaderDTO docGiaTonTai = dgBLL.selectById(docGia);
//            if (ktraTonTai("TENDN = '" + userName + "'") && ktraTonTai("SDT = '" + phone + "'") && ktraTonTai("Email = '" + email + "'")) {
//                dgBLL.addReader(docGia);
//                System.out.println(docGia);
//                JOptionPane.showMessageDialog(null, "Đăng kí thành công. Vui lòng đăng nhập lại", "warning", JOptionPane.INFORMATION_MESSAGE);
//                dispose(); 
//                new LoginGUI();
//            }
//        }
//    }
    
    public void registerTaiKhoanUser() {

        if (checkRegex() == false) {
            JOptionPane.showMessageDialog(null, "Yêu cầu nhập đúng dữ liệu", "warning", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String userName = jTextField_userName.getText();
            String pass = String.valueOf(jPassField.getPassword());
            String fullName = jTextField_fullName.getText();
            String phone = jTextField_phone.getText();
            String email = jTextField_email.getText();
            String address = jComboBox_address.getSelectedItem().toString();
            ReaderDTO docGia = new ReaderDTO(userName,  fullName, pass, phone, email, address, 1);
            ReaderDTO docGiaTonTai = dgBLL.selectById(docGia.getTenDN());
            if (ktraTonTai("TENDN = '" + userName + "'") && ktraTonTai("SDT = '" + phone + "'") && ktraTonTai("Email = '" + email + "'")) {
                dgBLL.addReader(docGia);
                System.out.println(docGia);
                JOptionPane.showMessageDialog(null, "Đăng kí thành công. Vui lòng đăng nhập lại", "warning", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                Date ngayDK = new Date(System.currentTimeMillis());
                CardDTO card = new CardDTO("Chưa có",userName,ngayDK,0);
                JOptionPane.showMessageDialog(rootPane, cBLL.addCard(card));
                new LoginGUI();
            }
        }
    }
}
