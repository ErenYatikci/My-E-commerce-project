package com.myproject.backend.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/products/")
public class ProductController {
    private final ProductService productService;
    @GetMapping("all")
    List<Product> fetchAllProducts(){
        return productService.fetchAll();
    }

    @GetMapping("gender")
    List<Product> fetchByGender(@RequestParam("sex") String sex){
        ProductType productType = (sex.equals("male") || sex.equals("MALE")) ? ProductType.MALE : ProductType.FEMALE;
        return  productService.fetchByGender(productType);
    }

    @GetMapping("category")
    List<Product> fetchByCategory(@RequestParam("category") String categoryStr){
        Category category = (categoryStr.equals("clothes") || categoryStr.equals("ACCESORIES")) ? Category.CLOTHES : Category.ACCESORIES;
        return productService.fetchByCategory(category);
    }


    @GetMapping("specific")
    List<Product> fetchByGenderAndCategory(@RequestParam("sex") String sex, @RequestParam("category") String categoryStr){
        return productService.fetchByGenderAndCategory(sex, categoryStr);
    }
}
