package com.myproject.backend.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> fetchAll() {
        return productRepository.findAll();
    }

    public List<Product> fetchByGender(ProductType productType) {
        return productRepository.findAllByProductType(productType);
    }
    public List<Product> fetchByType(ProductType productType) {
        return productRepository.findAllByProductType(productType);
    }


    public List<Product> fetchByCategory(Category category) {
        return  productRepository.findAllByCategory(category);
    }


    public List<Product> fetchByGenderAndCategory(String sex, String categoryStr) {
        ProductType productType = (sex.equals("male") || sex.equals("MALE")) ? ProductType.MALE : ProductType.FEMALE;
        Category category = (categoryStr.equals("clothes") || categoryStr.equals("ACCESORIES")) ? Category.CLOTHES : Category.ACCESORIES;

        return productRepository.findAllByCategoryAndSex(productType, category);
    }
}
