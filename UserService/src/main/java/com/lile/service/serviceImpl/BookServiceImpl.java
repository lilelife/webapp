package com.lile.service.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.lile.common.mybits.model.Book;
import com.lile.dao.BookDao;
import com.lile.service.BookService;
import dto.BookDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    @Override
    public Book addBook(BookDto bookDto) {
        Book book =new Book();
        BeanUtils.copyProperties(bookDto,book);
        book.setCreateTime(LocalDateTime.now());


        //test LocalDateTime
        LocalDateTime rightNow = LocalDateTime.now();// 获取当前事件
        System.out.println("当前年"+rightNow.getYear());
        rightNow.minusDays(1);// 修改日期
       // 自定义日期时间
        LocalDateTime localDateTime = LocalDateTime.of(2020,1,1,18,23,2);



        return null;
    }

    @Override
    public PageInfo<Book> findBookByUser(int userId, int status) {
        return null;
    }
}
