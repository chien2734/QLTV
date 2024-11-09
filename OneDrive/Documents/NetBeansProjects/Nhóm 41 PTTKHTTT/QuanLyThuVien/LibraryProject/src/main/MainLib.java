/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import GUI.PhieuTraGUI;
import java.awt.*;
import javax.swing.*;
import GUI.LoaiSachGUI;
import GUI.LoginGUI;
import GUI.MainBookMana;
import GUI.MainCardMana;
import GUI.MainLoanMana;
import GUI.MainReaderMana;
import GUI.trangChu;
import java.awt.event.*;

/**
 *
 * @author ADMIN
 */
public class MainLib extends JFrame {

    public JButton jButton_qLyDocGia;
    public JButton jButton_qLyMuon;
    public JButton jButton_qLyThe;
    public JButton jButton_qLyTra;
    public JButton jButton_qlySach;
    public JButton jButton_trangChu;
    public JButton jButton_qLyLoaiSach;
    public JButton jButton_logOut;
    public JLabel jLabel_logo;
    public JPanel jPanel_title;
    public JPanel jPanel_main;
    public JPanel jPanel_menu;

    public MainLib() {
        this.init();
    }

    public void init() {
        jPanel_menu = new JPanel();
        jButton_qlySach = new JButton();
        jButton_qLyThe = new JButton();
        jButton_qLyMuon = new JButton();
        jButton_qLyTra = new JButton();
        jButton_qLyDocGia = new JButton();
        jButton_trangChu = new JButton();
        jButton_logOut = new JButton();
        jButton_qLyLoaiSach = new JButton();
        jLabel_logo = new JLabel();
        jPanel_main = new JPanel();
        jPanel_title = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MainView");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(null);

        ActionListener ac = new MainBUS(this);

        jPanel_menu.setBackground(Color.white);
        jPanel_menu.setLayout(null);
        jPanel_main.setLayout(null);
        jPanel_title.setBackground(Color.white);
        jPanel_title.setLayout(null);

        jLabel_logo.setText("   Admin");
        jLabel_logo.setFont(new Font("Roboto", 1, 24));
        jLabel_logo.setIcon(new ImageIcon(getClass().getResource("/res/lib.png")));
        jPanel_menu.add(jLabel_logo);
        jLabel_logo.setBounds(40, 20, 200, 100);

        JLabel ex = new JLabel("-");
        ex.setBounds(0, 90, jPanel_menu.getWidth(), 1);
        jPanel_menu.add(ex);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setBounds(0, 150, 250, 10);
        jPanel_menu.add(separator);

        jButton_trangChu.setFont(new Font("Roboto", 0, 18));
        jButton_trangChu.setIcon(new ImageIcon(getClass().getResource("/res/trangchu.png")));
        jButton_trangChu.setText("    Trang chủ");
        jButton_trangChu.setBackground(new Color(187, 222, 251));
        jButton_trangChu.setBorderPainted(false);
        jPanel_menu.add(jButton_trangChu);
        jButton_trangChu.setBounds(15, 160, 220, 60);
        jButton_trangChu.addActionListener(ac);

        jButton_qLyLoaiSach.setFont(new Font("Roboto", 0, 18));
        jButton_qLyLoaiSach.setIcon(new ImageIcon(getClass().getResource("/res/loaisach.png")));
        jButton_qLyLoaiSach.setText("Quản lý loại sách");
        jButton_qLyLoaiSach.setBackground(Color.WHITE);
        jButton_qLyLoaiSach.setBorderPainted(false);
        jPanel_menu.add(jButton_qLyLoaiSach);
        jButton_qLyLoaiSach.setBounds(15, 250, 220, 60);
        jButton_qLyLoaiSach.addActionListener(ac);

        jButton_qlySach.setBackground(Color.WHITE);
        jButton_qlySach.setFont(new Font("Roboto", 0, 18));
        jButton_qlySach.setIcon(new ImageIcon(getClass().getResource("/res/book_mana.png")));
        jButton_qlySach.setText("Quản lý sách");
        jButton_qlySach.setHorizontalTextPosition(SwingConstants.RIGHT);
        jButton_qlySach.setBorderPainted(false);
        jPanel_menu.add(jButton_qlySach);
        jButton_qlySach.setBounds(15, 340, 220, 60);
        jButton_qlySach.addActionListener(ac);

        jButton_qLyThe.setFont(new Font("Roboto", 0, 18));
        jButton_qLyThe.setIcon(new ImageIcon(getClass().getResource("/res/the.png")));
        jButton_qLyThe.setText("Quản lý thẻ");
        jButton_qLyThe.setBackground(Color.WHITE);
        jButton_qLyThe.setBorderPainted(false);
        jPanel_menu.add(jButton_qLyThe);
        jButton_qLyThe.setBounds(15, 430, 220, 60);
        jButton_qLyThe.addActionListener(ac);

        jButton_qLyMuon.setFont(new Font("Roboto", 0, 18));
        jButton_qLyMuon.setIcon(new ImageIcon(getClass().getResource("/res/muon.png")));
        jButton_qLyMuon.setText("Quản lý mượn");
        jButton_qLyMuon.setBorderPainted(false);
        jButton_qLyMuon.setBackground(Color.WHITE);
        jPanel_menu.add(jButton_qLyMuon);
        jButton_qLyMuon.setBounds(15, 520, 220, 60);
        jButton_qLyMuon.addActionListener(ac);

        jButton_qLyTra.setFont(new Font("Roboto", 0, 18));
        jButton_qLyTra.setIcon(new ImageIcon(getClass().getResource("/res/tra.png")));
        jButton_qLyTra.setText("  Quản lý trả");
        jButton_qLyTra.setBorderPainted(false);
        jButton_qLyTra.setBackground(Color.WHITE);
        jPanel_menu.add(jButton_qLyTra);
        jButton_qLyTra.setBounds(15, 610, 220, 60);
        jButton_qLyTra.addActionListener(ac);

        jButton_qLyDocGia.setFont(new Font("Roboto", 0, 18));
        jButton_qLyDocGia.setIcon(new ImageIcon(getClass().getResource("/res/docgia.png")));
        jButton_qLyDocGia.setText("Quản lý độc giả");
        jButton_qLyDocGia.setBorderPainted(false);
        jButton_qLyDocGia.setBackground(Color.WHITE);
        jPanel_menu.add(jButton_qLyDocGia);
        jButton_qLyDocGia.setBounds(15, 700, 220, 60);
        jButton_qLyDocGia.addActionListener(ac);

        jButton_logOut.setFont(new Font("Roboto", 1, 24));
        jButton_logOut.setIcon(new ImageIcon(getClass().getResource("/res/dxuat.png")));
        jButton_logOut.setText("Đăng xuất");
        jPanel_menu.add(jButton_logOut);
        jButton_logOut.setBackground(Color.white);
        jButton_logOut.setBounds(0, 870, 230, 70);
        jButton_logOut.setBorderPainted(false);
        jButton_logOut.addActionListener(ac);

        jPanel_menu.setBounds(0, 0, 250, this.getMaximumSize().height);
        getContentPane().add(jPanel_menu);

        jPanel_title.setFont(new Font("Roboto", 0, 18));
        jPanel_title.setBounds(260, 0, this.getMaximumSize().width - 290, 40);

        jPanel_main.setFont(new Font("Roboto", 0, 18));
        jPanel_main.setBounds(260, 0, this.getMaximumSize().width - 290, this.getMaximumSize().height);
        jPanel_main.add(new trangChu());

        getContentPane().add(jPanel_main);
        this.setVisible(true);

    }

