
package com.yourname.library.ui.Book_Panel;

import javax.swing.*;
import java.awt.*;
public class BorrowBooks  extends JPanel{
    public BorrowBooks(){
        InitUI();
    }
    private void InitUI(){
        JLabel la = new JLabel("归还页面");
        add(la);

        JButton bu = new JButton();
        add(bu);
    }
}
