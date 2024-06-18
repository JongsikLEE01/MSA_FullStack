package com.jslee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class BasicServiceImpl implements ???Service{

    @Autowired
    private ???Mapper ???Mapper;

    @Override
    public List<???> list() throws Exception {
       return ???Mapper.list();
    }

    @Override
    public ???s select(int no) throws Exception {
        return ???Mapper.select(no);
    }

    @Override
    public int update(??? ???) throws Exception {
        return ???Mapper.update(???);
    }

    @Override
    public int insert(??? ???) throws Exception {
        return ???Mapper.insert(???);
    }

    @Override
    public int delete(int no) throws Exception {
        return ???Mapper.delete(no);
    }
    
}
