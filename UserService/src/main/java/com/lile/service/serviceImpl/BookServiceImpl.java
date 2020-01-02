package com.lile.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lile.common.mybits.model.Book;
import com.lile.dao.BookDao;
import com.lile.enumns.BookPrecent;
import com.lile.enumns.BookStauts;
import com.lile.service.BookService;
import dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    @Override
    public Book addBook(BookDto bookDto) {
        Book book =new Book();
        BeanUtils.copyProperties(bookDto,book);
        book.setCreateTime(LocalDateTime.now());
        book.setPercent(BookPrecent.ZERO.getPrecent());
        book.setStatus(BookStauts.PREPARE.getCode());
        if(!Optional.of(bookDto.getBookCover()).isPresent()||bookDto.getBookCover().equals("")){
            book.setBookCover("");
            //todo  插入缺省书籍封面  可取数据库
        }
        bookDao.insertBook(book);
        log.info("bookService: 新增书籍："+ JSONObject.toJSONString(bookDto));
       return book;
    }

    @Override
    public PageInfo<Book> findBookByUser(int userId, int status) {

        return PageInfo.of(bookDao.findBookByUser(userId,status));
    }
}
