package br.com.capitalgains.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import br.com.capitalgains.domain.OperationContext;
import br.com.capitalgains.domain.OperationInfo;
import br.com.capitalgains.domain.Tax;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SellOperationHandlerTest {

    final SellOperationHandler sellOperationHandler = new SellOperationHandler();

    @Test
    void shouldHandleSellOperation() {
        final var expected = new OperationContext(
                false,
                BigDecimal.valueOf(20),
                new OperationInfo("sell", BigDecimal.valueOf(30), 5000),
                1000,
                BigDecimal.valueOf(35000),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(7000)))
        );

        final var operationContext = new OperationContext(
                false,
                BigDecimal.valueOf(20),
                new OperationInfo("sell", BigDecimal.valueOf(30), 5000),
                6000,
                BigDecimal.valueOf(-15000),
                null
        );

        final var actual = sellOperationHandler.handle(operationContext);

        assertEquals(expected.toString(), actual.toString());
    }
}
