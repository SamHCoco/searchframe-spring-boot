package com.samhcoco.project.searchframe.listener;

import com.samhcoco.project.searchframe.repository.DocumentProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.samhcoco.project.searchframe.model.VehicleProductDocument;

import java.util.List;

import static com.samhcoco.project.searchframe.util.ImportJsonUtil.importJson;

@Service
@Slf4j
@RequiredArgsConstructor
public class Listeners {

    private final DocumentProductRepository documentProductRepository;

    /** Initialises MongoDB database with test data */
    @EventListener(ApplicationReadyEvent.class)
    public void initialiseDatabaseData() {
        try {
            documentProductRepository.deleteAll();
            List<VehicleProductDocument> products = importJson(new ClassPathResource("data/products.json"), VehicleProductDocument[].class);
            documentProductRepository.insert(products);
            log.info("Product data successfully initialised for MongoDB: {} documents inserted.", products.size());
        } catch (Exception e) {
            log.error("Failed to initialise MongoDB data - reason: {}", e.getMessage());
        }
    }


}
