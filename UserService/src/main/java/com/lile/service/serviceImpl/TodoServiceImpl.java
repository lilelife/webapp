package com.lile.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lile.common.mybits.model.Todo;
import com.lile.common.mybits.model.TodoExample;
import com.lile.common.mybits.persistence.TodoMapper;
import com.lile.enumns.TodoStatus;
import com.lile.service.TodoService;

import dto.TodoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoServiceImpl  implements TodoService {
    @Resource
    private TodoMapper todoMapper;


    @Override
    public Todo createTodo(TodoDto todoDto) {
      Todo todo = new Todo();
      BeanUtils.copyProperties(todoDto,todo);
      todo.setStatus(TodoStatus.WAIT.getCode());
      todo.setCreateTime(LocalDateTime.now());

      todoMapper.insertSelective(todo);
      log.info( "新增代办事项"+ JSONObject.toJSONString(todo));
      return todo;
    }

    @Override
    public PageInfo<TodoDto> findTodo(int userId, int status) {

        TodoExample todoExample = new TodoExample();
        todoExample.or().andUserIdEqualTo(userId).andStatusEqualTo(status);
        List<Todo> list = todoMapper.selectByExample(todoExample);
        List<TodoDto> todoDtos = new ArrayList<>();
        log.info("todos:"+JSONObject.toJSONString(list));
        todoDtos = list.stream().map(
            todo->{
                TodoDto to = new TodoDto();
                BeanUtils.copyProperties(todo,to);
                return to;
            }
        ).collect(Collectors.toList());
        PageInfo<TodoDto> pageInfo = new PageInfo<>(todoDtos);
        log.info("用户："+userId+",代办事项列表："+JSONObject.toJSONString(pageInfo));
        return pageInfo;
    }


    @Override
    public Integer doneTodo(int todoId) {
        Todo todo = new Todo();
        todo.setId(todoId);
        todo.setStatus(TodoStatus.DONE.getCode()); // 代做事项 完成
        int result = todoMapper.updateByPrimaryKeySelective(todo);
        log.info("更新 totdId"+todoId+", 完成");
        return result;
    }
}
