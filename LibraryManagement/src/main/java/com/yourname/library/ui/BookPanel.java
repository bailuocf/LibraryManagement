package com.yourname.library.ui;

import javax.swing.*;
import java.awt.*;
import com.yourname.library.ui.Book_Panel.Home;
import com.yourname.library.ui.Book_Panel.ViewBook;
import com.yourname.library.ui.Book_Panel.BorrowBooks;
import com.yourname.library.ui.Book_Panel.UserSettings;

public class BookPanel extends JPanel {
    
    private MainFrame mainFrame;

    private JPanel Min_Panel;//当前页面
    private JPanel Home_Panel;//主页 页面留存
    private JPanel ViewBooks_Panel;//查看书籍 页面留存
    private JPanel BorrowBooks_Panel;//借阅书籍 页面留存
    private JPanel UserSettings_Panel;//用户与设置 页面留存
    // ========== 顶部标题 ==========
    private JLabel titleLabel;
    
    // ========== 菜单按钮 ==========
    private JButton btnHome;//主页
    private JButton btnViewBooks;//查看书籍
    private JButton btnBorrowBooks;//借阅书籍
    private JButton btnUserSettings;//用户与设置
    
    // ========== 颜色常量 ==========
    private static final Color PRIMARY = new Color(40, 65, 110);
    private static final Color PRIMARY_LIGHT = new Color(60, 90, 150);
    private static final Color PRIMARY_DARK = new Color(30, 50, 85);
    private static final Color DIVIDER = new Color(170, 185, 210);
    private static final Color BG_CONTENT = new Color(245, 247, 250);
    private static final Color TEXT_WHITE = new Color(248, 248, 252);
    
    // ========== 固定尺寸 ==========
    private static final int HEADER_HEIGHT = 100;      // 顶部色块高度（缩小）
    private static final int SIDEBAR_WIDTH = 150;      // 侧边栏宽度
    private static final int BUTTON_WIDTH = 130;       // 按钮宽度
    private static final int BUTTON_HEIGHT = 40;       // 按钮高度
    private static final int BUTTON_GAP = 10;          // 按钮间距

    // ========== 内容区域尺寸 ==========
    private static final int CONTENT_X = 150;
    private static final int CONTENT_Y = 100;
    private static final int CONTENT_WIDTH = 850;
    private static final int CONTENT_HEIGHT = 470;  
    
    public BookPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initUI();
        Settings_Panel();
    }
    
    private void initUI() {
        setLayout(null);
        setBackground(BG_CONTENT);
        
        // ========== 顶部标题 ==========
        titleLabel = new JLabel("📚 图书管理系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_WHITE);
        titleLabel.setBounds(30, 32, 280, 40);
        add(titleLabel);
        
        // ========== 计算按钮位置（覆盖左下色块） ==========
        // 4个按钮 + 3个间距 = 4 * 40 + 3 * 10 = 160 + 30 = 190
        // 总高度 190，足够覆盖侧边栏
        
        Font btnFont = new Font("微软雅黑", Font.PLAIN, 14);
        int startY = HEADER_HEIGHT + 20;  // 从顶部色块底部 + 20px 开始
        
        btnHome = createMenuButton("首页", btnFont);
        btnHome.setBounds(10, startY, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(btnHome);
        
        btnViewBooks = createMenuButton("查看书籍", btnFont);
        btnViewBooks.setBounds(10, startY + BUTTON_HEIGHT + BUTTON_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(btnViewBooks);
        
        btnBorrowBooks = createMenuButton("借阅书籍", btnFont);
        btnBorrowBooks.setBounds(10, startY + (BUTTON_HEIGHT + BUTTON_GAP) * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(btnBorrowBooks);
        
        btnUserSettings = createMenuButton("用户与设置", btnFont);
        btnUserSettings.setBounds(10, startY + (BUTTON_HEIGHT + BUTTON_GAP) * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(btnUserSettings);

        btnHome.addActionListener(e -> btnHome_Action());
        btnViewBooks.addActionListener(e -> btnViewBooks_Action());
        btnBorrowBooks.addActionListener(e -> btnBorrowBooks_Action());
        btnUserSettings.addActionListener(e -> btnUserSettings_Action());

    }
    
    private void Settings_Panel(){
        //如果没有页面 默认使用主页
        if(Min_Panel == null){
            ShowPanel(0);
        }
    }

    private JButton createMenuButton(String text, Font font) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                super.paintComponent(g2d);
                g2d.dispose();
            }
        };
        
        btn.setFont(font);
        btn.setForeground(TEXT_WHITE);
        btn.setBackground(PRIMARY);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 0));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(PRIMARY_LIGHT);
                btn.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(PRIMARY);
                btn.repaint();
            }
        });
        
        return btn;
    }
    
    //用于切换窗体
   private void ShowPanel(int panel) {
    // 1. 移除当前页面
    if (Min_Panel != null) {
        remove(Min_Panel);
    }
    
    // 2. 根据参数选择页面
    if (panel == 0) {
        // 主页
        if (Home_Panel == null) {
            Home_Panel = new Home();
        }
        Min_Panel = Home_Panel;
        
    } else if (panel == 1) {
        // 查看书籍
        if (ViewBooks_Panel == null) {
            ViewBooks_Panel = new ViewBook();
        }
        Min_Panel = ViewBooks_Panel;
        
    } else if (panel == 2) {
        // 借阅书籍
        if (BorrowBooks_Panel == null) {
            BorrowBooks_Panel = new BorrowBooks();
        }
        Min_Panel = BorrowBooks_Panel;
        
    } else if (panel == 3) {
        // 用户与设置
        if (UserSettings_Panel == null) {
            UserSettings_Panel = new UserSettings();
        }
        Min_Panel = UserSettings_Panel;
        
    } else {
        // 默认：显示主页
        if (Home_Panel == null) {
            Home_Panel = new Home();
        }
        Min_Panel = Home_Panel;
    }
    
    // 3. 设置位置大小并添加到面板
    Min_Panel.setBounds(CONTENT_X, CONTENT_Y, CONTENT_WIDTH, CONTENT_HEIGHT);
    add(Min_Panel);
    
    // 4. 刷新界面
    revalidate();
    repaint();
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // ========================================
        // 顶部色块：高度 100px（缩小了）
        // ========================================
        g2d.setColor(PRIMARY_DARK);
        g2d.fillRect(0, 0, getWidth(), HEADER_HEIGHT);
        
        // 顶部色块底部加一根细亮线装饰
        g2d.setColor(new Color(70, 95, 145));
        g2d.drawLine(0, HEADER_HEIGHT, getWidth(), HEADER_HEIGHT);
        
        // ========================================
        // 侧边栏：从顶部色块底部开始到窗口底部
        // ========================================
        g2d.setColor(PRIMARY_DARK);
        g2d.fillRect(0, HEADER_HEIGHT, SIDEBAR_WIDTH, getHeight() - HEADER_HEIGHT);
        
        // ========================================
        // 分隔线
        // ========================================
        g2d.setColor(DIVIDER);
        g2d.drawLine(SIDEBAR_WIDTH, HEADER_HEIGHT, SIDEBAR_WIDTH, getHeight());
        
        g2d.dispose();
    }

    //主页 动作处理
    public void btnHome_Action(){
        ShowPanel(0);
    }

    //查看书籍 动作处理
    public void btnViewBooks_Action(){
        ShowPanel(1);
    }
    
    //借阅书籍 动作处理
    public void btnBorrowBooks_Action(){
        ShowPanel(2);
    }

    //用户与设置 动作处理
    public void btnUserSettings_Action(){
       ShowPanel(3);
    }
}