package br.com.capitalgains.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import br.com.capitalgains.domain.OperationInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServiceTest {

    @Test
    void shouldCalculateActualProfit() {
        final var expected = BigDecimal.valueOf(50000);

        final var operationInfo = new OperationInfo("sell", BigDecimal.valueOf(10), 10000);

        final var actualWeightedAveragePrice = BigDecimal.valueOf(5);

        final var actual = CalculatorService.calculateActualProfit(operationInfo, actualWeightedAveragePrice);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateActualWeightedAveragePrice() {
        final var expected = BigDecimal.valueOf(15).setScale(2, RoundingMode.HALF_UP);

        final var operationInfo = new OperationInfo("buy", BigDecimal.valueOf(10), 10000);

        final var actualWeightedAveragePrice = BigDecimal.valueOf(25);

        final var actualStockBalance = 5000;

        final var actual = CalculatorService.calculateActualWeightedAveragePrice(operationInfo, actualWeightedAveragePrice, actualStockBalance);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFormatDecimalCases() {
        final var expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP);;

        final var value = BigDecimal.valueOf(10);

        final var actual = CalculatorService.formatDecimalCases(value);

        assertEquals(expected, actual);
    }
}
