package com.lile.service;

import com.github.pagehelper.PageInfo;
import com.lile.common.mybits.model.Book;
import dto.BookDto;

public interface BookService {
    public Book addBook(BookDto bookDto);

    public PageInfo<Book> findBookByUser(int userId,int status);
}
