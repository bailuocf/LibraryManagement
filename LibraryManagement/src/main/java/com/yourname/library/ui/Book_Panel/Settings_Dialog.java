package com.yourname.library.ui.Book_Panel;
import com.yourname.library.util.FontLoader;

import javax.swing.*;
import java.awt.*;

public class Settings_Dialog extends JDialog {
    
    // ========== 控件 ==========
    private JTextField NameField;      // 名字输入框
    private JTextField AgeField;       // 年龄输入框
    private JPasswordField PasswordField; // 密码输入框
    private JTextArea introduction;    // 简介输入框
    
    private JButton confirmButton;     // 确认按钮
    private JButton cancelButton;      // 取消按钮
    
    public Settings_Dialog() {
        initUI();
        setVisible(false);  // 默认不显示
    }
    
    /**
     * 显示设置窗口
     */
    public void showDialog() {
        setVisible(true);
    }
    
    /**
     * 初始化UI
     */
    private void initUI() {
        setSize(400, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        initControls();
    }
    
    /**
     * 初始化所有控件（只创建，不显示）
     */
    private void initControls() {
    // ========== 标题 ==========
    JLabel titleLabel = new JLabel("⚙️ 个人设置");
    titleLabel.setFont(FontLoader.getBold(18f));
    titleLabel.setBounds(20, 15, 150, 30);
    add(titleLabel);
    
    // ========== 分隔线 ==========
    JSeparator line = new JSeparator();
    line.setBounds(10, 50, 380, 2);
    add(line);
    
    // ========== 名字 ==========
    JLabel nameLabel = new JLabel("名字：");
    nameLabel.setBounds(40, 75, 60, 25);
    nameLabel.setFont(FontLoader.getRegular(14f));
    add(nameLabel);
    
    NameField = new JTextField();
    NameField.setBounds(110, 73, 200, 28);
    NameField.setFont(FontLoader.getRegular(14f));
    add(NameField);
    
    // ========== 年龄 ==========
    JLabel ageLabel = new JLabel("年龄：");
    ageLabel.setBounds(40, 115, 60, 25);
    ageLabel.setFont(FontLoader.getRegular(14f));
    add(ageLabel);
    
    AgeField = new JTextField();
    AgeField.setBounds(110, 113, 200, 28);
    AgeField.setFont(FontLoader.getRegular(14f));
    add(AgeField);
    
    // ========== 密码 ==========
    JLabel passwordLabel = new JLabel("密码：");
    passwordLabel.setBounds(40, 155, 60, 25);
    passwordLabel.setFont(FontLoader.getRegular(14f));
    add(passwordLabel);
    
    PasswordField = new JPasswordField();
    PasswordField.setBounds(110, 153, 200, 28);
    PasswordField.setFont(FontLoader.getRegular(14f));
    add(PasswordField);
    
    // ========== 简介 ==========
    JLabel introLabel = new JLabel("简介：");
    introLabel.setBounds(40, 195, 60, 25);
    introLabel.setFont(FontLoader.getRegular(14f));
    add(introLabel);
    
    introduction = new JTextArea();
    introduction.setFont(FontLoader.getRegular(14f));
    introduction.setLineWrap(true);
    introduction.setWrapStyleWord(true);
    introduction.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
    
    JScrollPane scrollPane = new JScrollPane(introduction);
    scrollPane.setBounds(110, 193, 200, 60);
    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
    add(scrollPane);
    
    // ========== 按钮 ==========
    confirmButton = new JButton("确认");
    confirmButton.setBounds(180, 270, 80, 30);
    confirmButton.setFont(FontLoader.getBold(14f));
    confirmButton.addActionListener(e -> confirmAction());
    add(confirmButton);
    
    cancelButton = new JButton("取消");
    cancelButton.setBounds(275, 270, 80, 30);
    cancelButton.setFont(FontLoader.getRegular(14f));
    cancelButton.addActionListener(e -> dispose());
    add(cancelButton);
}
    
    /**
     * ============================================================
     * 确认按钮点击事件（由你实现具体逻辑）
     * ============================================================
     */
    private void confirmAction() {
        // TODO: 获取输入数据并处理
        
        // String name = NameField.getText().trim();
        // String age = AgeField.getText().trim();
        // String password = new String(PasswordField.getPassword());
        // String intro = introduction.getText().trim();
        
        // 关闭窗口（数据由外部通过 getter 获取，或在这里回调）
        dispose();
    }
    
    // ========== Getter 方法（供外部获取输入数据） ==========
    
    public String getNameInput() {
        return NameField.getText().trim();
    }
    
    public String getAgeInput() {
        return AgeField.getText().trim();
    }
    
    public String getPasswordInput() {
        return new String(PasswordField.getPassword());
    }
    
    public String getIntroductionInput() {
        return introduction.getText().trim();
    }
}