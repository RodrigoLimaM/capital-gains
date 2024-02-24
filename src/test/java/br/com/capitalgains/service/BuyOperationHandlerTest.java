package br.com.capitalgains.service;

import java.math.BigDecimal;
import br.com.capitalgains.domain.OperationContext;
import br.com.capitalgains.domain.OperationInfo;
import br.com.capitalgains.domain.Tax;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuyOperationHandlerTest {

    final BuyOperationHandler buyOperationHandler = new BuyOperationHandler();

    @Test
    void shouldHandleBuyOperation() {
        final var expected = new OperationContext(
                false,
                BigDecimal.valueOf(30),
                new OperationInfo("buy", BigDecimal.valueOf(30), 5000L),
                10000,
                BigDecimal.valueOf(35000),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO))
        );

        final var operationContext = new OperationContext(
                true,
                BigDecimal.valueOf(20),
                new OperationInfo("buy", BigDecimal.valueOf(30), 5000L),
                5000,
                BigDecimal.valueOf(35000),
                null
        );

        final var actual = buyOperationHandler.handle(operationContext);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void shouldHandleBuyOperation_whenIsNotFirstIteration() {
        final var expected = new OperationContext(
                false,
                TestUtils.formatExpectedValues(BigDecimal.valueOf(25)),
                new OperationInfo("buy", BigDecimal.valueOf(30), 5000L),
                10000,
                BigDecimal.valueOf(35000),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO))
        );

        final var operationContext = new OperationContext(
                false,
                BigDecimal.valueOf(20),
                new OperationInfo("buy", BigDecimal.valueOf(30), 5000L),
                5000,
                BigDecimal.valueOf(35000),
                null
        );

        final var actual = buyOperationHandler.handle(operationContext);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void shouldHandleBuyOperation_whenHasAllStocksSold() {
        final var expected = new OperationContext(
                false,
                TestUtils.formatExpectedValues(BigDecimal.valueOf(30)),
                new OperationInfo("buy", BigDecimal.valueOf(30), 5000L),
                5000,
                BigDecimal.ZERO,
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO))
        );

        final var operationContext = new OperationContext(
                false,
                BigDecimal.valueOf(20),
                new OperationInfo("buy", BigDecimal.valueOf(30), 5000L),
                0,
                BigDecimal.valueOf(35000),
                null
        );

        final var actual = buyOperationHandler.handle(operationContext);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void shouldReturnTrue_whenAllStocksSold() {
        final var actual = BuyOperationHandler.hasAllStocksSold(0);

        assertTrue(actual);
    }

    @Test
    void shouldReturnFalse_whenNotAllStocksSold() {
        final var actual = BuyOperationHandler.hasAllStocksSold(1);

        assertFalse(actual);
    }
}
