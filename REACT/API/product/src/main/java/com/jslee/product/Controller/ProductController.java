package com.jslee.product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jslee.product.dto.Product;
import com.jslee.product.service.ProductService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 전체 조회
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> list() {
        try{
            List<Product> productList = productService.list();
            return new ResponseEntity<>(productList ,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 단일 조회
     * @param no
     * @return
     */
    @GetMapping("/{no}")
    public ResponseEntity<?> select(@PathVariable("no") int no) {
        try{
            Product product = productService.select(no);
            return new ResponseEntity<>(product ,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 수정
     * @param no
     * @param product
     * @return
     */
    @PutMapping("/{no}")
    public ResponseEntity<?> update(@PathVariable("no") int no, @RequestBody Product product) {
        try {
            product.setNo(no);
            log.info("pro? {}", product);
            int result = productService.update(product);
            if (result > 0) {
                return new ResponseEntity<>(product ,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 삽입
     * @param product
     * @return
     */
    @PostMapping()
    public ResponseEntity<?> insert(@RequestBody Product product) {
        try {
            int result = productService.insert(product);
            if (result > 0) {
                return new ResponseEntity<>(product ,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 삭제
     * @param no
     * @return
     */
    @DeleteMapping("/{no}")
    public ResponseEntity<?> delete(@PathVariable("no") int no) {
        try {
            int result = productService.delete(no);
            if (result > 0) {
                return new ResponseEntity<>(no ,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
