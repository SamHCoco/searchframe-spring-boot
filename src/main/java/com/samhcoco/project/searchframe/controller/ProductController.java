package com.samhcoco.project.searchframe.controller;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private static final String VERSION = "api/v1";

    private final ProductService productService;

    @PostMapping(VERSION + "/product/query")
    public ResponseEntity<Object> query(@RequestBody Query query) {
        try {
            return new ResponseEntity<>(productService.query(query), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

}
