package com.es.prosacyte.web.demo.intefaces.rest;

import com.es.prosacyte.web.demo.aplication.service.PriceHistoryService;
import com.es.prosacyte.web.demo.domain.model.PriceHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class PriceHistoryController {

    private final PriceHistoryService priceHistoryService;

    public PriceHistoryController(PriceHistoryService priceHistoryService) {
        this.priceHistoryService = priceHistoryService;
    }

    @GetMapping("/{id}/price-history")
    public ResponseEntity<List<PriceHistory>> getPriceHistory(@PathVariable Long id) {
        return ResponseEntity.ok(priceHistoryService.getPriceHistory(id));
    }
}