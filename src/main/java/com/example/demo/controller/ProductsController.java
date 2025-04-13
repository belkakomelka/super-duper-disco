package com.example.demo.controller;

import com.example.demo.dto.product.ProductAddRq;
import com.example.demo.service.products.AddProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductsController {

    @Autowired
    AddProductService addProductService;

    @PostMapping(path = "/add-product", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> addProduct(@RequestPart("file") MultipartFile image,
                                             @RequestPart("data") ProductAddRq productAddRq) {
        return addProductService.addProduct(productAddRq, image);
    }

}