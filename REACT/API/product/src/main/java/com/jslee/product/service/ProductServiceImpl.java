package com.jslee.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslee.product.dto.Product;
import com.jslee.product.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list() throws Exception {
        return productMapper.list();
    }

    @Override
    public Product select(int no) throws Exception {
        return productMapper.select(no);
    }

    @Override
    public int update(Product product) throws Exception {
        return productMapper.update(product);
    }

    @Override
    public int insert(Product product) throws Exception {
        return productMapper.insert(product);
    }

    @Override
    public int delete(int no) throws Exception {
        return productMapper.delete(no);
    }
    
}
