package com.yourname.library.ui.Book_Panel;

import javax.swing.*;
import java.awt.*;
public class ViewBook  extends JPanel{
    public ViewBook(){
  InitUI();
    }
    private void InitUI(){
        JLabel la = new JLabel("借阅页面");
        add(la);

        JButton bu = new JButton();
        add(bu);
    }
}
