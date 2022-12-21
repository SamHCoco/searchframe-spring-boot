package com.samhcoco.project.searchframe.controller;

import com.samhcoco.project.searchframe.model.Queries;
import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.impl.VehicleProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.MediaSize;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class VehicleProductController {

    private static final String NAME = "vehicle-product";
    private static final String VERSION = "api/v1";

    private final VehicleProductService vehicleProductService;

    @PostMapping(VERSION + "/" + NAME + "/query")
    public ResponseEntity<Object> query(@RequestBody Query query) {
        try {
            return new ResponseEntity<>(vehicleProductService.query(query), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(VERSION + "/" + NAME + "/queries")
    public ResponseEntity<Object> queries(@RequestBody Queries queries) {
        try {
            return new ResponseEntity<>(vehicleProductService.query(queries), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

}
