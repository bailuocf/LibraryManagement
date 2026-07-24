package com.yourname.library.ui.Book_Panel;

import com.yourname.library.util.AvatarImageService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UserSettings extends JPanel{
    public UserSettings(){
        InitUI();
    }

    private Settings_Dialog settings_Dialog = new Settings_Dialog();
    private ImageIcon scaledIcon;
    private ImageIcon settingsIcon;
    JButton settings_button;
    private JButton selectAvatarButton;

    private void InitUI(){
        setLayout(null);

        loadSavedAvatar();

        selectAvatarButton = new JButton("选择头像");
        selectAvatarButton.setBounds(10, 120, 100, 30);
        selectAvatarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectAvatarButton.addActionListener(e -> selectAvatar());
        add(selectAvatarButton);

        JLabel formatLabel = new JLabel("支持 JXL/JPG/PNG");
        formatLabel.setForeground(Color.GRAY);
        formatLabel.setFont(new Font("微软雅黑", Font.PLAIN, 11));
        formatLabel.setBounds(10, 152, 130, 20);
        add(formatLabel);

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
              int x = 10 + (95 - width) / 2;
              int y = 10 + (95 - height) / 2;
              g.drawImage(scaledIcon.getImage(), x, y, this);
            } else {
              showNoAvatarText(g);
           }
     } else {
            showNoAvatarText(g);
       }
}

    private void selectAvatar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择头像图片");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "头像图片 (*.jxl, *.png, *.jpg, *.jpeg)",
                "jxl", "png", "jpg", "jpeg"
        ));

        if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        try {
            BufferedImage image = AvatarImageService.importAvatar(
                    fileChooser.getSelectedFile()
            );
            updateAvatarPreview(image);
            JOptionPane.showMessageDialog(this, "头像已保存");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "头像加载失败",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadSavedAvatar() {
        try {
            BufferedImage image = AvatarImageService.loadAvatar();
            if (image != null) {
                updateAvatarPreview(image);
            }
        } catch (IOException e) {
            scaledIcon = null;
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "头像加载失败",
                    JOptionPane.WARNING_MESSAGE
            ));
        }
    }

    private void updateAvatarPreview(BufferedImage image) {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        double scale = Math.min(
                95.0 / originalWidth,
                95.0 / originalHeight
        );
        int previewWidth = Math.max(1, (int) Math.round(originalWidth * scale));
        int previewHeight = Math.max(1, (int) Math.round(originalHeight * scale));

        Image preview = image.getScaledInstance(
                previewWidth,
                previewHeight,
                Image.SCALE_SMOOTH
        );
        scaledIcon = new ImageIcon(preview);
        repaint();
    }

    //显示没有头像时的文本
    private void showNoAvatarText(Graphics g) {
    g.setColor(Color.GRAY);
    g.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    g.drawString("暂无头像", 25, 55);
}
}
