package com.samhcoco.project.searchframe.repository;

import com.samhcoco.project.searchframe.model.VehicleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<VehicleProduct, Long>,
                                           JpaSpecificationExecutor<VehicleProduct> {
}
