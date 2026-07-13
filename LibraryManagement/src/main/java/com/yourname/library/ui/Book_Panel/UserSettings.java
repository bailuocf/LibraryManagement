package com.yourname.library.ui.Book_Panel;

import javax.swing.*;
import java.awt.*;

public class UserSettings extends JPanel{
    
    // 表示所有内容的起点
    private int x, y;
    public UserSettings(){
        InitUI();
    }
    private void InitUI(){
        JLabel la = new JLabel("设置页面");
        la.setBounds(0,0,10,10);
        add(la);

        JButton bu = new JButton();
        bu.setBounds(10,40,100,40);
        add(bu);
    }
}
