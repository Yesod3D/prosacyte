package com.es.prosacyte.web.demo.infraestructure.persistence;

import com.es.prosacyte.web.demo.domain.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    List<PriceHistory> findByProductIdOrderByChangeDateDesc(Long productId);

    List<PriceHistory> findByProductId(Long id);
}