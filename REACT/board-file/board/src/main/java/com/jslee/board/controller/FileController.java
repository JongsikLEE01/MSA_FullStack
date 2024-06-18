package com.jslee.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jslee.board.dto.Files;
import com.jslee.board.service.FileService;

/**
 * 📄 파일
 * 1. 파일 업로드
 * 2. 파일 다운로드
 * 3. 이미지 썸네일
 * 4. 파일 삭제
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/files")
public class FileController {
    
    @Autowired
    private FileService fileService;

    /**
     * sp-post
     * 파일 업로드
     * @param file
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Files file) {
        try {
            Files uploadedFile = fileService.upload(file);

            return new ResponseEntity<>(uploadedFile, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
