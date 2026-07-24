package com.yourname.library.ui;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initUI();
    }

    //控件类声明
    private JTextField LoginField = new JTextField("");
    private JPasswordField PasswordField = new JPasswordField("");
    private JButton LoginButton = new JButton("登录");
    private JButton RegisterButton = new JButton("注册");
    private JLabel LoginLabel = new JLabel("登录:");
    private JLabel PasswordLabel = new JLabel("密码:");
    private void initUI() {
        //初始化控件信息
        LoginLabel.setBounds(50, 50, 50, 30);
        PasswordLabel.setBounds(50, 100, 50, 30);
        LoginField.setBounds(100, 50, 150, 30);
        PasswordField.setBounds(100, 100, 150, 30);
        LoginButton.setBounds(100, 150, 70, 30);
        RegisterButton.setBounds(180, 150, 70, 30);
        //设置布局为null
        setLayout(null);
        //添加控件到面板
        add(LoginField);
        add(PasswordField);
        add(LoginButton);
        add(RegisterButton);
        add(LoginLabel);
        add(PasswordLabel);

        //动作监听 同时实现结果函数调用调用
        LoginButton.addActionListener(e -> LoginButton_Action());//登陆按钮处理
        RegisterButton.addActionListener(e -> RegisterButton_Action());//注册按钮处理
        LoginField.addActionListener(e -> LoginField_Action());//用户框处理
        PasswordField.addActionListener(e -> PasswordField_Action());//密码框处理
    }

    //登陆按钮动作
    private void LoginButton_Action(){
        mainFrame.showBookPanel();
    }
    //注册按钮动作
    private void RegisterButton_Action(){

    }
/**
 * 用户框动作（按回车触发）
 */
private void LoginField_Action() {
    String username = LoginField.getText().trim();
    
    if (username.isEmpty()) {
        // 用户名为空 → 停留在用户框，提示
        JOptionPane.showMessageDialog(this, "请输入用户名！");
        LoginField.requestFocus();  // 焦点回到用户框
        return;
    }
    
    // 用户名有内容 → 焦点跳到密码框
    PasswordField.requestFocus();
}

/**
 * 密码框动作（按回车触发）
 */
private void PasswordField_Action() {
    String username = LoginField.getText().trim();
    String password = new String(PasswordField.getPassword());
    
    // 检查用户名是否为空
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "请先输入用户名！");
        LoginField.requestFocus();  // 焦点回到用户框
        return;
    }
    
    // 检查密码是否为空
    if (password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "请输入密码！");
        PasswordField.requestFocus();  // 焦点停留在密码框
        return;
    }
    
    // 用户名和密码都不为空 → 执行登录
    LoginButton_Action();
}

}