/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import GUI.LoginGUI;

/**
 *
 * @author ADMIN
 */
public class UserGUI extends JFrame {

    public JButton jButton_muonSach;
    public JButton jButton_timKiem;
    public JButton jButton_thongTin;
    public JButton jButton_trangChu;
    public JButton jButton_logOut;
    public JLabel jLabel_logo;
    public JPanel jPanel_main;
    public JPanel jPanel_menu;
    public String maDG;

    public UserGUI(String maDG) {
        this.maDG = maDG;
        this.init();
    }

    public void init() {
        jPanel_menu = new JPanel();
        jButton_thongTin = new JButton();
        jButton_timKiem = new JButton();
        jButton_muonSach = new JButton();
        jButton_trangChu = new JButton();
        jButton_logOut = new JButton();
        jLabel_logo = new JLabel();
        jPanel_main = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MainView");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(null);

        jPanel_menu.setBackground(Color.white);
        jPanel_menu.setLayout(null);
        
        jLabel_logo.setText("Hi, " + maDG + " !");
        jLabel_logo.setFont(new Font("Roboto", 1, 22));
        jLabel_logo.setIcon(new ImageIcon(getClass().getResource("/res/user.png")));
        jPanel_menu.add(jLabel_logo);
        jLabel_logo.setBounds(40, 20, 400, 100);

        JLabel ex = new JLabel("-");
        ex.setBounds(0, 90, jPanel_menu.getWidth(), 1);
        jPanel_menu.add(ex);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setBounds(0, 150, 250, 10);
        jPanel_menu.add(separator);

        jButton_trangChu.setFont(new Font("Roboto", 0, 18));
        jButton_trangChu.setIcon(new ImageIcon(getClass().getResource("/res/trangchu.png")));
        jButton_trangChu.setText("Trang chủ");
        jButton_trangChu.setBackground(new Color(187, 222, 251));
        jButton_trangChu.setBorderPainted(false);
        jPanel_menu.add(jButton_trangChu);
        jButton_trangChu.setBounds(15, 180, 220, 60);
        jButton_trangChu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeTab("Trang chủ");
                //ktraMuon();
            }

        });

        jButton_thongTin.setBackground(Color.WHITE);
        jButton_thongTin.setFont(new Font("Roboto", 0, 18));
        jButton_thongTin.setIcon(new ImageIcon(getClass().getResource("/res/thongtin.png")));
        jButton_thongTin.setText("Thông tin");
        jButton_thongTin.setHorizontalTextPosition(SwingConstants.RIGHT);
        jButton_thongTin.setBorderPainted(false);
        jPanel_menu.add(jButton_thongTin);
        jButton_thongTin.setBounds(15, 280, 220, 58);
        jButton_thongTin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeTab("Thông tin");                
            }
        });
        jButton_timKiem.setFont(new Font("Roboto", 0, 18));
        jButton_timKiem.setIcon(new ImageIcon(getClass().getResource("/res/timkiemsach.png")));
        jButton_timKiem.setText("Tìm kiếm sách");
        jButton_timKiem.setBackground(Color.WHITE);
        jButton_timKiem.setBorderPainted(false);
        jPanel_menu.add(jButton_timKiem);
        jButton_timKiem.setBounds(15, 380, 220, 60);
        jButton_timKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               changeTab("Tìm kiếm sách");
            }
        });

        jButton_muonSach.setFont(new Font("Roboto", 0, 18));
        jButton_muonSach.setIcon(new ImageIcon(getClass().getResource("/res/muontra.png")));
        jButton_muonSach.setText("Danh sách mượn");
        jButton_muonSach.setBorderPainted(false);
        jButton_muonSach.setBackground(Color.WHITE);
        jPanel_menu.add(jButton_muonSach);
        jButton_muonSach.setBounds(15, 480, 220, 60);
        jButton_muonSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeTab("Danh sách mượn");
            }
        });

        jButton_logOut.setFont(new Font("Roboto", 1, 24));
        jButton_logOut.setIcon(new ImageIcon(getClass().getResource("/res/dxuat.png")));
        jButton_logOut.setText("Đăng xuất");
        jPanel_menu.add(jButton_logOut);
        jButton_logOut.setBackground(Color.white);
//        jButton_logOut.setBounds(0, 900, 230, 70);
        jButton_logOut.setBounds(15, 780, 220, 60);
        jButton_logOut.setBorderPainted(false);
        jButton_logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeTab("Đăng xuất");
            }
        });

        jPanel_menu.setBounds(0, 0, 250, this.getMaximumSize().height);
        getContentPane().add(jPanel_menu);

        jPanel_main.setFont(new Font("Roboto", 0, 18));
        jPanel_main.setBounds(260, 0, this.getMaximumSize().width - 290, this.getMaximumSize().height);
        jPanel_main.add(new trangChu());
        jPanel_main.setLayout(null);
        getContentPane().add(jPanel_main);

        this.setVisible(true);
    }

    public void changeTab(String tab) {
        if (tab.equals("Trang chủ")) {
            jButton_trangChu.setBackground(new Color(187, 222, 251));
            jButton_thongTin.setBackground(Color.white);
            jButton_muonSach.setBackground(Color.white);
            jButton_timKiem.setBackground(Color.white);

        } else if(tab.equals("Thông tin")) {
            jButton_trangChu.setBackground(Color.white);
            jButton_thongTin.setBackground(new Color(187, 222, 251));
            jButton_muonSach.setBackground(Color.white);
            jButton_timKiem.setBackground(Color.white);
        }
        
        else if (tab.equals("Tìm kiếm sách")) {
            jButton_trangChu.setBackground(Color.white);
            jButton_thongTin.setBackground(Color.white);
            jButton_muonSach.setBackground(Color.white);
            jButton_timKiem.setBackground(new Color(187, 222, 251));
        } else if (tab.equals("Danh sách mượn")) {
            jButton_trangChu.setBackground(Color.white);
            jButton_thongTin.setBackground(Color.white);
            jButton_muonSach.setBackground(new Color(187, 222, 251));
            jButton_timKiem.setBackground(Color.white);

        } else {

        }
        removeAndAddJTabble(tab);
    }

    public void removeAndAddJTabble(String tab) {
        if (tab.equals("Trang chủ")) {
           jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new trangChu());
        } else if (tab.equals("Thông tin")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new InformationGUI(maDG));
        } else if (tab.equals("Tìm kiếm sách")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new SearchUserGUI(maDG));

        } else if (tab.equals("Danh sách mượn")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new PhieuMuonUser(maDG));
            
        } else if (tab.equals("Đăng xuất")) {
            int check = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn đăng xuất?", "warning", JOptionPane.YES_NO_OPTION);
            if(check == JOptionPane.YES_OPTION) {
                dispose();
                new LoginGUI();
            }
        }
    }
    
//    private void ktraMuon() {
//        
//    }
}
