package org.example.dbcore.integration;

import lombok.RequiredArgsConstructor;
import org.example.dbcore.stock.DailyStockPriceJpaRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class DailyStockPriceIntegrationTest {

    private final DailyStockPriceJpaRepository jpaRepository;
}
