package com.lile.dao;

import com.lile.common.mybits.model.Book;

import java.util.List;

public interface BookDao {

//    用户 、书籍状态查找书籍
    List<Book> findBookByUser(int userId,int status);

   boolean insertBook(Book book);

}
