//用户管理面板
package com.yourname.library.ui;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
   
   private MainFrame mainFrame;

   public UserPanel(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
    initUI();
   }

   private void initUI(){
    JLabel titleLabel = new JLabel("用户管理");
    add(titleLabel);
   }
}