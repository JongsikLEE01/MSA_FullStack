package com.jslee.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslee.todo.dto.Todo;
import com.jslee.todo.mapper.TodoMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoServiceimpl implements TodoService{

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public List<Todo> list() throws Exception {
        return todoMapper.list();
    }

    @Override
    public Todo select(int no) throws Exception {
        return todoMapper.select(no);
    }

    @Override
    public int update(Todo todo) throws Exception {
        return todoMapper.update(todo);
    }

    @Override
    public Todo insert(Todo todo) throws Exception {
        int result = todoMapper.insert(todo);
        log.info("todo {}", todo);
        log.info("result {}", result);
        int newTodoNo = todo.getNo();
        Todo newTodo = todoMapper.select(newTodoNo);
        return newTodo;
    }

    @Override
    public int delete(int no) throws Exception {
        return todoMapper.delete(no);
    }

    @Override
    public int updateAll() throws Exception {
        return todoMapper.updateAll();
    }

    @Override
    public int deleteAll() throws Exception {
        return todoMapper.deleteAll();
    }
    
}
