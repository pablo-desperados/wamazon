package com.wamazon.app.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProductModel, Long> {
}
