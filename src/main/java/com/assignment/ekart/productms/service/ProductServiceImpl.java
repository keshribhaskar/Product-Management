package com.assignment.ekart.productms.service;

import com.assignment.ekart.productms.entity.AllProductEntity;
import com.assignment.ekart.productms.model.ProductDetails;
import com.assignment.ekart.productms.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepo productRepo;
    @Override
    public List<ProductDetails> getAllProducts() {
        List<AllProductEntity> ape = productRepo.findAll();
        List<ProductDetails> pdl = new ArrayList<>();
        for(AllProductEntity p : ape){
            ProductDetails pd = new ProductDetails();
            pd.setProductId(p.getProductId());
            pd.setName(p.getName());
            pd.setBrand(p.getBrand());
            pd.setDescription(p.getDescription());
            pd.setCategory(p.getCategory());
            pd.setPrice(p.getPrice());
            pd.setAvailableQuantity(p.getAvailableQuantity());
            pdl.add(pd);
        }
        return pdl;
    }

    @Override
    public ProductDetails getProductById(Integer productId) throws Exception {
        Optional<AllProductEntity> productData = productRepo.findById(productId);
        AllProductEntity product = productData.orElseThrow(() -> new Exception("Product not available"));
        ProductDetails pd = new ProductDetails();
        pd.setProductId(product.getProductId());
        pd.setName(product.getName());
        pd.setBrand(product.getBrand());
        pd.setDescription(product.getDescription());
        pd.setCategory(product.getCategory());
        pd.setPrice(product.getPrice());
        pd.setAvailableQuantity(product.getAvailableQuantity());
        return pd;
    }

    @Override
    public String addProduct(ProductDetails product) {
        try{
            AllProductEntity pe = AllProductEntity.builder()
                    .name(product.getName())
                    .availableQuantity(product.getAvailableQuantity())
                    .brand(product.getBrand())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .productId(product.getProductId())
                    .category(product.getCategory())
                    .build();
            productRepo.save(pe);
            return "Product added.";
        }catch (Exception e){
            return "Product addition failed. "+ e.getMessage();
        }
    }
}
