package com.yourname.library.ui;

import javax.swing.*;
import java.awt.*;

public class BookPanel extends JPanel {
    
    private MainFrame mainFrame;

    public BookPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initUI();
    }
    
    private void initUI() {
       JLabel titleLabel = new JLabel("Book Title");
        add(titleLabel);
    }
}   