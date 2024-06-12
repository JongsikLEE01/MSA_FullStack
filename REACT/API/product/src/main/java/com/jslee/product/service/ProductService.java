package com.jslee.product.service;

import java.util.List;

import com.jslee.product.dto.Product;

public interface ProductService {
    public List<Product> list() throws Exception;
    public Product select(int no)throws Exception;
    public int update(Product product)throws Exception;
    public int insert(Product product)throws Exception;
    public int delete(int no)throws Exception;
}
