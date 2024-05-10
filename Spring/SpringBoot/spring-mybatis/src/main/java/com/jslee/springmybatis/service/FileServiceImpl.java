package com.jslee.springmybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jslee.springmybatis.dto.Files;
import com.jslee.springmybatis.mapper.FileMapper;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<Files> list() throws Exception {
        // TODO : 파일 목록 조회
        List<Files> fileList = fileMapper.list();
        return fileList;
    }

    @Override
    public Files select(int no) throws Exception {
        // TODO : 파일 조회
        Files file = fileMapper.select(no);
        return file;
    }

    @Override
    public int insert(Files file) throws Exception {
        // TODO : 파일 등록
        int result = fileMapper.insert(file);
        return result;
    }

    @Override
    public int update(Files file) throws Exception {
        // TODO : 파일 수정
        int result = fileMapper.update(file);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        // TODO : 파일 삭제
        int result = fileMapper.delete(no);
        return result;
    }

    @Override
    public List<Files> listByParent(Files file) throws Exception {
        // TODO : 부모 테이블에 종속된 파일 목록 조회
        List<Files> fileList = fileMapper.listByParent(file);
        return fileList;
    }

    @Override
    public int deleteByParent(Files file) throws Exception {
        // TODO : 부모 테이블에 종속된 파일 목록 삭제
        int result = fileMapper.deleteByParent(file);

        return result;
    }

    // @Override
    // public int upload(String parentTable, int parentNo, List<MultipartFile> fileList) throws Exception {
    //     // TODO : 파일 업로드
    //     // int result =  fileMapper.upload(parentTable, parentNo, fileList);
    //     // return result;
    //     throw new UnsupportedOperationException("파일 업로드 구현하세요.");
    // }

    @Override
    public int download(int no, HttpServletResponse response) throws Exception {
        // TODO : 파일 다운로드
        // int result = fileMapper.d
        throw new UnsupportedOperationException("파일 다운로드 구현하세요.");
    }    
}