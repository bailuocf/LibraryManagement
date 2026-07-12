package com.yourname.library.model;
import java.io.Serializable;
import java.time.LocalDate;
//实体类 Book.java（放在 model 文件夹）
// 必须实现 Serializable，才能用 ObjectOutputStream 直接写入文件
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;  // 版本号，防止反序列化报错

    private String id;          // 书号（唯一）
    private String title;       // 书名
    private String author;      // 作者
    private String publisher;   // 出版社
    private int totalPages;     // 总页数
    private boolean isBorrowed; // 是否借出

    // 构造方法（全参数）
    public Book(String id, String title, String author, String publisher, int totalPages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.totalPages = totalPages;
        this.isBorrowed = false; // 默认未借出
    }

    // ---------- 以下是 Getter 和 Setter（必须要有） ----------
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean borrowed) { isBorrowed = borrowed; }

    @Override
    public String toString() {
        return String.format("《%s》 作者:%s 书号:%s", title, author, id);
    }
}   