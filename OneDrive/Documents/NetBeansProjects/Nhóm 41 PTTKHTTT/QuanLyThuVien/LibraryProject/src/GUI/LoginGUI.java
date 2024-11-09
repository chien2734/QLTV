/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUI.UserGUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import main.MainLib;
import BLL.ReaderBUS;
import DTO.ReaderDTO;

/**
 *
 * @author ADMIN
 */
public class LoginGUI extends JFrame {

    public JButton jButton_login;
    public JLabel jLabel_img;
    public JLabel jLabel_password;
    public JButton jButton_signNow;
    public JLabel jLabel_text;
    public JLabel jLabel_title;
    public JLabel jLabel_userName;
    public JPasswordField jPassField;
    public JTextField jTextField_userName;
    public ReaderBUS dgBLL = new ReaderBUS();

    public LoginGUI() {
        this.init();
    }

    public void init() {
        jLabel_img = new JLabel();
        jLabel_title = new JLabel();
        jLabel_userName = new JLabel();
        jTextField_userName = new JTextField();
        jLabel_password = new JLabel();
        jPassField = new JPasswordField();
        jButton_login = new JButton();
        jLabel_text = new JLabel();
        jButton_signNow = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log_in");
        getContentPane().setBackground(Color.white);
        this.setResizable(false);
        setBounds(0, 0, 530, 775);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        jLabel_img.setIcon(new ImageIcon(getClass().getResource("/res/title.png")));
        getContentPane().add(jLabel_img);
        jLabel_img.setBounds(10, 50, 510, 330);

        jLabel_title.setFont(new Font("Segoe UI", 1, 24));
        jLabel_title.setForeground(new Color(102, 153, 255));
        jLabel_title.setText("CHÀO MỪNG ĐẾN THƯ VIỆN");
        getContentPane().add(jLabel_title);
        jLabel_title.setBounds(90, 10, 350, 40);

        jLabel_userName.setFont(new Font("Segoe UI", 0, 20));
        jLabel_userName.setText("Tên đăng nhập");
        getContentPane().add(jLabel_userName);
        jLabel_userName.setBounds(90, 390, 150, 30);

        getContentPane().add(jTextField_userName);
        jTextField_userName.setBounds(90, 430, 350, 40);
        jTextField_userName.setFont(new Font("Segoe UI", 0, 24));

        jLabel_password.setFont(new Font("Segoe UI", 0, 20));
        jLabel_password.setText("Mật khẩu");
        getContentPane().add(jLabel_password);
        jLabel_password.setBounds(90, 500, 90, 27);
        getContentPane().add(jPassField);
        jPassField.setBounds(90, 540, 350, 40);
        jPassField.setFont(new Font("Segoe UI", 0, 24));

        jButton_login.setBackground(new Color(102, 153, 255));
        jButton_login.setFont(new Font("Segoe UI", 1, 18));
        jButton_login.setForeground(new Color(255, 255, 255));
        jButton_login.setText("Đăng nhập");
        getContentPane().add(jButton_login);
        jButton_login.setBounds(190, 600, 150, 55);
        jButton_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(jTextField_userName.getText().equals("admin")) {
                if(String.valueOf(jPassField.getPassword()).equals("admin")) {
                    dispose();
                    new MainLib();
                }
            }else {
                checkLoginUser();
            }
            }
        });

       jLabel_text.setFont(new Font("Segoe UI", 0, 18));
        jLabel_text.setText("Bạn chưa có tài khoản?");
        getContentPane().add(jLabel_text);
        jLabel_text.setBounds(70, 670, 220, 40);

        jButton_signNow.setFont(new Font("Segoe UI", 1, 18));
        jButton_signNow.setForeground(new Color(102, 153, 255));
        jButton_signNow.setText("Đăng kí ngay");
        getContentPane().add(jButton_signNow);
        jButton_signNow.setBounds(255, 660, 170, 60);
        jButton_signNow.setBackground(Color.white);
        jButton_signNow.setBorderPainted(false);
        jButton_signNow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new SignUpGUI();
            }
        });

        this.setVisible(true);
    }

    public void checkLoginUser() {
        if (jTextField_userName.getText().equals("admin")) {
            if (String.valueOf(this.jPassField.getPassword()).equals("admin")) {
                dispose();
                new MainLib();
            }
        } else {
            ReaderDTO t = new ReaderDTO(jTextField_userName.getText());
            ReaderDTO tk = dgBLL.selectById(t.getTenDN());
            System.out.println("người đọc có tk chưa: " + tk.getTenDN());
            if (tk == null) {
                JOptionPane.showMessageDialog(null, "Tài khoản này chưa tồn tai. Vui lòng đăng kí", "info", JOptionPane.INFORMATION_MESSAGE);
                resetFrm();
            } else {
                String pass = String.valueOf(jPassField.getPassword());
                System.out.println(pass + ", " + tk.getMatKhau());
                if (pass.equals(tk.getMatKhau())) {
                    String maDG = tk.getTenDN();
                    dispose();
                    new UserGUI(maDG);
                } else {
                    JOptionPane.showMessageDialog(null, "Mật khẩu không đúng", "info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public void resetFrm() {
        jTextField_userName.setText("");
        jPassField.setText("");
    }
    public static void main(String[] args) {
        new LoginGUI();
    }
}
