package com.jslee.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jslee.board.dto.Files;
import com.jslee.board.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileMapper fileMapper;

    // 파일 경로
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public List<Files> list() throws Exception {
       return fileMapper.list();
    }

    @Override
    public Files select(int no) throws Exception {
        return fileMapper.select(no);
    }

    @Override
    public int update(Files file) throws Exception {
        return fileMapper.update(file);
    }

    @Override
    public int insert(Files file) throws Exception {
        return fileMapper.insert(file);
    }

    @Override
    public int delete(int no) throws Exception {
        return fileMapper.delete(no);
    }

    @Override
    public Files upload(Files file) throws Exception {
        Files uploadedFile = uploadFile(file, file.getFile());
        if (uploadedFile != null)   log.info("파일 업로드 성공");

        return uploadedFile;
    }

    // 파일 업로드 로직
    public Files uploadFile(Files fileInfo, MultipartFile file) throws Exception{
        int result = 0;
        if (file.isEmpty()) return null;

        // 📄 파일 원본명, 사이즈, 데이터
        String originName = file.getOriginalFilename();
        long fileSize = file.getSize();
        byte[] fileData = file.getBytes();

        // 파일명 중복 방지
        String fileName = UUID.randomUUID().toString() + "_" + originName;
        String filePath = uploadPath + "/" + fileName;

        // 파일 시스템에 복사 (업로드)
        File uploadFile = new File(uploadPath, fileName);
        FileCopyUtils.copy(fileData,uploadFile);

        // DB에 파일 정보 등록
        Files uploadedFile = new Files();
        uploadedFile.setParentTable(fileInfo.getParentTable());
        uploadedFile.setParentNo(fileInfo.getParentNo());
        uploadedFile.setFileName(fileName);
        uploadedFile.setFilePath(filePath);
        uploadedFile.setFileSize(fileSize);
        uploadedFile.setFileCode(fileInfo.getFileCode());;

        result = fileMapper.insert(uploadedFile);
        log.info("result? {}",result);
        return uploadedFile;
    }

    @Override
    public List<Files> uploadFiles(Files fileInfo, List<MultipartFile> fileList) throws Exception {
        List<Files> uploadedFileList = new ArrayList<Files>();

        for (MultipartFile file : fileList) {
            Files uploadedFile = uploadFile(fileInfo, file);
            uploadedFileList.add(uploadedFile);
            log.info("업로드된 파일? {}", uploadedFile);
        }

        return uploadedFileList;
    }
    
}
