package com.es.prosacyte.web.demo.infraestructure.persistence;

import com.es.prosacyte.web.demo.domain.model.PriceHistory;
import com.es.prosacyte.web.demo.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PriceHistoryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    @Test
    void shouldFindByProductIdOrderByChangeDateDesc() {
        // 1. Crear y persistir Producto
        Product product = new Product("Smartphone", "Flagship device", BigDecimal.valueOf(999.99));
        product = testEntityManager.persistAndFlush(product);

        // 2. Crear 2 historicos de precios con diferentes fechas
        PriceHistory history1 = testEntityManager.persistAndFlush(
                new PriceHistory(product, BigDecimal.valueOf(899.99), BigDecimal.valueOf(999.99))
        );

        // Forzar diferencia de tiempo
        testEntityManager.flush();

        PriceHistory history2 = testEntityManager.persistAndFlush(
                new PriceHistory(product, BigDecimal.valueOf(999.99), BigDecimal.valueOf(1099.99))
        );

        // 3. Ejecutar la consulta
        List<PriceHistory> result = priceHistoryRepository.findByProductIdOrderByChangeDateDesc(product.getId());

        // 4. Verificar resultados
        assertThat(result)
                .hasSize(2)
                .extracting(PriceHistory::getNewPrice)
                .containsExactly(
                        BigDecimal.valueOf(1099.99),
                        BigDecimal.valueOf(999.99)
                );

        // Verificar orden descendente por fecha
        assertThat(result.get(0).getChangeDate())
                .isAfter(result.get(1).getChangeDate());
    }

    @Test
    void shouldReturnEmptyListWhenNoHistoryFound() {
        Product product = testEntityManager.persistAndFlush(
                new Product("Tablet", "Android tablet", BigDecimal.valueOf(299.99))
        );

        List<PriceHistory> result = priceHistoryRepository.findByProductIdOrderByChangeDateDesc(product.getId());

        assertThat(result).isEmpty();
    }
}