package com.lile.controller;

import com.github.pagehelper.PageInfo;
import com.lile.common.mybits.model.Todo;
import com.lile.service.TodoService;
import dto.TodoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import utils.DynamicResponse;

import javax.annotation.Resource;

@Api("代办事项")
@RestController
@RequestMapping(value = "/todos")
@Slf4j
public class TodoController {
    @Resource
    private TodoService todoService;

    @ApiOperation(value = "插入新代办事项")
    @PutMapping()
    @ResponseBody
    public DynamicResponse<Todo> addTodo(@RequestBody TodoDto todoDto){

        return DynamicResponse.of(()->todoService.createTodo(todoDto));
    }

    @ApiOperation(value = "获取代办事列表")
    @GetMapping(path = "/{userId}/{status}")
    @ResponseBody
    public DynamicResponse<PageInfo<TodoDto>> findTodos(@PathVariable int userId,@PathVariable int status){

        return DynamicResponse.of(()->todoService.findTodo(userId,status));
    }

    @ApiOperation("做完某项")
    @GetMapping(path = "/{todoId}")
    @ResponseBody
    public DynamicResponse<Integer> doneTodo(@PathVariable int todoId){

        return DynamicResponse.of(()->todoService.doneTodo(todoId));
    }
}
