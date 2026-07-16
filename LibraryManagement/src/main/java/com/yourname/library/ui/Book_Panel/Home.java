package com.yourname.library.ui.Book_Panel;

import javax.swing.*;
import java.awt.*;
public class Home extends JPanel{
    public Home(){
      InitUI();
    }
    private void InitUI(){
    // CONTENT_WIDTH = 820;
    // CONTENT_HEIGHT = 470;  
        setLayout(null);
        JLabel la = new JLabel("主页面");
        add(la);


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
}
