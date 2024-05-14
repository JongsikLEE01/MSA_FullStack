package com.jslee.qrcode.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
// import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QrController {

    @GetMapping("/qr/home")
    public ResponseEntity<byte[]> qrToGitHub() throws Exception{
        // QR 정보
        int width = 200;
        int height = 200;
        // 고정 주소 사용
        String url = "https://github.com/JongsikLEE01";

        // QR Code 정보 생성
        // MultiFormatWriter().encode(url, 포맷, 세로, 가로);
        BitMatrix encode = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);

        // 1회성 img 생성하므로 stream으로 Generate
        // 1회성이 아닐 경우 File로 작성 가능
        try {
            // output stream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Bitmatrix, file, format, outputStream
            MatrixToImageWriter.writeToStream(encode, "PNG", baos);
            // QR 이미지 사이즈, 색상 커스텀 가능
            // MatrixToImageConfig custom = new MatrixToImageConfig(MatrixToImageConfig.BLACK, -1);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(baos.toByteArray());
        } catch (Exception e) {
            log.warn("QR Code 생성중 오류 발생 ");
            log.warn("오류 내용 : "+e.getMessage());
        }
        return null;
    }
}