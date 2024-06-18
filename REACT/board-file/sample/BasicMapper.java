package com.jslee.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasicMapper {
    public List<???> list();
    public ??? select(int no);
    public int update(??? ???);
    public int insert(??? ???);
    public int delete(int no);
}
