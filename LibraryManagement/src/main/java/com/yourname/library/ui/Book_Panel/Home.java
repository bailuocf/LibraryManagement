package com.yourname.library.ui.Book_Panel;

import javax.swing.*;
import java.awt.*;
public class Home extends JPanel{
    public Home(){
      InitUI();
    }
    private void InitUI(){
        JLabel la = new JLabel("主页面");
        add(la);

        JButton bu = new JButton();
        add(bu);
    }
}
