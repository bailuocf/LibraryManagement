package com.yourname.library.dao.impl;

import com.yourname.library.dao.BookDao;
import com.yourname.library.model.Book;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileBookDao implements BookDao {

    // 数据文件存放在项目根目录的 data/books.dat
    private static final String DATA_DIR = "./data";
    private static final String DATA_FILE = DATA_DIR + "/books.dat";

    public FileBookDao() {
        // 构造方法里自动创建 data 文件夹（如果不存在）
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------- 核心：从文件读取列表 ----------
    private List<Book> loadBooks() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>(); // 第一次运行，返回空列表
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // ---------- 核心：把列表写入文件 ----------
    private void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------- 实现接口方法 ----------
    @Override
    public boolean addBook(Book book) {
        List<Book> books = loadBooks();
        // 简单的防重复检查（如果书号已存在则拒绝添加）
        if (findById(book.getId()) != null) {
            return false;
        }
        books.add(book);
        saveBooks(books);
        return true;
    }

    @Override
    public boolean deleteBook(String id) {
        List<Book> books = loadBooks();
        boolean removed = books.removeIf(b -> b.getId().equals(id));
        if (removed) {
            saveBooks(books);
        }
        return removed;
    }

    @Override
    public boolean updateBook(Book book) {
        List<Book> books = loadBooks();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(book.getId())) {
                books.set(i, book);
                saveBooks(books);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> findAll() {
        return loadBooks(); // 直接读取文件返回
    }

    @Override
    public Book findById(String id) {
        List<Book> books = loadBooks();
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }
}