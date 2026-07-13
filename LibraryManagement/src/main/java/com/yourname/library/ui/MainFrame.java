package com.yourname.library.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    
    private JFrame frame;      // 主窗口
    private JPanel panel;      // 当前显示的面板
    // 缓存所有面板
    private LoginPanel loginPanel;//登陆面板
    private BookPanel bookPanel;//图书管理面板
    private UserPanel userPanel;//用户管理面板

    public MainFrame() {
        initUI();
        frame.setVisible(true);
    }
    
    private void initUI() {
        // 创建窗口
        frame = new JFrame();
        frame.setTitle("图书管理系统");
        frame.setSize(350,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        // 默认显示登录面板
        showLoginPanel();
    }
    //切换到 -> 登陆窗口
    public void showLoginPanel() {
        if (loginPanel == null) {
            loginPanel = new LoginPanel(this);
        }
        switchPanel(loginPanel);
        frame.setSize(350,250);
    }
    //切换到 -> 用户窗口
    public void showUserPanel(){
        if(userPanel == null){
            userPanel = new UserPanel(this);
        }
        switchPanel(userPanel);
    }
    //切换到 -> 书籍窗口
    public void showBookPanel() {
        if (bookPanel == null) {
            bookPanel = new BookPanel(this);
        }
        switchPanel(bookPanel);
        frame.setSize(1000, 700);
    }

     //切换面板
    public void switchPanel(JPanel newPanel) {
        if (panel != null) {
            frame.remove(panel);  // 移除旧面板
        }
        panel = newPanel;
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}