    public void changeTab(String tab) {
        if (tab.equals("    Trang chủ")) {
            jButton_trangChu.setBackground(new Color(187, 222, 251));
            jButton_qlySach.setBackground(Color.white);
            jButton_qLyThe.setBackground(Color.white);
            jButton_qLyMuon.setBackground(Color.white);
            jButton_qLyTra.setBackground(Color.white);
            jButton_qLyDocGia.setBackground(Color.white);
            jButton_qLyLoaiSach.setBackground(Color.white);
        } else if (tab.equals("Quản lý sách")) {
            jButton_qlySach.setBackground(new Color(187, 222, 251));
            jButton_trangChu.setBackground(Color.white);
            jButton_qLyThe.setBackground(Color.white);
            jButton_qLyMuon.setBackground(Color.white);
            jButton_qLyTra.setBackground(Color.white);
            jButton_qLyDocGia.setBackground(Color.white);
            jButton_qLyLoaiSach.setBackground(Color.white);
        } else if (tab.equals("Quản lý thẻ")) {
            jButton_qLyThe.setBackground(new Color(187, 222, 251));
            jButton_qlySach.setBackground(Color.white);
            jButton_trangChu.setBackground(Color.white);
            jButton_qLyMuon.setBackground(Color.white);
            jButton_qLyTra.setBackground(Color.white);
            jButton_qLyDocGia.setBackground(Color.white);
            jButton_qLyLoaiSach.setBackground(Color.white);
        } else if (tab.equals("Quản lý mượn")) {
            jButton_qLyMuon.setBackground(new Color(187, 222, 251));
            jButton_qlySach.setBackground(Color.white);
            jButton_trangChu.setBackground(Color.white);
            jButton_qLyThe.setBackground(Color.white);
            jButton_qLyTra.setBackground(Color.white);
            jButton_qLyDocGia.setBackground(Color.white);
            jButton_qLyLoaiSach.setBackground(Color.white);
        } else if (tab.equals("  Quản lý trả")) {
            jButton_qLyTra.setBackground(new Color(187, 222, 251));
            jButton_qlySach.setBackground(Color.white);
            jButton_qLyThe.setBackground(Color.white);
            jButton_qLyMuon.setBackground(Color.white);
            jButton_trangChu.setBackground(Color.white);
            jButton_qLyDocGia.setBackground(Color.white);
            jButton_qLyLoaiSach.setBackground(Color.white);
        } else if (tab.equals("Quản lý độc giả")) {
            jButton_qLyDocGia.setBackground(new Color(187, 222, 251));
            jButton_qlySach.setBackground(Color.white);
            jButton_qLyThe.setBackground(Color.white);
            jButton_qLyMuon.setBackground(Color.white);
            jButton_qLyTra.setBackground(Color.white);
            jButton_trangChu.setBackground(Color.white);
            jButton_qLyLoaiSach.setBackground(Color.white);
        } else if (tab.equals("Quản lý loại sách")) {
            jButton_qLyLoaiSach.setBackground(new Color(187, 222, 251));
            jButton_qlySach.setBackground(Color.white);
            jButton_qLyThe.setBackground(Color.white);
            jButton_qLyMuon.setBackground(Color.white);
            jButton_qLyTra.setBackground(Color.white);
            jButton_trangChu.setBackground(Color.white);
            jButton_qLyDocGia.setBackground(Color.white);
        }
        removeAndAddJTabble(tab);
    }

    public void removeAndAddJTabble(String tab) {
        if (tab.equals("    Trang chủ")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new trangChu());
        } else if (tab.equals("Quản lý loại sách")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new LoaiSachGUI());
        } else if (tab.equals("Quản lý sách")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new MainBookMana());
        } else if (tab.equals("Quản lý thẻ")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new MainCardMana());
        } else if (tab.equals("Quản lý mượn")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new MainLoanMana());
        } else if (tab.equals("  Quản lý trả")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new PhieuTraGUI());
        } else if (tab.equals("Quản lý độc giả")) {
            jPanel_main.removeAll();
            // Cập nhật lại giao diện
            revalidate();
            repaint();
            jPanel_main.add(new MainReaderMana());
        } else if (tab.equals("Đăng xuất")) {
            int check = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn đăng xuất?", "warning", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                dispose();
                new LoginGUI();
            }
        }
    }

}
