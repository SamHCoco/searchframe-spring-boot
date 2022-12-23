package com.samhcoco.project.searchframe.repository;

import com.samhcoco.project.searchframe.model.VehicleProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentProductRepository extends MongoRepository<VehicleProductDocument, Integer> {
}
