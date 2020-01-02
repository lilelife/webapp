package com.lile.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lile.common.mybits.model.Book;
import com.lile.dao.BookDao;
import com.lile.service.BookService;
import dto.BookDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.DynamicResponse;

import javax.annotation.Resource;

@Api(value = "/books")
@RestController
@RequestMapping("books")
@Slf4j
public class BookController {
    @Resource
    private BookService bookService;

    @ApiOperation(value="新增书籍")
    @PutMapping
    @ResponseBody
    public DynamicResponse<Book> createBook(@RequestBody BookDto bookDto){

        return DynamicResponse.of(()->
                bookService.addBook(bookDto)
        );
    }

    @ApiOperation(value = "查书单")
    @GetMapping(path = "/{userId}/{status}")
    @ResponseBody
    public DynamicResponse<PageInfo<Book>> findBooksByuser(int userId,int status){

        PageInfo<Book> result = bookService.findBookByUser(userId,status);
        log.info("用户："+userId +"书单："+ JSONObject.toJSONString(result));
        return DynamicResponse.of(()->result);

    }

}
