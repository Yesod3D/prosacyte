package com.es.prosacyte.web.demo.aplication.service;

import com.es.prosacyte.web.demo.domain.model.PriceHistory;
import com.es.prosacyte.web.demo.infraestructure.persistence.PriceHistoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistoryService(PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    public List<PriceHistory> getPriceHistory(Long productId) {
        return priceHistoryRepository.findByProductIdOrderByChangeDateDesc(productId);
    }
}