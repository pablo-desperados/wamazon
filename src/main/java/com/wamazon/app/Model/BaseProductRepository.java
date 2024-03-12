package com.wamazon.app.Model;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface BaseProductRepository extends JpaRepository<BaseProductModel, Long> {
}
