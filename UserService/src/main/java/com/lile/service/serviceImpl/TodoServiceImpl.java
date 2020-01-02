package com.lile.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lile.common.mybits.model.Todo;
import com.lile.common.mybits.model.TodoExample;
import com.lile.common.mybits.persistence.TodoMapper;
import com.lile.enumns.TodoStatus;
import com.lile.service.TodoService;
import com.sun.xml.internal.bind.v2.TODO;
import dto.TodoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
        PageInfo<TodoDto> pageInfo = new PageInfo<>();
        TodoExample todoExample = new TodoExample();
        todoExample.or().andUserIdEqualTo(userId).andStatusEqualTo(status);
        List<Todo> list = todoMapper.selectByExample(todoExample);
        List<TodoDto> todoDtos = new ArrayList<>();
        todoDtos = list.stream().map(
            todo->{
                TodoDto to = new TodoDto();
                BeanUtils.copyProperties(todo,to);
                return to;
            }
        ).collect(Collectors.toList());
        log.info("用户："+userId+",代办事项列表："+JSONObject.toJSONString(pageInfo));
        return pageInfo;
    }
}
