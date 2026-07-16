package com.yourname.library.ui.Book_Panel;

import javax.swing.*;
import java.awt.*;

public class UserSettings extends JPanel{
    public UserSettings(){
        InitUI();
    }

    private Settings_Dialog settings_Dialog = new Settings_Dialog();
    private ImageIcon scaledIcon;
    private ImageIcon settingsIcon;
    JButton settings_button;
    private void InitUI(){
        setLayout(null);

        //头像
        ImageIcon icon = new ImageIcon("image/TouXiang.png");
        Image img = icon.getImage();
        Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaled);

        // ========== 设置按钮 ==========
        ImageIcon settings_icon = new ImageIcon("image/settings.png");
        Image settImage_img = settings_icon.getImage();
        Image settImage_Image = settImage_img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon settingsIcon = new ImageIcon(settImage_Image);

        //设置图标
        settings_button = new JButton(settingsIcon);
        showsettings();
        add(settings_button);

    }


   @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    //显示头像 如果没有头像就显示文字提示
    showAvatar(g);
}

    //设置方面
    private void showsettings(){
        settings_button.setBounds(795, 10, 30, 30);
        settings_button.setFocusPainted(false);
        settings_button.setBorderPainted(false);
        settings_button.setContentAreaFilled(false);
        settings_button.setCursor(new Cursor(Cursor.HAND_CURSOR));

       // 点击事件
       settings_button.addActionListener(e -> {
       settings_Dialog.show();
     });
    }
    //显示头像
    private void showAvatar(Graphics g){
        // 头像框背景
        g.setColor(new Color(240, 240, 245));
        g.fillRect(10, 10, 95, 95);
    
        // 头像框边框
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 95, 95);
    
        // 画头像
        if (scaledIcon != null && scaledIcon.getImage() != null) {
            int width = scaledIcon.getIconWidth();
            int height = scaledIcon.getIconHeight();
            if (width > 0 && height > 0) {
              g.drawImage(scaledIcon.getImage(), 10, 10, this);
            } else {
             System.err.println("error(3): 头像文件无效");
              showNoAvatarText(g);
           }
     } else {
         System.err.println("error(3): 未识别到头像png文件");
            showNoAvatarText(g);
       }
}
    //显示没有头像时的文本
    private void showNoAvatarText(Graphics g) {
    g.setColor(Color.GRAY);
    g.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    g.drawString("暂无头像", 25, 55);
}
}
