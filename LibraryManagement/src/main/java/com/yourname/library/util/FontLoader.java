package com.yourname.library.util;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * 字体加载工具类
 * 用于加载项目 resources/fonts/ 目录下的自定义字体
 */
public class FontLoader {
    
    // ========== 字体路径常量 ==========
    public static final String FONT_REGULAR = "/fonts/SourceHanSansSC-Regular.otf";
    public static final String FONT_BOLD = "/fonts/SourceHanSansSC-Bold.otf";
    public static final String FONT_LIGHT = "/fonts/SourceHanSansSC-Light.otf";
    public static final String FONT_MEDIUM = "/fonts/SourceHanSansSC-Medium.otf";
    public static final String FONT_EXTRA_LIGHT = "/fonts/SourceHanSansSC-ExtraLight.otf";
    public static final String FONT_HEAVY = "/fonts/SourceHanSansSC-Heavy.otf";
    public static final String FONT_NORMAL = "/fonts/SourceHanSansSC-Normal.otf";
    
    // ========== 缓存字体对象 ==========
    private static Font regularFont;
    private static Font boldFont;
    private static Font lightFont;
    
    /**
     * 加载字体（不指定大小）
     */
    private static Font loadFont(String path) {
        try (InputStream is = FontLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                System.err.println("字体文件未找到: " + path);
                return new Font("Dialog", Font.PLAIN, 12);
            }
            return Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            System.err.println("字体加载失败: " + path);
            return new Font("Dialog", Font.PLAIN, 12);
        }
    }
    
    /**
     * 加载字体并指定大小
     */
    private static Font loadFont(String path, float size) {
        Font font = loadFont(path);
        return font.deriveFont(size);
    }
    
    // ========== 公开方法 ==========
    
    /**
     * 获取常规体 (Regular)
     */
    public static Font getRegular(float size) {
        if (regularFont == null) {
            regularFont = loadFont(FONT_REGULAR);
        }
        return regularFont.deriveFont(size);
    }
    
    /**
     * 获取粗体 (Bold)
     */
    public static Font getBold(float size) {
        if (boldFont == null) {
            boldFont = loadFont(FONT_BOLD);
        }
        return boldFont.deriveFont(size);
    }
    
    /**
     * 获取细体 (Light)
     */
    public static Font getLight(float size) {
        if (lightFont == null) {
            lightFont = loadFont(FONT_LIGHT);
        }
        return lightFont.deriveFont(size);
    }
    
    /**
     * 获取任意字重
     * @param path 字体路径常量
     * @param size 字体大小
     */
    public static Font getFont(String path, float size) {
        return loadFont(path, size);
    }
    
    /**
     * 直接设置组件的字体
     * @param component 目标组件
     * @param path 字体路径
     * @param size 字体大小
     */
    public static void setFont(JComponent component, String path, float size) {
        component.setFont(loadFont(path, size));
    }
}