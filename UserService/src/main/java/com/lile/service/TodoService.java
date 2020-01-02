package com.lile.service;

import com.github.pagehelper.PageInfo;
import com.lile.common.mybits.model.Todo;
import dto.TodoDto;
import org.springframework.stereotype.Service;


public interface TodoService {
    public  Todo createTodo(TodoDto todoDto);

    public PageInfo<TodoDto> findTodo(int userId,int status);
}
