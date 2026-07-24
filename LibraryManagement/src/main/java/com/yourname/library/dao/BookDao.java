package com.yourname.library.dao;

import com.yourname.library.model.Book;
import java.util.List;
public interface BookDao {
    // 添加一本书
    boolean addBook(Book book);

    // 根据书号删除
    boolean deleteBook(String id);

    // 更新书本信息
    boolean updateBook(Book book);

    // 查询所有书
    List<Book> findAll();

    // 根据书号查找单本书
    Book findById(String id);
}