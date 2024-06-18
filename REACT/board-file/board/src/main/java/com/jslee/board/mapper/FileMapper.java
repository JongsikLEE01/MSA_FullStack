package com.jslee.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jslee.board.dto.Files;

@Mapper
public interface FileMapper {
    public List<Files> list();
    public Files select(int no);
    public int update(Files file);
    public int insert(Files file);
    public int delete(int no);

    // 파일 목록 - 부모 기준
    public List<Files> listByParent(Files file);
}
