package com.samhcoco.project.searchframe.controller;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.model.VehicleProductDocument;
import com.samhcoco.project.searchframe.service.impl.MongoDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class MongoDbController {

    private static final String VERSION = "api/v1";
    private static final String RESOURCE = "mongodb/vehicle-product";

    private final MongoDbService mongoDbService;

    @PostMapping(VERSION + "/" + RESOURCE + "/query")
    public ResponseEntity<Object> query(@RequestBody Query query) {
        try {
            return new ResponseEntity<>(mongoDbService.query(query, VehicleProductDocument.class), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

}
