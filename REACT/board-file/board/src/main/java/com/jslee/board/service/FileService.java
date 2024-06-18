package com.jslee.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jslee.board.dto.Files;

public interface FileService {
    public List<Files> list() throws Exception;
    public Files select(int no) throws Exception;
    public int update(Files file) throws Exception;
    public int insert(Files file) throws Exception;
    public int delete(int no) throws Exception;

    // 파일 업로드
    public Files upload(Files file) throws Exception;
    // 여러 파일 업로드
    public List<Files> uploadFiles(Files file, List<MultipartFile> fileList) throws Exception;
}
